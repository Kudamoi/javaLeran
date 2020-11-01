class CardAccount extends BankAccount {

    boolean withdrawMoney(double count) {
        double withPercentCount = count * .01 + count;
        if (super.withdrawMoney(withPercentCount))
            return true;
        return false;
    }
}
