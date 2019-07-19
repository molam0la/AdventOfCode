import java.util.List;

public class Day6 {

    FileLoader f = new FileLoader();

    public List<String> loadInput() {
        String filePath = "/Users/sandra/Documents/Dev/AdventOfCode/puzzle6input.txt";
        return f.loadInputFromFile(filePath);
    }

    public static void main(String[] args) {
        Day6 d6 = new Day6();
        Grid grid = new Grid();
        List<String> input = d6.loadInput();
        grid.load(input);
        grid.calculateManhattanDistance();

        System.out.println("Answer to part A is " + grid.countFinitePointsArea());
    }

}
