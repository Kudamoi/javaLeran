public class Account {

    private long money;
    private String accNumber;
    private boolean block;

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public boolean isBlocked() {
        return this.block;
    }

    public void setBlock(boolean bool) {
        this.block = bool;
    }
}
