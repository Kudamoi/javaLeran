public class Main {
    public static void main(String[] args) {
        System.out.println("\nТест 1\n");
        Client first = new PrivatePerson(500);

        first.addToAccountMoney(100);
        first.addToAccountMoney(500);
        System.out.println(first.getAccountMoney() + " денег на счету физического лица после добавления 100, и попытки добавить больше чем есть на руках");
        first.withdrawAccount(300);
        first.withdrawAccount(100);
        System.out.println(first.getAccountMoney() + " денег на счету физического лица после попытки снятия 300 рублей, а после 100");
        first.withdrawAccount(1);

        System.out.println("\nТест 2\n");
        Client second = new LegalPerson(1000);

        second.addToAccountMoney(100);
        second.addToAccountMoney(500);
        System.out.println(second.getAccountMoney() + " денег на счету юридического лица после добавления 100 рублей, а после 500");
        second.withdrawAccount(300);
        second.withdrawAccount(100);
        System.out.println(second.getAccountMoney() + " денег на счету юридического лица после попытки снятия 300 рублей, а после 100");

        System.out.println("\nТест 3\n");

        Client third = new IndividualBusinessman(10000);

        third.addToAccountMoney(999);
        third.addToAccountMoney(5000);
        System.out.println(third.getAccountMoney() + " денег на счету ИП после добавления 999 рублей, а после 5000");
        third.withdrawAccount(1000);
        third.withdrawAccount(500);
        System.out.println(third.getAccountMoney() + " денег на счету ИП после снятия 1000 рублей, а после 500");
    }
}
