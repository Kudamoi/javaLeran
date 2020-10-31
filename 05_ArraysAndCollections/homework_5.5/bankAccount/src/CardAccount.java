public class CardAccount extends BankAccount {

    public boolean withdrawMoney(double count) {
        double withPercentCount = count / 100 + count;
        if (super.withdrawMoney(withPercentCount)) {
            money = Math.floor(money * 100) / 100;
            return true;
        } return false;
    }
}
