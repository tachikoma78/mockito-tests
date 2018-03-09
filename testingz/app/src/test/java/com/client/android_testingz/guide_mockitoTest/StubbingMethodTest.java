package com.client.android_testingz.guide_mockitoTest;

import com.client.android_testingz.guide_mockito.PasswordEncoder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * Calling Real Methods
 */

public class StubbingMethodTest {

    private PasswordEncoder passwordEncoder;

    @Before
    public void beforeMethod(){
        passwordEncoder = mock(PasswordEncoder.class);
    }

    @Test
    public void thenReturn(){
        when(passwordEncoder.encode("1")).thenReturn("a");
        assertEquals("a", passwordEncoder.encode("1"));
    }

   @Test
    public void thenReturnConsecutive(){
       when(passwordEncoder.encode("1")).thenReturn("a","b");

       assertEquals("a", passwordEncoder.encode("1"));
       assertEquals("b", passwordEncoder.encode("1"));
       assertEquals("b", passwordEncoder.encode("1"));
   }

   @Test
   public void thenAnswerGetArgument(){
        when(passwordEncoder.encode("1")).thenAnswer(invocation -> invocation.getArgument(0) + "!");
        assertEquals("1!", passwordEncoder.encode("1"));
   }

   @Test(expected = IllegalArgumentException.class)
    public void thenAnswerThrowException(){
       when(passwordEncoder.encode("1")).thenAnswer(invocation -> {
           throw new IllegalArgumentException();
       });

       passwordEncoder.encode("1");
   }

    @Test
    public void thenAnswerCallRealMethod(){
        Date mock = mock(Date.class);
        doAnswer(InvocationOnMock::callRealMethod).when(mock).setTime(42);
        doAnswer(InvocationOnMock::callRealMethod).when(mock).getTime();

        mock.setTime(42);
        assertEquals(42, mock.getTime());
    }

    @Test(expected = Exception.class) // otherwise throws cannot call abstract real method on Java object
    public void thenCallRealMethodFail(){
        when(passwordEncoder.encode("1")).thenCallRealMethod();
        //correct example:
        //when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();
    }

    @Test
    public void thenCallRealMethod() {
        Date mockDate = mock(Date.class);
        doCallRealMethod().when(mockDate).setTime(42);
        when(mockDate.getTime()).thenCallRealMethod();

        mockDate.setTime(42);

        assertEquals(42, mockDate.getTime());
    }

    @Test(expected = IllegalArgumentException.class)
    public void thenThrowExceptionByInstance(){
        when(passwordEncoder.encode("1")).thenThrow(new IllegalArgumentException());

        passwordEncoder.encode("1");
    }


    @Test(expected = IllegalArgumentException.class)
    public void thenThrowExceptionByClass() {
        when(passwordEncoder.encode("1")).thenThrow(IllegalArgumentException.class);
        passwordEncoder.encode("1");
    }


}
