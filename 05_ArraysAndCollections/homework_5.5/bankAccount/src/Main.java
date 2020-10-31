public class Main {
    public static void main(String[] args) {
        DepositAccount test = new DepositAccount();
        CardAccount test2 = new CardAccount();

        test.depositMoney(110);

        System.out.println(test.getMoney() + " Внесли сюда + 110");
        System.out.println(test2.getMoney());
        System.out.println();

        test.withdrawMoney(1);

        test.send(test2, 100);

        System.out.println(test.getMoney() + " снимаем 1 передаем на тест2 100");
        System.out.println(test2.getMoney());
        System.out.println();

        test2.depositMoney(100);

        test2.withdrawMoney(100);

        System.out.println(test.getMoney());
        System.out.println(test2.getMoney() + " Добавляем 100 сюда Снимаем 100 тест2");
        System.out.println();

        test2.withdrawMoney(99);

        System.out.println(test.getMoney());
        System.out.println(test2.getMoney() + " Снимаем 99 тест2");
        System.out.println();
    }
}
