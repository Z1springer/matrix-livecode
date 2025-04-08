import java.util.*;

public class SalamanderSearch {
    public static void main(String[] args) {
        char[][] enclosure1 = {
                { '.', '.', '.', '.', '.', '.' },
                { 'W', '.', 'W', 'W', '.', '.' },
                { '.', '.', 'W', '.', '.', 'W' },
                { 'f', 'W', '.', '.', 'W', '.' },
                { 'W', '.', 'W', 's', '.', '.' },
        };

        char[][] enclosure2 = {
                { '.', '.', '.', '.', '.', '.' },
                { 'W', 'W', 'W', 'W', 's', '.' },
                { '.', '.', 'W', '.', '.', 'W' },
                { 'f', 'W', '.', '.', 'W', '.' },
                { 'W', '.', 'W', '.', '.', '.' },
        };

        Set<int[]> coordinateSet = new HashSet<>();
        int[] coord1 = new int[] { 1, 5 };
        int[] coord2 = new int[] { 3, 7 };
        int[] coord3 = new int[] { 1, 5 };

        coordinateSet.add(coord1);
        coordinateSet.add(coord2);
        coordinateSet.add(coord3);

        System.out.println(coordinateSet.size());
    }

    /**
     * Returns whether a salamander can reach the food in an enclosure.
     * 
     * The enclosure is represented by a rectangular char[][] that contains
     * ONLY the following characters:
     * 
     * 's': represents the starting location of the salamander
     * 'f': represents the location of the food
     * 'W': represents a wall
     * '.': represents an empty space the salamander can walk through
     * 
     * The salamander can move one square at a time: up, down, left, or right.
     * It CANNOT move diagonally.
     * It CANNOT move off the edge of the enclosure.
     * It CANNOT move onto a wall.
     * 
     * This method should return true if there is any sequence of steps that
     * the salamander could take to reach food.
     * 
     * @param enclosure
     * @return whether the salamander can reach the food
     */
    public static boolean canReach(char[][] enclosure) {
        int[] start = salamanderLocation(enclosure);
        boolean[][] visited = new boolean[enclosure.length][enclosure[0].length];
        return canReach(enclosure, start, visited);
    }

    // HELPER Methods
    public static boolean canReach(char[][] enclosure, int[] currentLocation, boolean[][] visited) {
        int currentRow = currentLocation[0];
        int currentColumn = currentLocation[1];
        // Base Cases
        if (visited[currentRow][currentColumn])
            return false;
        if (enclosure[currentRow][currentColumn] == 'f')
            return true;

        visited[currentRow][currentColumn] = true;

        List<int[]> moves = possibleMoves(enclosure, currentLocation);
        for (int[] move : moves) {
            if (canReach(enclosure, move, visited))
                return true;
        }
        return false;
    }

    public static List<int[]> possibleMoves(char[][] enclosure, int[] currentLocation) {
        int currentRow = currentLocation[0];
        int currentColumn = currentLocation[1];

        List<int[]> moves = new ArrayList<>();

        int[][] directions = new int[][] {
                { -1, 0 },
                { 1, 0 },
                { 0, -1 },
                { 0, 1 }
        };

        for (int[] direction : directions) {
            int newRow = currentRow + direction[0];
            int newColumn = currentColumn + direction[1];

            if (newRow >= 0 && newRow < enclosure.length &&
                    newColumn >= 0 && newColumn < enclosure.length &&
                    enclosure[newRow][newColumn] != 'W') {
                moves.add(new int[] { newRow, newColumn });
            }
        }

        return moves;

        // // UP
        // int newRow = currentRow - 1;
        // int newColumn = currentColumn;
        // if (newRow >= 0 && enclosure[newRow][newColumn] != 'W') {
        // moves.add(new int[] { newRow, newColumn });
        // }
        // // DOWN
        // newRow = currentRow + 1;
        // newColumn = currentColumn;
        // if (newRow < enclosure.length && enclosure[newRow][newColumn] != 'W') {
        // moves.add(new int[] { newRow, newColumn });
        // }
        // // LEFT
        // newRow = currentRow;
        // newColumn = currentColumn - 1;
        // if (newColumn >= 0 && enclosure[newRow][newColumn] != 'W') {
        // moves.add(new int[] { newRow, newColumn });
        // }
        // // RIGHT
        // newRow = currentRow;
        // newColumn = currentColumn + 1;
        // if (newColumn < enclosure[0].length && enclosure[newRow][newColumn] != 'W') {
        // moves.add(new int[] { newRow, newColumn });
        // }
        // return moves;
    }

    public static int[] salamanderLocation(char[][] enclosure) {
        for (int r = 0; r < enclosure.length; r++) {
            for (int c = 0; c < enclosure[0].length; c++) {
                if (enclosure[r][c] == 's') {
                    return new int[] { r, c };
                }
            }
        }
        throw new IllegalArgumentException("No salamander present");
    }

}
