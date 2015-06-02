package com.aconex.codingchallenge.infrastructure.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LineParserTest {

    private LineParser lineParser;

    @Before
    public void setUp() throws Exception {
        lineParser = new LineParser();
    }

    @Test
    public void testGetNumber() throws Exception {
        String numberWithNoChar = "12390000";
        String parsedNumber = lineParser.getNumber(numberWithNoChar);
        Assert.assertEquals(numberWithNoChar,parsedNumber);
    }

    @Test
    public void testGetNumberWhenLineHasCharacters() throws Exception {
        String numberWithNoChar = " 123.900?00 ";
        String parsedNumber = lineParser.getNumber(numberWithNoChar);
        Assert.assertEquals("12390000",parsedNumber);
    }

}