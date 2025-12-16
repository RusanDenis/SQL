package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLHelper {
    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    public static DataHelper.VerificationCode getVerificationCode() {
        var codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
        var conn = getConnection();
        var verificationCode = runner.query(conn, codeSQL, new ScalarHandler<String>());
        return new DataHelper.VerificationCode(verificationCode);
    }

    @SneakyThrows
    public static void cleanDB() {
        var conn = getConnection();
        runner.update(conn, "DELETE FROM cards");
        runner.update(conn, "DELETE FROM card_transactions");
        runner.update(conn, "DELETE FROM auth_codes");
        runner.update(conn, "DELETE FROM users");
    }
}