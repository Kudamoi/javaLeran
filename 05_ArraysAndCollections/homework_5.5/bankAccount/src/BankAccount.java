class BankAccount {
    double money;

    boolean withdrawMoney(double count) {
        if (money >= count) {
            money = Double.parseDouble(String.format("%.2f",money - count).replaceAll(",", "."));
            return true;
        } else {
            System.out.println("Недостаточно средств для выполнения данной операции!");
            return false;
        }
    }

    void depositMoney(double count) {
        this.money += count;
    }

    double getMoney() {
        return money;
    }

    boolean send(BankAccount account, double amount) {
        if (withdrawMoney(amount)) {
            account.money += amount;
            this.money -= amount;
            return true;
        }
        return false;
    }
}
