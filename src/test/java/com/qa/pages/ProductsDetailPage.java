package com.qa.pages;

import com.qa.main.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ProductsDetailPage extends BaseTest {

    @AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]")
    private WebElement productTitle;

    @AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[2]")
    private WebElement productDesc;

    @AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"test-Price\"]")
    private WebElement productPrice;

    @AndroidFindBy(accessibility = "test-ADD TO CART")
    private WebElement addToCart;

    @AndroidFindBy(accessibility = "test-BACK TO PRODUCTS")
    private WebElement backToProductBtn;


    public    String  getSLBTitle(){
        waitForVisibility(productTitle);
       return productTitle.getText();

    }

    public    String  getSLBTxt(){
        waitForVisibility(productDesc);
        return productDesc.getText();

    }

    public    String  getSLBPrice(){
        waitForVisibility(productPrice);
        return productPrice.getText();

    }
    public ProductsPage backToHome(){
        waitForVisibility(backToProductBtn);
        backToProductBtn.click();
        return  new ProductsPage();
    }
}
