package Ivin.HW;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Test1 {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-popup-blocking");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://magazinmebeli73.ru/customer/account/logout/");

        driver.get("https://magazinmebeli73.ru/customer/account/login/");
        driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys("lol@ro.ru");
        driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys("12345678");
        driver.findElement(By.xpath("//button[@name='send']")).click();

        System.out.println("Тест №1 пройден");

        driver.quit();
    }
}
