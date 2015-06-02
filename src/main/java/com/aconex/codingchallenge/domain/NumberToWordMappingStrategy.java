package com.aconex.codingchallenge.domain;

import java.util.Set;

public interface NumberToWordMappingStrategy {

    Set<String> matchNumbersToWord(Digit[] digits);

}
