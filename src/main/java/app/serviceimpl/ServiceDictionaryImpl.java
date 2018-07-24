package app.serviceimpl;

import app.daoimpl.DictionaryDAOImpl;
import app.service.ServiceDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceDictionaryImpl implements ServiceDictionary {

    @Autowired
    private DictionaryDAOImpl dictionaryDAO;

    @Override
    public List<String> getReducedWordList(final String linkToRemoteFile, final String localFileName, final Integer wordLength) {
        return dictionaryDAO.getReducedWordList(linkToRemoteFile,localFileName,wordLength);
    }
}
