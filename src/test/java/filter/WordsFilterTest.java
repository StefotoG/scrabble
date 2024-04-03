package filter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.List;

import org.example.filter.WordsFilter;

public class WordsFilterTest {
    private static final String WORD_WITH_W = "STARWORT";
    private static final List<String> DICTIONARY = Arrays.asList("A", "B", "C", "STARTLING", WORD_WITH_W, "STARTUPS", "STARVERS", "STARVING");
    
    private WordsFilter wordsFilter;

    @BeforeEach
    public void setUp() {
        wordsFilter = new WordsFilter(DICTIONARY);
    }

    @Test
    public void getAllWordsWithNLettersTest_getSingleCharWords() {
        List<String> actualResult = wordsFilter.getAllWordsWithNLetters(1);
       
        List<String> expectedResult = Arrays.asList("A", "B", "C");

        assertThat(actualResult, containsInAnyOrder(expectedResult.toArray()));
    }

    @Test
    public void getWordsContainingCharactersAtLeastOnceTest_getWordsContainingW() {
        List<Character> requiredCharacters = Arrays.asList('W');

        List<String> actualResult = wordsFilter.getWordsContainingCharactersAtLeastOnce(DICTIONARY, requiredCharacters);

        List<String> expectedResult = Arrays.asList(WORD_WITH_W);
        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void trimWordsTest_trimFirstLetterInString() {
        List<String> actualResult = wordsFilter.trimWords(Arrays.asList(WORD_WITH_W));

        String expectedWord = WORD_WITH_W.substring(1);
        List<String> expectedResult = Arrays.asList(expectedWord);

        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void searchInDictionaryTest() {
        List<String> expectedResult = Arrays.asList(WORD_WITH_W);

        List<String> actualResult = wordsFilter.searchInDictionary(DICTIONARY, expectedResult);

        assertThat(actualResult, is(expectedResult));
    }
    
}
