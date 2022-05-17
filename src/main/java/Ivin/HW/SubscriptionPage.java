package Ivin.HW;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SubscriptionPage extends BasisPage {
    final String Message = "Возникла проблема с подпиской: Извините, но подписка разрешена только для зарегистрированных пользователей. Пожалуйста, зарегистрируйтесь.";

    @FindBy(xpath = "//p[@class='hello']")
    private WebElement userName;

    @FindBy(xpath = "//li[@class='success-msg']")
    private WebElement Sub;

    @FindBy(xpath = "//li[@class='error-msg']")
    private WebElement erSub;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement Email;

    @FindBy(xpath = "//button[@title='Отправить']")
    private WebElement inSub;

    public SubscriptionPage(WebDriver driver){
        super(driver);
    }

    public Boolean checkUser(String chUserName){
        String s = userName.getText();
        return s.equals(chUserName);
    }

    public SubscriptionPage setEmail(String Email){
        this.Email.click();
        this.Email.clear();
        this.Email.sendKeys(Email);
        return this;
    }

    public SubscriptionPage pressInSub(){
        this.inSub.click();
        return this;
    }

    public Boolean checkSub(String chSub){
        String s = Sub.getText();
        return s.equals(chSub);
    }

    public Boolean errorSub() {
        return this.erSub.getText().equals(Message);
    }

}
