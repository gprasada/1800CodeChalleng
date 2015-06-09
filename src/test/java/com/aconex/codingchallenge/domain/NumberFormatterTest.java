package com.aconex.codingchallenge.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static com.aconex.codingchallenge.domain.Digit.*;

public class NumberFormatterTest {

    private NumberFormatter numberFormatter;

    @Before
    public void setUp() throws Exception {
        numberFormatter = new NumberFormatter();
    }

    @Test
    public void testFormat() throws Exception {
        Digit[] digits = numberFormatter.format(" !2C@255* ");
        Assert.assertArrayEquals(new Digit[] {DIGIT_2, DIGIT_2, DIGIT_5, DIGIT_5}, digits);

    }

    @Test
    public void testFormatForNullString() throws Exception {
        Digit[] digits = numberFormatter.format(null);
        Assert.assertArrayEquals(new Digit[0], digits);
    }

    @Test
    public void testFormatForInvalidNumber() throws Exception {
        Digit[] digits = numberFormatter.format(" !@#dsfsdfsd%^");
        Assert.assertArrayEquals(new Digit[0], digits);
    }

}