public class CardAccount extends BankAccount {

    public boolean withdrawMoney(double count) {
        double withPercentCount = count * .01 + count;
        if (super.withdrawMoney(withPercentCount))
            return true;
        return false;
    }
}
