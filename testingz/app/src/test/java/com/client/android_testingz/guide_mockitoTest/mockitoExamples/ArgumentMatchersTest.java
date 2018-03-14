package com.client.android_testingz.guide_mockitoTest.mockitoExamples;

import com.client.android_testingz.guide_mockito.PasswordEncoder;

import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.exceptions.misusing.InvalidUseOfMatchersException;

import java.io.File;
import java.io.FileFilter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Verifying Behavior verify that specific interactions took place.
 * Hey, Mockito, make sure this method was called with these arguments.”
 *
 * + Custom Matchers:some matching logic
 */

public class ArgumentMatchersTest {

    private PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

    @Test
    public void simpleDemo(){
        when(passwordEncoder.encode(anyString())).thenReturn("exact");
        assertEquals("exact", passwordEncoder.encode("1"));
        assertEquals("exact", passwordEncoder.encode("abc"));
    }

    @Test(expected = InvalidUseOfMatchersException.class)
    public void allArgumentsByMatchersWithInvalidUseOfMatchers(){
        abstract class AClass{
            public abstract boolean call(String s, int i);
        }

        AClass mock = mock(AClass.class);

        // illegal use example
        when(mock.call("a", anyInt())).thenReturn(true);
    }

    @Test
    public void allArgumentsByMatchersWithValidUseOfMatchers() {
        abstract class AClass {
            public abstract boolean call(String s, int i);
        }

        AClass mock = mock(AClass.class);
       when(mock.call(eq("a"), anyInt())).thenReturn(true);
        //when(mock.call(anyString(), anyInt())).thenReturn(true);
    }

    //// Custom matchers
    @Test
    public void customMatcher() {
        FileFilter fileFilter = mock(FileFilter.class);
        ArgumentMatcher<File> hasLuck = file -> file.getName().endsWith("luck"); // create  argument matcher
        when(fileFilter.accept(argThat(hasLuck))).thenReturn(true);  // argThat() pass the matcher as an argument to a mocked method,

        assertFalse(fileFilter.accept(new File("/abv")));
        assertTrue(fileFilter.accept(new File("blfdsfa/luck/")));

    }


}
