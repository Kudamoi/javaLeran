import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "12345";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM purchaseList pl WHERE YEAR(pl.subscription_date) = 2018 ORDER BY pl.subscription_date");

            HashMap<String, Integer> countPurchase = new HashMap<>();
            HashMap<String, Date> minPurchase = new HashMap<>();
            HashMap<String, Date> maxPurchase = new HashMap<>();


            while (resultSet.next()) {
                int count = 1;
                Date minDate = resultSet.getDate("subscription_date");
                Date maxDate = resultSet.getDate("subscription_date");
                if (countPurchase.containsKey(resultSet.getString("course_name"))) {
                    if (minPurchase.get(resultSet.getString("course_name")).before(minDate))
                        minDate = minPurchase.get(resultSet.getString("course_name"));

                    if (maxPurchase.get(resultSet.getString("course_name")).after(maxDate))
                        maxDate = minPurchase.get(resultSet.getString("course_name"));

                    count = countPurchase.get(resultSet.getString("course_name")) + 1;
                }
                maxPurchase.put(resultSet.getString("course_name"), maxDate);
                minPurchase.put(resultSet.getString("course_name"), minDate);
                countPurchase.put(resultSet.getString("course_name"), count);
            }

            resultSet.close();
            statement.close();
            connection.close();

            for (String key : countPurchase.keySet()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate startDate = LocalDate.parse(minPurchase.get(key).toString(), formatter);
                LocalDate endDate = LocalDate.parse(maxPurchase.get(key).toString(), formatter);

                System.out.printf("%-35s => Count purchase: %-2d  |  First Date: %s  |  Last Date: %s  |  Conversion: %s\n", key, countPurchase.get(key), minPurchase.get(key), maxPurchase.get(key), Math.floor((double) countPurchase.get(key) / (double) (endDate.getMonthValue() - startDate.getMonthValue()) * 100) / 100);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
