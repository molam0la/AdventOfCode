import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

public class Day4 {

    private FileLoader f = new FileLoader();

    private String group1 = "([^]]+)";
    private String group2 = "(.*)";

    private String regex = "\\[" + group1 + "]" + group2;
    private Pattern pattern = Pattern.compile(regex);
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private Map<Integer, Long> minutesAsleep = new HashMap<>();


    private int longestSleeperId = 0;
    private int[] mostSleepMinutes = new int[60];
    private Map<Integer, int[]> allGuardsMappedSleep = new HashMap<>();


    public int[] getMostSleepMinutes() {
        return mostSleepMinutes;
    }

    public Map<Integer, int[]> getAllGuardsMappedSleep() {
        return allGuardsMappedSleep;
    }

    private List<String> loadInput() {
        String filePath = "/Users/sandra/Documents/Dev/AdventOfCode/puzzle4input.txt";
        return f.loadInputFromFile(filePath);
    }

    private List<String> sortLines(List<String> lines) {
        Collections.sort(lines);
        return lines;
    }


    Map<Integer, Long> calculateMinutesAsleep(List<String> lines) {

        List<String> sortedLines = sortLines(lines);

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

    int findLongestSleeper(Map<Integer, Long> minutesAsleep) {

        long longestSleep = Collections.max(minutesAsleep.values());

        for (Map.Entry<Integer, Long> sleeper : minutesAsleep.entrySet()) {
            if (sleeper.getValue() == longestSleep) {
                longestSleeperId = sleeper.getKey();
            }
        }
        return longestSleeperId;
    }

    int[] mapSleepMinutes(List<String> lines, int longestSleeperId) {

        List<String> sortedLines = sortLines(lines);
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
                int minuteOfTheHour = fallsAsleep.get(ChronoField.MINUTE_OF_HOUR);

                if (guard == longestSleeperId) {
//                    System.out.println("Longest sleeper" + guard);
                    fillArrayWithSleepMinutes(mostSleepMinutes, minuteOfTheHour, minutes);
                } else {
                    int[] newMostSleepMinutes = new int[60];
                    allGuardsMappedSleep.put(guard, fillArrayWithSleepMinutes(newMostSleepMinutes, minuteOfTheHour, minutes));
                }
            }

        }
        return mostSleepMinutes;
    }

    int[] fillArrayWithSleepMinutes(int[] mostSleepMinutes, int minuteOfTheHour, long minutes) {

        for (int j = 0; j < minutes; j++) {
            int index = minuteOfTheHour + j;
            if (index != 0) {
                index = minuteOfTheHour + j - 1;
            }
            int currentValue = mostSleepMinutes[index];
            mostSleepMinutes[index] = currentValue + 1;
        }
        return mostSleepMinutes;
    }

    int findMostSleptMinute(int[] mostSleepMinutes) {

        int max = mostSleepMinutes[0];
        int index = 0;

        for (int i = 0; i < mostSleepMinutes.length; i++) {
            if (max < mostSleepMinutes[i]) {
                max = mostSleepMinutes[i];
                index = i;
            }
        }
        return index + 1;
    }


    public static void main(String[] args) {

        Day4 d4 = new Day4();
        d4.loadInput();
        int longestSleeper = d4.findLongestSleeper(d4.calculateMinutesAsleep(d4.loadInput()));
        int mostSleptMinute = d4.findMostSleptMinute(d4.mapSleepMinutes(d4.loadInput(), longestSleeper));

        System.out.println("Part A solution " + longestSleeper * mostSleptMinute);


        System.out.println(Arrays.toString(d4.getAllGuardsMappedSleep().get(2521)));
    }
}


