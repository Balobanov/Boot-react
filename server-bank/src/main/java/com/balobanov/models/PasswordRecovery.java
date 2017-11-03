package com.balobanov.models;

import com.balobanov.models.base.BaseModel;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class PasswordRecovery extends BaseModel{

    private String email;

    private LocalDateTime localDateTime;

    private String approveCode;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getApproveCode() {
        return approveCode;
    }

    public void setApproveCode(String approveCode) {
        this.approveCode = approveCode;
    }
}
