public class Actions implements Runnable {

    private int countAccounts;
    private String from;
    private String to;
    private Bank bank;

    public Actions(Bank bank, int countAccounts) {
        this.countAccounts = countAccounts;
        this.from = Integer.toString((int) (Math.random() * ++countAccounts));
        this.to = Integer.toString((int) (Math.random() * ++countAccounts));
        this.bank = bank;
    }

    @Override
    public void run() {
        bank.transfer(this.from, this.to, (int) (Math.random() * Math.random() * 100 * Math.random() * 100 / Math.random() * Math.random() * 100));
    }
}
