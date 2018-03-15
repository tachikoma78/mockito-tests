package com.client.android_testingz.verify_cookbook;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * http://www.baeldung.com/mockito-verify
 */

public class MyListTest_verify {

    @Test
    public void simpleInvocationTest(){
        List<String> mockedList = mock(MyList.class);

        mockedList.size();

        verify(mockedList).size();
    }

    @Test
    public void numberOfInvocationOnMoc(){
        List<String> mockedList = mock(MyList.class);

        mockedList.size();

        verify(mockedList, times(1)).size();
    }

    @Test
    public void no_interaction_withMock(){
        List<String> mockedList = mock(MyList.class);

        verifyZeroInteractions(mockedList);
    }

    @Test
    public void noInteraction_withSize(){
        List<String> mockedList = mock(MyList.class);

        verify(mockedList, times(0)).size();
    }

    @Test
    public void noUnexpected_interaction(){
        List<String> mockedList = mock(MyList.class);

        mockedList.size();
        mockedList.clear(); // function that returns an empty list

        verify(mockedList).size();
        verify(mockedList).clear(); // fails if you comment this one.

        verifyNoMoreInteractions(mockedList);
    }

    @Test
    public void order_of_interactions(){
        List<String> mockedList = mock(MyList.class);

        mockedList.size();
        mockedList.add("add some text");
        mockedList.clear();

        InOrder inOrder = Mockito.inOrder(mockedList);

        inOrder.verify(mockedList).size();
        inOrder.verify(mockedList).add("add some text");
        inOrder.verify(mockedList).clear();
    }

    @Test
    public void interaction_has_not_occurred(){
        List<String> mockedList = mock(MyList.class);
        mockedList.size();

        verify(mockedList, never()).clear();
    }


    @Test
    public void interaction_has_occurred_at_least_certain_number_of_times(){
        List<String> mockedList = mock(MyList.class);

        mockedList.clear();

        mockedList.clear();

        mockedList.clear();

        verify(mockedList, atLeast(1)).clear();

        verify(mockedList, atMost(10)).clear();
    }

    @Test
    public void interaction_with_exact_argument(){
        List<String> mockedList = mock(MyList.class);
        mockedList.add("test");
        verify(mockedList).add("test");
    }

    @Test
    public void interaction_with_any_argument(){
        List<String> mockedList = mock(MyList.class);
        mockedList.add("test");
        verify(mockedList).add(anyString());
    }


}
