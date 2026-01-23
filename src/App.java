import java.util.Scanner;

/**
 * Weekly Step Counter Application
 * Allows users to enter daily steps for a week, set a goal, and see analysis results.
 * 
 * This application provides an interactive interface for users to:
 * - Set a custom daily step goal (default 10,000 steps)
 * - Track steps for a variable number of days
 * - Receive instant daily feedback
 * - View weekly analysis and personalized encouragement
 * 
 * @author Jacob Le
 * @version 1.0
 */
public class App {

    public static void main(String[] args) {
        // Create a Scanner to read user input from the keyboard
        Scanner scanner = new Scanner(System.in);

        // Display a welcome message
        System.out.println("===== WEEKLY STEP COUNTER =====\n");

        // ----- STEP 1: Get the user's daily goal -----
        System.out.print("Enter your daily step goal (healthy goal 10,000): ");
        
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

        // ----- STEP 2: Ask how many days to track -----
        System.out.print("How many days do you want to track? (default 7): ");
        
        // Set default to 7 days
        int numDays = 7;
        
        // Check if user entered a valid number
        if (scanner.hasNextInt()) {
            int input = scanner.nextInt();  // Read the input
            
            // Only update numDays if input is positive
            if (input > 0) {
                numDays = input;
            }
        }
        
        // Clear the rest of the line (important for Scanner)
        scanner.nextLine();
        
        System.out.println("Tracking " + numDays + " days\n");

        // ----- STEP 3: Create an array to store step data -----
        // Create array size based on user's choice
        double[] steps = new double[numDays];

        // ----- STEP 4: Collect step data for each day -----
        // Loop for however many days the user chose
        for (int day = 1; day <= numDays; day++) {
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
                        
                        // ----- Display per-day feedback -----
                        // Give instant feedback based on whether they met today's goal
                        if (value >= goal) {
                            System.out.println("   ✓ Great job! You met your goal for today! 💪");
                        } else {
                            double remaining = goal - value;
                            System.out.println("   You need " + (int)remaining + " more steps to reach your goal. Keep going!");
                        }
                        System.out.println();  // Add blank line for readability
                        
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

        // ----- STEP 5: Create a WeeklyData object to analyze the data -----
        // Pass the steps array and the goal to the WeeklyData constructor
        WeeklyData data = new WeeklyData(steps, goal);

        // ----- STEP 6: Display the results -----
        System.out.println("\n===== RESULTS =====\n");
        
        // Display summary statistics
        System.out.println("Total Steps: " + (int)data.getTotal());
        System.out.println("Average: " + String.format("%.0f", data.getAverage()));
        System.out.println("Highest Day: " + (int)data.getMax());
        System.out.println("Lowest Day: " + (int)data.getMin());

        // Display all 7 days using toString() method
        // The toString() method handles formatting and checkmarks automatically
        System.out.println("\n" + data.toString());

        // ----- STEP 7: Display personalized feedback and encouragement -----
        int daysMetGoal = data.getDaysGoalMet();  // Get the number of days goal was met
        
        System.out.println("===== FEEDBACK =====\n");
        System.out.println("Goal Achievement: " + daysMetGoal + " out of " + numDays + " days");
        System.out.println();
        
        // Display encouraging message based on performance
        if (daysMetGoal == numDays) {
            System.out.println("🎉 INCREDIBLE! You crushed it!");
            System.out.println("You met your goal every single day this week!");
            System.out.println("You are a step counter champion! Keep this amazing streak going!");
        } else if (daysMetGoal >= 5) {
            System.out.println("⭐ EXCELLENT! You had an awesome week!");
            System.out.println("You met your goal " + daysMetGoal + " out of 7 days!");
            System.out.println("You're so close to perfection. One more push next week!");
        } else if (daysMetGoal >= 3) {
            System.out.println("👍 GOOD EFFORT! You're making progress!");
            System.out.println("You met your goal " + daysMetGoal + " days this week.");
            System.out.println("Keep building that momentum. You're on the right track!");
        } else if (daysMetGoal >= 1) {
            System.out.println("💪 NICE START! You're getting there!");
            System.out.println("You met your goal " + daysMetGoal + " day(s) this week.");
            System.out.println("Every step counts. Push harder next week!");
        } else {
            System.out.println("🌟 KEEP TRYING! You've got this!");
            System.out.println("You didn't reach your goal this week, but that's okay!");
            System.out.println("Next week is a fresh start. Let's go get those steps!");
        }
        
        System.out.println();
        System.out.println("Next week's goal: Beat your best day of " + (int)data.getMax() + " steps!");
        System.out.println();

        // Close the Scanner to prevent resource leaks
        scanner.close();
    }
}
