package com.aconex.codingchallenge;

import com.aconex.codingchallenge.infrastructure.factory.ApplicationFactory;
import com.aconex.codingchallenge.infrastructure.factory.UserInputReaderFactory;
import com.aconex.codingchallenge.infrastructure.io.UserInputReader;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DriverTest {

    private Driver driver;

    @Mock
    private ApplicationFactory applicationFactory;

    @Mock
    private Application application;

    @Mock
    private UserInputReaderFactory userInputReaderFactory;

    @Mock
    private UserInputReader userInputReader;

    private String[] commandLineArgs;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        driver = Mockito.spy(new Driver());

    }

    @Test
    public void testStart() throws Exception {
        doReturn(applicationFactory).when(driver).createApplicationFactory();
        doReturn(userInputReaderFactory).when(driver).createUserInputReaderFactory();
        when(applicationFactory.createApplication()).thenReturn(application);
        commandLineArgs = new String[0];
        when(userInputReaderFactory.createInputReader(Mockito.eq(commandLineArgs))).thenReturn(userInputReader);

        driver.start(commandLineArgs);
        Mockito.verify(userInputReader).readInput();
        Mockito.verify(userInputReaderFactory).setApp(application);

    }
}