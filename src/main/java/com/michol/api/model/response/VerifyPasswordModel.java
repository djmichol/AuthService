package com.michol.api.model.response;

import java.io.Serializable;

public class VerifyPasswordModel implements Serializable {

    private boolean authenticated;

    private boolean canChangePassword;

    private boolean userLocked;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public boolean isCanChangePassword() {
        return canChangePassword;
    }

    public boolean isUserLocked() { return userLocked; }

    public void setAuthenticated(boolean authenticated) { this.authenticated = authenticated; }

    public void setCanChangePassword(boolean canChangePassword) { this.canChangePassword = canChangePassword; }

    public void setUserLocked(boolean userLocked) { this.userLocked = userLocked; }

    public VerifyPasswordModel() {
    }
}
