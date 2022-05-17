package Ivin.HW;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends AuthorizationPage {

    @FindBy(xpath = "//button[@title='В корзину']")
    private WebElement inBttProd;

    @FindBy(xpath = "//div[@class='page-title title-buttons']")
    private WebElement basket;


    public ProductPage (WebDriver driver) {
        super(driver);
    }

    public ProductPage pressInBttProd(){
        this.inBttProd.click();
        return this;
    }

    public Boolean checkBasket(String chBasket){
        String s = basket.getText();
        return s.equals(chBasket);
    }

}
