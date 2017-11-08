package com.balobanov.batch;

import com.balobanov.batch.mappers.BankRowMapper;
import com.balobanov.models.Bank;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.ftp.config.FtpInboundChannelAdapterParser;

import javax.sql.DataSource;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableBatchProcessing
public class BankJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Value("${local.folder}")
    private String localFolder;

    @Bean
    public JdbcPagingItemReader<Bank> pagingItemReader() {
        JdbcPagingItemReader<Bank> reader = new JdbcPagingItemReader<>();

        reader.setDataSource(this.dataSource);
        reader.setFetchSize(10);
        reader.setRowMapper(new BankRowMapper());

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("id, name");
        queryProvider.setFromClause("from bank");

        Map<String, Order> sortKeys = new HashMap<>(1);

        sortKeys.put("id", Order.ASCENDING);

        queryProvider.setSortKeys(sortKeys);

        reader.setQueryProvider(queryProvider);

        return reader;
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Bank> reader(@Value("#{jobParameters[pathToFile]}") String pathToFile){

        FlatFileItemReader<Bank> itemReader = new FlatFileItemReader<>();

        DefaultLineMapper<Bank> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[] { "id", "name"});
        tokenizer.setDelimiter(",");

        FieldSetMapper<Bank> fieldSetMapper = fieldSet -> {
            Bank bank = new Bank();
            bank.setId(fieldSet.readLong("id"));
            bank.setName(fieldSet.readString("name"));
            return bank;
        };

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        itemReader.setLineMapper(lineMapper);
        itemReader.setResource(new FileSystemResource(pathToFile));
        return itemReader;
    }


    @Bean
    public ItemWriter<Bank> customerItemWriter() {
        FlatFileItemWriter<Bank> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource(localFolder + LocalDateTime.now() +  ".csv"));
        writer.setShouldDeleteIfExists(true);

        DelimitedLineAggregator<Bank> delimitedLineAggregator = new DelimitedLineAggregator<>();

        BeanWrapperFieldExtractor<Bank> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<>();
        beanWrapperFieldExtractor.setNames(new String[]{"id", "name"});

        delimitedLineAggregator.setDelimiter(",");
        delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);

        writer.setLineAggregator(delimitedLineAggregator);
        return writer;
    }

    @Bean
    public Step readBanksFromDb() {
        return stepBuilderFactory.get("readAndWriteCsvFileStep")
                .<Bank, Bank>chunk(10)
                .reader(reader(null))
                .processor(bank -> {
                    System.out.println("processor");
                    return bank;
                })
                .writer(customerItemWriter())
                .build();
    }

    @Bean
    public Job banksFtpCsvJob() {
        return jobBuilderFactory.get("banksFtpCsvJob")
                .start(readBanksFromDb())
                .build();
    }
}
