package com.balobanov.batch.processors;

import com.balobanov.models.Department;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class DepartmentIdNulllizerItemProcessor implements ItemProcessor<Department, Department>{

    @Override
    public Department process(Department item) throws Exception {
        item.setId(null);
        return item;
    }
}
