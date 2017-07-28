package com.michol.api.model.response;

import java.io.Serializable;

public class VerifyUserResponseModel implements Serializable{

    private boolean authenticationVerified;

    private VerifyUserResponseModel(boolean authenticationVerified) {
        this.authenticationVerified = authenticationVerified;
    }

    public static VerifyUserResponseModel verified(){
        return new VerifyUserResponseModel(true);
    }

    public static VerifyUserResponseModel notVerified(){
        return new VerifyUserResponseModel(false);
    }

    public boolean isAuthenticationVerified() {
        return authenticationVerified;
    }

    public void setAuthenticationVerified(boolean authenticationVerified) {
        this.authenticationVerified = authenticationVerified;
    }
}
