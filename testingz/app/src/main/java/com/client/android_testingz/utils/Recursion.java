package com.client.android_testingz.utils;
// Recursive java program to
// find sum of digits of a number
class Recursion
{
    // Function to check sum of digit using recursion
    static int sumOfDigit(int n)
    {
        // if number is too big to compute, stop and return 0
        if(n < -200 || n > 200){
            return 0;
        }

        // Logic for positive numbers
        if(n > 1){
            return (n + sumOfDigit(n - 1));
        }

        if(n == 1){
            return 1;
        }

        //Logic for negative numbers
        if(n < -1){
          return (n + sumOfDigit(n + 1));
        }

        if(n == -1){
          return -1;
        }

        // when n == 0, end of recursion, return 0
        return 0;
    }


}
 