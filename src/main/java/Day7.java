import java.util.*;

public class Day7 {

    FileLoader f = new FileLoader();
    Regex r = new Regex();

    public List<String> loadInput() {
        String filePath = "/Users/sandra/Documents/Dev/AdventOfCode/puzzle7input.txt";
        return f.loadInputFromFile(filePath);
    }

    public List<String> sortLines(TreeMap<String, List<String>> stepsToDependencies) {
        List<String> processedSteps = new ArrayList<>();

        while (!stepsToDependencies.isEmpty()) {
//            System.out.println(processedSteps);
//            System.out.println(stepsToDependencies);

            Map.Entry<String, List<String>> availableStep = stepsToDependencies.entrySet().stream()
                    .filter(stepToDependencies -> allDependenciesCompleted(processedSteps, stepToDependencies.getValue()))
                    .findFirst()
                    .get();

            processedSteps.add(availableStep.getKey());

            stepsToDependencies.remove(availableStep.getKey());
        }

        processedSteps.forEach(System.out::print);

        return processedSteps;
    }

    private Boolean allDependenciesCompleted(List<String> processedSteps, List<String> dependencies) {
        return processedSteps.containsAll(dependencies);
    }


    public static void main(String[] args) {
        Day7 d7 = new Day7();
        Regex r = new Regex();
        String regex = "Step ([A-Z]) must be finished before step ([A-Z]) can begin.";
        List<String> input = d7.loadInput();
        TreeMap<String, List<String>> regexInput = r.parseLines(input, regex);
        d7.sortLines(regexInput);
    }

}
