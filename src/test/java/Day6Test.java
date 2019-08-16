import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day6Test {


    Day6 d6;
    Grid grid;
    List<String> sampleCoordinates = new ArrayList<>();

    @Before
    public void setUp() {

        d6 = new Day6();
        grid = new Grid();

        sampleCoordinates.add("1,,1");
        sampleCoordinates.add("1,,6");
        sampleCoordinates.add("8,,3");
        sampleCoordinates.add("3,,4");
        sampleCoordinates.add("5,,5");
        sampleCoordinates.add("8,,9");

        grid.load(sampleCoordinates);

    }

    @Test
    public void testMakingGrid() {
        assertEquals(11, grid.getGrid().length);
    }

    @Test
    public void testFindingLargestArea() {
        grid.calculateManhattanDistance();
        assertEquals(17, grid.countFinitePointsArea());
    }

    @Test
    public void testManhattanDistanceForRegion() {
        assertEquals(16, grid.findSafeRegion(32));


    }
}
