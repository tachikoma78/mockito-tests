package com.client.android_testingz.guide_mockitoTest;

import com.client.android_testingz.guide_mockito.PasswordEncoder;

import org.junit.Test;

import static org.mockito.AdditionalMatchers.or;
import static org.mockito.Mockito.endsWith;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Combining Matchers
 * verify : good to see that some interactions take plave
 */

public class HowItWorksTest {
    private final String password = "wwb"; // end with b

    @Test
    public void testSnippet(){
        // 1: create
        PasswordEncoder mockEncoder = mock(PasswordEncoder.class);

        // 2: stub
        when(mockEncoder.encode(password)).thenReturn("123"); // will return 123 when code is encode() is run

        // 3: act
        // System.out.print(mockEncoder.encode(password).toString()); // prints 123
        mockEncoder.encode(password);

        // 4. verify
       verify(mockEncoder).encode(or(eq("1234"), endsWith("b")));
       // “the argument string must be equal to “1234” or ends with “b”.
    }


    @Test
    public void testMatcherInMethod() {
        // 1: create
        PasswordEncoder mock = mock(PasswordEncoder.class);

        // 2: stub
        when(mock.encode(password)).thenReturn("1");

        // 3: act
        mock.encode(password);

        // 4. verify
        verify(mock).encode(matchCondition());
    }

        private String matchCondition(){
                return or(eq(password), endsWith("b"));
        }

}
