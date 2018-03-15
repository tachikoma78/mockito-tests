package com.client.android_testingz.verify_cookbook;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * http://www.baeldung.com/mockito-behavior
 */

public class MyListTest_when_then {


    @Test
    public void simple_return_behaviour(){
        MyList listMock = Mockito.mock(MyList.class);
        when(listMock.add(anyString())).thenReturn(false);
        boolean added = listMock.add("asdfw");
        assertFalse(added);
    }


    @Test
    public void simple_return_behaviour_v2(){
        MyList listMock = Mockito.mock(MyList.class);
        doReturn(false).when(listMock).add(any());
        boolean added = listMock.add("adv");
        assertFalse(added);
    }

    @Test(expected = IllegalStateException.class)
    public void throw_exception_method_call(){
        MyList listMock = Mockito.mock(MyList.class);
        when(listMock.add(anyString())).thenThrow(IllegalStateException.class);
        listMock.add(anyString());
    }



}
