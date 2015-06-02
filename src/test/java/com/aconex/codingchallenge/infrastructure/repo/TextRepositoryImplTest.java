package com.aconex.codingchallenge.infrastructure.repo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class TextRepositoryImplTest {

    private TextRepositoryImpl textRepository;

    @Before
    public void setUp() throws Exception {
        textRepository = new TextRepositoryImpl();
    }

    @Test
    public void testAddText() throws Exception {
        textRepository.addText("HELLO");
        textRepository.addText("WORLD");
        Set<String> words = textRepository.firstCharToTextMapping.get('H');
        Assert.assertEquals(1, words.size());
        Assert.assertTrue(words.contains("HELLO"));
        words = textRepository.firstCharToTextMapping.get('W');
        Assert.assertEquals(1, words.size());
        Assert.assertTrue(words.contains("WORLD"));
    }

    @Test
    public void testAddTextForEmptyString() {
        textRepository.addText(new StringBuffer().toString());
        Assert.assertEquals(0, textRepository.firstCharToTextMapping.size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetText() {
        Set<String> mappedText = new HashSet<String>();
        mappedText.add("HI");
        mappedText.add("HELLO");
        textRepository.firstCharToTextMapping.put('H', mappedText);
        Set<String> textSet = textRepository.getTextSet('H');
        assertIfSetUnModifiable(textSet);
    }

    private void assertIfSetUnModifiable(Set<String> textSet) {
        textSet.add("newItem");
    }

}