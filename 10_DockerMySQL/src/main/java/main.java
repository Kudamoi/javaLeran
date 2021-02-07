import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "12345";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();

            System.out.print("|");
            for (int i = 0; i < 65; i++) {
                System.out.print("-");
            }
            System.out.println("|");

            System.out.printf("| %-30s | %-30s |\n","Название курса", "Время подписки");

            System.out.print("|");
            for (int i = 0; i < 65; i++) {
                System.out.print("-");
            }
            System.out.println("|");

            ResultSet lastPoint = statement.executeQuery("SELECT MONTH(pl.subscription_date) point FROM PurchaseList pl WHERE pl.course_name = \"Веб-разработчик c 0 до PRO\" ORDER BY pl.subscription_date DESC LIMIT 1");

            String last = null;

            while (lastPoint.next())
                last = lastPoint.getString("point");

            lastPoint.close();

            ResultSet firstPoint = statement.executeQuery("SELECT MONTH(pl.subscription_date) point FROM PurchaseList pl WHERE pl.course_name = \"Веб-разработчик c 0 до PRO\" ORDER BY pl.subscription_date LIMIT 1");

            String first = null;

            while (firstPoint.next())
                first = firstPoint.getString("point");

            firstPoint.close();

            double averageNumber = Double.parseDouble(last) - Double.parseDouble(first) + 1.0;
            double sumPurchases = 0.0;
            ResultSet resultSet = statement.executeQuery("SELECT pl.course_name, pl.subscription_date FROM PurchaseList pl WHERE pl.course_name = \"Веб-разработчик c 0 до PRO\" ORDER BY pl.subscription_date");

            while (resultSet.next())
            {
                sumPurchases += 1.0;
                System.out.printf("| %-30s | %-30s |\n",resultSet.getString("course_name"), resultSet.getString("subscription_date"));
            }

            System.out.print("|");
            for (int i = 0; i < 65; i++) {
                System.out.print("-");
            }
            System.out.println("|");

            resultSet.close();
            statement.close();
            connection.close();

            double result = sumPurchases / averageNumber;

            System.out.println("Среднее количество покупок в месяц = " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
