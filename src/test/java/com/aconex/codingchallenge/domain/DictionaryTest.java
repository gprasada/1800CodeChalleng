package com.aconex.codingchallenge.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.Assert.*;

public class DictionaryTest {

    private Dictionary dictionary;

    @Mock
    private TextRepository textRepository;

    @Mock
    private TextFormatter textFormatter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        dictionary = new Dictionary();
        dictionary.setTextRepository(textRepository);
        dictionary.setTextFormatter(textFormatter);

    }

    @Test
    public void testAddWord() {
        String word = " he?l.lO ";
        Mockito.when(textFormatter.format(word)).thenReturn("HELLO");
        dictionary.addWord(word);
        Mockito.verify(textRepository).addText("HELLO");

    }

}