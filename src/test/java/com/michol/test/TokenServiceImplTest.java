package com.michol.test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.michol.api.model.response.VerifyUserResponseModel;
import com.michol.auth.TokenService;
import com.michol.dao.model.User;
import com.michol.test.configruation.HashSereviceConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Calendar;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import({HashSereviceConfiguration.class})
public class TokenServiceImplTest {

    @Autowired
    @Qualifier("environmentMock")
    private Environment environmentMock;

    @Autowired
    @Qualifier("tokenServiceTest")
    private TokenService tokenService;
    private Algorithm algorithm;

    @Before
    public void setUp() throws UnsupportedEncodingException {
        this.algorithm = Algorithm.HMAC512(environmentMock.getProperty("secret.key"));
    }

    @Test
    public void validateTokenValid() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 48);
        String token = JWT.create()
                .withIssuer(environmentMock.getProperty("secret.issuer"))
                .withIssuedAt(Calendar.getInstance().getTime())
                .withSubject(environmentMock.getProperty("secret.subject"))
                .withAudience("test")
                .withExpiresAt(calendar.getTime())
                .sign(algorithm);
        VerifyUserResponseModel verifyUserResponseModel = tokenService.validateToken(token, "test");
        Assert.assertTrue(verifyUserResponseModel.isAuthenticationVerified());
        Assert.assertFalse(verifyUserResponseModel.isTokenExpired());
    }

    @Test
    public void validateTokenExpired() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -48);
        String token = JWT.create()
                .withIssuer(environmentMock.getProperty("secret.issuer"))
                .withIssuedAt(Calendar.getInstance().getTime())
                .withSubject(environmentMock.getProperty("secret.subject"))
                .withAudience("test")
                .withExpiresAt(calendar.getTime())
                .sign(algorithm);
        VerifyUserResponseModel verifyUserResponseModel = tokenService.validateToken(token, "test");
        Assert.assertFalse(verifyUserResponseModel.isAuthenticationVerified());
        Assert.assertTrue(verifyUserResponseModel.isTokenExpired());
    }

    @Test
    public void validateTokenInValid() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 48);
        String token = JWT.create()
                .withIssuedAt(Calendar.getInstance().getTime())
                .withAudience("test11")
                .withExpiresAt(calendar.getTime())
                .sign(algorithm);
        VerifyUserResponseModel verifyUserResponseModel = tokenService.validateToken(token, "test");
        Assert.assertFalse(verifyUserResponseModel.isAuthenticationVerified());
    }

    @Test(expected = InvalidClaimException.class)
    public void generateTokenEmptyUserName() throws Exception {
        User user = new User();
        String token = tokenService.generateToken(user);
        JWTVerifier verifier = JWT.require(algorithm)
                .withAudience(user.getLogin())
                .build();
        DecodedJWT jwt = verifier.verify(token);
    }

    @Test
    public void generateTokenTestUserName() throws Exception {
        User user = new User();
        user.setLogin("test");
        String token = tokenService.generateToken(user);
        JWTVerifier verifier = JWT.require(algorithm)
                .withAudience(user.getLogin())
                .build();
        DecodedJWT jwt = verifier.verify(token);
        Assert.assertNotNull(jwt);
    }

}