import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Puzzle2 {

    FileLoader f = new FileLoader();
    List<String> input = new ArrayList<>();
    HashMap<Character, Integer> charHashMap = new HashMap<>();
    String filePath = "/Users/sandra/Documents/Dev/AdventOfCode/puzzle2input.txt";

    List<String> loadInput() {
        input = f.loadInputFromFile(filePath);
        return input;
    }

    HashMap<Character, Integer> countLetterInstances(String id) {

        char[] idCharArray = id.toCharArray();
        HashMap<Character, Integer> charHashMap = new HashMap<>();

        for (char letter : idCharArray) {
            if (!charHashMap.containsKey(letter)) {
                charHashMap.put(letter, 1);
            } else {
                charHashMap.put(letter, charHashMap.get(letter) + 1);
            }
        }
        return charHashMap;
    }

    Map<Character, Integer> findDuplicateValues(HashMap<Character, Integer> hm) {
        Map<Character, Integer> hmWithDuplicates = null;
        if (hm.containsValue(2) || hm.containsValue(3)) {
            hmWithDuplicates = hm.entrySet().stream()
                    .filter(characterIntegerEntry -> characterIntegerEntry.getValue() >= 2)
                    .collect(Collectors.toMap(o -> o.getKey(), o -> o.getValue()));
        }
        return hmWithDuplicates;
    }

    Integer calculateOccurences(List<String> input) {
        int appearsTwice = 0;
        int appearsThreeTimes = 0;
        int result = 0;

        for (String id : input) {
            HashMap<Character, Integer> hm = countLetterInstances(id);
            if (hm.containsValue(2)) {
                appearsTwice++;
            }
            if (hm.containsValue(3)) {
                appearsThreeTimes++;
            }
            result = appearsTwice * appearsThreeTimes;
        }
        return result;
    }
}
