package com.aconex.codingchallenge.infrastructure.io;

import com.aconex.codingchallenge.Application;

public class UserFileReaderCallBackImpl implements FileReaderCallBackInterace {

    private Application app;

    @Override
    public void newLine(String newLine) {
        app.findMatches(newLine);
    }

    public void setApp(Application app) {
        this.app = app;
    }
}
