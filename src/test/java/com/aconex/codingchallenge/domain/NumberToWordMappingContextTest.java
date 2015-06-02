package com.aconex.codingchallenge.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static com.aconex.codingchallenge.domain.Digit.*;

public class NumberToWordMappingContextTest {

    @Before
    public void setUp() throws Exception {


    }

    @Test
    public void testNumberToWordMapping() {
        Digit[] number = {DIGIT_2, DIGIT_2, DIGIT_5, DIGIT_5};
        NumberToWordMappingContext mappingContext = new NumberToWordMappingContext(number, "CALL");
        Assert.assertTrue(mappingContext.done());
        Assert.assertNull(mappingContext.getMsg());
        Assert.assertEquals("CALL",mappingContext.getMappedNumber());
    }

    @Test
    public void testNumberToWordMappingForWordLonerThanDigit() {
        Digit[] number = {DIGIT_2, DIGIT_2, DIGIT_5, DIGIT_5};
        NumberToWordMappingContext mappingContext = new NumberToWordMappingContext(number, "CALLS");
        Assert.assertTrue(mappingContext.done());
        Assert.assertEquals("Word is longer than Number", mappingContext.getMsg());
        Assert.assertNull(mappingContext.getMappedNumber());
    }

    @Test
    public void testForNumberNotMatchingWord() {
        Digit[] number = {DIGIT_2, DIGIT_2, DIGIT_5, DIGIT_5};
        NumberToWordMappingContext mappingContext = new NumberToWordMappingContext(number, "COOL");
        Assert.assertTrue(mappingContext.done());
        Assert.assertEquals("Number and Word do not match", mappingContext.getMsg());
        Assert.assertNull(mappingContext.getMappedNumber());
    }

    @Test
    public void testForNumberLongerThanWord() {
        Digit[] number = {DIGIT_2, DIGIT_2, DIGIT_5, DIGIT_5, DIGIT_8, DIGIT_9};
        NumberToWordMappingContext mappingContext = new NumberToWordMappingContext(number, "CALL");
        Assert.assertTrue(mappingContext.done());
        Assert.assertNull(mappingContext.getMsg());
        Assert.assertEquals("CALL-89", mappingContext.getMappedNumber());
    }




}