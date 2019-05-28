import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

public class Day4 {

    private FileLoader f = new FileLoader();
    private List<String> lines = new ArrayList<>();
    private String filePath = "/Users/sandra/Documents/Dev/AdventOfCode/puzzle4input.txt";


    private Map<Integer, Long> minutesAsleep = new HashMap<>();

    public Map<Integer, Long> getMinutesAsleep() {
        return minutesAsleep;
    }

    private int longestSleeperId = 0;
    private int [] mostSleepMinutes = new int [60];

    public int[] getMostSleepMinutes() {
        return mostSleepMinutes;
    }

    private List<String> loadInput() {
        lines = f.loadInputFromFile(filePath);
        return lines;
    }

    private List<String> sortLines(List<String> lines) {
        Collections.sort(lines);
        return lines;
    }

    public Map<Integer, Long> calculateMinutesAsleep(List<String> lines) {

        List<String> sortedLines = sortLines(lines);

        String group1 = "([^]]+)";
        String group2 = "(.*)";

        String regex = "\\[" + group1 + "]" + group2;
        Pattern pattern = Pattern.compile(regex);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        int guard = 0;
        LocalDateTime fallsAsleep = null;

        for (String line : sortedLines) {
            Matcher matcher = pattern.matcher(line);
            if (!matcher.matches())
                throw new IllegalStateException(format("Regex %s didn't match line: %s", regex, line));
            if (line.contains("shift")) {
                guard = Integer.parseInt(matcher.group(2).replaceAll("[^\\d.]", ""));
            } else if (line.contains("falls")) {
                fallsAsleep = LocalDateTime.parse(matcher.group(1), dateFormat);
            } else {
                LocalDateTime wakesUp = LocalDateTime.parse(matcher.group(1), dateFormat);

                long minutes = Duration.between(fallsAsleep, wakesUp).toMinutes();
                long previousMinutes = 0;
                if (minutesAsleep.get(guard) != null) {
                    previousMinutes = minutesAsleep.get(guard);
                }
                minutesAsleep.put(guard, previousMinutes + minutes);
            }
        }
        return minutesAsleep;
    }

    public int findLongestSleeper(Map<Integer, Long> minutesAsleep) {

        long longestSleep = Collections.max(minutesAsleep.values());

        for (Map.Entry<Integer, Long> sleeper : minutesAsleep.entrySet()) {
            if (sleeper.getValue() == longestSleep) {
                longestSleeperId = sleeper.getKey();
            }
        }
        return longestSleeperId;
    }

    public static void main(String[] args) {

        Day4 d4 = new Day4();
        System.out.println(d4.findLongestSleeper(d4.calculateMinutesAsleep(d4.loadInput())));

    }
}

