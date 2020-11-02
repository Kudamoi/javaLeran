public class Manager implements Employee{
    private double incomeForCompany = Double.parseDouble(String.format("%.2f", (Math.random() * (140000 - 115000)) + 115000).replaceAll(",","."));
    @Override
    public int getMonthSalary() {
        return 30000 + (int) (incomeForCompany * .05);
    }

    double getIncomeForCompany() {
        return incomeForCompany;
    }
}
