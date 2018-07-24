package app.service;

import java.util.List;

public interface ServiceWordChains {
    List<String> getPathWay(final List<String> wordList, final String initialWord, final String searchedWord);
    }
