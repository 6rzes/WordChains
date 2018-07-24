package app.dao;

import app.daoimpl.DictionaryDAOImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class DictionaryDAOTest {
    private  static final String REMOTE_FILE_LINK = "http://codekata.com/data/wordlist.txt";
    private static final String LOCAL_FILE_NAME = "wordList.txt";
    private static final int WORD_LENGTH_1 = 1;
    private static final int WORD_LENGTH_3 = 3;
    private static final int WORD_LENGTH_10 = 10;
    private static final int NUMBER_OF_WORDS_LENGTH_1 = 52;
    private static final int NUMBER_OF_WORDS_LENGTH_3 = 2412;
    private static final int NUMBER_OF_WORDS_LENGTH_10 = 43229;

    private  static DictionaryDAOImpl dictionaryDAO;

    @BeforeAll
    static void setUp() {
        dictionaryDAO = new DictionaryDAOImpl();
    }

    @Test
    void getReducedWordList() {
        Assert.assertEquals(NUMBER_OF_WORDS_LENGTH_1,dictionaryDAO
                .getReducedWordList(REMOTE_FILE_LINK, LOCAL_FILE_NAME, WORD_LENGTH_1).size());
        Assert.assertEquals(NUMBER_OF_WORDS_LENGTH_3, dictionaryDAO
                .getReducedWordList(REMOTE_FILE_LINK, LOCAL_FILE_NAME, WORD_LENGTH_3).size());
        Assert.assertEquals(NUMBER_OF_WORDS_LENGTH_10, dictionaryDAO
                .getReducedWordList(REMOTE_FILE_LINK, LOCAL_FILE_NAME, WORD_LENGTH_10).size());
    }
}