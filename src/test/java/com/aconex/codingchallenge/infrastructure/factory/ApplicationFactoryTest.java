package com.aconex.codingchallenge.infrastructure.factory;

import com.aconex.codingchallenge.Application;
import com.aconex.codingchallenge.domain.Dictionary;
import com.aconex.codingchallenge.domain.NumberFormatter;
import com.aconex.codingchallenge.domain.TextRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.OutputStream;

import static org.mockito.Mockito.*;

public class ApplicationFactoryTest {

    private ApplicationFactory applicationFactorySpy;

    @Mock
    private Dictionary dictionary;

    @Mock
    private DictionaryFactory dictionaryFactory;

    @Mock
    private TextRepository textRepository;
    private ApplicationFactory applicationFactory;

    @Mock
    private NumberFormatter numberFormater;

    @Mock
    private OutputStream outputStream;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        applicationFactory = new ApplicationFactory();
        applicationFactorySpy = spy(applicationFactory);
        doReturn(dictionaryFactory).when(applicationFactorySpy).createDictionaryFactory();
        doReturn(textRepository).when(applicationFactorySpy).createTextRepository();
        doReturn(numberFormater).when(applicationFactorySpy).createNumberFormatter();
        when(dictionaryFactory.createDictionary()).thenReturn(dictionary);
    }

    @Test
    public void testCreateApplication() throws Exception {
        Application application = applicationFactorySpy.createApplication();
        Assert.assertSame(dictionary, application.getDictionary());
        Assert.assertSame(numberFormater, application.getNumberFormatter());
        Assert.assertSame(System.out, application.getOutputStream());
        verify(dictionaryFactory).setTextRepository(textRepository);


    }

}
