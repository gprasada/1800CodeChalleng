package com.aconex.codingchallenge.infrastructure.factory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class InputStreamFactoryTest {

    private InputStreamFactory inputStreamFactory;

    private File fileToAssert;

    @Mock
    private InputStream inputStream;

    private boolean testForfileNotFoundException;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        inputStreamFactory = new InputStreamFactory() {
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
    public void testCreateInputStream() throws Exception {
        InputStream is = inputStreamFactory.createInputStream("dummyFile");
        Assert.assertEquals(inputStream, is);
        Assert.assertEquals("dummyFile", fileToAssert.getName());
    }

    @Test
    public void testGetDictionaryInputStreamForException() throws Exception {
        testForfileNotFoundException = true;
        try {
            inputStreamFactory.createInputStream("dummyFile");
            Assert.fail("Runtime Exception Expected");
        } catch (RuntimeException e) {
            Assert.assertEquals(FileNotFoundException.class, e.getCause().getClass());

        }
    }



}