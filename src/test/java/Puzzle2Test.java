import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Puzzle2Test {

    Puzzle2 p2;

    @Before
    public void setUp() throws Exception {
        p2 = new Puzzle2();
    }

    @Test
    public void testLoadInput() {
        p2.loadInput();
        assertTrue(p2.loadInput().contains("uqyoeizfhmbrstppbnhacjxwld"));
    }

    @Test
    public void testCountLetterInstancesInId() {
        String id = "sandra";
        System.out.println(p2.countLetterInstances(id));
//      assertThat("HashMap cointains values: ", p2)
    }

    @Test
    public void testCountLetterInInput() {
        String id = p2.loadInput().get(2);
        System.out.println(p2.countLetterInstances(id));
    }

    @Test
    public void testFindingDuplicates() {
        String id = p2.loadInput().get(2);
        HashMap<Character, Integer> hm = p2.countLetterInstances(id);
        System.out.println(p2.findDuplicateValues(hm));
    }

    @Test
    public void testFindingDuplicatesFromInputFile() {

        List<String> input = p2.loadInput();
        for (String id : input) {
            System.out.println("ID " + id);
            System.out.println(p2.findDuplicateValues(p2.countLetterInstances(id)));
        }
    }

    @Test
    public void testAddingResults() {
        List<String> sampleIds = new ArrayList<>();
        sampleIds.add("abcdef");
        sampleIds.add("bababc");
        sampleIds.add("abbcde");
        sampleIds.add("abcccd");
        sampleIds.add("aabcdd");
        sampleIds.add("abcdee");
        sampleIds.add("ababab");

        int result = p2.calculateOccurences(sampleIds);
        assertEquals(12, result);
    }

    @Test
    public void testInputResults() {
        List<String> input = p2.loadInput();
        int result = p2.calculateOccurences(input);
        System.out.println(result);
    }
}
