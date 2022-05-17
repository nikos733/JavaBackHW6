package Ivin.HW;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductTest extends BasisTest {

    @Test
    @DisplayName("Тест-кейс №5: Добавление товара в корзину (Позитивный сценарий)")
    public void testCase5() {
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
        logger.info("Тест-кейс №5 пройден");
    }

    @Test
    @DisplayName("Тест-кейс №6: Добавление купона (Негативный сценарий)")
    public void testCase6() {
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
        logger.info("Тест-кейс №6 пройден");
    }
}
