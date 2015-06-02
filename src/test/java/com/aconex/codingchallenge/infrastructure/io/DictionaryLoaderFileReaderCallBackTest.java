package com.aconex.codingchallenge.infrastructure.io;

import com.aconex.codingchallenge.domain.Dictionary;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class DictionaryLoaderFileReaderCallBackTest {

    private DictionaryLoaderFileReaderCallBack dictionaryLoaderFileReaderCallBack;

    @Mock
    private Dictionary dictionary;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        dictionaryLoaderFileReaderCallBack = new DictionaryLoaderFileReaderCallBack();
        dictionaryLoaderFileReaderCallBack.setDictionary(dictionary);
    }

    @Test
    public void testNewLine() throws Exception {
        dictionaryLoaderFileReaderCallBack.newLine("HELLO");
        dictionaryLoaderFileReaderCallBack.newLine("WORLD");
        Mockito.verify(dictionary).addWord("HELLO");
        Mockito.verify(dictionary).addWord("WORLD");
        Mockito.verifyNoMoreInteractions(dictionary);

    }
}