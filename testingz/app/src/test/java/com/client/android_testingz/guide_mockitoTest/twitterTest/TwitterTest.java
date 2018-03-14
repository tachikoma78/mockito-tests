package com.client.android_testingz.guide_mockitoTest.twitterTest;

import com.client.android_testingz.twitter.ITweet;
import com.client.android_testingz.twitter.TwitterClient;

import org.junit.Test;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 */


public class TwitterTest {


    @Test
    public void testSendingMessage(){
        TwitterClient twitterClient = new TwitterClient();
        ITweet iTweet = mock(ITweet.class);

        when(iTweet.getMessage()).thenReturn("some twitter message");
        twitterClient.sendTweet(iTweet);

        verify(iTweet, atLeastOnce()).getMessage();
    }



}
