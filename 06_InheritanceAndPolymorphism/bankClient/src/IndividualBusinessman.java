public class IndividualBusinessman extends Client {

    IndividualBusinessman(double count) {
        super(count);
    }

    boolean withdrawAccount(double amount) {
        if (this.getAccountMoney() >= amount * .01 + amount) {
            super.withdrawAccount(amount * .01 + amount);
            return true;
        }
        return false;
    }

    boolean addToAccountMoney(double amount) {
        if (amount < 1000) {
            return super.addToAccountMoney(amount - amount * .01);
        } return super.addToAccountMoney(amount - amount * .005);

    }

    @Override
    public void getInformation() {
        System.out.println("Информация счета для индивидуального предпренемателя:");
        System.out.println("Коммисия для снятия: 1%");
        System.out.println("Коммисия для пополнения до 1000 рублей: 1%");
        System.out.println("Коммисия для пополнения от 1000 рублей: 0,5%");
    }
}
