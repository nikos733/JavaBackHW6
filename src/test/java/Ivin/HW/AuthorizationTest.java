package Ivin.HW;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorizationTest extends BasisTest {

    @Test
    @DisplayName("Тест-кейс №1: Авторизация сайте (Негативный сценарий)")
    public void testCase1() {
        getWebDriver().get("https://magazinmebeli73.ru/customer/account/login/");
        assertTrue(
                new AuthorizationPage(getWebDriver())
                        .setLogin("lol@ro.ru")
                        .setPassword("12345678")
                        .pressInBtt()
                        .isError()
        );
        logger.info("Тест-кейс №1 пройден");
    }
    @Test
    @DisplayName("Тест-кейс №2: Авторизация на сайте (Позитивный сценарий)")
    public void testCase2() {
        getWebDriver().get("https://magazinmebeli73.ru/customer/account/login/");
        new AuthorizationPage(getWebDriver())
                .setLogin("nikos@yandex.ru")
                .setPassword("12345678")
                .pressInBtt();
        assertTrue(new SubscriptionPage(getWebDriver()).checkUser("Здравствуйте, Роман Мулан!"));
        logger.info("Тест-кейс №2 пройден");
    }
}
