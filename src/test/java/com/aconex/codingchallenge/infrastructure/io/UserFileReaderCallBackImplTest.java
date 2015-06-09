package com.aconex.codingchallenge.infrastructure.io;

import com.aconex.codingchallenge.Application;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class UserFileReaderCallBackImplTest {

    private UserFileReaderCallBackImpl userFileReaderCallBack;

    @Mock
    private Application app;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userFileReaderCallBack = new UserFileReaderCallBackImpl();
        userFileReaderCallBack.setApp(app);

    }

    @Test
    public void testNewLine() throws Exception {
        userFileReaderCallBack.newLine("2255");
        userFileReaderCallBack.newLine("1800");
        Mockito.verify(app).findMatches("2255");
        Mockito.verify(app).findMatches("1800");

    }
}