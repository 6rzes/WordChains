package app.service;

import java.util.List;
import java.util.Map;

public interface ServiceWordChains {
    Map<String, String> getTranstionsMap(final List<String> words, final String initialWord, final String searchedWord);
    List<String> getPathWayFromTransition(final Map<String,String> transitionMap, final String initialWord, final String searchedWord);
    List<String> getPathWay(final List<String> wordList, final String initialWord, final String searchedWord);
    }
