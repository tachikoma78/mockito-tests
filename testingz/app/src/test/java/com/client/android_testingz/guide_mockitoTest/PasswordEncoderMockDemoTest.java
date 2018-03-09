package com.client.android_testingz.guide_mockitoTest;

import com.client.android_testingz.guide_mockito.PasswordEncoder;

import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * Creating a mock
 */

public class PasswordEncoderMockDemoTest {


    @Test
    public void simpleMock(){
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    }

}
