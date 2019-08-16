import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Grid {

    static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {

            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private int[][] grid;
    private HashMap<Integer, Point> idsAndCoordinatesHashMap = new HashMap<>();
    private int gridSize = 0;

    public int[][] getGrid() {
        return grid;
    }

    public void load(List<String> lines) {
        createGrid(lines);
        loadCoordinates(lines);
    }

    private int[][] createGrid(List<String> lines) {

        int x;
        int y;

        for (String line : lines) {

            ArrayList<Integer> gridSizeFinder = new ArrayList<>();

            String removedSymbols = line.replaceAll("[,:x@]", " ");
            List<String> splitString = Arrays.asList(removedSymbols.split("\\s"));
            x = Integer.parseInt(splitString.get(0));
            y = Integer.parseInt(splitString.get(2));

            gridSizeFinder.add(x);
            gridSizeFinder.add(y);

            int max = 0;

            for (int i = 0; i < gridSizeFinder.size(); i++) {

                if (gridSizeFinder.get(i) > gridSize) {
                    gridSize = gridSizeFinder.get(i) + 2;
                }
            }

            grid = new int[gridSize][gridSize];

        }
        return grid;
    }


    private void loadCoordinates(List<String> lines) {

        int id = 1;

        for (String line : lines) {
            int x;
            int y;

            String removedSymbols = line.replaceAll("[,:x@]", " ");
            List<String> splitString = Arrays.asList(removedSymbols.split("\\s"));
            x = Integer.parseInt(splitString.get(0));
            y = Integer.parseInt(splitString.get(2));

            idsAndCoordinatesHashMap.put(id, new Point(x, y));

            grid[x][y] = id;
            id++;

        }
    }

    public void drawCoordinates(int[][] grid) {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                int c = grid[y][x];
                if (c != 0) {
                    System.out.print(c);
                } else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void calculateManhattanDistance() {

        for (int gridX = 0; gridX < grid.length; gridX++) {
            for (int gridY = 0; gridY < grid.length; gridY++) {

                int prevMinManhattanDistance = Integer.MAX_VALUE;

                for (Map.Entry<Integer, Point> entry : idsAndCoordinatesHashMap.entrySet()) {
                    int id = entry.getKey();
                    int coordinateX = entry.getValue().getX();
                    int coordinateY = entry.getValue().getY();

                    int manhattanDistance = Math.abs(gridX - coordinateX) + Math.abs(gridY - coordinateY);

                    grid[coordinateX][coordinateY] = 0;

                    if (prevMinManhattanDistance > manhattanDistance) {
                        prevMinManhattanDistance = manhattanDistance;
                        grid[gridX][gridY] = id;

                    } else if (prevMinManhattanDistance == manhattanDistance) {
                        grid[gridX][gridY] = 0;
                    }
                }
            }
        }
    }

    public boolean isTouchingBoundaries(int gridX, int gridY) {
        boolean touchingBoundaries = false;
        if (gridX == 0 && gridY <= grid.length || gridX <= grid.length && gridY == 0 ||
                gridX <= grid.length && gridY == grid.length - 1 || gridX == grid.length - 1 && gridY <= grid.length) {
            touchingBoundaries = true;

        }
        return touchingBoundaries;
    }

    public long countFinitePointsArea() {

        HashSet<Integer> infinitePoints = new HashSet<>();
        ArrayList<Integer> finitePointsList = new ArrayList<>();

        for (int gridX = 0; gridX < grid.length; gridX++) {
            for (int gridY = 0; gridY < grid.length; gridY++) {

                if (isTouchingBoundaries(gridX, gridY)) {
                    infinitePoints.add(grid[gridX][gridY]);
                } else {
                    finitePointsList.add(grid[gridX][gridY]);
                    finitePointsList.removeAll(infinitePoints);
                }
            }
        }

        Map<Integer, Long> numberOfFinitePoints;
        numberOfFinitePoints = finitePointsList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Optional<Map.Entry<Integer, Long>> totalPoints = numberOfFinitePoints.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue));

        long largestAreaSize = totalPoints.get().getValue() + 1;

        return largestAreaSize;
    }

    public int findSafeRegion(int range) {
        int manhattanDistance;
        int totalDistance = 0;
        ArrayList acceptedDistance = new ArrayList();

        for (int gridX = 0; gridX < grid.length; gridX++) {
            for (int gridY = 0; gridY < grid.length; gridY++) {

                for (Map.Entry<Integer, Point> entry : idsAndCoordinatesHashMap.entrySet()) {
                    int coordinateX = entry.getValue().getX();
                    int coordinateY = entry.getValue().getY();

                    manhattanDistance = Math.abs(gridX - coordinateX) + Math.abs(gridY - coordinateY);

                    grid[gridX][gridY] = manhattanDistance;
                    totalDistance = totalDistance + manhattanDistance;
                }
                if (totalDistance < range) {
                    acceptedDistance.add(totalDistance);
                }
                totalDistance = 0;
            }
        }
        return acceptedDistance.size();
    }
}








