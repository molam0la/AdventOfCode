import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Puzzle1 {

    FileLoader f = new FileLoader();
    List<Integer> input = new ArrayList<>();
    String filePath = "/Users/sandra/Documents/Dev/AdventOfCode/puzzle1input.txt";

    List<Integer> loadInput() {
        f.convertInputToInterger(f.loadInputFromFile(filePath));
        return input;
    }

    int findDuplicateSum(List<Integer> list) {
        int sum = 0;
        List<Integer> listOfSums = new ArrayList<>();

        while (true) {
            for (int i : list) {
                sum = sum + i;
                if (listOfSums.contains(sum)) {
                    return sum;
                } else {
                    listOfSums.add(sum);
                }
            }
        }
    }
}
