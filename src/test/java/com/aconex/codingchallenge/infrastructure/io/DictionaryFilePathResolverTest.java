package com.aconex.codingchallenge.infrastructure.io;


import com.aconex.codingchallenge.infrastructure.factory.InputStreamFactory;
import com.aconex.codingchallenge.infrastructure.io.DictionaryFilePathResolver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class DictionaryFilePathResolverTest {

    private DictionaryFilePathResolver dictionaryFilePathResolver;

    @Mock
    private InputStream inputStream;

    @Mock
    private ClassLoader classLoader;

    @Mock
    private InputStreamFactory inputStreamFactory;

    private File fileToAssert;

    private boolean testForfileNotFoundException;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        dictionaryFilePathResolver = new DictionaryFilePathResolver() {
            @Override
            protected ClassLoader getClassLoader() {
                return classLoader;
            }
        };
        dictionaryFilePathResolver.setInputStreamFactory(inputStreamFactory);
    }

    @Test
    public void testGetDictionaryInputStreamForDefaultFile() throws Exception {
        Mockito.when(classLoader.getResourceAsStream("dictionary.txt")).thenReturn(inputStream);
        InputStream dictionaryFileInputStream = dictionaryFilePathResolver.getDictionaryFileInputStream();
        Assert.assertEquals(inputStream, dictionaryFileInputStream);
    }

    @Test
    public void testGetDictionaryInputStreamForExternalFile() throws Exception {
        System.setProperty("DictionaryFilePath", "dummyFile");
        Mockito.when(inputStreamFactory.createInputStream("dummyFile")).thenReturn(inputStream);
        InputStream dictionaryFileInputStream = dictionaryFilePathResolver.getDictionaryFileInputStream();
        Assert.assertEquals(inputStream, dictionaryFileInputStream);
    }

    @After
    public void after() throws Exception {
        System.clearProperty("DictionaryFilePath");

    }


}