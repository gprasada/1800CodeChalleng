package com.aconex.codingchallenge;

import com.aconex.codingchallenge.domain.Dictionary;
import com.aconex.codingchallenge.domain.Digit;
import com.aconex.codingchallenge.domain.NumberFormatter;
import com.aconex.codingchallenge.domain.NumberToWordMappingStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static com.aconex.codingchallenge.domain.Digit.DIGIT_2;
import static com.aconex.codingchallenge.domain.Digit.DIGIT_5;


public class ApplicationTest {

    private Application app;

    @Mock
    private Dictionary dictionary;

    @Mock
    private OutputStream outputStream;

    @Mock
    private NumberFormatter numberFormatter;

    private String newLineChar;
    private InOrder inOrder;
    private String inputString;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        app = new Application();
        app.setDictionary(dictionary);
        app.setOutputStream(outputStream);
        app.setNumberFormatter(numberFormatter);
        newLineChar = System.getProperty("line.separator");
        inOrder = Mockito.inOrder(outputStream);
        inputString = " !@2%255&* ";
        setupMock();

    }

    @Test
    public void testFindMatches() throws Exception {
        setupMock();
        app.findMatches(inputString);

        inOrder.verify(outputStream).write(("2 Matches for number " + inputString + " ----").getBytes());
        inOrder.verify(outputStream).write(newLineChar.getBytes());
        inOrder.verify(outputStream).write(("CALL").getBytes());
        inOrder.verify(outputStream).write(newLineChar.getBytes());
        inOrder.verify(outputStream).write(("COOL").getBytes());
        inOrder.verify(outputStream).write(newLineChar.getBytes());
    }

    @Test
    public void testFindMatchesForIOException() throws IOException {

        IOException ioException = new IOException();
        Mockito.doThrow(ioException).when(outputStream).write(("2 Matches for number " + inputString + " ----").getBytes());

        try {
            app.findMatches(inputString);
            Assert.fail("Expecting RuntimeException");
        } catch (RuntimeException e) {
            Assert.assertSame(ioException, e.getCause());
        }

    }

    private void setupMock() {

        Set<String> matches = new TreeSet<String>();
        matches.add("CALL");
        matches.add("COOL");
        Digit[] digits = new Digit[] {DIGIT_2, DIGIT_2, DIGIT_5, DIGIT_5};
        Mockito.when(dictionary.matchNumbersToWord(digits)).thenReturn(matches);

        Mockito.when((numberFormatter.format(inputString))).thenReturn(digits);
    }
}