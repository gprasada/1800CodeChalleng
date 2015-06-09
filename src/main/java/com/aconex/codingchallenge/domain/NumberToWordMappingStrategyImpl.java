package com.aconex.codingchallenge.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class NumberToWordMappingStrategyImpl implements NumberToWordMappingStrategy {

    private TextRepository textRepository;

    @Override
    public Set<String> matchNumbersToWord(Digit[] digits) {
        if(digits == null || digits.length == 0) {
            return Collections.EMPTY_SET;
        }
        return matchNumbersWithWordsInRepo(digits);
    }

    private Set<String> matchNumbersWithWordsInRepo(Digit[] digits) {
        char[] charactersMappedToDigit = digits[0].getMappedCharacters();
        Set<String> matchResults = new HashSet<String>();
        for (char mappedCharacter : charactersMappedToDigit) {
            Set<String> mappedNumbers = getNumbersAsText(mappedCharacter, digits);
            matchResults.addAll(mappedNumbers);
        }
        return matchResults;
    }

    private Set<String> getNumbersAsText(char mappedCharacter, Digit[] digits) {
        Set<String> dictionaryWords = textRepository.getTextSet(mappedCharacter);
        Set<String> matches = new HashSet<String>();
        for (String word : dictionaryWords) {
            NumberToWordMappingData numberToWordMappingData = createNumberToWordMappingData(digits, word);
            Set<String> matchSet = getMatches(numberToWordMappingData);
            matches.addAll(matchSet);
        }
        return matches;
    }

    private Set<String> getMatches(NumberToWordMappingData numberToWordMappingData) {
        Set<String> matches = new HashSet<String>();
        String match = numberToWordMappingData.getMappedNumber();
        addMappedNumbers(matches, match);
        return matches;
    }

    private void addMappedNumbers(Set<String> matches, String match) {
        if(match != null) {
            matches.add(match);
        }
    }

    public void setTextRepository(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    protected NumberToWordMappingData createNumberToWordMappingData(Digit[] number, String word) {
        return new NumberToWordMappingData(number, word);
    }

}
