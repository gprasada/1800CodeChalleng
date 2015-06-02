package com.aconex.codingchallenge.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TextFormatterTest {

    private TextFormatter textFormatter;

    @Before
    public void setUp() throws Exception {
        textFormatter = new TextFormatter();
    }

    @Test
    public void testFormat() throws Exception {
        String word = textFormatter.format("HELLO");
        Assert.assertEquals("HELLO", word);
    }

    @Test
    public void testFormatForLowerCase() throws Exception {
        String word = textFormatter.format("hello");
        Assert.assertEquals("HELLO", word);
    }

    @Test
    public void testFormatForSpecialCharacter() throws Exception {
        String word = textFormatter.format(" ?h*.ell&O ");
        Assert.assertEquals("HELLO", word);
    }

}