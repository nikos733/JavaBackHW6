package Ivin.HW;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionTest extends BasisTest {

    @Test
    @DisplayName("Тест-кейс №3: Проверка блока «Подписаться на Спецпредложения» (Позитивный сценарий)")
    public void testCase3() {
        getWebDriver().get("https://magazinmebeli73.ru/customer/account/login/");
        new AuthorizationPage(getWebDriver())
                .setLogin("nikos@yandex.ru")
                .setPassword("12345678")
                .pressInBtt();
        driver.get("https://magazinmebeli73.ru/");
        new SubscriptionPage(getWebDriver())
                .setEmail("nikos@yandex.ru")
                .pressInSub();
        assertTrue(new SubscriptionPage(getWebDriver()).checkSub("Спасибо за то что подписались на нашу рассылку."));
        logger.info("Тест-кейс №3 пройден");
    }

    @Test
    @DisplayName("Тест-кейс №4: Проверка блока «Подписаться на Спецпредложения» (Негативный сценарий)")
    public void testCase4() {
        getWebDriver().get("https://magazinmebeli73.ru/");
        assertTrue(
        new AuthorizationPage(getWebDriver())
                .setEmail("lol@ro.ru")
                .pressInSub()
                .errorSub()
        );
        logger.info("Тест-кейс №4 пройден");
    }
}
