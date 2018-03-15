package com.client.android_testingz.BDD_mockito;

/**
 *
 */

public interface PhoneBookRepository {

    /**
     * Insert phone record
     * @param name Contact name
     * @param phone Phone number
     */
    void insert(String name, String phone);

    /**
     * Check if the phonebook contains this contact
     * @param name Contact name
     * @return true if this contact name exists
     */
    boolean contains(String name);

    /**
     * Search for contact phone number
     * @param name Contact name
     * @return phone number
     */
    String getPhoneNumberByContactName(String name);
}
