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
    }

    @Test
    public void testMakingGrid() {
        grid.load(sampleCoordinates);
        assertEquals(9, grid.getGrid().length);
    }

    @Test
    public void testDrawingCoordinates() {
//        grid.createGrid(sampleCoordinates);
//        grid.loadCoordinates(sampleCoordinates);
    }

    @Test
    public void testManhattanDistance() {
        grid.load(sampleCoordinates);
        grid.calculateManhattanDistance();
        grid.countFinitePointsArea();

    }
}
