package com.balobanov.batch.mappers;

import com.balobanov.models.Bank;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankRowMapper implements RowMapper<Bank> {

    @Override
    public Bank mapRow(ResultSet resultSet, int i) throws SQLException {
        Bank bank = new Bank();
        bank.setName(resultSet.getString("name"));
        bank.setId(resultSet.getLong("id"));
        return bank;
    }
}