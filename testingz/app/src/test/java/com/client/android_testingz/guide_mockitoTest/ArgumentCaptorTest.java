package com.client.android_testingz.guide_mockitoTest;

import com.client.android_testingz.guide_mockito.PasswordEncoder;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Capturing Arguments to run assertion on them
 */

public class ArgumentCaptorTest {

   @Test
    public void testSingleCall(){
       PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
       passwordEncoder.encode("password");

       ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);
       verify(passwordEncoder).encode(passwordCaptor.capture());

      // Capture value check it is correct
       assertEquals("password", passwordCaptor.getValue());
   }

   @Test
   public void testMultipleCalls() {
      PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
      passwordEncoder.encode("password1");
      passwordEncoder.encode("password2");
      passwordEncoder.encode("password3");

      ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);

      // Check method is called 3 times
      verify(passwordEncoder, times(3)).encode(passwordCaptor.capture());

      // check values are correct
      assertEquals(Arrays.asList("password1", "password2", "password3"),
              passwordCaptor.getAllValues());
   }


}
