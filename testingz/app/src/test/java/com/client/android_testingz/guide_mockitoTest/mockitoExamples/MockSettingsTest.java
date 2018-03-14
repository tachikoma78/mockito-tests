package com.client.android_testingz.guide_mockitoTest.mockitoExamples;

import com.client.android_testingz.guide_mockito.PasswordEncoder;
import com.client.android_testingz.guide_mockito.User;
import com.client.android_testingz.guide_mockito.UserRepository;
import com.client.android_testingz.guide_mockito.UserService;

import org.junit.Test;
import org.mockito.exceptions.verification.SmartNullPointerException;
import org.mockito.exceptions.verification.WantedButNotInvoked;
import org.mockito.listeners.InvocationListener;
import org.mockito.listeners.MethodInvocationReport;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.RETURNS_SMART_NULLS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

/**
 *
 */

public class MockSettingsTest {

    @Test(expected = SmartNullPointerException.class)
    public void returnsSmartNulls() {
        // When you write mock() returns methods with empty values, nulls or 0 for primitives and false for booleans
        UserRepository userRepository = mock(UserRepository.class, RETURNS_SMART_NULLS);
        User user = userRepository.findById(null);

        // user is SmartNull but would be null with RETURNS_DEFAULTS answer
        assertNotNull(user);

        // will fail with SmartNullPointerException and nice stacktrace
        String passwordHash = user.getPasswordHash();
        assertEquals("", user.getPasswordHash());
    }

    @Test
    public void returnsMocks() {
        UserRepository userRepository = mock(UserRepository.class, RETURNS_MOCKS);
        User user = userRepository.findById(null);

        // Mocked method returned a mock
        assertNotNull(user);
        assertEquals("", user.getPasswordHash());
        assertFalse(user.isEnabled());
    }

    @Test(expected = WantedButNotInvoked.class) // because we do not interact with the mock
    public void mockName(){
        PasswordEncoder robustPasswordEncoder = mock(PasswordEncoder.class, "robustPasswordEncoder");
        PasswordEncoder weakPasswordEncoder = mock(PasswordEncoder.class, "weakPasswordEncoder");

        verify(robustPasswordEncoder).encode(anyString());
    }

    @Test
    public void extraInterfaces(){
        PasswordEncoder mockPasswordEncoder = mock(PasswordEncoder.class, withSettings().extraInterfaces(List.class, Map.class));
        assertTrue(mockPasswordEncoder instanceof List);
        assertTrue(mockPasswordEncoder instanceof Map);
    }

    @Test
    public void invocationListeners() {
        InvocationListener invocationListener = new InvocationListener() {

            @Override
            public void reportInvocation(MethodInvocationReport report) {
              if(report.threwException()){
                  Throwable throwable = report.getThrowable();
                  // do something with throwable
                  throwable.printStackTrace();
              }else{
                  Object returnedValue = report.getReturnedValue();
                  //
                  System.out.println(returnedValue);
              }
            }
        };
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class, withSettings().invocationListeners(invocationListener));
        passwordEncoder.encode("1");
    }

    @Test
    public void verboseLogging(){
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class, withSettings().verboseLogging());
        // listeners are called upon encode() invocation
        when(passwordEncoder.encode("a")).thenReturn("encoded1");

        // doReturn("encoded1").when(passwordEncoder).encode("1!);
        passwordEncoder.encode("1");
        passwordEncoder.encode("2");
    }


    @Test
    public void spiedInstance() {
        UserService userService = new UserService(mock(UserRepository.class), mock(PasswordEncoder.class));

        UserService userServiceMock = mock(UserService.class, withSettings().spiedInstance(userService).name("spied service"));
    }

}
