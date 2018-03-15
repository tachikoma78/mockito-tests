package com.client.android_testingz.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Pierre Vanderpol on 2018-03-15.
 */

public class RecursionTest {

    int min = -2147483648;
    int zero = 0;
    int testCasePositive = 5;
    int correctResultPositive = 15;
    int testCaseNegative = -5;
    int correctResultNegative = -15;
    int max = 2147483647;
    int minimumLimit = -201;
    int maximumLimit = 201;

    // exclude value out of range
    @Test
    public void sumOfDigit_shouldReturnZero_whenInputSmaller_thanLowerLimit(){
        assertEquals(zero , Recursion.sumOfDigit(min));
    }

    @Test
    public void sumOfDigit_shouldReturnZero_whenInputBigger_thanHigherLimit(){
        assertEquals(zero , Recursion.sumOfDigit(max));
    }

    // zero should return zero to stop recursion
    @Test
    public void sumOfDigit_shouldReturnZero_whenInputIsZero(){
        assertEquals(zero , Recursion.sumOfDigit(zero));
    }

    // check logic for positive
    @Test
    public void sumOfDigit_shouldReturnCorrectNumber_whenInputIsPositiveInteger(){
        assertEquals(correctResultPositive , Recursion.sumOfDigit(testCasePositive));
    }

    @Test
    public void sumOfDigit_shouldReturnOne_whenInputIsOne(){
        assertEquals(1 , Recursion.sumOfDigit(1));
    }

    // check logic for negative
    @Test
    public void sumOfDigit_shouldReturnCorrectNumber_whenInputIsNegativeInteger(){
        assertEquals(correctResultNegative , Recursion.sumOfDigit(testCaseNegative));
    }

    @Test
    public void sumOfDigit_shouldReturnMinusOne_whenInputIsMinusOne(){
        assertEquals(-1 , Recursion.sumOfDigit(-1));
    }

    // check for sign error: input -5 should not return +15 and +5 input should not return -15.
    @Test
    public void sumOfDigit_shouldNotReturnCorrectDigitButPositive_whenInputIsNegative(){
        assertNotEquals(correctResultPositive , Recursion.sumOfDigit(testCaseNegative));
    }

    @Test
    public void sumOfDigit_shouldNotReturnCorrectDigitButNegative_whenInputIsPositive(){
        assertNotEquals(correctResultNegative , Recursion.sumOfDigit(testCasePositive));
    }

    // Test of border case but inside limits: 200 and -200
    @Test
    public void sumOfDigit_shoulddReturn_correctResult_whenInputIsOneABelowMaximumLimit(){
        assertEquals(20100 , Recursion.sumOfDigit(200));
    }

    @Test
    public void sumOfDigit_shouldReturn_correctResult_whenInputIsOneAboveMinimumLimit(){
        assertEquals(-20100 , Recursion.sumOfDigit(-200));
    }

    // Test of border cases just outside limits: 201 and -201
    @Test
    public void sumOfDigit_shoulddReturnZero_whenInputIsAtMaximumLimit(){
        assertEquals(0 , Recursion.sumOfDigit(maximumLimit));
    }

    @Test
    public void sumOfDigit_shoulddReturnZero_whenInputIsAtMinimumLimit(){
        assertEquals(0 , Recursion.sumOfDigit(minimumLimit));
    }

}
