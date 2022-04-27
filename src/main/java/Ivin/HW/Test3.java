package Ivin.HW;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Test3 {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-popup-blocking");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://magazinmebeli73.ru/customer/account/logout/");

        driver.get("https://magazinmebeli73.ru/customer/account/login/");
        driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys("nikos@yandex.ru");
        driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys("12345678");
        driver.findElement(By.xpath("//button[@name='send']")).click();

        driver.get("https://magazinmebeli73.ru/");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("nikos@yandex.ru");
        driver.findElement(By.xpath("//button[@title='Отправить']")).click();

        System.out.println("Тест №3 пройден");

        driver.quit();
    }
}
