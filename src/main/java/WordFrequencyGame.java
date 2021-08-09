import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String WHITE_SPACES = "\\s+";
    public static final String CALCULATE_ERROR_MSG = "Calculate Error";

    public String getResult(String sentence) {
        if (isInputOneWord(sentence)) {
            return sentence + " 1";
        }
        try {
            List<WordInfo> wordInfoList = generateWordInfoList(sentence);
            Map<String, List<WordInfo>> wordInfoListMap = getMapOfWordInfoList(wordInfoList);

            wordInfoList = calculateWordFrequency(wordInfoListMap);
            sortWordInfoListByCount(wordInfoList);

            return joinWordInfoList(wordInfoList);
        } catch (Exception e) {
            return CALCULATE_ERROR_MSG;
        }
    }

    public String joinWordInfoList(List<WordInfo> wordInfoList) {
        StringJoiner result = new StringJoiner("\n");
        wordInfoList
                .stream()
                .map(wordInfo -> wordInfo.getWordValue() + " " + wordInfo.getWordCount())
                .forEach(result::add);
        return result.toString();
    }

    public void sortWordInfoListByCount(List<WordInfo> wordInfoList) {
        wordInfoList.sort((firstWord, nextWord) -> nextWord.getWordCount() - firstWord.getWordCount());
    }

    public List<WordInfo> calculateWordFrequency(Map<String, List<WordInfo>> wordInfoListMap) {
        return wordInfoListMap
                .entrySet()
                .stream()
                .map(listOfWordInfo -> new WordInfo(listOfWordInfo.getKey(), listOfWordInfo.getValue().size()))
                .collect(Collectors.toList());
    }

    public boolean isInputOneWord(String sentence) {
        return sentence.split(WHITE_SPACES).length == 1;
    }

    public List<WordInfo> generateWordInfoList(String sentence) {
        return Arrays.stream(sentence.split(WHITE_SPACES))
                .map(word -> new WordInfo(word, 1)).collect(Collectors.toList());
    }

    private Map<String, List<WordInfo>> getMapOfWordInfoList(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> wordInfoListMap = new HashMap<>();

        for (WordInfo wordInfo : wordInfoList) {
            if (!wordInfoListMap.containsKey(wordInfo.getWordValue())) {
                List<WordInfo> wordInfos = new ArrayList<>();
                wordInfos.add(wordInfo);
                wordInfoListMap.put(wordInfo.getWordValue(), wordInfos);
            } else {
                wordInfoListMap.get(wordInfo.getWordValue()).add(wordInfo);
            }
        }
        return wordInfoListMap;
    }
}
