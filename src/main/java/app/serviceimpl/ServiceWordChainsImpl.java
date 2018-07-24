package app.serviceimpl;

import app.model.ModelWordChains;
import app.service.ServiceWordChains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceWordChainsImpl implements ServiceWordChains {

    @Autowired
    private ModelWordChains modelWordChains;

    @Override
    public List<String> getPathWay(List<String> wordList, String initialWord, String searchedWord) {
        return modelWordChains.getPathWay(wordList,initialWord,searchedWord);
    }
}
