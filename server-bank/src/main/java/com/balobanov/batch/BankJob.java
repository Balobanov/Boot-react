package com.balobanov.batch;

import com.balobanov.batch.mappers.BankRowMapper;
import com.balobanov.models.Bank;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class BankJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

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
    public ItemWriter<Bank> customerItemWriter() {
        FlatFileItemWriter<Bank> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("/home/user/Desktop/filesuploaded/" + LocalDateTime.now() + ".csv"));
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
        return stepBuilderFactory.get("readBanksFromDb")
                .<Bank, Bank>chunk(10)
                .reader(pagingItemReader())
                .processor(bank -> {
                    System.out.println("processor");
                    return bank;
                })
                .writer(customerItemWriter())
                .build();
    }

    @Bean
    public Job banksToConsole() {
        return jobBuilderFactory.get("banksToConsole")
                .start(readBanksFromDb())
                .build();
    }
}
