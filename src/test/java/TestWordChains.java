import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TestWordChains {
    Dictionary dictionary;
    WordChains wordChains;

    @Before
    public void setUp() {
        dictionary = new Dictionary();
        wordChains = new WordChains();
    }

    @Test
    public void testDownloadFile() {
        dictionary.getRemoteFile("http://codekata.com/data/wordlist.txt", "src\\main\\resources\\wordlist.txt");
        File localFile = new File("src\\main\\resources\\wordlist.txt");
        Assert.assertTrue(localFile.exists());
    }

    @Test
    public void testWordsDiff() {
        Assert.assertTrue(wordChains.verifyWordsDiff("z", "z", 0));
        Assert.assertTrue(wordChains.verifyWordsDiff("abc", "abc", 0));
        Assert.assertTrue(wordChains.verifyWordsDiff("ABC", "abc", 3));
        Assert.assertTrue(wordChains.verifyWordsDiff("ABC", "ABC", 0));
        Assert.assertTrue(wordChains.verifyWordsDiff("aaaa", "abcd", 3));
        Assert.assertTrue(wordChains.verifyWordsDiff("abc123", "abcdef", 3));
        Assert.assertTrue(wordChains.verifyWordsDiff("", "", 0));
        Assert.assertTrue(wordChains.verifyWordsDiff(" ", " ", 0));
        Assert.assertTrue(wordChains.verifyWordsDiff("!@#$%^&*()", "zzzzzzzzzz", 10));
        Assert.assertTrue(wordChains.verifyWordsDiff("aaazaaa", "aaa aaa", 1));
        Assert.assertTrue(wordChains.verifyWordsDiff("dog", "cat", 3));
        Assert.assertTrue(wordChains.verifyWordsDiff(" s", " ", 1));
        Assert.assertTrue(wordChains.verifyWordsDiff("", " ", 1));

        Assert.assertFalse(wordChains.verifyWordsDiff("abc", "abc", 1));
        Assert.assertFalse(wordChains.verifyWordsDiff("abc", "abcdef", 0));
        Assert.assertFalse(wordChains.verifyWordsDiff("www", "www", 1));
        Assert.assertFalse(wordChains.verifyWordsDiff("abc", "ABC", 1));
        Assert.assertFalse(wordChains.verifyWordsDiff("abc", "abcdef", 1));
        Assert.assertFalse(wordChains.verifyWordsDiff(" ", " s", 2));
    }

    @Test
    public void testGetReducedWordList() {
        Assert.assertEquals(dictionary.getReducedWordList("src\\main\\resources\\wordlist.txt", 1).size(), 52);
        Assert.assertEquals(dictionary.getReducedWordList("src\\main\\resources\\wordlist.txt", 3).size(), 2412);
        Assert.assertEquals(dictionary.getReducedWordList("src\\main\\resources\\wordlist.txt", 10).size(), 43229);
    }

    @Test
    public void testGetNextNodes() {
        List<String> nodesList = Arrays.asList("one", "two", "twx", "three", "ttt", "otw", "iwo", "a", "b",
                "thraa", " ", "^&%", "^&%", "^&#");

        List<String> nextNodesList = Arrays.asList("twx", "iwo");
        Assert.assertEquals(nextNodesList, wordChains.getNeighbourNodes(nodesList, "two"));

        nextNodesList = Arrays.asList("^&#");
        Assert.assertEquals(nextNodesList, wordChains.getNeighbourNodes(nodesList, "^&%"));
        nextNodesList = Arrays.asList("a", "b");
        Assert.assertEquals(nextNodesList, wordChains.getNeighbourNodes(nodesList, " "));
        nextNodesList = Arrays.asList("a", " ");
        Assert.assertEquals(nextNodesList, wordChains.getNeighbourNodes(nodesList, "b"));

        Assert.assertEquals(new ArrayList<>(), wordChains.getNeighbourNodes(nodesList, "ttt"));
        Assert.assertEquals(new ArrayList<>(), wordChains.getNeighbourNodes(nodesList, "one"));
        Assert.assertEquals(new ArrayList<>(), wordChains.getNeighbourNodes(nodesList, "nonExisting"));
    }

    @Test
    public void testGetTransitionMap() {
        List<String> nodesList = Arrays.asList("one", "two", "twx", "three", "ttt", "otw", "iwo", "a", "b",
                "thraa", " ", "^&%", "^&%", "^&#", "owx");

        Map<String, String> expectedMap = Map.of("iwo","two", "owx", "twx", "twx", "two", "two", "iwo");
        Map transitionMap = wordChains.getTranstionsMap(nodesList, "two", "owx");
        Assert.assertEquals(expectedMap,transitionMap);
    }

    @Test
    public void testGetPathWay() {
        Map<String, String> trasintionMap = Map.of("iwo","two", "owx", "twx", "twx", "two", "two", "iwo");
        List<String> expectedPath = Arrays.asList("two", "twx", "owx");
        Assert.assertEquals(expectedPath, wordChains.getPathWay(trasintionMap,"two","owx"));
    }
}
