package com.michol.api.model.request;

import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@ApiModel(description = "User auth data")
public class VerifyUserModel implements Serializable{

    @NotEmpty
    private String token;

    @NotEmpty
    private String userLogin;

    public VerifyUserModel() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
