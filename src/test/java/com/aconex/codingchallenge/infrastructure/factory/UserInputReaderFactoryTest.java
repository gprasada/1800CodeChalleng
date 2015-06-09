package com.aconex.codingchallenge.infrastructure.factory;

import com.aconex.codingchallenge.Application;
import com.aconex.codingchallenge.infrastructure.io.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class UserInputReaderFactoryTest {

    private UserInputReaderFactory userInputReaderFactory;

    @Mock
    private UserInputFileReaderImpl userInputFileReaderImpl;

    @Mock
    private FileReader fileReader;

    @Mock
    private Application app;

    @Mock
    private UserFileReaderCallBackImpl userFileReaderCallBackImpl;

    @Mock
    private BufferedReader bufferedReader;

    @Mock
    private InputStreamFactory inputStreamFactory;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        UserInputReaderFactory inputReaderFactory = new UserInputReaderFactory();
        inputReaderFactory.setApp(app);
        this.userInputReaderFactory = Mockito.spy(inputReaderFactory);
        Mockito.when(this.userInputReaderFactory.getBufferedReader()).thenReturn(bufferedReader);
        Mockito.when(this.userInputReaderFactory.createUserInputFileReader()).thenReturn(userInputFileReaderImpl);
        Mockito.when(this.userInputReaderFactory.createFileReader()).thenReturn(fileReader);
        Mockito.when(this.userInputReaderFactory.createUserFileReaderCallBack()).thenReturn(userFileReaderCallBackImpl);
        Mockito.when(userInputReaderFactory.createInputStreamFactory()).thenReturn(inputStreamFactory);


    }

    @Test
    public void testCreateInputReader() throws Exception {
        String[] inputArgs = new String[]{"file1"};
        UserInputFileReaderImpl userInputReader = (UserInputFileReaderImpl) userInputReaderFactory.createInputReader(inputArgs);
        Mockito.verify(userInputFileReaderImpl).setFileReader(fileReader);
        Mockito.verify(userInputFileReaderImpl).setFiles(inputArgs);
        Mockito.verify(userInputReader).setFileReaderCallBackInterace(userFileReaderCallBackImpl);
        Mockito.verify(userFileReaderCallBackImpl).setApp(app);
        Mockito.verify(userInputFileReaderImpl).setInputStreamFactory(inputStreamFactory);


    }

    @Test
    public void testCreateInputReaderForNoArgs() throws Exception {
        String[] inputArgs = new String[0];
        UserInputReaderImpl userInputReader = (UserInputReaderImpl)userInputReaderFactory.createInputReader(inputArgs);
        Assert.assertEquals(app, userInputReader.getApplication());
        Assert.assertEquals(bufferedReader, userInputReader.getBufferedReader());
    }




}