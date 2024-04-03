package org.example.riddle;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.filter.WordsFilter;

public class Riddle {
    private static final int WORD_MAXIMUM_LENGTH = 9;
    private static final List<Character> REQUIRED_CHARACTERS = List.of('A', 'I');
    private static Logger logger = LogManager.getLogger(Riddle.class);

    private final List<String> dictionary;
    private final WordsFilter wordsFilter;

    public Riddle(List<String> dictionary, WordsFilter wordsFilter) {
        this.dictionary = dictionary;
        this.wordsFilter = wordsFilter;
    }

    public void solveRiddle() {
        List<String> filteredDictionary = prepareFilteredDictionary();
        solve(filteredDictionary);
    }

    private List<String> prepareFilteredDictionary() {
        logger.info("dictionary size: " + dictionary.size());

        List<String> filteredWords = wordsFilter.getAllWordsWithNLetters(WORD_MAXIMUM_LENGTH);

        logger.info("all " + WORD_MAXIMUM_LENGTH + " letter words: " + filteredWords.size());

        return wordsFilter.getWordsContainingCharactersAtLeastOnce(filteredWords, REQUIRED_CHARACTERS);
    }

    private void solve(List<String> filteredDictionary) {
        logger.info("valid words with 9 letters containing a and i :" + filteredDictionary.size());

        while (!isFilteredDictionaryEmpty(filteredDictionary)) {
            List<String> trimmedWords = wordsFilter.trimWords(filteredDictionary);

            logger.info("trimmed words: " + trimmedWords.size());

            filteredDictionary = wordsFilter.searchInDictionary(dictionary, trimmedWords);

            if (isFilteredDictionaryEmpty(filteredDictionary)) {
                logger.info("No valid words found");
                break;
            }
            logger.info("valid words with " + filteredDictionary.get(0).length()
                    + " letters containing a and i :" + filteredDictionary.size());
        }
    }

    private boolean isFilteredDictionaryEmpty(List<String> filteredDictionary) {
        return filteredDictionary.size() <= 0;
    }

}
