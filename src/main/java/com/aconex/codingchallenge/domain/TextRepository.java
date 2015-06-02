package com.aconex.codingchallenge.domain;

import java.util.Set;

public interface TextRepository {

    public void addText(String text);

    public Set<String> getTextSet(char firstCharInText);

}
