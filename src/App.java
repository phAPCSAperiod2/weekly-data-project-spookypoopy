import java.util.Scanner;
import java.util.Random;

/**
 * Weekly Step Counter Application (refactored)
 * - Extracts input and output into helper methods
 * - Adds a small compliments feature for personalized encouragement
 * 
 * Behavior is preserved; the code is just cleaner and easier to read.
 *
 * @author Jacob Le
 * @version 1.1
 */
public class App {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("===== WEEKLY STEP COUNTER =====\n");

            double goal = promptDailyGoal(scanner);
            int numDays = promptNumDays(scanner);

            double[] steps = collectSteps(scanner, numDays, goal);

            WeeklyData data = new WeeklyData(steps, goal);

            printSummary(data);
            printFeedback(data, numDays);
        }
    }

    // ----- Input helpers -----
    private static double promptDailyGoal(Scanner scanner) {
        System.out.print("Enter your daily step goal (healthy goal 10,000): ");
        double defaultGoal = 10000.0;

        if (scanner.hasNextDouble()) {
            double input = scanner.nextDouble();
            scanner.nextLine(); // consume rest of line
            if (input > 0) return input;
        } else {
            scanner.nextLine(); // clear bad input
        }

        System.out.println("Goal: " + (int)defaultGoal + " steps\n");
        return defaultGoal;
    }

    private static int promptNumDays(Scanner scanner) {
        System.out.print("How many days do you want to track? (default 7): ");
        int defaultDays = 7;

        if (scanner.hasNextInt()) {
            int input = scanner.nextInt();
            scanner.nextLine(); // consume rest of line
            if (input > 0) return input;
        } else {
            scanner.nextLine(); // clear bad input
        }

        System.out.println("Tracking " + defaultDays + " days\n");
        return defaultDays;
    }

    // ----- Data collection -----
    private static double[] collectSteps(Scanner scanner, int numDays, double goal) {
        double[] steps = new double[numDays];

        for (int day = 1; day <= numDays; day++) {
            double value = readNonNegativeDouble(scanner, "Day " + day + " - Enter steps: ");
            steps[day - 1] = value;

            if (value >= goal) {
                System.out.println("   ✓ Great job! You met your goal for today! 💪\n");
            } else {
                System.out.println("   You need " + (int)(goal - value) + " more steps to reach your goal. Keep going!\n");
            }
        }

        return steps;
    }

    private static double readNonNegativeDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine(); // consume rest of line
                if (value >= 0) return value;
                System.out.println("Please enter a positive number.");
            } else {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // clear bad input
            }
        }
    }

    // ----- Output helpers -----
    private static void printSummary(WeeklyData data) {
        System.out.println("\n===== RESULTS =====\n");
        System.out.println("Total Steps: " + (int)data.getTotal());
        System.out.println("Average: " + String.format("%.0f", data.getAverage()));
        System.out.println("Highest Day: " + (int)data.getMax());
        System.out.println("Lowest Day: " + (int)data.getMin());
        System.out.println("\n" + data.toString());
    }

    private static void printFeedback(WeeklyData data, int numDays) {
        int daysMetGoal = data.getDaysGoalMet();

        System.out.println("===== FEEDBACK =====\n");
        System.out.println("Goal Achievement: " + daysMetGoal + " out of " + numDays + " days\n");

        // Core message
        if (daysMetGoal == numDays) {
            System.out.println("🎉 INCREDIBLE! You crushed it!");
            System.out.println("You met your goal every single day this week!");
        } else if (daysMetGoal >= 5) {
            System.out.println("⭐ EXCELLENT! You had an awesome week!");
            System.out.println("You met your goal " + daysMetGoal + " out of " + numDays + " days!");
        } else if (daysMetGoal >= 3) {
            System.out.println("👍 GOOD EFFORT! You're making progress!");
            System.out.println("You met your goal " + daysMetGoal + " days this week.");
        } else if (daysMetGoal >= 1) {
            System.out.println("💪 NICE START! You're getting there!");
            System.out.println("You met your goal " + daysMetGoal + " day(s) this week.");
        } else {
            System.out.println("🌟 KEEP TRYING! You've got this!");
            System.out.println("You didn't reach your goal this week, but that's okay!");
        }

        // Add a short random compliment to finish
        System.out.println();
        System.out.println(getRandomCompliment());

        System.out.println();
        System.out.println("Next week's goal: Beat your best day of " + (int)data.getMax() + " steps!");
        System.out.println();
    }

    private static String getRandomCompliment() {
        String[] compliments = {
            "Nice work — your effort is paying off! ✨",
            "You're doing great — keep that energy up! 🔥",
            "Small steps lead to big wins — proud of you! 💯",
            "You're consistent and it's showing — way to go! 🌟",
            "Love the progress — keep stepping! 👏"
        };

        Random rnd = new Random();
        return "Compliment: " + compliments[rnd.nextInt(compliments.length)];
    }
}
