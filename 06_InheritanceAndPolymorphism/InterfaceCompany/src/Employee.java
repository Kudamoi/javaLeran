public interface Employee extends Comparable<Employee> {
    int getMonthSalary();

    default int compareTo(Employee o) {
        if (getMonthSalary() > o.getMonthSalary())
            return 1;
        else if (getMonthSalary() < o.getMonthSalary())
            return -1;
        return 0;
    }
}
