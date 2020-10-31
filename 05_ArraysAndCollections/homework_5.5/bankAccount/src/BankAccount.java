public class BankAccount {
    protected double money;

    public void withdrawMoney(double count) {
        if (money >= count)
            money -= count;
        else System.out.println("Недостаточно средств для выполнения данной операции!");
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
