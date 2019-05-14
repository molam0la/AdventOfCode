import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Puzzle1Test {

    Puzzle1 p1;
    FileLoader f;

    @Before
    public void setUp() throws Exception {
        p1 = new Puzzle1();
        f = new FileLoader();
    }

    @Test
    public void convertingStringListToInt() {
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("+1");
        listOfStrings.add("-2");
        listOfStrings.add("-5");
        List<Integer> listOfInts = f.convertInputToInterger(listOfStrings);
        assertTrue(listOfInts.contains(-5));
    }

    @Test
    public void testFindingDuplicate() {

        List<Integer> list1 = new ArrayList<>();
        list1.add(Integer.parseInt("+3"));
        list1.add(+3);
        list1.add(+4);
        list1.add(-2);
        list1.add(-4);
        assertEquals(10, p1.findDuplicateSum(list1));
    }

    @Test
    public void puzzleSolution() {
        p1.loadInput();
        List<String> fileInput = f.loadInputFromFile("/Users/sandra/Documents/Dev/AdventOfCode/puzzle1input.txt");
        List<Integer> fileInputInt = f.convertInputToInterger(fileInput);
        System.out.println(p1.findDuplicateSum(fileInputInt));
        //709
    }
}

