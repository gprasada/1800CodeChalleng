package com.aconex.codingchallenge.domain;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class DigitTest {

    private char[] allChars;

    @Before
    public void setUp() throws Exception {

        String allAlphabets = "ABCDEFGHIJKLMSNOPQRSTUVWXYZ";
        allChars = allAlphabets.toCharArray();

    }

    @Test
    public void testIsCharMappedToDigit() throws Exception {
        assertMappedCharacters( Digit.DIGIT_0, getMappedCharacterSet(Collections.emptyList())) ;
        assertMappedCharacters( Digit.DIGIT_1, getMappedCharacterSet(Collections.emptyList())) ;
        assertMappedCharacters( Digit.DIGIT_2, getMappedCharacterSet(Arrays.asList('A', 'B', 'C'))) ;
        assertMappedCharacters( Digit.DIGIT_3, getMappedCharacterSet(Arrays.asList('D', 'E', 'F'))) ;
        assertMappedCharacters( Digit.DIGIT_4, getMappedCharacterSet(Arrays.asList('G', 'H', 'I'))) ;
        assertMappedCharacters( Digit.DIGIT_5, getMappedCharacterSet(Arrays.asList('J', 'K', 'L'))) ;
        assertMappedCharacters( Digit.DIGIT_6, getMappedCharacterSet(Arrays.asList('M', 'N', 'O'))) ;
        assertMappedCharacters( Digit.DIGIT_7, getMappedCharacterSet(Arrays.asList('P', 'Q', 'R', 'S'))) ;
        assertMappedCharacters( Digit.DIGIT_8, getMappedCharacterSet(Arrays.asList('T', 'U', 'V'))) ;
        assertMappedCharacters( Digit.DIGIT_9, getMappedCharacterSet(Arrays.asList('W', 'X', 'Y', 'Z'))) ;

    }

    private void assertMappedCharacters(Digit digit, Set<Character> mappedCharacterSet) {
        for (char charToAssert : allChars) {
            assertMappedCharacters(charToAssert, mappedCharacterSet, digit);
        }
    }

    private void assertMappedCharacters(char charToAssert, Set<Character> mappedCharacterSet, Digit digit) {
        boolean charMappingToDigitExpected = mappedCharacterSet.contains(charToAssert) ? true : false;
        Assert.assertEquals("charToAssert mapping with digit "+digit.toString()+ "? "+charMappingToDigitExpected, charMappingToDigitExpected, digit.isCharMappedToDigit(charToAssert));

    }

    public Set<Character> getMappedCharacterSet(List chars) {
        HashSet<Character> characterSet = new HashSet<Character>();
        characterSet.addAll(chars);
        return characterSet;
    }
}