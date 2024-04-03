package org.example.filter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class WordsFilter {

    private final List<String> dictionary;


    public WordsFilter(List<String> dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> getAllWordsWithNLetters(int length) {
        return dictionary.stream()
                .filter(word -> word.length() == length)
                .collect(Collectors.toList());
    }

    public List<String> getWordsContainingCharactersAtLeastOnce(List<String> words,
            List<Character> requiredCharacters) {
        return words.stream()
                .filter(word -> {
                    for (Character requiredCharacter : requiredCharacters) {
                        if (word.contains(requiredCharacter.toString())) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    public List<String> trimWords(List<String> wordsToTrim) {
        return wordsToTrim.stream()
                .map(this::trimWord)
                .filter(word -> word.length() > 0)
                .collect(Collectors.toList());
    }

    public List<String> searchInDictionary(List<String> dictionary, List<String> trimmedWords) {
        Set<String> wordSet = new HashSet<>(dictionary);
        return trimmedWords.stream()
                .filter(wordSet::contains)
                .collect(Collectors.toList());
    }

    private String trimWord(String word) {
        int index = 0;

        while (index < word.length() && letterIsAorI(word.charAt(index))) {
            index++;
        }

        String trimmedWord;

        if (index < word.length()) {
            StringBuilder sb = new StringBuilder(word);
            trimmedWord = sb.deleteCharAt(index).toString();
        } else if (word.length() == 2) {
            StringBuilder sb = new StringBuilder(word);
            trimmedWord = sb.deleteCharAt(0).toString();
        } else {
            trimmedWord = word;
        }

        return trimmedWord;
    }

    private boolean letterIsAorI(char charAt) {
        return charAt == 'I' || charAt == 'A';
    }

}
