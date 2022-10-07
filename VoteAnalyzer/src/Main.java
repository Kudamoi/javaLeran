import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/learn?useSSL=false";
        String user = "root";
        String passwd = "12345";
        Connection con = DriverManager.getConnection(url, user, passwd);
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM test");
        while (rs.next()) {
            String st = rs.getString(1);
            System.out.println(st);
        }
    }
}
