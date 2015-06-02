package com.aconex.codingchallenge.infrastructure.repo;

import com.aconex.codingchallenge.domain.TextRepository;

import java.util.*;

public class TextRepositoryImpl implements TextRepository {

    protected Map<Character, Set<String>> firstCharToTextMapping = new HashMap<Character, Set<String>>();

    @Override
    public void addText(String text) {
        if(text.length() == 0) {
            return;
        }
        char firstChar = text.charAt(0);
        Set<String> mappedWordsForChar = getMappedTextForChar(firstChar);
        mappedWordsForChar.add(text);
    }

    @Override
    public Set<String> getTextSet(char firstCharInText) {
        Set<String> textSet = firstCharToTextMapping.get(firstCharInText);
        if(textSet == null) {
            textSet = new HashSet<String>();
        }

        return Collections.unmodifiableSet(textSet);
    }

    private Set<String> getMappedTextForChar(char firstChar) {
        Set<String> words = firstCharToTextMapping.get(firstChar);
        if(words == null) {
            words = new HashSet<String>();
            firstCharToTextMapping.put(firstChar, words);
        }
        return words;
    }

}
