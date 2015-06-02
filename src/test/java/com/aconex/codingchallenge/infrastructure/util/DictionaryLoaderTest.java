package com.aconex.codingchallenge.infrastructure.util;

import com.aconex.codingchallenge.domain.Dictionary;
import com.aconex.codingchallenge.infrastructure.io.DictionaryFilePathResolver;
import com.aconex.codingchallenge.infrastructure.io.FileReader;
import com.aconex.codingchallenge.infrastructure.io.FileReaderCallBackInterace;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;

import static org.junit.Assert.*;

public class DictionaryLoaderTest {

    private DictionaryLoader dictionaryLoader;

    @Mock
    private FileReaderCallBackInterace callBackInterface;

    @Mock
    private DictionaryFilePathResolver dictionaryFilePathResolver;

    @Mock
    private FileReader fileReader;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        dictionaryLoader = new DictionaryLoader();
        dictionaryLoader.setCallBackInterace(callBackInterface);
        dictionaryLoader.setDictionaryFilePathResolver(dictionaryFilePathResolver);
        dictionaryLoader.setFileReader(fileReader);
    }


    @Test
    public void testLoadDictionary() throws Exception {
        InputStream is = Mockito.mock(InputStream.class);
        Mockito.when(dictionaryFilePathResolver.getDictionaryFileInputStream()).thenReturn(is);
        dictionaryLoader.loadDictionary();
        Mockito.verify(fileReader).readFile(is, callBackInterface);

    }
}