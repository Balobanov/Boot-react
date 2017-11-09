package com.balobanov.batch.writers;

import com.balobanov.models.Department;
import com.balobanov.repositories.DepartmentsRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentsDbItemWriter implements ItemWriter<Department> {

    private DepartmentsRepository departmentService;

    @Override
    public void write(List<? extends Department> items) throws Exception {
        departmentService.save(items);
    }

    @Autowired
    public void setDepartmentService(DepartmentsRepository departmentService) {
        this.departmentService = departmentService;
    }
}
