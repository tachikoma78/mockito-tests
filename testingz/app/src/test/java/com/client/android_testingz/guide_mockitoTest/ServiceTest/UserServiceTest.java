package com.client.android_testingz.guide_mockitoTest.ServiceTest;

import com.client.android_testingz.guide_mockito.PasswordEncoder;
import com.client.android_testingz.guide_mockito.User;
import com.client.android_testingz.guide_mockito.UserRepository;
import com.client.android_testingz.guide_mockito.UserService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 *
 */

public class UserServiceTest {

    private static final String PASSWORD = "password";

    private static final User ENABLED_USER = new User("user id", "hash", true);

    private static final User DISABLED_USER = new User("disabled user id", "hash", false);

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Before
    public void setUp() {
        userRepository = createUserRepository();
        passwordEncoder = createPasswordEncoder();
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    public void shouldBeValidFOrValidCredentials(){
        boolean userIsValid = userService.isValidUser(ENABLED_USER.getId(), PASSWORD);
        assertTrue(userIsValid);

        // userRepository had to be used to find a user with id="user id"
        verify(userRepository).findById(ENABLED_USER.getId());

        // passwordEncoder had to be used to compute a hash of "password"
        verify(passwordEncoder).encode(PASSWORD);
    }


    @Test
    public void shouldBeInvalidForInvalidId() {
        boolean userIsValid = userService.isValidUser("invalid id", PASSWORD);
        assertFalse(userIsValid);

        InOrder inOrder = Mockito.inOrder(userRepository, passwordEncoder);
        inOrder.verify(userRepository).findById("invalid id");
        inOrder.verify(passwordEncoder, never()).encode(anyString());
    }

    @Test
    public void shouldBeInvalidForInvalidPassword() {
        boolean userIsValid = userService.isValidUser(ENABLED_USER.getId(), "invalid");
        assertFalse(userIsValid);
        ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);
        verify(passwordEncoder).encode(passwordCaptor.capture());
        assertEquals("invalid", passwordCaptor.getValue());
    }

    @Test
    public void shouldBeInvalidForDisabledUser() {
        boolean userIsValid = userService.isValidUser(DISABLED_USER.getId(), PASSWORD);
        assertFalse(userIsValid);
        verify(userRepository).findById(DISABLED_USER.getId());
        verifyZeroInteractions(passwordEncoder);
    }


    private PasswordEncoder createPasswordEncoder() {
        PasswordEncoder mockPasswordEncoder = mock(PasswordEncoder.class);
        when(mockPasswordEncoder.encode(anyString())).thenReturn("any password hash");
        when(mockPasswordEncoder.encode(PASSWORD)).thenReturn(ENABLED_USER.getPasswordHash());
        return mockPasswordEncoder;
    }

    private UserRepository createUserRepository() {
        UserRepository mockUserRepo = mock(UserRepository.class);
        when(mockUserRepo.findById(ENABLED_USER.getId())).thenReturn(ENABLED_USER);
        when(mockUserRepo.findById(DISABLED_USER.getId())).thenReturn(DISABLED_USER);
        return mockUserRepo;
    }

}
