package com.aconex.codingchallenge.domain;

import java.util.Set;

public class NumberToWordMappingStrategyImpl implements NumberToWordMappingStrategy {

    private TextRepository textRepository;

    @Override
    public Set<String> matchNumbersToWord(Digit[] digits) {
        char[] mappedCharacters = digits[0].getMappedCharacters();
        for (char mappedCharacter : mappedCharacters) {
            Set<String> mappedNumbers = getNumbersAsText(mappedCharacter, digits);

        }


        return null;
    }

    private Set<String> getNumbersAsText(char mappedCharacter, Digit[] digits) {
        Set<String> dictionaryWords = textRepository.getTextSet(mappedCharacter);
        for (String word : dictionaryWords) {
            String mappedNumber = createNumberToWordMappingStrategy(digits, word).getMappedNumber();
            System.out.println("Mapped number "+mappedNumber);
        }
        return null;
    }

    public void setTextRepository(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    protected NumberToWordMappingContext createNumberToWordMappingStrategy(Digit[] number, String word) {
        return new NumberToWordMappingContext(number, word);
    }

}
