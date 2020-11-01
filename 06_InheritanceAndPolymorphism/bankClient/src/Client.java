public abstract class Client {
    private double money;
    private double accountMoney;

    Client(double count) {
        money = count;
    }

    double getAccountMoney() {
        return accountMoney;
    }

    boolean addToAccountMoney(double amount) {
        if (money >= amount) {
            accountMoney += amount;
            accountMoney = Double.parseDouble(String.format("%.2f", accountMoney).replaceAll(",", "."));
            money -= amount;
            money = Double.parseDouble(String.format("%.2f", money).replaceAll(",", "."));
            return true;
        }
        System.out.println("Не достаточно денег для зачисления на счет!");
        return false;
    }

    boolean withdrawAccount(double amount) {
        if (accountMoney >= amount) {
            accountMoney -= amount;
            accountMoney = Double.parseDouble(String.format("%.2f", accountMoney).replaceAll(",", "."));
            money += amount;
            money = Double.parseDouble(String.format("%.2f", money).replaceAll(",", "."));
            return true;
        }
        System.out.println("На счету не достаточно средств!");
        return false;
    }

    void setMoney(double money) {
        this.money = money;
    }

    double getMoney() {
        return money;
    }

    public abstract void getInformation();
}
