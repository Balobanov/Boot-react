package com.balobanov.models;

import com.balobanov.models.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "departments")
public class Department extends BaseModel {

    @Column(name = "department_name")
    private String name;

    public Department() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
