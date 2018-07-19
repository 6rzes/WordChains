package app.service;

import java.util.List;

public interface ServiceDictionary {
    List<String> getReducedWordList(final String linkToRemoteFile, final String localFileName, final Integer wordLength);
    }
