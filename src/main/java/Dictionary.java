import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {

    protected void getRemoteFile(final String linkToRemoteFile, final String localFilename) {
        URL remoteFileURL = null;
        try {
            remoteFileURL = new URL(linkToRemoteFile);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        File localFile = new File(localFilename);
        if (!localFile.exists()) {
            try {
                FileUtils.copyURLToFile(remoteFileURL, localFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected List<String> getReducedWordList(final String localFileName, final Integer wordLength) {
        List<String> wordList = new ArrayList<>();

        File localFile = new File(localFileName);
        try {
            wordList = FileUtils.readLines(localFile, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        wordList.removeIf(word -> word.length() != wordLength);
        return wordList;
    }
}
