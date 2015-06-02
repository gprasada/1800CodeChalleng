package com.aconex.codingchallenge.infrastructure.io;


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

            @Override
            protected InputStream getInputStreamToExternalFile(File file) throws FileNotFoundException {
                if(testForfileNotFoundException) {
                    throw new FileNotFoundException("DictionaryFileNotFound");
                }
                fileToAssert = file;
                return inputStream;

            }

        };
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
        InputStream dictionaryFileInputStream = dictionaryFilePathResolver.getDictionaryFileInputStream();
        Assert.assertEquals(inputStream, dictionaryFileInputStream);
        Assert.assertEquals("dummyFile", fileToAssert.getName());
    }

    @Test
    public void testGetDictionaryInputStreamForException() throws Exception {
        System.setProperty("DictionaryFilePath", "dummyFile");
        testForfileNotFoundException = true;
        try {
            dictionaryFilePathResolver.getDictionaryFileInputStream();
            Assert.fail("Runtime Exception Expected");
        } catch (RuntimeException e) {
            Assert.assertEquals(FileNotFoundException.class, e.getCause().getClass());

        }
    }

    @After
    public void after() throws Exception {
        System.clearProperty("DictionaryFilePath");

    }


}