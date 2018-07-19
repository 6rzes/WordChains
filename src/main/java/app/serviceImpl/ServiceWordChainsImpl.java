package app.serviceImpl;

import app.model.ModelWordChains;
import app.service.ServiceWordChains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ServiceWordChainsImpl implements ServiceWordChains {

    @Autowired
    private ModelWordChains modelWordChains;

    @Override
    public Map<String, String> getTranstionsMap(List<String> words, String initialWord, String searchedWord) {
        return modelWordChains.getTranstionsMap(words,initialWord,searchedWord);
    }

    @Override
    public List<String> getPathWayFromTransition(Map<String, String> transitionMap, String initialWord, String searchedWord) {
        return modelWordChains.getPathWayFromTransition(transitionMap,initialWord,searchedWord);
    }

    @Override
    public List<String> getPathWay(List<String> wordList, String initialWord, String searchedWord) {
        return modelWordChains.getPathWay(wordList,initialWord,searchedWord);
    }
}
