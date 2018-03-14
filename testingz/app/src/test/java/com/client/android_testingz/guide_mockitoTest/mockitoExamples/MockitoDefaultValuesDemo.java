package com.client.android_testingz.guide_mockitoTest.mockitoExamples;

import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

/**
 * Created by Pierre Vanderpol on 2018-03-09.
 */
public class MockitoDefaultValuesDemo {


    @Test
    public void testDefaultValues() {
        Demo demo = mock(Demo.class);
        assertEquals(0, demo.getInt());
        assertEquals(0, demo.getInteger().intValue());
        assertEquals(0d, demo.getDouble(), 0d);
        assertFalse(demo.getBoolean());
        assertNull(demo.getObject());
        assertEquals(Collections.emptyList(), demo.getCollection());
        assertNull(demo.getArray());
        assertEquals(0L, demo.getStream().count());
        assertFalse(demo.getOptional().isPresent());
    }


    interface Demo{
        int getInt();
        Integer getInteger();
        double getDouble();
        boolean getBoolean();
        String getObject();
        Collection<String> getCollection();
        String[] getArray();
        Stream<?> getStream();
        Optional<?> getOptional();

    }


}
