package com.michol.api;

import com.michol.api.model.request.VerifyUserModel;
import com.michol.api.model.response.VerifyUserResponseModel;
import com.michol.api.usecase.HandlerExecutor;
import com.michol.api.usecase.impl.AuthenticationHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(AuthenticationServiceHttp.PATH)
public class AuthenticationServiceHttp {

    public static final String PATH = "auth";

    private HandlerExecutor handlerExecutor;

    public AuthenticationServiceHttp(HandlerExecutor handlerExecutor) {
        this.handlerExecutor = handlerExecutor;
    }

    @RequestMapping(value = "verify", method = RequestMethod.POST)
    public ResponseEntity<VerifyUserResponseModel> verify(@RequestBody @Valid VerifyUserModel verifyUserModel) {
        VerifyUserResponseModel verifyUserResponseModel = handlerExecutor.execute(AuthenticationHandler.class, verifyUserModel);
        if (verifyUserResponseModel.isAuthenticationVerified()) {
            return new ResponseEntity<>(verifyUserResponseModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(verifyUserResponseModel, HttpStatus.UNAUTHORIZED);
        }
    }

}
