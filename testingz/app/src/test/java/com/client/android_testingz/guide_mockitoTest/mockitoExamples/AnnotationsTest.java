package com.client.android_testingz.guide_mockitoTest.mockitoExamples;


import com.client.android_testingz.guide_mockito.PasswordEncoder;
import com.client.android_testingz.guide_mockito.UserRepository;
import com.client.android_testingz.guide_mockito.UserService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.internal.util.MockUtil.isMock;

/**
 * Clearing Invocations
 */

public class AnnotationsTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Before
    public void beforeTest(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test1(){
        passwordEncoder.encode("1");
        verify(passwordEncoder).encode(anyString()); // non null
    }

    @Test
    public void test2(){
        verifyZeroInteractions(passwordEncoder, userRepository); // Clearing Invocations
        assertFalse(isMock(userService));
    }


}