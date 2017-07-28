package com.michol.api.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@ApiModel(description = "Change user password data")
public class ChangePasswordModel  implements Serializable {

    @NotEmpty
    @ApiModelProperty(value = "login", allowableValues = "login", required = true)
    private String login;
    @NotEmpty
    @ApiModelProperty(value = "oldPassword", allowableValues = "password", required = true)
    private String oldPassword;
    @NotEmpty
    @ApiModelProperty(value = "newPassword", allowableValues = "password", required = true)
    private String newPassword;

    public ChangePasswordModel() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}