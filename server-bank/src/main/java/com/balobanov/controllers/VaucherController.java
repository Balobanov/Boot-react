package com.balobanov.controllers;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RestController
public class VaucherController {

    @Autowired
    VauserService vauserService;

    @RequestMapping(value = "/vaucher")
    public void generate() throws SQLException {
       vauserService.saveVauser();
    }
}

@Service
class VauserService {

    @Autowired
    DataSource dataSource;

    @Transactional
    void saveVauser() throws SQLException {
        int amount = 0;

        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO vaucher(pin) VALUES (?)");
        while (amount < 1_500_000){
            try {
                preparedStatement.setString(1, Generator.generate());
                preparedStatement.execute();
                amount++;
            }
            catch (Exception e) {
                System.out.println("Duplicate");
                amount--;
            }
        }
        connection.commit();
        connection.close();
    }
}

@Repository
interface VaucherRepo extends JpaRepository<Vaucher, Long> {}

@Entity
class Vaucher {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String pin;

    public Vaucher() {
    }

    public Vaucher(String pin) {
        this.pin = pin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}

class Generator {

    static String generate() {
        String tepm = RandomStringUtils.randomNumeric(100);
        int start = (tepm.length() - 1) - 12;
        return tepm.substring(start, start + 12);
    }
}
