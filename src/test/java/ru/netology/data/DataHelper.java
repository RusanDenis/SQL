package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;


public class DataHelper {
    private static final Faker faker = new Faker();

    private DataHelper() {
    }

    public static AuthInfo getAuthInfoWithValidUser() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getAuthInfoWithInvalidLogin() {
        return new AuthInfo(generateRandomLogin(), getAuthInfoWithValidUser().password);
    }

    public static AuthInfo getAuthInfoWithInvalidPass() {
        return new AuthInfo(getAuthInfoWithValidUser().login, generateRandomPassword());
    }

    private static String generateRandomLogin() {
        return faker.name().username();
    }

    private static String generateRandomPassword() {
        return faker.internet().password();
    }

    public static VerificationCode generateRandomVerificationCode() {
        return new VerificationCode(faker.numerify("######"));
    }

    public static VerificationCode getVerificationCode() {
        SQLHelper.getVerificationCode().getCode();
        return new VerificationCode(SQLHelper.getVerificationCode().getCode());
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

}