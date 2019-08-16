import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public TreeMap<String, List<String>> parseLines (List<String> lines, String regex) {

        TreeMap<String, List<String>> dependencies = new TreeMap<>();


        for (String line : lines) {

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(line);
            matcher.matches();

            List<String> steps = dependencies.get(matcher.group(2));

            if (steps == null) {
                steps = new ArrayList<>();
                dependencies.put(matcher.group(2), steps);
            }
            if (!dependencies.containsKey(matcher.group(1))) {
                dependencies.put(matcher.group(1), new ArrayList<>());
            }

            steps.add(matcher.group(1));
        }
        System.out.println(dependencies);

        return dependencies;
    }
}
