import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

public class Day4 {

    FileLoader f = new FileLoader();
    List<String> lines = new ArrayList<>();
    String filePath = "/Users/sandra/Documents/Dev/AdventOfCode/puzzle4input.txt";
    List<String> splitLines = new ArrayList<>();

    public class Guard {

        private int guardId;
        private Date timeStamp;
        private String state;
        private int minutesAwake;
        private int minutesAsleep;

        public Guard(int guardId, Date timeStamp, String state) {
            this.guardId = guardId;
            this.timeStamp = timeStamp;
            this.state = state;
        }

        public int getGuardId() {
            return guardId;
        }

        public void setGuardId(int guardId) {
            this.guardId = guardId;
        }

        public Date getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(Date timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getMinutesAwake() {
            return minutesAwake;
        }

        public void setMinutesAwake(int minutesAwake) {
            this.minutesAwake = minutesAwake;
        }

        public int getMinutesAsleep() {
            return minutesAsleep;
        }

        public void setMinutesAsleep(int minutesAsleep) {
            this.minutesAsleep = minutesAsleep;
        }
    }

    List<String> loadInput() {
        lines = f.loadInputFromFile(filePath);
        return lines;
    }

    List<String> sortLines(List<String> lines) {
        Collections.sort(lines);
        return lines;
    }

    List<String> splitLines(List<String> lines) {

        // - Matches every character except ] and expects to find 1 or more characters
        // - The square bracket format is powerful as you can do [ A-Za-z0-9\\-] to match any upper or lowercase letter,
        //     number, space or dash, for example. The \\s are an "escape character" so that Java knows you mean the exact
        //     character - not a range (like between A-Z).
        // - The ()s denote a group in regex
        String group1 = "([^]]+)";

        // Matches any character and expects to find 0 or more characters
        String group2 = "(.*)";

        String regex = "\\[" + group1 + "]" + group2;
        Pattern pattern = Pattern.compile(regex); // Compiles our rexeg into a Pattern which can be used to create multiple Matchers

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (!matcher.matches()) throw new IllegalStateException(format("Regex %s didn't match line: %s", regex, line));

        }
        return splitLines;
    }


    public static void main(String[] args) {

    }
}
