package com.aconex.codingchallenge.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static com.aconex.codingchallenge.domain.Digit.*;

public class NumberToWordMappingStrategyImplTest {

    private NumberToWordMappingStrategyImpl numberToTextMappingStrategy;

    @Mock
    private TextRepository textRepository;

    @Mock
    private NumberToWordMappingData numberToWordMappingData;

    private Map<String, String> wordToNumberMappingTestData = new HashMap<String, String>();
    private Digit[] numberToMatch;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        numberToTextMappingStrategy = new NumberToWordMappingStrategyImpl() {

            @Override
            protected NumberToWordMappingData createNumberToWordMappingData(Digit[] number, String word) {
                Mockito.reset(numberToWordMappingData);
                setupNumberToWordMappingData(number, word);
                return numberToWordMappingData;
            }

        };
        numberToTextMappingStrategy.setTextRepository(textRepository);

        setupTextRepository('C', Arrays.asList("CALL", "COUNTRY","COOL"));
        setupTextRepository('A', Arrays.asList("APPLE", "AMERICA"));
        setupTextRepository('B', Arrays.asList("BANGALORE", "BEER", "BALL"));

        wordToNumberMappingTestData.put("CALL", "CALL");
        wordToNumberMappingTestData.put("BALL", "BALL");

    }

    private void setupNumberToWordMappingData(Digit[] number, String word) {
        String response = null;
        if(numberToMatch.equals(number)) {
            response = wordToNumberMappingTestData.get(word);
        }
        Mockito.when(numberToWordMappingData.getMappedNumber()).thenReturn(response);
    }

    private void setupTextRepository(char firstChar, List<String> textList) {
        Set<String> textSet = new HashSet<String>();
        textSet.addAll(textList);
        Mockito.when(textRepository.getTextSet(firstChar)).thenReturn(textSet);
    }

    @Test
    public void testMatchNumbersToText() throws Exception {
        numberToMatch = new Digit[]{DIGIT_2, DIGIT_2, DIGIT_5, DIGIT_5};
        Set<String> matchedText = numberToTextMappingStrategy.matchNumbersToWord(numberToMatch);
        Assert.assertEquals(2, matchedText.size());
        Assert.assertTrue(matchedText.contains("CALL"));
    }

    @Test
    public void testMatchNumbersToTextForZeroDigits() throws Exception {
        numberToMatch = new Digit[0];
        Set<String> matchedText = numberToTextMappingStrategy.matchNumbersToWord(numberToMatch);
        Assert.assertEquals(0, matchedText.size());
    }

    @Test
    public void testMatchNumbersToTextForNull() throws Exception {
        Set<String> matchedText = numberToTextMappingStrategy.matchNumbersToWord(null);
        Assert.assertEquals(0, matchedText.size());
    }



}