package org.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import org.example.exception.FailedToReadWordsException;

public class DictionaryClient {
    private final URL dictionaryUrl;

    public DictionaryClient(String dictonaryUrl) {
        try {
            this.dictionaryUrl = new URL(
                    "https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt");
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid URL");
        }

    }

    public List<String> getDictionary() throws FailedToReadWordsException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(dictionaryUrl.openStream()))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
             throw new FailedToReadWordsException("Failed to read words", e);
        }
    }
    
}
