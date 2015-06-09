package com.aconex.codingchallenge.infrastructure.io;

import com.aconex.codingchallenge.infrastructure.factory.InputStreamFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;

public class UserInputFileReaderImplTest {

    private UserInputFileReaderImpl fileInputReader;

    @Mock
    private FileReaderCallBackInterace callBackInterface;

    @Mock
    private FileReader fileReader;
    private String[] files = new String[] {"file1", "file2"};

    @Mock
    private InputStreamFactory inputStreamFactory;

    @Mock
    private InputStream is0;

    @Mock
    private InputStream is1;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        fileInputReader = new UserInputFileReaderImpl();
        fileInputReader.setFileReaderCallBackInterace(callBackInterface);
        fileInputReader.setFileReader(fileReader);
        fileInputReader.setFiles(files);
        fileInputReader.setInputStreamFactory(inputStreamFactory);
        Mockito.when(inputStreamFactory.createInputStream(files[0])).thenReturn(is0);
        Mockito.when(inputStreamFactory.createInputStream(files[1])).thenReturn(is1);

    }

    @Test
    public void testReadInput() throws Exception {
        fileInputReader.readInput();
        Mockito.verify(fileReader).readFile(is0, callBackInterface);
        Mockito.verify(fileReader).readFile(is1, callBackInterface);
    }


}