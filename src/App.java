import java.util.Scanner;

/**
 * Weekly Step Counter Application
 * Allows users to enter daily steps for a week, set a goal, and see analysis results.
 */
public class App {

    public static void main(String[] args) {
        // Create a Scanner to read user input from the keyboard
        Scanner scanner = new Scanner(System.in);

        // Display a welcome message
        System.out.println("===== WEEKLY STEP COUNTER =====\n");

        // ----- STEP 1: Get the user's daily goal -----
        System.out.print("Enter your daily step goal (default 10000): ");
        
        // Set default goal to 10,000 steps
        double goal = 10000;
        
        // Check if user entered a valid number
        if (scanner.hasNextDouble()) {
            double input = scanner.nextDouble();  // Read the input
            
            // Only update goal if input is positive (no negative goals!)
            if (input > 0) {
                goal = input;
            }
        }
        
        // Clear the rest of the line (important for Scanner)
        scanner.nextLine();
        
        // Display the goal that will be used
        System.out.println("Goal: " + (int)goal + " steps\n");

        // ----- STEP 2: Create an array to store 7 days of step data -----
        double[] steps = new double[7];

        // ----- STEP 3: Collect step data for each day -----
        // Loop 7 times (once for each day of the week)
        for (int day = 1; day <= 7; day++) {
            // Use a while loop to validate the input
            boolean valid = false;  // Flag to track if input is valid
            
            while (!valid) {  // Keep asking until we get a valid number
                System.out.print("Day " + day + " - Enter steps: ");
                
                // Check if user entered a number
                if (scanner.hasNextDouble()) {
                    double value = scanner.nextDouble();  // Read the number
                    
                    // Make sure the number is not negative
                    if (value >= 0) {
                        // Store in array (day-1 because array starts at 0, not 1)
                        steps[day - 1] = value;
                        valid = true;  // Exit the while loop
                    } else {
                        // Tell user they entered a negative number
                        System.out.println("Please enter a positive number.");
                    }
                } else {
                    // User didn't enter a number
                    System.out.println("Please enter a valid number.");
                    scanner.nextLine();  // Clear the bad input
                }
            }
        }

        // ----- STEP 4: Create a WeeklyData object to analyze the data -----
        // Pass the steps array and the goal to the WeeklyData constructor
        WeeklyData data = new WeeklyData(steps, goal);

        // ----- STEP 5: Display the results -----
        System.out.println("\n===== RESULTS =====\n");
        
        // Display summary statistics
        System.out.println("Total Steps: " + (int)data.getTotal());
        System.out.println("Average: " + String.format("%.0f", data.getAverage()));
        System.out.println("Highest Day: " + (int)data.getMax());
        System.out.println("Lowest Day: " + (int)data.getMin());

        // Display all 7 days using toString() method
        // The toString() method handles formatting and checkmarks automatically
        System.out.println("\n" + data.toString());

        // Close the Scanner to prevent resource leaks
        scanner.close();
    }
}
