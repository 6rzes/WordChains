package app.controller;

import app.service.ServiceDictionary;
import app.service.ServiceWordChains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;
import java.util.List;

@Controller
@PropertySource("classpath:config.properties")
public class ControllerWordChains {

    @Autowired
    Environment env;
    @Autowired
    private ServiceDictionary serviceDictionary;
    @Autowired
    private ServiceWordChains serviceWordChains;

    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    String getWordPath(
            @RequestParam(value = "initialWord") String initialWord,
            @RequestParam(value = "lastWord") String lastWord) {

        if (initialWord.equals("") || lastWord.equals("")) {
            return MessageFormat.format(env.getProperty("invalidDataNoWord"), initialWord, lastWord);
        }

        if (initialWord.length() != lastWord.length()) {
            return MessageFormat.format(env.getProperty("invalidDataWordsLength"), initialWord, lastWord);
        }

        List<String> wordList = serviceDictionary.getReducedWordList(
                env.getProperty("remoteFileLink"),env.getProperty("localFileName"), initialWord.length());

        if (wordList == null || wordList.isEmpty()) {
            return MessageFormat.format(env.getProperty("invalidDataWordNotFound"), initialWord, lastWord);
        }

        List<String> pathWay = serviceWordChains.getPathWay(wordList, initialWord, lastWord);
        if(pathWay == null || pathWay.isEmpty()) {
            return MessageFormat.format(env.getProperty("invalidDataPathNotFound"), initialWord, lastWord);
        }

        return serviceWordChains.getPathWay(wordList, initialWord, lastWord).toString();
    }
}
