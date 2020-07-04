package learning.mybatis;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/books", "sa", "");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select id, title, published_on from book")) {

            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String title = resultSet.getString(2);
                Date publishedOn = resultSet.getDate(3);

                System.out.printf("%d, %s, %s", id, title, publishedOn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
