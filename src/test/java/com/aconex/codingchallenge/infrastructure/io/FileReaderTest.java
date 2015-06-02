package com.aconex.codingchallenge.infrastructure.io;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;


public class FileReaderTest {

    private FileReader fileReader;

    private InputStream inputStream;

    @Mock
    private FileReaderCallBackInterace readerCallBackInterace;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        inputStream = this.getClass().getClassLoader().getResourceAsStream("test-dictionary.txt");


    }

    @Test
    public void testReadFile() throws Exception {
        fileReader = new FileReader();
        fileReader.readFile(inputStream, readerCallBackInterace);
        Mockito.verify(readerCallBackInterace).newLine("Hello");
        Mockito.verify(readerCallBackInterace).newLine("World");
        Mockito.verifyNoMoreInteractions(readerCallBackInterace);
    }

    @Test
    public void testReadFileForIOException() {
        fileReader = getFileReader();
        try {
            fileReader.readFile(inputStream, readerCallBackInterace);
            Assert.fail("Expecting Runtime Exception");
        } catch (RuntimeException e) {
            Assert.assertSame(IOException.class, e.getCause().getClass());
        }

    }

    private FileReader getFileReader() {
        return new FileReader() {
            protected String getNextLine(BufferedReader br) throws IOException {
                throw new IOException();
            }
        };
    }

}