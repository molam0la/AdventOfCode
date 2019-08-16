import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import static junit.framework.TestCase.assertEquals;

public class Day7Test {

    Day7 d7;
    Regex r;
    List<String> sampleLines;
    String regex;

    @Before
    public void setUp() throws Exception {

        d7 = new Day7();
        r = new Regex();
        regex = "Step ([A-Z]) must be finished before step ([A-Z]) can begin.";


        sampleLines = new ArrayList<>();


        sampleLines.add("Step C must be finished before step A can begin.");
        sampleLines.add("Step C must be finished before step F can begin.");
        sampleLines.add("Step A must be finished before step B can begin.");
        sampleLines.add("Step A must be finished before step D can begin.");
        sampleLines.add("Step B must be finished before step E can begin.");
        sampleLines.add("Step D must be finished before step E can begin.");
        sampleLines.add("Step F must be finished before step E can begin.");

    }

    @Test
    public void parsingLines() {

        assertEquals(5, r.parseLines(sampleLines, regex).size());
    }

    @Test
    public void testSortingLines() {
        TreeMap<String, List<String>> input = r.parseLines(sampleLines, regex);
        assertEquals(Arrays.asList("C", "A", "B", "D", "F", "E"), d7.sortLines(input));

    }

}