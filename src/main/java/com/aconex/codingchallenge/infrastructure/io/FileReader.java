package com.aconex.codingchallenge.infrastructure.io;

import java.io.*;

public class FileReader {

    // TODO close BufferedReader

    public void readFile(InputStream inputStream, FileReaderCallBackInterace callBackInterace) {
        BufferedReader br = getBufferedReader(inputStream);
        String line;
        while( (line = readLine(br)) != null )  {
            callBackInterace.newLine(line);
        }

    }

    protected BufferedReader getBufferedReader(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    protected String readLine(BufferedReader br) {
        try {
            return getNextLine(br);
        } catch (IOException e) {
            throw new RuntimeException("Error Reading File", e);
        }
    }

    protected String getNextLine(BufferedReader br) throws IOException {
        return br.readLine();
    }

}
