package com.michol.auth.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.michol.api.model.response.VerifyUserResponseModel;
import com.michol.auth.TokenService;
import com.michol.dao.model.User;
import org.springframework.core.env.Environment;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

public class TokenServiceImpl implements TokenService {

    private Environment environment;
    private Algorithm algorithm;

    public TokenServiceImpl(Environment environment) throws UnsupportedEncodingException {
        this.environment = environment;
        this.algorithm = Algorithm.HMAC512(environment.getProperty("secret.key"));
    }

    @Override
    public VerifyUserResponseModel validateToken(String token, String userName) {
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withAudience(userName)
                    .withIssuer(environment.getProperty("secret.issuer"))
                    .withSubject(environment.getProperty("secret.subject"))
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            if (Calendar.getInstance().getTime().after(jwt.getExpiresAt())) {
                return VerifyUserResponseModel.tokenExpired();
            } else {
                return VerifyUserResponseModel.verified();
            }
        } catch (TokenExpiredException e) {
            return VerifyUserResponseModel.tokenExpired();
        } catch (JWTVerificationException exception) {
            return VerifyUserResponseModel.notVerified();
        }
    }

    @Override
    public String generateToken(User user) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 48);
        String token = JWT.create().withIssuer(environment.getProperty("secret.issuer")).withIssuedAt(Calendar.getInstance().getTime()).withSubject(environment.getProperty
                ("secret.subject")).withAudience(user.getLogin()).withExpiresAt(calendar.getTime()).sign(algorithm);
        return token;
    }
}
