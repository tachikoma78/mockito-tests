package com.client.android_testingz.guide_mockitoTest;

import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;

/**
 * Spy test :  call real methods
 */

public class SpyTest {

    @Test
    public void test(){
        DecimalFormat decimalFormat = spy(new DecimalFormat()); // Pass to spy an instance to spy on
        assertEquals("42", decimalFormat.format(42L));
    }

}
