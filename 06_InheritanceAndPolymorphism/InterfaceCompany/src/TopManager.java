public class TopManager implements Employee {
    private int salary = 30000;
    private static boolean present;
    static void setPresent() {
        present = true;
    }
    static void removePresent() {
        present = false;
    }

    @Override
    public int getMonthSalary() {
        return present ? (int)(salary * 1.5) : salary;
    }
}
