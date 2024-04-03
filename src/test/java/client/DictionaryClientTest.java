
package client;

import java.util.List;

import org.example.client.DictionaryClient;
import org.example.exception.FailedToReadWordsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DictionaryClientTest {
    private static final String DICTIONARY_URL = "https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt";

    private DictionaryClient dictionaryClient;

    @BeforeEach
    public void setUp() {
        dictionaryClient = new DictionaryClient(DICTIONARY_URL);
    }

    @Test
    public void getDictionaryTest_SuccessfulGet() throws FailedToReadWordsException {
        List<String> dictionary = dictionaryClient.getDictionary();

        assertThat(dictionary, is(notNullValue()));
        assertThat(dictionary, is(not(empty())));
    }
    
}