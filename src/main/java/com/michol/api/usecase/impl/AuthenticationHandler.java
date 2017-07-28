package com.michol.api.usecase.impl;

import com.michol.api.model.request.VerifyUserModel;
import com.michol.api.model.response.VerifyUserResponseModel;
import com.michol.api.usecase.RequestHandler;
import com.michol.auth.Authenticator;

public class AuthenticationHandler implements RequestHandler<VerifyUserModel, VerifyUserResponseModel> {

    private Authenticator authenticator;

    public AuthenticationHandler(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    public VerifyUserResponseModel handle(VerifyUserModel verifyUserModel) {
        if(authenticator.validateToken(verifyUserModel.getUserLogin(), verifyUserModel.getToken())) {
            return VerifyUserResponseModel.verified();
        }else{
            return VerifyUserResponseModel.notVerified();
        }
    }
}
