import java.util.ArrayList;
import java.util.List;

public class Day1 {

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

    public static void main(String[] args) {
        Day1 d1 = new Day1();
        FileLoader f = new FileLoader();
        d1.loadInput();
        List<String> fileInput = f.loadInputFromFile("/Users/sandra/Documents/Dev/AdventOfCode/puzzle1input.txt");
        List<Integer> fileInputInt = f.convertInputToInterger(fileInput);
        System.out.println("Answer to puzzle 1: " + d1.findDuplicateSum(fileInputInt));
    }
}
