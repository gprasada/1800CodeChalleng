package com.aconex.codingchallenge.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.*;
import static com.aconex.codingchallenge.domain.Digit.*;

public class DictionaryTest {

    private Dictionary dictionary;

    @Mock
    private TextRepository textRepository;

    @Mock
    private TextFormatter textFormatter;

    @Mock
    private NumberToWordMappingStrategy numberToWordMappingStratergy;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        dictionary = new Dictionary();
        dictionary.setTextRepository(textRepository);
        dictionary.setTextFormatter(textFormatter);
        dictionary.setNumberToWordMappingStrategy(numberToWordMappingStratergy);
    }

    @Test
    public void testAddWord() {
        String word = " he?l.lO ";
        Mockito.when(textFormatter.format(word)).thenReturn("HELLO");
        dictionary.addWord(word);
        Mockito.verify(textRepository).addText("HELLO");
    }

    @Test
    public void testMatchNumbersToWord() {
        Digit[] digits = new Digit[] {Digit.DIGIT_2, Digit.DIGIT_2};
        List<String> strings = Arrays.asList("HI", "HO");
        Set<String> matches = new HashSet<String>(strings);
        Set<String> unmodifiableSet = Collections.unmodifiableSet(matches);
        Mockito.when(numberToWordMappingStratergy.matchNumbersToWord(digits)).thenReturn(unmodifiableSet);
        Set<String> matchedWords = dictionary.matchNumbersToWord(digits);
        Assert.assertSame(unmodifiableSet, matchedWords);

    }

}