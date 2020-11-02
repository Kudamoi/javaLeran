import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.TreeSet;

public class Company {
    ArrayList<Employee> employees = new ArrayList<>();

    void hire(Employee employee) {
        employees.add(employee);
        checkIncome();
    }

    void hire(Class employee, int count) {
        for (int i = 0; i < count; i++)
            hire(employee.equals(Manager.class) ? new Manager() : employee.equals(TopManager.class) ? new TopManager() : new Operator());
        checkIncome();
    }

    void hireAll(ArrayList<Employee> list) {
        employees.addAll(list);
        checkIncome();
    }

    void fire(Employee employee) {
        employees.remove(employee);
        checkIncome();
    }

    void fire(double percent) {
        int count = 0;
        if (percent <= 100 && percent >= 0)
            count = (int) (employees.size() * percent / 100);
        for (int i = 0; i < count; i++) {
            employees.remove((int) (Math.random() * employees.size()));
        }
        System.out.println("Удалено " + count + " сотрудников. Осталось " + employees.size());
        checkIncome();
    }

    BigDecimal getIncome() {
        double incomeCompany = 0;
        for (Employee employee : employees)
            if (employee.getClass().equals(Manager.class))
                incomeCompany += ((Manager) employee).getIncomeForCompany();
        return new BigDecimal(incomeCompany).setScale(2, RoundingMode.DOWN);

    }

    public ArrayList<Employee> getTopSalaryStaff(int count) {
        TreeSet<Employee> sort = new TreeSet<>(employees);
        ArrayList<Employee> sorting = new ArrayList<>(sort);
        ArrayList<Employee> sortRevert = new ArrayList<>();
        for (int i = sort.size() - 1; i > 0; i--)
            sortRevert.add(sorting.get(i));
        ArrayList<Employee> result = new ArrayList<>();
        int i = 0;
        for (Employee sorted : sortRevert) {
            if (i < count) {
                i++;
                result.add(sorted);
                System.out.println("* " + sorted.getMonthSalary());
            } else break;
        }
        return result;
    }

    public ArrayList<Employee> getLowestSalaryStaff(int count) {
        TreeSet<Employee> sort = new TreeSet<>(employees);
        ArrayList<Employee> result = new ArrayList<>();
        int i = 0;
        for (Employee sorted : sort) {
            if (i < count) {
                i++;
                result.add(sorted);
                System.out.println("* " + sorted.getMonthSalary());
            } else break;
        }
        return result;
    }

    void checkIncome() {
        if (getIncome().doubleValue() > 10000000)
            TopManager.setPresent();
        else TopManager.removePresent();
    }
}
