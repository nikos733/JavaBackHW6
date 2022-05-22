package Ivin.HW;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllTest extends BasisTest {
    JavascriptExecutor js = (JavascriptExecutor) getWebDriver();

    void saveBrowserLogs() {
        LogEntries browserLogs = getWebDriver().manage().logs().get(LogType.BROWSER);
        List<LogEntry> allLogRows = browserLogs.getAll();
        if (allLogRows.size() > 0) {
            allLogRows.forEach(logEntry -> {
                logger.debug("BROWSERLOGS: "+logEntry.getMessage());
            });
        }
    }

    @Test
    @DisplayName("Тест-кейс №1: Авторизация сайте (Негативный сценарий)")
    @Description("Тест-кейс №1: Авторизация сайте (Негативный сценарий)")
    @Link("https://magazinmebeli73.ru/customer/account/login/")
    @Severity(SeverityLevel.BLOCKER)
    @Epic("Тестирование интерфейса сайта")
    @Feature("Авторизация на сайте")
    @Story("Ошибка при входе в личный кабинет")
    public void testCase1() throws IOException {
            getWebDriver().get("https://magazinmebeli73.ru/customer/account/login/");
            assertTrue(
                    new AuthorizationPage(getWebDriver())
                            .setLogin("lol@ro.ru")
                            .setPassword("12345678")
                            .pressInBtt()
                            .isError()
            );
        saveBrowserLogs();
        String fileName =  "Test1 - " + System.currentTimeMillis() + ".png";
        DoScrenshot.makeScreenshot(getWebDriver(),fileName);
        logger.debug(" - тест-кейс №1 : ошибка авторизации");
        logger.info("Тест-кейс №1 пройден");
    }

    @Test
    @DisplayName("Тест-кейс №2: Авторизация на сайте (Позитивный сценарий)")
    @Description("Тест-кейс №2: Авторизация на сайте (Позитивный сценарий)")
    @Link("https://magazinmebeli73.ru/customer/account/login/")
    @Severity(SeverityLevel.BLOCKER)
    @Epic("Тестирование интерфейса сайта")
    @Feature("Авторизация на сайте")
    @Story("Вход на сайт по имени пользователя и паролю")
    public void testCase2() throws IOException {
        getWebDriver().get("https://magazinmebeli73.ru/customer/account/login/");
        new AuthorizationPage(getWebDriver())
                .setLogin("nikos@yandex.ru")
                .setPassword("12345678")
                .pressInBtt();
        assertTrue(new SubscriptionPage(getWebDriver()).checkUser("Здравствуйте, Роман Мулан!"));
        String fileName =  "Test2 - " + System.currentTimeMillis() + ".png";
        DoScrenshot.makeScreenshot(getWebDriver(),fileName);
        logger.debug(" - тест-кейс №2 : авторизация успешна");
        logger.info("Тест-кейс №2 пройден");
    }

    @Test
    @DisplayName("Тест-кейс №3: Проверка блока «Подписаться на Спецпредложения» (Позитивный сценарий)")
    @Description("Тест-кейс №3: Проверка блока «Подписаться на Спецпредложения» (Позитивный сценарий)")
    @Link("https://magazinmebeli73.ru/")
    @Severity(SeverityLevel.BLOCKER)
    @Epic("Тестирование интерфейса сайта")
    @Feature("Подписка на спецпредложения")
    @Story("Подписка осуществлена")
    public void testCase3() throws IOException {
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
        String fileName =  "Test3 - " + System.currentTimeMillis() + ".png";
        DoScrenshot.makeScreenshot(getWebDriver(),fileName);
        logger.debug(" - тест-кейс №3 : подписка осуществлена");
        logger.info("Тест-кейс №3 пройден");
    }

    @Test
    @DisplayName("Тест-кейс №4: Проверка блока «Подписаться на Спецпредложения» (Негативный сценарий)")
    @Description("Тест-кейс №4: Проверка блока «Подписаться на Спецпредложения» (Негативный сценарий)")
    @Link("https://magazinmebeli73.ru/")
    @Severity(SeverityLevel.BLOCKER)
    @Epic("Тестирование интерфейса сайта")
    @Feature("Подписка на спецпредложения")
    @Story("Ошибка при оформлении подписки")
    public void testCase4() throws IOException {
        getWebDriver().get("https://magazinmebeli73.ru/");
        assertTrue(
                new AuthorizationPage(getWebDriver())
                        .setEmail("lol@ro.ru")
                        .pressInSub()
                        .errorSub()
        );
        String fileName =  "Test4 - " + System.currentTimeMillis() + ".png";
        DoScrenshot.makeScreenshot(getWebDriver(),fileName);
        logger.debug(" - тест-кейс №4 : ошибка при оформлении подписки");
        logger.info("Тест-кейс №4 пройден");
    }

    @Test
    @DisplayName("Тест-кейс №5: Добавление товара в корзину (Позитивный сценарий)")
    @Description("Тест-кейс №5: Добавление товара в корзину (Позитивный сценарий)")
    @Link("https://magazinmebeli73.ru/")
    @Severity(SeverityLevel.BLOCKER)
    @Epic("Тестирование интерфейса сайта")
    @Feature("Добавление товара")
    @Story("Товар добавлен в корзину")
    public void testCase5() throws IOException {
        getWebDriver().get("https://magazinmebeli73.ru/customer/account/login/");
        new AuthorizationPage(getWebDriver())
                .setLogin("nikos@yandex.ru")
                .setPassword("12345678")
                .pressInBtt();
        driver.get("https://magazinmebeli73.ru/");
        driver.get("https://magazinmebeli73.ru/ism-msk-162321.html");
        new ProductPage(getWebDriver())
                .pressInBttProd();
        driver.get("https://magazinmebeli73.ru/checkout/cart/");
        assertTrue(new ProductPage(getWebDriver()).checkBasket("Корзина"));
        String fileName =  "Test5 - " + System.currentTimeMillis() + ".png";
        DoScrenshot.makeScreenshot(getWebDriver(),fileName);
        logger.debug(" - тест-кейс №5 : Товар добавлен в корзину");
        logger.info("Тест-кейс №5 пройден");
    }

    @Test
    @DisplayName("Тест-кейс №6: Добавление купона (Негативный сценарий)")
    @Description("Тест-кейс №6: Добавление купона (Негативный сценарий)")
    @Link("https://magazinmebeli73.ru/")
    @Severity(SeverityLevel.BLOCKER)
    @Epic("Тестирование интерфейса сайта")
    @Feature("Добавление купона")
    @Story("Ошибка применения купона")
    public void testCase6() throws IOException {
        getWebDriver().get("https://magazinmebeli73.ru/customer/account/login/");
        new AuthorizationPage(getWebDriver())
                .setLogin("nikos@yandex.ru")
                .setPassword("12345678")
                .pressInBtt();
        driver.get("https://magazinmebeli73.ru/");
        driver.get("https://magazinmebeli73.ru/ism-msk-162321.html");
        new ProductPage(getWebDriver())
                .pressInBttProd();
        driver.get("https://magazinmebeli73.ru/checkout/cart/");
        assertTrue(
                new CouponPage(getWebDriver())
                        .setCode("wfaw13g")
                        .pressInBttCode()
                        .errorMes()
        );
        String fileName =  "Test6 - " + System.currentTimeMillis() + ".png";
        DoScrenshot.makeScreenshot(getWebDriver(),fileName);
        logger.debug(" - тест-кейс №6 : Ошибка применения купона");
        logger.info("Тест-кейс №6 пройден");
    }
}
