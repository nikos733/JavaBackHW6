package Ivin.HW;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.WebDriver;

public class AuthorizationPage extends SubscriptionPage {
    final String errorMessage = "Неверный адрес электронной почты (email) или пароль.";

    @FindBy(xpath = "//input[@name='login[username]']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@name='login[password]']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@name='send']")
    private WebElement inBtt;

    @FindBy(xpath = "//li[@class='error-msg']")
    private WebElement error;

    public AuthorizationPage(WebDriver driver){
        super (driver);
    }

    public AuthorizationPage setLogin(String login){
        this.loginField.click();
        this.loginField.clear();
        this.loginField.sendKeys(login);
        return this;
    }

    public AuthorizationPage setPassword(String password){
        this.passwordField.click();
        this.passwordField.clear();
        this.passwordField.sendKeys(password);
        return this;
    }

    public AuthorizationPage pressInBtt(){
        this.inBtt.click();
        return this;
    }

    public Boolean isError(){
        return this.error.getText().equals(errorMessage);
    }

}
