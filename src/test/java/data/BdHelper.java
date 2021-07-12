package data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BdHelper {
    private static final QueryRunner runner = new QueryRunner();

       public static Connection getConnection() throws SQLException {
        String dbUrl = System.getProperty("db.url");
        String login = System.getProperty("login");
        String password = System.getProperty("password");
        final Connection connection = DriverManager.getConnection(dbUrl, login, password);
        return connection;
    }



    @SneakyThrows
    public String getPaymentStatus() {
        val status = "SELECT status FROM payment_entity ORDER BY created DESC";
        return runner.query(getConnection(), status, new ScalarHandler<>());
    }

    @SneakyThrows
    public Integer getPaymentAmount() {
        val amount = "SELECT amount FROM payment_entity ORDER BY created DESC";
        return runner.query(getConnection(), amount, new ScalarHandler<>());
    }

    @SneakyThrows
    public String getCreditRequestStatus() {
        val status = "SELECT status FROM credit_request_entity ORDER BY created DESC";
        return runner.query(getConnection(), status, new ScalarHandler<>());
    }

    @SneakyThrows
    public String getCreditId() {
        val id = "SELECT credit_id FROM order_entity ORDER BY created DESC";
        return runner.query(getConnection(), id, new ScalarHandler<>());
    }
}
