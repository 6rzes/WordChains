package app.daoImpl;

import app.dao.DictionaryDAO;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class DictionaryDAOImpl implements DictionaryDAO {

    private void getRemoteFile(final String linkToRemoteFile, final String localFilename) {
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

    public List<String> getReducedWordList(final String linkToRemoteFile, final String localFileName, final Integer wordLength) {
        getRemoteFile(linkToRemoteFile,localFileName);
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
