/**
 * The WeeklyData class stores and analyzes 7 days of step data.
 * It provides methods to calculate total, average, max, and min steps.
 * 
 * This class encapsulates data storage and analysis methods for tracking
 * daily step counts against a user-defined daily goal.
 * 
 * @author Jacob Le
 * @version 1.0
 */
public class WeeklyData {

    // Private variables - only accessible within this class
    private double[] data;          // Stores the 7 days of step counts
    private double dailyGoal;       // Stores the user's daily step goal

    /**
     * Constructor with default goal: Takes in an array of step data.
     * Uses a default daily goal of 10,000 steps.
     * Makes a COPY of the input array (deep copy) to protect the original data.
     * 
     * @param input The array of step data
     */
    public WeeklyData(double[] input) {
        this(input, 10000.0);  // Call the other constructor with default goal
    }

    /**
     * Constructor with custom goal: Takes in an array of step data and a daily goal.
     * Makes a COPY of the input array (deep copy) to protect the original data.
     * 
     * @param input The array of step data
     * @param goal The user's daily step goal
     */
    public WeeklyData(double[] input, double goal) {
        // Create a new array with the same length as input
        this.data = new double[input.length];
        
        // Copy each element from input into our data array (one by one)
        // This prevents the original array from being modified externally
        for (int i = 0; i < input.length; i++) {
            this.data[i] = input[i];
        }
        
        // Store the goal value
        this.dailyGoal = goal;
    }


    /**
     * Returns the sum of all steps for the week.
     * Uses a loop to add up each day's steps.
     * 
     * @return The total steps for all 7 days
     */
    public double getTotal() {
        double total = 0.0;  // Start with 0
        
        // Loop through each day in the array
        for (int i = 0; i < data.length; i++) {
            total += data[i];  // Add this day's steps to the total
        }
        
        return total;
    }


    /**
     * Returns the average steps per day.
     * Divides the total steps by the number of days (7).
     * 
     * @return The average steps per day
     */
    public double getAverage() {
        // Call getTotal() to get sum, then divide by number of days
        return getTotal() / data.length;
    }


    /**
     * Returns the highest day of steps.
     * Compares each day to find the maximum value.
     * 
     * @return The highest number of steps in any single day
     */
    public double getMax() {
        // Start with the first day as the maximum
        double max = data[0];
        
        // Compare each remaining day (starting at index 1)
        for (int i = 1; i < data.length; i++) {
            // If this day has more steps than our current max, update max
            if (data[i] > max) {
                max = data[i];
            }
        }
        
        return max;
    }


    /**
     * Returns the lowest day of steps.
     * Compares each day to find the minimum value.
     * 
     * @return The lowest number of steps in any single day
     */
    public double getMin() {
        // Start with the first day as the minimum
        double min = data[0];
        
        // Compare each remaining day (starting at index 1)
        for (int i = 1; i < data.length; i++) {
            // If this day has fewer steps than our current min, update min
            if (data[i] < min) {
                min = data[i];
            }
        }
        
        return min;
    }


    // -------------------------------------------------------------
    // toString
    // -------------------------------------------------------------
    /**
     * Returns a formatted, multi-line String showing each day’s data.
     *
     * Example:
     * Day 1: 4500
     * Day 2: 6200
     * Day 3: 5100
     *
     * @return a formatted String representing the week’s data
     */
    @Override
    public String toString() {
        String result = "";  // Start with empty string
        
        // Loop through each day in the data array
        for (int i = 0; i < data.length; i++) {
            // Check if this day's steps met the goal
            String check = (data[i] >= dailyGoal) ? " ✓" : "";
            
            // Add this day's info to the result with comma formatting
            // String.format("%,.0f", data[i]) formats numbers with commas
            // Example: 1000.0 becomes "1,000"
            result += "Day " + (i + 1) + ": " + String.format("%,.0f", data[i]) + check + "\n";
        }
        
        return result;
    }

    /**
     * Returns the daily step goal.
     * 
     * @return The daily goal
     */
    /**
     * Returns the daily step goal.
     * 
     * @return The daily goal in steps
     */
    public double getDailyGoal() {
        return dailyGoal;
    }

    /**
     * Counts how many days the goal was met.
     * 
     * @return The number of days where steps >= goal
     */
    public int getDaysGoalMet() {
        int count = 0;  // Start count at 0
        
        // Loop through each day in the data array
        for (int i = 0; i < data.length; i++) {
            // If this day met or exceeded the goal, increment count
            if (data[i] >= dailyGoal) {
                count++;
            }
        }
        
        return count;
    }
}