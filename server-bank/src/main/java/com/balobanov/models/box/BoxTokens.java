package com.balobanov.models.box;

import com.balobanov.models.base.BaseModel;

import javax.persistence.Entity;

@Entity
public class BoxTokens extends BaseModel {

    private String access_token;
    private String token_type;
    private String refresh_token;
    private String restricted_to;
    private String expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getRestricted_to() {
        return restricted_to;
    }

    public void setRestricted_to(String restricted_to) {
        this.restricted_to = restricted_to;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
}
