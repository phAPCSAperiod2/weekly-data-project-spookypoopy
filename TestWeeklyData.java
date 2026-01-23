// Simple test to verify WeeklyData works correctly
public class TestWeeklyData {
    public static void main(String[] args) {
        System.out.println("Testing WeeklyData...\n");
        
        // Test 1: Deep copy
        double[] input = {10.0, 20.0, 30.0};
        WeeklyData wd = new WeeklyData(input);
        double originalTotal = wd.getTotal();
        input[0] = 9999.0;
        
        if (wd.getTotal() == originalTotal) {
            System.out.println("✓ Test 1 PASSED: Deep copy works correctly");
        } else {
            System.out.println("✗ Test 1 FAILED: Deep copy not working");
        }
        
        // Test 2: getTotal
        double[] input2 = {100, 200, 300};
        WeeklyData wd2 = new WeeklyData(input2);
        if (wd2.getTotal() == 600.0) {
            System.out.println("✓ Test 2 PASSED: getTotal works");
        } else {
            System.out.println("✗ Test 2 FAILED: getTotal expected 600, got " + wd2.getTotal());
        }
        
        // Test 3: getAverage
        double[] input3 = {2, 4, 6, 8};
        WeeklyData wd3 = new WeeklyData(input3);
        if (Math.abs(wd3.getAverage() - 5.0) < 0.0001) {
            System.out.println("✓ Test 3 PASSED: getAverage works");
        } else {
            System.out.println("✗ Test 3 FAILED: getAverage expected 5, got " + wd3.getAverage());
        }
        
        // Test 4: getMax
        double[] input4 = {10, 50, 30, 75, 25};
        WeeklyData wd4 = new WeeklyData(input4);
        if (wd4.getMax() == 75.0) {
            System.out.println("✓ Test 4 PASSED: getMax works");
        } else {
            System.out.println("✗ Test 4 FAILED: getMax expected 75, got " + wd4.getMax());
        }
        
        // Test 5: getMin
        double[] input5 = {10, 50, 30, 75, 25};
        WeeklyData wd5 = new WeeklyData(input5);
        if (wd5.getMin() == 10.0) {
            System.out.println("✓ Test 5 PASSED: getMin works");
        } else {
            System.out.println("✗ Test 5 FAILED: getMin expected 10, got " + wd5.getMin());
        }
        
        // Test 6: toString formatting
        double[] input6 = {1000, 2000, 3000};
        WeeklyData wd6 = new WeeklyData(input6, 1500);
        String toString = wd6.toString();
        if (toString.contains("1,000") && toString.contains("2,000") && toString.contains("3,000")) {
            System.out.println("✓ Test 6 PASSED: toString has comma formatting");
        } else {
            System.out.println("✗ Test 6 FAILED: toString missing comma formatting");
            System.out.println(toString);
        }
        
        // Test 7: getDaysGoalMet
        double[] input7 = {1500, 2000, 2500};
        WeeklyData wd7 = new WeeklyData(input7, 1000);
        if (wd7.getDaysGoalMet() == 3) {
            System.out.println("✓ Test 7 PASSED: getDaysGoalMet works");
        } else {
            System.out.println("✗ Test 7 FAILED: getDaysGoalMet expected 3, got " + wd7.getDaysGoalMet());
        }
        
        System.out.println("\nAll core tests completed!");
    }
}
