import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.GregorianCalendar;

public class DepositAccount extends BankAccount {
    Calendar enterDate;
    String lastDepositDate;
    String allowWithdrawDate;

    DateFormat format = new SimpleDateFormat("yyyyMMdd");

    public void withdrawMoney(double count) {
        if (Integer.parseInt(allowWithdrawDate) < Integer.parseInt(format.format(new GregorianCalendar().getTime())))
            if (count < money)
                System.out.println("Недостаточно средств для снятия денег!");
            else money -= count;
        else System.out.println("Вы не можете снять деньги, так как с последнего депозита не прошел месяц!");
    }

    public void depositMoney(double count) {
        money += count;
        enterDate = new GregorianCalendar();
        lastDepositDate = format.format(enterDate.getTime());

        enterDate.add(Calendar.MONTH, 1);

        allowWithdrawDate = format.format(enterDate.getTime());
    }
}
