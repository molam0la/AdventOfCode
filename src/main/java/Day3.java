import java.util.*;

public class Day3 {

    FileLoader f = new FileLoader();
    List<String> lines = new ArrayList<>();
    String filePath = "/Users/sandra/Documents/Dev/AdventOfCode/puzzle3input.txt";

    private int[][] claim = new int[1000][1000];

    public List<String> splitString = new ArrayList<>();
    public Map<Integer, Boolean> clashingIds = new HashMap<>();

    int id;
    int xStart;
    int yStart;
    int xWidth;
    int yHeight;

    List<String> loadInput() {
        lines = f.loadInputFromFile(filePath);
        return lines;
    }


    public int countOverlap(List<String> lines) {

        int count = 0;

        for (String line : lines) {
            String removedSymbols = line.replaceAll("[,:x@]", " ");
            splitString = Arrays.asList(removedSymbols.split("\\s"));
            id = Integer.parseInt(splitString.get(0).replace("#", ""));
            xStart = Integer.parseInt(splitString.get(3));
            yStart = Integer.parseInt(splitString.get(4));
            xWidth = Integer.parseInt(splitString.get(6));
            yHeight = Integer.parseInt(splitString.get(7));

            for (int xOffset = 0; xOffset < xWidth; xOffset++) {
                for (int yOffset = 0; yOffset < yHeight; yOffset++) {
                    int x = xStart + xOffset;
                    int y = yStart + yOffset;

                    int oldValue = claim[x][y];

                    if (oldValue == '\u0000') {
                        claim[x][y] = id;
                    } else if (oldValue != '#') {
                        int clashedId = oldValue;
                        claim[x][y] = '#';
                        count++;
                        clashingIds.put(clashedId, false);
                        clashingIds.put(id, false);
                    }
                }
            }
        }

            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    int c = claim[x][y];
                    if (c != '\u0000') {
                        System.out.print(c);
                    } else {
                        System.out.print('.');
                    }
                }
                System.out.println();
            }
        return count;
    }

    HashSet<Integer> findValidId(List<String> lines) {
        Map<Integer, Boolean> allIds = new HashMap<>();
        HashSet<Integer> validId = new HashSet<>();
        for (String line : lines) {
            String removedSymbols = line.replaceAll("[,:x@]", " ");
            splitString = Arrays.asList(removedSymbols.split("\\s"));
            id = Integer.parseInt(splitString.get(0).replace("#", ""));
            allIds.put(id, true);
            validId = new HashSet<>(clashingIds.keySet());
            validId.addAll(allIds.keySet());
            validId.removeAll(clashingIds.keySet());
        }
        return validId;
    }

    public static void main(String[] args) {
        Day3 d3 = new Day3();
        System.out.println(d3.countOverlap(d3.loadInput()));
        System.out.println(d3.findValidId(d3.loadInput()));
    }
}