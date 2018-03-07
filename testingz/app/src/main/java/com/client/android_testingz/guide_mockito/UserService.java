package com.client.android_testingz.guide_mockito;

/**
 *
 */

public class UserService {
    private final UserRepository mUserRepository;
    private final PasswordEncoder mPasswordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        mPasswordEncoder = passwordEncoder;
        mUserRepository = userRepository;
    }

    public boolean isValidUser(String id, String password){
        User user = mUserRepository.findById(id);
        return isEnabledUser(user) && isValidPassword(user, password);
    }

    private boolean isEnabledUser(User user) {
        return user != null && user.isEnabled();
    }

    private boolean isValidPassword(User user, String password) {
        String encodedPassword = mPasswordEncoder.encode(password);
        return encodedPassword.equals(user.getPasswordHash());
    }

}
