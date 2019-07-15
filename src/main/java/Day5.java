import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day5 {

    private ArrayList<Character> polymer;

    public ArrayList<Character> getPolymer() {
        return polymer;
    }

    private FileLoader f = new FileLoader();

    private List<String> loadInput() {
        String filePath = "/Users/sandra/Documents/Dev/AdventOfCode/puzzle5input.txt";
        return f.loadInputFromFile(filePath);
    }

    public ArrayList<Character> makePolymer(List<String> lines) {
        String input = lines.get(0);
        polymer = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            polymer.add(input.charAt(i));
        }
        return polymer;
    }

    public static ArrayList<Character> reactPolymer(ArrayList<Character> polymer) {

        boolean removed;

        do {
            removed = false;
            for (int i = 0; i < polymer.size() - 1; i++) {


                int char0Index = i;
                int char1Index = i + 1;

                char char0 = polymer.get(char0Index);
                char char1 = polymer.get(char1Index);

                if (char0 != char1) {
                    char compChar0 = Character.toUpperCase(char0);
                    char compChar1 = Character.toUpperCase(char1);

                    if (compChar0 == compChar1) {
                        removed = true;
                        polymer.remove(char1Index);
                        polymer.remove(char0Index);
                    }
                }
            }
        }
        while (removed);

        return polymer;
    }

    public HashMap<Character, ArrayList<Character>> removeSameLetterUnits(ArrayList<Character> polymer) {

        HashMap<Character, ArrayList<Character>> unitToPolymerMap = new HashMap<>();

        ArrayList<Character> copy;

        char c;

        for (c = 'a'; c <= 'z'; c++) {
            copy = new ArrayList<>(polymer);

            for (int i = 0; i < copy.size(); i++) {
                    if (c == copy.get(i) || Character.toUpperCase(c) == copy.get(i)) {
                        copy.remove(i);
                        unitToPolymerMap.put(c, copy);
                        i--;
                    }
            }
        }
        return unitToPolymerMap;
    }

    public int reactShortenedPolymer(HashMap<Character, ArrayList<Character>> unitToPolymerMap) {

        int polymerSize = Integer.MAX_VALUE;

        for (ArrayList<Character> reactingPolymer : unitToPolymerMap.values()) {
            int currentPolymerSize = reactPolymer(reactingPolymer).size();
            if (currentPolymerSize < polymerSize) {
                polymerSize = currentPolymerSize;
            }
        }
        return polymerSize;
    }


    public static void main(String[] args) {
        Day5 d5 = new Day5();
        d5.reactPolymer(d5.makePolymer(d5.loadInput()));
        System.out.println("Answer to part A is : " + d5.getPolymer().size());
        int answerB = d5.reactShortenedPolymer(d5.removeSameLetterUnits(d5.getPolymer()));
        System.out.println("Answer to part B is : " + answerB);

    }
}

