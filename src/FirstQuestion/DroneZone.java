package FirstQuestion;
import java.util.Scanner;

public class DroneZone {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input grid dimensions
        System.out.print("Enter grid size (rows columns): ");
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();

        // Initialize grid
        char[][] grid = new char[rows][columns];

        // Input target position
        System.out.print("Enter target position (row column): ");
        int targetRow = scanner.nextInt();
        int targetColumn = scanner.nextInt();

        // Input drone positions
        System.out.print("Enter the number of drones: ");
        int numDrones = scanner.nextInt();

        int[][] dronePositions = new int[numDrones][2];

        for (int i = 0; i < numDrones; i++) {
            System.out.print("Enter drone position " + (i + 1) + " (row column): ");
            dronePositions[i][0] = scanner.nextInt();
            dronePositions[i][1] = scanner.nextInt();
        }

        // Find drone to strike the target
        int[] selectedDrone = findDrone(grid, dronePositions, targetRow, targetColumn);

        if (selectedDrone != null) {
            System.out.println("Target struck by Drone " + (selectedDrone[0] + 1));
            System.out.println("Path taken by Drone:");

            // Print the path taken by the selected drone
            printPath(grid, dronePositions[selectedDrone[0]], targetRow, targetColumn);
        } else {
            System.out.println("No drone reached the target.");
        }
    }

    private static int[] findDrone(char[][] grid, int[][] dronePositions, int targetRow, int targetColumn) {
        for (int i = 0; i < dronePositions.length; i++) {
            int[] dronePosition = dronePositions[i];
            if (isTargetInZone(dronePosition, targetRow, targetColumn, grid.length, grid[0].length)) {
                // Drone responsible for the zone containing the target
                return new int[]{i};
            }
        }
        return null;
    }

    private static boolean isTargetInZone(int[] dronePosition, int targetRow, int targetColumn, int numRows, int numColumns) {
        // Check if the target is in the same zone as the drone
        int droneZoneStartRow = (dronePosition[0] * numRows) / 2;
        int droneZoneEndRow = ((dronePosition[0] + 1) * numRows) / 2;
        int droneZoneStartColumn = (dronePosition[1] * numColumns) / 2;
        int droneZoneEndColumn = ((dronePosition[1] + 1) * numColumns) / 2;

        return targetRow >= droneZoneStartRow && targetRow < droneZoneEndRow &&
                targetColumn >= droneZoneStartColumn && targetColumn < droneZoneEndColumn;
    }

    private static void printPath(char[][] grid, int[] start, int targetRow, int targetColumn) {
        // Simulate the movement of the drone to the target
        int currentRow = start[0];
        int currentColumn = start[1];

        while (currentRow != targetRow || currentColumn != targetColumn) {
            // Move towards the target
            if (currentRow < targetRow) {
                currentRow++;
            } else if (currentRow > targetRow) {
                currentRow--;
            }

            if (currentColumn < targetColumn) {
                currentColumn++;
            } else if (currentColumn > targetColumn) {
                currentColumn--;
            }

            // Print the path
            System.out.println("(" + currentRow + ", " + currentColumn + ")");
        }
    }
}
