package com.aconex.codingchallenge.infrastructure.io;

import com.aconex.codingchallenge.infrastructure.factory.InputStreamFactory;

import java.io.InputStream;


//TODO close IS
public class UserInputFileReaderImpl implements UserInputReader {

    protected FileReader fileReader;

    protected FileReaderCallBackInterace fileReaderCallBackInterace;

    protected String[] files;

    protected InputStreamFactory inputStreamFactory;


    @Override
    public void readInput() {
        for (String file : files) {
            InputStream inputStream = inputStreamFactory.createInputStream(file);
            fileReader.readFile(inputStream, fileReaderCallBackInterace);
        }
    }

    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public void setFileReaderCallBackInterace(FileReaderCallBackInterace fileReaderCallBackInterace) {
        this.fileReaderCallBackInterace = fileReaderCallBackInterace;
    }

    public void setFiles(String[] files) {
        this.files = files;
    }

    public void setInputStreamFactory(InputStreamFactory inputStreamFactory) {
        this.inputStreamFactory = inputStreamFactory;
    }
}
