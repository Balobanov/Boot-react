package com.balobanov.batch.readers;

import com.balobanov.models.Department;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@StepScope
public class RemoteDepartmentItemReader implements ItemReader<Department> {

    private RestTemplate restTemplate;
    private List<Department> departmentList;

    @PostConstruct
    public void init() {
        try {
            Department[] forObject = restTemplate.getForObject("http://localhost:9090/departments/all", Department[].class);
            departmentList = new ArrayList<>(Arrays.asList(forObject));
        } catch (Exception e) {
            departmentList =new ArrayList<>();
        }
    }


    @Override
    public Department read() throws Exception {
        if (!departmentList.isEmpty())
            return departmentList.remove(0);

        return null;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
