package app.controller;

import app.service.ServiceDictionary;
import app.service.ServiceWordChains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
public class ControllerWordChains {

    @Autowired
    private ServiceDictionary serviceDictionary;
    @Autowired
    private ServiceWordChains serviceWordChains;

    private static final String LINK_TO_REMOTE_FILE = "http://codekata.com/data/wordlist.txt";
    private static final String LOCAL_FILE_NAME = "src\\main\\resources\\wordlist.txt";

    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<String> getWordPath(
            @RequestParam(value = "initialWord", required = true) String initialWord,
            @RequestParam(value = "lastWord",required = true) String lastWord) {
        List <String> wordList = serviceDictionary.getReducedWordList(LINK_TO_REMOTE_FILE, LOCAL_FILE_NAME, initialWord.length());
        return serviceWordChains.getPathWay(wordList,initialWord,lastWord);
    }
}
