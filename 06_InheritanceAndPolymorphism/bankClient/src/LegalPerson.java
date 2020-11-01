public class LegalPerson extends Client {

    LegalPerson(double count) {
        super(count);
    }

    boolean withdrawAccount(double amount) {
        if (this.getAccountMoney() >= amount * .01 + amount) {
            super.withdrawAccount(amount * .01 + amount);
            return true;
        }
        return false;
    }

    @Override
    public void getInformation() {
        System.out.println("Информация счета для юридического лица:");
        System.out.println("Коммисия для пополнения: 0%");
        System.out.println("Коммисия для снятия: 1%");
    }
}
