package com.aconex.codingchallenge.infrastructure.factory;

import com.aconex.codingchallenge.domain.*;
import com.aconex.codingchallenge.infrastructure.io.DictionaryFilePathResolver;
import com.aconex.codingchallenge.infrastructure.io.DictionaryFileReaderCallBack;
import com.aconex.codingchallenge.infrastructure.io.FileReader;

import java.io.InputStream;

public class DictionaryFactory {


    private TextRepository textRepository;

    public Dictionary createDictionary() {
        Dictionary dictionary = new Dictionary();
        TextFormatter textFormatter = createTextFormatter();
        dictionary.setTextFormatter(textFormatter);
        dictionary.setTextRepository( textRepository );
        NumberToWordMappingStrategyImpl numberToWordMappingStrategy = createNumberToWordMappingStrategy();
        numberToWordMappingStrategy.setTextRepository(textRepository);
        dictionary.setNumberToWordMappingStrategy(numberToWordMappingStrategy);
        initDictionary(dictionary);
        return dictionary;
    }

    protected NumberToWordMappingStrategyImpl createNumberToWordMappingStrategy() {
        return new NumberToWordMappingStrategyImpl();
    }

    private void initDictionary(Dictionary dictionary) {
        DictionaryFilePathResolver dictionaryFilePathResolver = createDictionaryFilePathResolver();
        dictionaryFilePathResolver.setInputStreamFactory( createInputStreamFactory() );
        InputStream dictionaryFileInputStream = dictionaryFilePathResolver.getDictionaryFileInputStream();
        FileReader fileReader = createFileReader();
        DictionaryFileReaderCallBack dictionaryFileReaderCallBack = createDictionaryFileReaderCallBack();
        dictionaryFileReaderCallBack.setDictionary(dictionary);
        fileReader.readFile(dictionaryFileInputStream, dictionaryFileReaderCallBack);
    }

    protected TextFormatter createTextFormatter() {
        return new TextFormatter();
    }

    protected DictionaryFilePathResolver createDictionaryFilePathResolver() {
        return new DictionaryFilePathResolver();
    }

    protected DictionaryFileReaderCallBack createDictionaryFileReaderCallBack() {
        return new DictionaryFileReaderCallBack();
    }

    protected FileReader createFileReader() {
        return new FileReader();
    }


    public void setTextRepository(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    public InputStreamFactory createInputStreamFactory() {
        return new InputStreamFactory();
    }
}
