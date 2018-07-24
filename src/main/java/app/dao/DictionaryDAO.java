package app.dao;

import java.util.List;

public interface DictionaryDAO {
    List<String> getReducedWordList(final String linkToRemoteFile, final String localFileName, final Integer wordLength);
    }
