package com.michol.api.model.response;

import java.io.Serializable;

public class VerifyUserResponseModel implements Serializable{

    private boolean authenticationVerified;

    public VerifyUserResponseModel(boolean authenticationVerified) {
        this.authenticationVerified = authenticationVerified;
    }

    public boolean isAuthenticationVerified() {
        return authenticationVerified;
    }

    public void setAuthenticationVerified(boolean authenticationVerified) {
        this.authenticationVerified = authenticationVerified;
    }
}
