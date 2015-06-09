package com.aconex.codingchallenge.infrastructure.factory;

import com.aconex.codingchallenge.Application;
import com.aconex.codingchallenge.domain.NumberFormatter;
import com.aconex.codingchallenge.domain.TextRepository;
import com.aconex.codingchallenge.infrastructure.repo.TextRepositoryImpl;

import java.io.OutputStream;

public class ApplicationFactory {

    public Application createApplication() {
        Application application = new Application();
        TextRepository textRepository = createTextRepository();
        DictionaryFactory dictionaryFactory = createDictionaryFactory();
        dictionaryFactory.setTextRepository(textRepository);
        application.setDictionary( dictionaryFactory.createDictionary() );
        application.setNumberFormatter( createNumberFormatter() );
        application.setOutputStream( getOutputStream() );
        return application;
    }

    protected DictionaryFactory createDictionaryFactory() {
        return new DictionaryFactory();
    }

    protected TextRepository createTextRepository() {
        return new TextRepositoryImpl();
    }

    public NumberFormatter createNumberFormatter() {
        return new NumberFormatter();
    }

    public OutputStream getOutputStream() {
        return System.out;
    }
}
