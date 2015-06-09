package com.aconex.codingchallenge.infrastructure.io;

import com.aconex.codingchallenge.Application;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class UserInputReaderImplTest {

    private UserInputReaderImpl userInputReader;

    @Mock
    private Application application;

    @Mock
    private BufferedReader bufferedReader;

    @Mock
    private PrintStream stdOut;
    private InOrder inOrder;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        userInputReader = Mockito.spy(new UserInputReaderImpl(application, bufferedReader));
        Mockito.when(userInputReader.getPrintStream()).thenReturn(stdOut);
        inOrder = Mockito.inOrder(application, stdOut);
    }

    @Test
    public void testReadInput() throws Exception {
        Mockito.when(bufferedReader.readLine()).thenReturn("0012", "2255", "exit");
        userInputReader.readInput();

        inOrder.verify(stdOut).println("Please input the Phone number to see the matching words");
        inOrder.verify(stdOut).println("  Typing 'exit' to stop the program");
        inOrder.verify(application).findMatches("0012");
        inOrder.verify(stdOut).println("Please input the Phone number to see the matching words");
        inOrder.verify(application).findMatches("2255");
        inOrder.verify(stdOut).println("Please input the Phone number to see the matching words");
        Mockito.verify(bufferedReader, Mockito.times(3)).readLine();
        Mockito.verifyNoMoreInteractions(bufferedReader);


     }

}