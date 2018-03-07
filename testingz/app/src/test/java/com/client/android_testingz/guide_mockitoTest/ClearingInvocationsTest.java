package com.client.android_testingz.guide_mockitoTest;

import com.client.android_testingz.guide_mockito.PasswordEncoder;
import com.client.android_testingz.guide_mockito.UserRepository;

import org.junit.Test;

import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Clearing Invocations
 */

public class ClearingInvocationsTest {

    @Test
    public void clearInvocationsTest(){
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        UserRepository userRepository = mock(UserRepository.class);

        // Use mocks
        passwordEncoder.encode(null);
        userRepository.findById(null);

        // clear
        clearInvocations(passwordEncoder, userRepository);

        // succeeds because invocations were cleared
        verifyZeroInteractions(passwordEncoder, userRepository);

    }


}
