package com.michol.test.configruation;

import com.michol.auth.TokenService;
import com.michol.auth.impl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.UnsupportedEncodingException;

import static org.mockito.Mockito.when;

@Configuration
public class TokenServiceTestConfiguration {

    @Bean
    public Environment environmentMock(){
        Environment environmentMock = org.mockito.Mockito.mock(Environment.class);
        when(environmentMock.getProperty("secret.issuer")).thenReturn("issuer");
        when(environmentMock.getProperty("secret.subject")).thenReturn("subject");
        when(environmentMock.getProperty("secret.key")).thenReturn("jakis@#$secret12");
        return environmentMock;
    }

    @Bean
    public TokenService tokenServiceTest(Environment environmentMock) throws UnsupportedEncodingException {
        return new TokenServiceImpl(environmentMock);
    }

}
