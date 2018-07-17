public class WordChains {

    public Boolean verifyWordsDiff(final String firstWord, final String secondWord, final Integer numDiff){
        if (firstWord==null || secondWord==null || numDiff==null) {
            return null;
        }
        int count = Math.abs(firstWord.length() - secondWord.length());

        for (int i=0; i <(firstWord.length() > secondWord.length() ? secondWord : firstWord).length(); i++) {
            count += firstWord.charAt(i) == secondWord.charAt(i)? 0 : 1;
        }
        return count == numDiff ? true : false;
    }
}
