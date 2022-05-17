package Ivin.HW;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CouponPage extends AuthorizationPage {
    final String ErMessage = "Неверный код купона \"wfaw13g\".";

    @FindBy(xpath = "//input[@name='coupon_code']")
    private WebElement code;

    @FindBy(xpath = "//button[@title='Применить Купон']")
    private WebElement inBttCode;

    @FindBy(xpath = "//li[@class='error-msg']")
    private WebElement ErMes;

    public CouponPage(WebDriver driver) {
        super(driver);
    }

    public CouponPage setCode(String code){
        this.code.click();
        this.code.clear();
        this.code.sendKeys(code);
        return this;
    }

    public CouponPage pressInBttCode(){
        this.inBttCode.click();
        return this;
    }

    public Boolean errorMes() {
        return this.ErMes.getText().equals(ErMessage);
    }

}