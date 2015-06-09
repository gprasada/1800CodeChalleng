package com.aconex.codingchallenge.infrastructure.factory;

import com.aconex.codingchallenge.Application;
import com.aconex.codingchallenge.infrastructure.io.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class UserInputReaderFactory {

    private Application app;

    private static final Logger LOGGER = Logger.getLogger(UserInputReaderFactory.class.getName());

    public UserInputReader createInputReader(String commandLineArgs[]) {
        if(commandLineArgs.length > 0) {
            LOGGER.info("Configuring application to read user input from files passed as program arguments");
            return getUserInputFileReader(commandLineArgs);
        }

        LOGGER.info("Configuring application to take user input from console");
        return getUserInputReaderImpl();
    }

    private UserInputReader getUserInputReaderImpl() {
        return new UserInputReaderImpl(app, getBufferedReader());
    }

    protected BufferedReader getBufferedReader() {
        return new BufferedReader(new InputStreamReader((System.in)));
    }

    private UserInputFileReaderImpl getUserInputFileReader(String[] files) {
        UserInputFileReaderImpl userInputFileReader = createUserInputFileReader();
        userInputFileReader.setFileReader(createFileReader());
        userInputFileReader.setFiles(files);
        userInputFileReader.setFileReaderCallBackInterace( createFileReaderCallbackInterface());
        userInputFileReader.setInputStreamFactory(createInputStreamFactory());
        return userInputFileReader;
    }

    private FileReaderCallBackInterace createFileReaderCallbackInterface() {
        UserFileReaderCallBackImpl userInputReader = createUserFileReaderCallBack();
        userInputReader.setApp(app);
        return userInputReader;
    }

    protected UserFileReaderCallBackImpl createUserFileReaderCallBack() {
        return new UserFileReaderCallBackImpl();
    }

    protected FileReader createFileReader() {
        return new FileReader();
    }

    protected UserInputFileReaderImpl createUserInputFileReader() {
        return new UserInputFileReaderImpl();
    }

    public void setApp(Application app) {
        this.app = app;
    }

    public InputStreamFactory createInputStreamFactory() {
        return new InputStreamFactory();
    }
}
