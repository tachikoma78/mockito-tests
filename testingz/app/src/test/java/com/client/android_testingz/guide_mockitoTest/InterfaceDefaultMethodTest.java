package com.client.android_testingz.guide_mockitoTest;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * TESTING INTERFACE METHODS
 */

public class InterfaceDefaultMethodTest {


    @Test
    public void mockedDefatulMethod(){
        AnInterface mock = mock(AnInterface.class);
        // System.out.print(mock.isTrue()); // prints false default value of boolean is false
        assertFalse(mock.isTrue());
    }

    @Test
    public void callRealDefaultMethod(){
        AnInterface mock = mock(AnInterface.class);
        when(mock.isTrue()).thenCallRealMethod(); // necessary
        assertTrue(mock.isTrue());
    }

    interface AnInterface {
        default boolean isTrue(){
            return true;
        }
    }
}
