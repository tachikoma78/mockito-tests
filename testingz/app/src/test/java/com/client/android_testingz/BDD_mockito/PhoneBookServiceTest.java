package com.client.android_testingz.BDD_mockito;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class PhoneBookServiceTest {

    @Mock
    PhoneBookRepository phoneBookRepository;

    PhoneBookService phoneBookService;

    String contactName = "Alice";
    String contactName2 = "Terminator";
    String contactPhoneNumber = "1243576";
    String tooLongPhoneNumber = "01111111111111";

    @Before
    public void init(){
        phoneBookService = new PhoneBookService(phoneBookRepository);
    }

    @Test
    public void givenValidContactName_whenSearchInPhoneBook_thenRetunPhoneNumber(){
        given(phoneBookRepository.contains(contactName)).willReturn(true);

        given(phoneBookRepository.getPhoneNumberByContactName(contactName)).will(
                (InvocationOnMock invocation) -> invocation.getArgument(0).equals(contactName)
                ? contactPhoneNumber : null
        );

        phoneBookService.search(contactName);

        then(phoneBookRepository).should().contains(contactName);
        then(phoneBookRepository).should().getPhoneNumberByContactName(contactName);

    }

    @Test
    public void givenInvalidContactName_whenSearch_thenRetunNull(){
        given(phoneBookRepository.contains(contactName2)).willReturn(false);

        String phoneNumber = phoneBookService.search(contactName2);

        then(phoneBookRepository).should().contains(contactName2); // calls the method
        then(phoneBookRepository).should(never()).getPhoneNumberByContactName(contactName2);

        Assert.assertEquals(null, phoneNumber);
    }

    @Test
    public void givenValidContactNameAndPhoneNumber_whenRegister_thenSucceed(){
        given(phoneBookRepository.contains(contactName)).willReturn(false);

        phoneBookService.register(contactName, contactPhoneNumber);

        verify(phoneBookRepository).insert(contactName, contactPhoneNumber);
    }

    @Test
    public void givenEmptyPhoneNumber_whenRegister_thenFail(){
        given(phoneBookRepository.contains(contactName)).willReturn(false);

        phoneBookService.register(contactName, "");

        then(phoneBookRepository).should(never()).insert(contactName, contactPhoneNumber);
    }

    @Test
    public void givenLongNumber_whenRegister_thenFail(){
       given(phoneBookRepository.contains(contactName2)).willReturn(false);

       willThrow(new RuntimeException()).given(phoneBookRepository).insert(anyString(), eq(tooLongPhoneNumber));

       try{
           phoneBookService.register(contactName2, tooLongPhoneNumber);
            fail("Should throw exception");
       }catch (RuntimeException ex){

       }
       then(phoneBookRepository).should(never()).insert(contactName, tooLongPhoneNumber);

    }

    @Test
    public void givenExistingContactName_whenRegister_thenFail(){
        given(phoneBookRepository.contains(contactName)).willThrow(new RuntimeException(("name already exists")));

        try {
            phoneBookService.register(contactName, contactPhoneNumber);
            fail("Should throw exception");
        }catch(Exception e){}

        then(phoneBookRepository).should(never()).insert(contactName, contactPhoneNumber);
    }


}