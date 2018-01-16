package com.balobanov.batch.jobs;

import com.balobanov.models.Department;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoteDepartmentJob {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private ItemReader<Department> departmentItemReader;
    private ItemWriter<Department> departmentItemWriter;
    private ItemProcessor<Department, Department> departmentItemProcessor;

    @Bean
    public Step remoteDepartmentsStep() {
        return stepBuilderFactory.get("processRemoteDepartments")
                .<Department, Department>chunk(10)
                .reader(departmentItemReader)
                .processor(departmentItemProcessor)
                .writer(departmentItemWriter)
                .build();
    }

    @Bean
    public Job remoteDepartmentJobBean(){
        return jobBuilderFactory.get("remoteDepartmentJobBean")
                .start(remoteDepartmentsStep())
                .build();
    }

    @Autowired
    public void setJobBuilderFactory(JobBuilderFactory jobBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
    }

    @Autowired
    public void setStepBuilderFactory(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Autowired
    public void setDepartmentItemReader(ItemReader<Department> departmentItemReader) {
        this.departmentItemReader = departmentItemReader;
    }

    @Autowired
    public void setDepartmentItemWriter(ItemWriter<Department> departmentItemWriter) {
        this.departmentItemWriter = departmentItemWriter;
    }

    @Autowired
    public void setDepartmentItemProcessor(ItemProcessor<Department, Department> departmentItemProcessor) {
        this.departmentItemProcessor = departmentItemProcessor;
    }
}
