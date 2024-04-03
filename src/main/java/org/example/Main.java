package org.example;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.client.DictionaryClient;
import org.example.exception.FailedToReadWordsException;
import org.example.filter.WordsFilter;
import org.example.riddle.Riddle;

public class Main {
    private static final String WORDS_URL = "https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt";

    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        DictionaryClient dictionaryClient = new DictionaryClient(WORDS_URL);
        List<String> dictionary;
        try {
            dictionary = dictionaryClient.getDictionary();
        } catch (FailedToReadWordsException e) {
            logger.info("Failed to read words");
            return;
        }

        WordsFilter wordsFilter = new WordsFilter(dictionary);

        Riddle riddle = new Riddle(dictionary, wordsFilter);

        riddle.solveRiddle();
    }

}