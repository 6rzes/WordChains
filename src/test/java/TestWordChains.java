import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class TestWordChains {
    Dictionary dictionary;
    WordChains wordChains;
    public static void main(String[] args) {

    }

    @Before
    public void setUp(){
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
        Assert.assertTrue(wordChains.verifyWordsDiff("z","z",0));
        Assert.assertTrue(wordChains.verifyWordsDiff("abc","abc",0));
        Assert.assertTrue(wordChains.verifyWordsDiff("ABC","abc",3));
        Assert.assertTrue(wordChains.verifyWordsDiff("ABC","ABC",0));
        Assert.assertTrue(wordChains.verifyWordsDiff("aaaa","abcd",3));
        Assert.assertTrue(wordChains.verifyWordsDiff("abc123","abcdef",3));
        Assert.assertTrue(wordChains.verifyWordsDiff("","",0));
        Assert.assertTrue(wordChains.verifyWordsDiff(" "," ",0));
        Assert.assertTrue(wordChains.verifyWordsDiff("!@#$%^&*()","zzzzzzzzzz",10));
        Assert.assertTrue(wordChains.verifyWordsDiff("aaazaaa","aaa aaa",1));
        Assert.assertTrue(wordChains.verifyWordsDiff("dog","cat",3));
        Assert.assertTrue(wordChains.verifyWordsDiff(" s"," ",1));
        Assert.assertTrue(wordChains.verifyWordsDiff(""," ",1));

        Assert.assertFalse(wordChains.verifyWordsDiff("abc","abc",1));
        Assert.assertFalse(wordChains.verifyWordsDiff("abc","abcdef",0));
        Assert.assertFalse(wordChains.verifyWordsDiff("www","www",1));
        Assert.assertFalse(wordChains.verifyWordsDiff("abc","ABC",1));
        Assert.assertFalse(wordChains.verifyWordsDiff("abc","abcdef",1));
        Assert.assertFalse(wordChains.verifyWordsDiff(" "," s",2));


    }
}
