package com.aconex.codingchallenge.infrastructure.io;

import com.aconex.codingchallenge.domain.Dictionary;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class DictionaryFileReaderCallBackTest {

    private DictionaryFileReaderCallBack dictionaryFileReaderCallBack;

    @Mock
    private Dictionary dictionary;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        dictionaryFileReaderCallBack = new DictionaryFileReaderCallBack();
        dictionaryFileReaderCallBack.setDictionary(dictionary);
    }

    @Test
    public void testNewLine() throws Exception {
        dictionaryFileReaderCallBack.newLine("HELLO");
        dictionaryFileReaderCallBack.newLine("WORLD");
        Mockito.verify(dictionary).addWord("HELLO");
        Mockito.verify(dictionary).addWord("WORLD");
        Mockito.verifyNoMoreInteractions(dictionary);

    }
}