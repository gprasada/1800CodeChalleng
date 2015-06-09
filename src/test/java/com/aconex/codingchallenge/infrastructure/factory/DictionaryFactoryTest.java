package com.aconex.codingchallenge.infrastructure.factory;

import com.aconex.codingchallenge.domain.*;
import com.aconex.codingchallenge.infrastructure.io.DictionaryFilePathResolver;
import com.aconex.codingchallenge.infrastructure.io.DictionaryFileReaderCallBack;
import com.aconex.codingchallenge.infrastructure.io.FileReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DictionaryFactoryTest {

    private DictionaryFactory dictionaryFactory;

    @Mock
    private TextRepository textRepo;

    @Mock
    private TextFormatter textFormatter;

    @Mock
    private DictionaryFilePathResolver dictionaryFilePathResolver;

    @Mock
    private DictionaryFileReaderCallBack dictionaryFileReaderCallBack;

    @Mock
    private FileReader fileReader;

    @Mock
    private NumberToWordMappingStrategyImpl numberToWordMappingStrategy;

    @Mock
    private InputStreamFactory inputStreamFactory;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        dictionaryFactory = getDictionaryFactory();
        dictionaryFactory.setTextRepository(textRepo);

    }

    private DictionaryFactory getDictionaryFactory() {
        DictionaryFactory dictionaryFactory = Mockito.spy(new DictionaryFactory());
        doReturn(textFormatter).when(dictionaryFactory).createTextFormatter();
        doReturn(dictionaryFilePathResolver).when(dictionaryFactory).createDictionaryFilePathResolver();
        doReturn(dictionaryFileReaderCallBack).when(dictionaryFactory).createDictionaryFileReaderCallBack();
        doReturn(fileReader).when(dictionaryFactory).createFileReader();
        doReturn(numberToWordMappingStrategy).when(dictionaryFactory).createNumberToWordMappingStrategy();
        doReturn(inputStreamFactory).when(dictionaryFactory).createInputStreamFactory();
        return dictionaryFactory;
    }

    @Test
    public void testCreateDictionary() {
        InputStream is = Mockito.mock(InputStream.class);
        Mockito.when(dictionaryFilePathResolver.getDictionaryFileInputStream()).thenReturn(is);
        Dictionary dictionary = dictionaryFactory.createDictionary();
        Assert.assertSame(textFormatter, dictionary.getTextFormatter());
        Assert.assertSame(textRepo, dictionary.getTextRepository());
        Assert.assertEquals(numberToWordMappingStrategy, dictionary.getNumberToWordMappingStrategy());
        Mockito.verify(fileReader).readFile(is, dictionaryFileReaderCallBack);
        Mockito.verify(dictionaryFileReaderCallBack).setDictionary(dictionary);
        Mockito.verify(numberToWordMappingStrategy).setTextRepository(textRepo);
        Mockito.verify(dictionaryFilePathResolver).setInputStreamFactory(inputStreamFactory);


    }
}