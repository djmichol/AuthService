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
        VerifyUserResponseModel verifyUserResponseModel = authenticator.validateToken(verifyUserModel.getUserLogin(), verifyUserModel.getToken());
        if (verifyUserResponseModel.isAuthenticationVerified()) {
            return VerifyUserResponseModel.verified();
        } else if (verifyUserResponseModel.isTokenExpired()) {
            return VerifyUserResponseModel.tokenExppired();
        } else {
            return VerifyUserResponseModel.notVerified();
        }
    }
}
