package com.michol.api;

import com.michol.api.model.request.VerifyUserModel;
import com.michol.api.model.response.VerifyUserResponseModel;
import com.michol.auth.Authenticator;
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

    private Authenticator authenticator;

    public AuthenticationServiceHttp(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    @RequestMapping(value = "verify", method = RequestMethod.POST)
    public ResponseEntity<VerifyUserResponseModel> verify(@RequestBody @Valid VerifyUserModel verifyUserModel) {
        if(authenticator.validateToken(verifyUserModel.getUserLogin(), verifyUserModel.getToken())) {
            return new ResponseEntity<>(new VerifyUserResponseModel(true), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new VerifyUserResponseModel(false), HttpStatus.UNAUTHORIZED);
        }
    }

}
