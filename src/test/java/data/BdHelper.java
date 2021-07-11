package data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class BdHelper {
    private static final QueryRunner runner = new QueryRunner();
    private final Connection conn = getConnect();

    @SneakyThrows
    private Connection getConnect() {
        return DriverManager.getConnection(
                System.getProperty("spring.datasource.url"),
                System.getProperty("spring.datasource.username"),
                System.getProperty("spring.datasource.password")
        );
    }

    @SneakyThrows
    public String getPaymentStatus() {
        val status = "SELECT status FROM payment_entity ORDER BY created DESC";
        return runner.query(conn, status, new ScalarHandler<>());
    }

    @SneakyThrows
    public Integer getPaymentAmount() {
        val amount = "SELECT amount FROM payment_entity ORDER BY created DESC";
        return runner.query(conn, amount, new ScalarHandler<>());
    }

    @SneakyThrows
    public String getCreditRequestStatus() {
        val status = "SELECT status FROM credit_request_entity ORDER BY created DESC";
        return runner.query(conn, status, new ScalarHandler<>());
    }

    @SneakyThrows
    public String getCreditId() {
        val id = "SELECT credit_id FROM order_entity ORDER BY created DESC";
        return runner.query(conn, id, new ScalarHandler<>());
    }
}
