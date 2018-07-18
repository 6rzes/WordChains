import java.util.*;
import java.util.stream.Collectors;

public class WordChains {

    private static final int NEAREST_NODE = 1;

    protected Boolean verifyWordsDiff(final String firstWord, final String secondWord, final Integer numDiff) {
        if (firstWord == null || secondWord == null || numDiff == null) {
            return null;
        }
        int count = Math.abs(firstWord.length() - secondWord.length());

        for (int i = 0; i < (firstWord.length() > secondWord.length() ? secondWord : firstWord).length(); i++) {
            count += firstWord.charAt(i) == secondWord.charAt(i) ? 0 : 1;
        }
        return count == numDiff;
    }

    protected  List<String> getNeighbourNodes(final List<String> wordList, final String wordNode) {
        return wordList.stream().filter(word -> verifyWordsDiff(word, wordNode, NEAREST_NODE)).collect(Collectors.toList());
    }

    protected Map<String, String> getTranstionsMap(final List<String> words, final String initialWord, final String searchedWord) {
        Queue<String> exploredWords = new LinkedList<>();
        Map<String, String> translationMap = new HashMap<>();
        List<String> neighbourNodes;
        String word;
        exploredWords.add(initialWord);

        while (!exploredWords.isEmpty() && !(word = exploredWords.remove()).equals(searchedWord)) {
            neighbourNodes = getNeighbourNodes(words,word);
            for (String neighbour : neighbourNodes) {
                 translationMap.put(neighbour, word);
                 exploredWords.add(neighbour);
            }
        }
        return translationMap;
    }


    protected List<String> getPathWay(final Map<String,String> transitionMap, final String initialWord, final String searchedWord) {
        String node = searchedWord;
        List<String> pathWay = new ArrayList();

        while (!node.equals(initialWord)){
            pathWay.add(0,node);
            node = transitionMap.get(node);
        }
        pathWay.add(0,initialWord);
        return pathWay;
    }

}
