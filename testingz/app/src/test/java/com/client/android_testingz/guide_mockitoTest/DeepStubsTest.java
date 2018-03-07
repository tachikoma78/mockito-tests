package com.client.android_testingz.guide_mockitoTest;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 *  Changing Default Answers
 *  RETURNS_DEEP_STUBS = able to return mocks from mocks from mocks
 *  interactions between collaborators can be checked with verify()
 */

public class DeepStubsTest {

    @Test
    public void testVerify(){
        We mock = mock(We.class, Mockito.RETURNS_DEEP_STUBS);

        mock.we().are().so().deep(); // evaluates to FALSE because Mock of boolean is false
        verify(mock.we().are().so()).deep(); // verify method is called
    }

    @Test
    public void testAssert() {
        We mock = mock(We.class, Mockito.RETURNS_DEEP_STUBS);

        when(mock.we().are().so().deep()).thenReturn(true);
        // mock.we().are().so().deep(); // Fails because it is false as default
        assertTrue(mock.we().are().so().deep()); // test is true
    }

    interface We { Are we(); }
    interface Are { So are(); }
    interface So { Deep so(); }
    interface Deep { boolean deep(); }
}
