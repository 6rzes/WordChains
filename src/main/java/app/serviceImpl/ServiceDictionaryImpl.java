package app.serviceImpl;

import app.dao.DictionaryDAO;
import app.service.ServiceDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceDictionaryImpl implements ServiceDictionary {

    @Autowired
    private DictionaryDAO dictionaryDAO;

    @Override
    public List<String> getReducedWordList(final String linkToRemoteFile, final String localFileName, final Integer wordLength) {
        return dictionaryDAO.getReducedWordList(linkToRemoteFile,localFileName,wordLength);
    }
}
