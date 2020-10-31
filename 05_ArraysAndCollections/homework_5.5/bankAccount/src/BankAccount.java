public class BankAccount {
    double money;

    public void withdrawMoney(double count) {
        this.money -= count;
    }

    public void depositMoney(double count) {
        this.money += count;
    }

    public double getMoney() {
        return money;
    }

    boolean send(BankAccount account, double amount) {
        if (this.money >= amount) {
            account.money += amount;
            this.money -= amount;
            return true;
        } else return false;
    }
}
