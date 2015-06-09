package com.aconex.codingchallenge.infrastructure.io;

import com.aconex.codingchallenge.Application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class UserInputReaderImpl implements UserInputReader {

    public static final String INPUT_INSTRUCTION = "Please input the Phone number to see the matching words";
    private Application application;
    private BufferedReader bufferedReader;

    public UserInputReaderImpl(Application application, BufferedReader bufferedReader) {
        this.application = application;
        this.bufferedReader = bufferedReader;
    }

    protected PrintStream getPrintStream() {
        return System.out;
    }

    @Override
    public void readInput() {
        getPrintStream().println(INPUT_INSTRUCTION);
        getPrintStream().println("  Typing 'exit' to stop the program");
        String line;
        while(! "exit".equalsIgnoreCase(line = readLine())) {
            application.findMatches(line);
            getPrintStream().println(INPUT_INSTRUCTION);
        }


    }

    protected String readLine()  {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public Application getApplication() {
        return application;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }
}
