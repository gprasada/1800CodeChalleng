package com.aconex.codingchallenge;

import com.aconex.codingchallenge.domain.Dictionary;
import com.aconex.codingchallenge.domain.Digit;
import com.aconex.codingchallenge.domain.NumberFormatter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;

public class Application {

    private OutputStream outputStream;

    private Dictionary dictionary;

    private static final String NEW_LINE_CHAR = System.getProperty("line.separator");

    private static final String TEMPLATE = "%s Matches for number %s ----";

    private NumberFormatter numberFormatter;

    public void findMatches(String number) {
        Digit[] digits = numberFormatter.format(number);
        Set<String> strings = dictionary.matchNumbersToWord(digits);
        writeOutput(number, strings);
    }

    private void writeOutput(String number, Set<String> strings) {
        String format = String.format(TEMPLATE, strings.size(), number);
        writeNewLine(format);
        for (String string : strings) {
            writeNewLine(string);
        }
    }

    private void writeNewLine(String format) {
        writeBytes(format.getBytes());
        writeBytes(NEW_LINE_CHAR.getBytes());
    }

    private void writeBytes(byte[] bytes) {
        try {
            outputStream.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setOutputStream(OutputStream outputStream) {

        this.outputStream = outputStream;
    }

    public void setNumberFormatter(NumberFormatter numberFormatter) {

        this.numberFormatter = numberFormatter;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public NumberFormatter getNumberFormatter() {
        return numberFormatter;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
}
