import java.text.NumberFormat;
import java.util.*;

public class Bank {
    NumberFormat f = NumberFormat.getInstance();
    private volatile Hashtable<String, Account> accounts = new Hashtable<>();
    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        synchronized (new HashSet<>(Arrays.asList(this.accounts.get(fromAccountNum), this.accounts.get(toAccountNum)))) {

            long balFrom = this.getBalance(fromAccountNum);
            long balTo = this.getBalance(toAccountNum);
            StringBuilder log = new StringBuilder("Перевод с аккаунта " + fromAccountNum + "(" + balFrom + ") на аккаунт " + toAccountNum + "(" + balTo + "). Сумма " + amount);

            if (!this.accounts.get(fromAccountNum).isBlocked() && !this.accounts.get(toAccountNum).isBlocked()) {
                if (balFrom >= amount) {

                    if (amount <= 50000) {

                        this.accounts.get(fromAccountNum).setMoney(balFrom - amount);
                        this.accounts.get(toAccountNum).setMoney(balTo + amount);

                        log.append("\n\tБаланс после перевода ").append(fromAccountNum).append("(").append(this.getBalance(fromAccountNum)).append(") на аккаунт ").append(toAccountNum).append("(").append(this.getBalance(toAccountNum)).append(")");

                    } else {
                        try {

                            log.append("\n\tПеревод отправлен на проверку системой безопасности");

                            if (isFraud(fromAccountNum, toAccountNum, amount)) {
                                this.accounts.get(fromAccountNum).setBlock(true);
                                this.accounts.get(toAccountNum).setBlock(true);

                                log.append("\n\tАккаунты (").append(fromAccountNum).append(", ").append(toAccountNum).append(") заблокированы, службой безопасности!");
                            } else {
                                this.accounts.get(fromAccountNum).setMoney(balFrom - amount);
                                this.accounts.get(toAccountNum).setMoney(balTo + amount);

                                log.append("\n\tАккаунту ").append(fromAccountNum).append(", разрешено сделать перевод на ").append(toAccountNum).append(" службой безопасности!");
                                log.append("\n\t- Баланс после перевода ").append(fromAccountNum).append("(").append(this.getBalance(fromAccountNum)).append(") на аккаунт ").append(toAccountNum).append("(").append(this.getBalance(toAccountNum)).append(")");
                            }
                        } catch (InterruptedException e) {
                            log.append(e.getMessage());
                        }
                    }

                } else {
                    log.append("\n\tНедостаточно средств для совершения операции!").append("\n\t- Ваш (").append(fromAccountNum).append(") баланс: ").append(balFrom).append(". Попытка перевода: ").append(amount).append(" на аккаунт ").append(toAccountNum);
                    log.append("\n");
                }
            } else {
                if (!this.accounts.get(fromAccountNum).isBlocked()) {
                    log.append("\n\tВаш аккаунт заблокирован, перевод невозможен!");
                } else {
                    log.append("\n\tАккаунт приема перевода заблокирован, перевод невозможен!");
                }
            }

            log.append("\n");
            System.out.println(log);
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        return this.accounts.get(accountNum).getMoney();
    }

    public long getSumAllAccounts() {
        long resultSum = 0;
        for (String accountNumber : this.accounts.keySet()) {
            resultSum += this.accounts.get(accountNumber).getMoney();
        }
        return resultSum;
    }

    public void printSumAllAccounts() {
        long resultSum = 0;
        long resultSumBlocked = 0;
        long open = 0;
        long blocked = 0;
        for (String accountNumber : this.accounts.keySet()) {
            if (this.accounts.get(accountNumber).isBlocked()) {
                blocked++;
                resultSumBlocked += this.getBalance(accountNumber);
            } else {
                open++;
                resultSum += this.getBalance(accountNumber);
            }
        }
        System.out.println("Сбережения всех аккаунтов в банке: " + f.format(resultSum + resultSumBlocked));
        System.out.println("\tДоступных для перевода: " + f.format(resultSum));
        System.out.println("\tЗаблокированых: " + f.format(resultSumBlocked));
        System.out.println("\tОткрытых счетов: " + open);
        System.out.println("\tЗаблокированых счетов: " + blocked);
    }

    public void addAccount(String accountNumber, Account account) {
        //    System.out.println("Добавлен новый аккаунт: " + accountNumber);
        this.accounts.put(accountNumber, account);
    }

    public void printAllAccount() {
        for (String accountNumber : this.accounts.keySet()) {
            System.out.println("Номер аккаунта: " + accountNumber + "\n\t Остаток на счете: " + this.getBalance(accountNumber) + "\n\t Заблокирован: " + this.accounts.get(accountNumber).isBlocked());
        }
    }
}
