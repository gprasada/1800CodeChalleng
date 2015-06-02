package com.aconex.codingchallenge.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.aconex.codingchallenge.domain.Digit.*;

public class NumberToWordMappingStrategyImplTest {

    private NumberToWordMappingStrategyImpl numberToTextMappingStrategy;

    @Mock
    private TextRepository textRepository;

    @Mock
    private NumberToWordMappingContext numberToWordMappingContext;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        numberToTextMappingStrategy = new NumberToWordMappingStrategyImpl() {

            @Override
            protected NumberToWordMappingContext createNumberToWordMappingStrategy(Digit[] number, String word) {
                Mockito.reset(numberToWordMappingContext);
                setupNumberToWordMappingContext(number, word);
                return numberToWordMappingContext;
            }

        };
        numberToTextMappingStrategy.setTextRepository(textRepository);

        setupTextRepository('C', Arrays.asList("CALL", "COUNTRY","COOL"));
        setupTextRepository('A', Arrays.asList("APPLE", "AMERICA"));
        setupTextRepository('B', Arrays.asList("BANGALORE", "BEER"));

    }

    private void setupNumberToWordMappingContext(Digit[] number, String word) {
        String response = null;
        if(word.equals("CALL")) {
            response = "CALL";
        }
        Mockito.when(numberToWordMappingContext.getMappedNumber()).thenReturn(response);
    }

    private void setupTextRepository(char firstChar, List<String> textList) {
        Set<String> textSet = new HashSet<String>();
        textSet.addAll(textList);
        Mockito.when(textRepository.getTextSet(firstChar)).thenReturn(textSet);
    }

    @Test
    public void testMatchNumbersToText() throws Exception {
        Set<String> matchedText = numberToTextMappingStrategy.matchNumbersToWord(new Digit[]{DIGIT_2, DIGIT_2, DIGIT_5, DIGIT_5});
        Assert.assertEquals(1, matchedText.size());
        Assert.assertTrue(matchedText.contains("CALL"));
    }



}