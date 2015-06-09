package com.aconex.codingchallenge;


import com.aconex.codingchallenge.infrastructure.factory.ApplicationFactory;
import com.aconex.codingchallenge.infrastructure.factory.UserInputReaderFactory;
import com.aconex.codingchallenge.infrastructure.io.UserInputReader;

import java.io.InputStream;
import java.util.logging.LogManager;

public class Driver {

    public static void main(String[] args) throws Exception {
        InputStream resourceAsStream = Driver.class.getResourceAsStream("/logging.properties");
        LogManager.getLogManager().readConfiguration(resourceAsStream);
        new Driver().start(args);
    }

    protected void start(String[] args) {
        ApplicationFactory applicationFactory = createApplicationFactory();
        Application application = applicationFactory.createApplication();
        UserInputReaderFactory userInputReaderFactory = createUserInputReaderFactory();
        userInputReaderFactory.setApp(application);
        UserInputReader inputReader = userInputReaderFactory.createInputReader(args);
        inputReader.readInput();

    }

    public ApplicationFactory createApplicationFactory() {
        return new ApplicationFactory();
    }

    public UserInputReaderFactory createUserInputReaderFactory() {
        return new UserInputReaderFactory();
    }
}
