import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day4Test {

    Day4 d4;
    List<String> sampleEntries;

    @Before
    public void setUp() throws Exception {

        d4 = new Day4();
        sampleEntries = new ArrayList<>();

        sampleEntries.add("[1518-11-01 00:00]Guard #10 begins shift");
        sampleEntries.add("[1518-11-01 00:05]falls asleep");
        sampleEntries.add("[1518-11-01 00:25]wakes up");
        sampleEntries.add("[1518-11-01 00:30]falls asleep");
        sampleEntries.add("[1518-11-01 00:55]wakes up");
        sampleEntries.add("[1518-11-01 23:58]Guard #99 begins shift");
        sampleEntries.add("[1518-11-02 00:40]falls asleep");
        sampleEntries.add("[1518-11-02 00:50]wakes up");
        sampleEntries.add("[1518-11-03 00:05]Guard #10 begins shift");
        sampleEntries.add("[1518-11-03 00:24]falls asleep");
        sampleEntries.add("[1518-11-03 00:29]wakes up");
        sampleEntries.add("[1518-11-04 00:02]Guard #99 begins shift");
        sampleEntries.add("[1518-11-04 00:36]falls asleep");
        sampleEntries.add("[1518-11-04 00:46]wakes up");
        sampleEntries.add("[1518-11-05 00:03]Guard #99 begins shift");
        sampleEntries.add("[1518-11-05 00:45]falls asleep");
        sampleEntries.add("[1518-11-05 00:55]wakes up");

    }

    @Test
    public void testSplittingLines() {
        assertEquals(new Long(30), d4.calculateMinutesAsleep(sampleEntries).get(99));
    }

    @Test
    public void findLongestSleeper() {
        assertEquals(10, d4.findLongestSleeper(d4.calculateMinutesAsleep(sampleEntries)));
    }

    @Test
    public void checkMinutesInArray() {
        d4.calculateMinutesAsleep(sampleEntries);
        d4.mapSleepMinutes(sampleEntries, 10);
        System.out.println(Arrays.toString(d4.getMostSleepMinutes()));
    }

    @Test
    public void findMostSleptMinute() {
        d4.calculateMinutesAsleep(sampleEntries);
        d4.mapSleepMinutes(sampleEntries, 10);
        assertEquals(24, d4.findMostSleptMinute(d4.mapSleepMinutes(sampleEntries, 10)));
    }

    @Test
    public void checkAllGuardsSleepingPattern() {
        d4.mapSleepMinutes(sampleEntries, 10);
        System.out.println(d4.getAllGuardsMappedSleep().get(99));
//        assertEquals(3, d4.getAllGuardsMappedSleep().get(99)[44]-1);
    }

    @Test
    public void checkAllGuardsMostSleptMinute() {

    }
}