import java.util.List;

public class App {

    public static void main(String[] args) {
        Puzzle1 p1 = new Puzzle1();
        Puzzle2 p2 = new Puzzle2();
        FileLoader f = new FileLoader();

        //Puzzle 1
        p1.loadInput();
        List<String> fileInput = f.loadInputFromFile("/Users/sandra/Documents/Dev/AdventOfCode/puzzle1input.txt");
        List<Integer> fileInputInt = f.convertInputToInterger(fileInput);
//        System.out.println("Answer to puzzle 1: " + p1.findDuplicateSum(fileInputInt));

        //Puzzle 2
        int result = p2.calculateOccurences(p2.loadInput());

        System.out.println("Answer to puzzle 2A: " + result);
        System.out.println("Answer to puzzle 2B: "
                + p2.findCorrectId(p2.input)[0] + " " + p2.findCorrectId(p2.input)[1] + " without letters b & a");


    }
}
