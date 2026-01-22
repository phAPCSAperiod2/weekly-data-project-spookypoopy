/**
 * The WeeklyData class stores and analyzes 7 days of step data.
 * It provides methods to calculate total, average, max, and min steps.
 */
public class WeeklyData {

    // Private variables - only accessible within this class
    private double[] data;          // Stores the 7 days of step counts
    private double dailyGoal;       // Stores the user's daily step goal

    /**
     * Constructor: Takes in an array of step data and a daily goal.
     * Makes a COPY of the input array (deep copy) to protect the original data.
     * 
     * @param input The array of 7 days of steps
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
            
            // Add this day's info to the result
            // (i + 1) because days are numbered 1-7, not 0-6
            // (int) converts the double to an integer for cleaner display
            result += "Day " + (i + 1) + ": " + (int)data[i] + check + "\n";
        }
        
        return result;
    }
}