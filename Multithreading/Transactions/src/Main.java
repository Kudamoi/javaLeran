import java.text.NumberFormat;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        NumberFormat f = NumberFormat.getInstance();
        Bank bank = new Bank();
        int countAccounts = 3000;
        int countAccountsThread = 600;
        for (int i = 1; i < countAccounts; i++) {
            Account account = new Account();
            account.setAccNumber(Integer.toString(i));
            account.setMoney(Math.round(Math.random() * 200000));
            account.setBlock(false);

            bank.addAccount(Integer.toString(i), account);
        }

        bank.printSumAllAccounts();


        for (int i = 0; i < countAccountsThread; i++) {
            Actions actions = new Actions(bank, countAccounts);
            new Thread(actions).start();
            if (i == countAccountsThread - 1) {
                bank.printSumAllAccounts();
            }
        }

        while (Thread.activeCount() > 2) {
            Thread.sleep(1000);
            System.out.println("Операций в ожидании " + (Thread.activeCount() - 2));
        }

        bank.printSumAllAccounts();
    }
}
