package com.client.android_testingz;

import android.content.SharedPreferences;

import com.client.android_testingz.helper.SharedPreferencesHelper;
import com.client.android_testingz.model.SharedPreferenceEntry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the {@link /helper/SharedPreferencesHelper} that mocks SharedPreferences
 */
@RunWith(MockitoJUnitRunner.class)
public class SharedPrefenrencesHelperTest {

    static final String TEST_NAME = "Test name";
    static final String TEST_EMAIL = "test@email.com";
    static final Calendar TEST_DATE_OF_BIRTH = Calendar.getInstance();

    static {
        TEST_DATE_OF_BIRTH.set(1984, 12, 14);
    }

    private SharedPreferenceEntry mSharedPreferencesEntry;
    private SharedPreferencesHelper mMockSharedPreferencesHelper;
    private SharedPreferencesHelper mMockBrokenSharedPreferencesHelper;

    @Mock
    SharedPreferences mMockSharedPreferences;

    @Mock
    SharedPreferences mMockBrokenSharedPreferences;

    @Mock
    SharedPreferences.Editor mMockEditor;

    @Mock
    SharedPreferences.Editor mMockBrokenEditor;

    @Before
    public void initMocks(){
        // Create SharedPreferenceEntry to persist.
        mSharedPreferencesEntry = new SharedPreferenceEntry(TEST_NAME, TEST_DATE_OF_BIRTH, TEST_EMAIL);

        // Create a mocked SharedPreferences.
        mMockSharedPreferencesHelper = createMockSharedPreference();

        // Create a mocked SharedPreferences that fails at saving data.
        mMockBrokenSharedPreferencesHelper = createMockBrokenSharedPreference();
    }

    @Test
    public void sharedPreferencesHeper_SaveAndReadPersonalInformation(){
        // Save the personal information to SharedPreferences
        boolean success = mMockSharedPreferencesHelper.savePersonalInfo(mSharedPreferencesEntry);

        assertThat("Checking that SharedPreferencesEntry.save returns true", success, is(true));

        // Read personal information from SharedPreferences
        SharedPreferenceEntry savedSharedPreferenceEntry = mMockSharedPreferencesHelper.getPersonalInfo();

        // Make sure both written and retrieved personal information are equal.
        assertThat("Checking that SharedPreferenceEntry.name has been persisted and read correctly",
                mSharedPreferencesEntry.getName(),
                is(equalTo(savedSharedPreferenceEntry.getName())));

        assertThat("Checking that SharedPreferenceEntry.email has been persisted and read correctly",
                mSharedPreferencesEntry.getEmail(),
                is(equalTo(savedSharedPreferenceEntry.getEmail())));

        assertThat("Checking that SharedPreferenceEntry.getDateOfBirth() has been persisted and read correctly",
                mSharedPreferencesEntry.getDateOfBirth(),
                is(equalTo(savedSharedPreferenceEntry.getDateOfBirth())));
    }

    @Test
    public void sharedPreferencesHelper_SavePersonalInformationFailed_ReturnsFalse() {
        //Read personal information from a broken SharedPreferencesHelper
        boolean success = mMockBrokenSharedPreferencesHelper.savePersonalInfo(mSharedPreferencesEntry);

        assertThat("Make sure that writing to a broken SharedPreferencesHelper returns false", success, is(false));
    }

    /**
    * Creates a mocked SharedPreferences.
    */
    private SharedPreferencesHelper createMockSharedPreference(){
        // Return the MockEditor when requesting it.
        when(mMockSharedPreferences.edit()).thenReturn(mMockEditor);

        // mocking the shared preferences as is mMockSharedPreferences was previously written correctly
       when(mMockSharedPreferences.getString(eq(SharedPreferencesHelper.KEY_NAME), anyString()))
                .thenReturn(mSharedPreferencesEntry.getName());

       when(mMockSharedPreferences.getString(eq(SharedPreferencesHelper.KEY_EMAIL), anyString()))
              .thenReturn(mSharedPreferencesEntry.getEmail());

       when(mMockSharedPreferences.getLong(eq(SharedPreferencesHelper.KEY_DOB), anyLong()))
                .thenReturn(mSharedPreferencesEntry.getDateOfBirth().getTimeInMillis());

       // Mocking a successful commit.
       when(mMockEditor.commit()).thenReturn(true);


       return new SharedPreferencesHelper(mMockSharedPreferences);
    }



    /**
     * Creates a mocked SharedPreferences that fails when writing.
     */
    private SharedPreferencesHelper createMockBrokenSharedPreference(){
        // return the broken mMockEditorWhenRequesting
        when(mMockBrokenSharedPreferences.edit()).thenReturn(mMockBrokenEditor);

        // Mocking a commit that fails.
        when(mMockBrokenEditor.commit()).thenReturn(false);

        return new SharedPreferencesHelper(mMockBrokenSharedPreferences);
    }


}
