package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {

    @Test
    public void testPrimes() {
        Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE)); //простое
    }

    @Test
    public void testPrimesFast() {
        Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE)); //простое
    }

    @Test(enabled = false)
    public void testPrimesLong() {
        long n = Integer.MAX_VALUE;
        Assert.assertTrue(Primes.isPrime(n)); //простое
    }

    @Test
    public void testNotPrimes() {
        Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2)); //непростое
    }







}
