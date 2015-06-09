package com.aconex.codingchallenge.domain;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static com.aconex.codingchallenge.domain.Digit.*;

public class NumberToWordMappingDataTest {

    @Test
    public void testNumberToWordMapping() {
        Digit[] number = {DIGIT_2, DIGIT_2, DIGIT_5, DIGIT_5};
        NumberToWordMappingData mappingData = new NumberToWordMappingData(number, "CALL");
        Assert.assertTrue(mappingData.done());
        Assert.assertEquals("CALL", mappingData.getMappedNumber());
    }

    @Test
    public void testNumberToWordMappingForWordLonerThanDigit() {
        Digit[] number = {DIGIT_2, DIGIT_2, DIGIT_5, DIGIT_5};
        NumberToWordMappingData mappingData = new NumberToWordMappingData(number, "CALLS");
        Assert.assertTrue(mappingData.done());
        Assert.assertNull(mappingData.getMappedNumber());
    }

    @Test
    public void testForNumberNotMatchingWord() {
        Digit[] number = {DIGIT_2, DIGIT_2, DIGIT_5, DIGIT_5};
        NumberToWordMappingData mappingData = new NumberToWordMappingData(number, "COOL");
        Assert.assertTrue(mappingData.done());
        Assert.assertNull(mappingData.getMappedNumber());
    }

    @Test
    public void testForNumberLongerThanWord() {
        Digit[] number = {DIGIT_2, DIGIT_2, DIGIT_5, DIGIT_5, DIGIT_8, DIGIT_9};
        NumberToWordMappingData mappingData = new NumberToWordMappingData(number, "CALL");
        Assert.assertFalse(mappingData.done());
        Assert.assertEquals("CALL-89", mappingData.getMappedNumber());
        // Assert.assertTrue();
    }



    @Test
    @Ignore
    public void testForDigitsWithNoWordMapping() {
        Digit[] number = {DIGIT_1,DIGIT_8, DIGIT_0, DIGIT_0,  DIGIT_2, DIGIT_2, DIGIT_5, DIGIT_5, DIGIT_1, DIGIT_0, DIGIT_3, DIGIT_2};
        NumberToWordMappingData mappingData = new NumberToWordMappingData(number, "CALL");
        Assert.assertFalse(mappingData.done());
        Assert.assertEquals("1-800-CALL-10-32", mappingData.getMappedNumber());
    }




}