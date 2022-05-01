package Ivin.HW;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestHW5 {

    static WebDriver driver;
    Logger logger = LoggerFactory.getLogger("Test-Case's 1-6");
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @BeforeAll
    static void initClass() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);

    }

    @BeforeEach
    void initTest() {
        driver.get("https://magazinmebeli73.ru/customer/account/logout/");
    }

    @AfterAll
    static void finalClass() {
        driver.quit();
    }

    @Test
    @DisplayName("Тест-кейс №1: Авторизация сайте (Негативный сценарий)")
    public void testCase1() {
        driver.get("https://magazinmebeli73.ru/customer/account/login/");
        driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys("lol@ro.ru");
        driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys("12345678");
        driver.findElement(By.xpath("//button[@name='send']")).click();
        String s = driver.findElement(By.xpath("//li[@class='error-msg']")).getText();
        assertTrue(s.equals("Неверный адрес электронной почты (email) или пароль."));

        logger.info("Тест-кейс №1 пройден");
    }

    @Test
    @DisplayName("Тест-кейс №2: Авторизация на сайте (Позитивный сценарий)")
    public void testCase2() {
        driver.get("https://magazinmebeli73.ru/customer/account/login/");
        driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys("nikos@yandex.ru");
        driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys("12345678");
        driver.findElement(By.xpath("//button[@name='send']")).click();
        String s = driver.findElement(By.xpath("//p[@class='hello']")).getText();
        assertTrue(s.equals("Здравствуйте, Роман Мулан!"));

        logger.info("Тест-кейс №2 пройден");
    }

    @Test
    @DisplayName("Тест-кейс №3: Проверка блока «Подписаться на Спецпредложения» (Позитивный сценарий)")
    public void testCase3() {
        driver.get("https://magazinmebeli73.ru/customer/account/login/");
        driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys("nikos@yandex.ru");
        driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys("12345678");
        driver.findElement(By.xpath("//button[@name='send']")).click();

        driver.get("https://magazinmebeli73.ru/");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("nikos@yandex.ru");
        driver.findElement(By.xpath("//button[@title='Отправить']")).click();
        String s = driver.findElement(By.xpath("//li[@class='success-msg']")).getText();
        assertTrue(s.equals("Спасибо за то что подписались на нашу рассылку."));

        logger.info("Тест-кейс №3 пройден");
    }

    @Test
    @DisplayName("Тест-кейс №4: Проверка блока «Подписаться на Спецпредложения» (Негативный сценарий)")
    public void testCase4() {
        driver.get("https://magazinmebeli73.ru/");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("lol@ro.ru");
        driver.findElement(By.xpath("//button[@title='Отправить']")).click();
        String s = driver.findElement(By.xpath("//li[@class='error-msg']")).getText();
        assertTrue(s.equals("Возникла проблема с подпиской: Извините, но подписка разрешена только для зарегистрированных пользователей. Пожалуйста, зарегистрируйтесь."));

        logger.info("Тест-кейс №4 пройден");
    }

    @Test
    @DisplayName("Тест-кейс №5: Добавление товара в корзину (Позитивный сценарий)")
    public void testCase5() {
        driver.get("https://magazinmebeli73.ru/customer/account/login/");
        driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys("nikos@yandex.ru");
        driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys("12345678");
        driver.findElement(By.xpath("//button[@name='send']")).click();

        driver.get("https://magazinmebeli73.ru/");

        driver.get("https://magazinmebeli73.ru/ism-msk-162321.html");
        driver.findElement(By.xpath("//button[@title='В корзину']")).click();

        driver.get("https://magazinmebeli73.ru/checkout/cart/");
        String s = driver.findElement(By.xpath("//div[@class='page-title title-buttons']")).getText();
        assertTrue(s.equals("Корзина"));

        logger.info("Тест-кейс №5 пройден");
    }

    @Test
    @DisplayName("Тест-кейс №6: Добавление купона (Негативный сценарий)")
    public void testCase6() {
        driver.get("https://magazinmebeli73.ru/customer/account/login/");
        driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys("nikos@yandex.ru");
        driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys("12345678");
        driver.findElement(By.xpath("//button[@name='send']")).click();

        driver.get("https://magazinmebeli73.ru/");

        driver.get("https://magazinmebeli73.ru/ism-msk-162321.html");
        driver.findElement(By.xpath("//button[@title='В корзину']")).click();

        driver.get("https://magazinmebeli73.ru/checkout/cart/");
        driver.findElement(By.xpath("//input[@name='coupon_code']")).sendKeys("wfaw13g");
        driver.findElement(By.xpath("//button[@title='Применить Купон']")).click();
        String s = driver.findElement(By.xpath("//li[@class='error-msg']")).getText();
        assertTrue(s.equals("Неверный код купона \"wfaw13g\"."));

        logger.info("Тест-кейс №6 пройден");

    }
}
