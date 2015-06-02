package com.aconex.codingchallenge.domain;

import java.util.HashSet;
import java.util.Set;

public class NumberToWordMappingStrategyImpl implements NumberToWordMappingStrategy {

    private TextRepository textRepository;

    @Override
    public Set<String> matchNumbersToWord(Digit[] digits) {
        char[] mappedCharacters = digits[0].getMappedCharacters();
        Set<String> allMappedNumbers = new HashSet<String>();
        for (char mappedCharacter : mappedCharacters) {
            Set<String> mappedNumbers = getNumbersAsText(mappedCharacter, digits);
            allMappedNumbers.addAll(mappedNumbers);
        }


        return allMappedNumbers;
    }

    private Set<String> getNumbersAsText(char mappedCharacter, Digit[] digits) {
        Set<String> dictionaryWords = textRepository.getTextSet(mappedCharacter);
        Set<String> mappedNumbers = new HashSet<String>();
        for (String word : dictionaryWords) {
            String mappedNumber = createNumberToWordMappingStrategy(digits, word).getMappedNumber();
            if(mappedNumber != null) {
                mappedNumbers.add(mappedNumber);
            }
        }
        return mappedNumbers;
    }

    public void setTextRepository(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    protected NumberToWordMappingContext createNumberToWordMappingStrategy(Digit[] number, String word) {
        return new NumberToWordMappingContext(number, word);
    }

}
