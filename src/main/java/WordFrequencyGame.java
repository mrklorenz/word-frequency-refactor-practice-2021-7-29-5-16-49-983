import java.util.*;

public class WordFrequencyGame {

    public static final String WHITE_SPACES = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";


    public String getResult(String sentence) {
        if (isInputOneWord(sentence)) {
            return sentence + " 1";
        }
        try {
            List<WordInfo> wordInfoList = generateWordInfoList(sentence);
            Map<String, List<WordInfo>> wordInfoListMap = getMapOfWordInfoList(wordInfoList);

            wordInfoList = calculateWordFrequency(wordInfoListMap);

            wordInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

            StringJoiner joiner = new StringJoiner("\n");
            for (WordInfo w : wordInfoList) {
                String s = w.getValue() + " " + w.getWordCount();
                joiner.add(s);
            }
            return joiner.toString();
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }

    }

    public List<WordInfo> calculateWordFrequency(Map<String, List<WordInfo>> wordInfoListMap){
        List<WordInfo> wordInfoList = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> listOfWordInfo : wordInfoListMap.entrySet()) {
            WordInfo wordInfo = new WordInfo(listOfWordInfo.getKey(), listOfWordInfo.getValue().size());
            wordInfoList.add(wordInfo);
        }
        return wordInfoList;
    }


    private Map<String, List<WordInfo>> getMapOfWordInfoList(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
            if (!map.containsKey(wordInfo.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            } else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return map;
    }

    public boolean isInputOneWord(String sentence) {
        return sentence.split(WHITE_SPACES).length == 1;
    }

    public List<WordInfo> generateWordInfoList(String sentence) {
        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String word : sentence.split(WHITE_SPACES)) {
            WordInfo wordInfo = new WordInfo(word, 1);
            wordInfoList.add(wordInfo);
        }
        return wordInfoList;
    }

}
