package com.michol.test;

import com.michol.auth.HashService;
import com.michol.test.configruation.HashSereviceConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import({HashSereviceConfiguration.class})
public class HashServiceTest {

    @Autowired
    private HashService hashService;

    private String password = "password";
    private String wrongPassowrd = "wrongpass";

    @Test
    public void testHashValidPasswordExceptTrue() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String hashedPass = hashService.generateStrongPasswordHash(password);
        Assert.assertTrue(hashService.validatePassword(password,hashedPass));
    }

    @Test
    public void testHashInValidPasswordExceptFalse() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String hashedPass = hashService.generateStrongPasswordHash(password);
        Assert.assertFalse(hashService.validatePassword(wrongPassowrd,hashedPass));
    }

}
