package com.michol.api.model.request;


import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@ApiModel(description = "User login data")
public class LoginUserModel implements Serializable{

    @NotEmpty
    private String userName;

    @NotEmpty
    private String password;

    @NotEmpty
    private String serviceUrl;

    public LoginUserModel() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }
}
