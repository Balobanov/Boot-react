package com.balobanov.services;

import com.balobanov.models.Department;
import com.balobanov.repositories.DepartmentsRepository;
import com.balobanov.services.abstraction.DepartmentService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends AbstractBaseService<Department, Long, DepartmentsRepository> implements DepartmentService{
}
