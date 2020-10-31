import java.math.BigDecimal;


public class CardAccount extends BankAccount {

    public void withdrawMoney(double count) {
        double withPercentCount = count / 100 + count;
        if (money > withPercentCount) {
            money -= withPercentCount;
            money = Math.floor(money * 100) / 100;
        } else System.out.println("На счете недостаточно средств!");
    }
}
