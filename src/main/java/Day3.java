import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day3 {

    FileLoader f = new FileLoader();
    List<String> input = new ArrayList<>();
    String filePath = "/Users/sandra/Documents/Dev/AdventOfCode/puzzle3input.txt";

    private char[][] claim = new char[1000][1000];
    int uniqueId;

    private List<String> splitString = new ArrayList<>();

    List<String> loadInput() {
        input = f.loadInputFromFile(filePath);
        return input;
    }

    int countOverlap(List<String> lines) {

        int count = 0;
        int idCount = 0;

        for (String line : lines) {
            String removedSymbols = line.replaceAll("[,:x@]", " ");
            splitString = Arrays.asList(removedSymbols.split("\\s"));
            char id = splitString.get(0).replace("#", "").charAt(0);
            int xStart = Integer.parseInt(splitString.get(3));
            int yStart = Integer.parseInt(splitString.get(4));
            int xWidth = Integer.parseInt(splitString.get(6));
            int yHeight = Integer.parseInt(splitString.get(7));

            for (int xOffset = 0; xOffset < xWidth; xOffset++) {
                for (int yOffset = 0; yOffset < yHeight; yOffset++) {
                    int x = xStart + xOffset;
                    int y = yStart + yOffset;

                    int oldValue = claim[x][y];

                    if (oldValue == '\u0000') {
                        claim[x][y] = id;
                    } else if (oldValue != '#') {
                        claim[x][y] = '#';
                        count++;
                    }

                }
            }
        }

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                char c = claim[x][y];
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

    public static void main(String[] args) {
        Day3 d3 = new Day3();
        System.out.println(d3.countOverlap(d3.loadInput()));
    }
}