import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.GregorianCalendar;

class DepositAccount extends BankAccount {
    private String lastDepositDate;
    private String allowWithdrawDate;

    private final DateFormat format = new SimpleDateFormat("yyyyMMdd");

    boolean withdrawMoney(double count) {
        if (Integer.parseInt(allowWithdrawDate) < Integer.parseInt(format.format(new GregorianCalendar().getTime()))) {
            super.withdrawMoney(count);
            return true;
        } else {
            System.out.println("Вы не можете снять деньги, так как с последнего депозита не прошел месяц!");
            return false;
        }
    }

    void depositMoney(double count) {
        money += count;
        Calendar enterDate = new GregorianCalendar();
        lastDepositDate = format.format(enterDate.getTime());

        enterDate.add(Calendar.MONTH, 1);

        allowWithdrawDate = format.format(enterDate.getTime());
    }
}
