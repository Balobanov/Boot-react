package com.balobanov.repositories;

import com.balobanov.models.PasswordRecovery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.isNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AuthFlowRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthFlowRepository repository;

    @Test
    public void persistOne() {
        PasswordRecovery passwordRecovery = new PasswordRecovery();
        passwordRecovery.setLocalDateTime(LocalDateTime.now());
        passwordRecovery.setEmail("dummy@mail.ru");
        passwordRecovery.setApproveCode("code");

        PasswordRecovery persisted = entityManager.persist(passwordRecovery);

        PasswordRecovery one = repository.findOne(persisted.getId());
        assertThat(one.getId()).isNotNull();
    }

    @Test
    public void findEmpty() {
        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    public void delete() {
        PasswordRecovery passwordRecovery = new PasswordRecovery();
        passwordRecovery.setLocalDateTime(LocalDateTime.now());
        passwordRecovery.setEmail("dummy@mail.ru");
        passwordRecovery.setApproveCode("code");

        entityManager.persist(passwordRecovery);

        assertThat(repository.findAll()).isNotEmpty();

        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }
}
