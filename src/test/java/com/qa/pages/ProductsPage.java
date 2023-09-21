package com.qa.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ProductsPage extends MenuPage {
    @AndroidFindBy(xpath = "//*[contains(@text,'PRODUCTS')]")
    public static WebElement productTitle;

    @AndroidFindBy(xpath="(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
    public  static  WebElement SLBtitle;

    @AndroidFindBy(xpath ="(//android.widget.TextView[@content-desc=\"test-Price\"])[1]")
    public  static WebElement  SLBPrice;

    public static String getTitle() {
        return productTitle.getText();
    }

    public static String getSLBTitle() {
        return SLBtitle.getText();
    }

    public static String getSLBPrice() {
        return SLBPrice.getText();
    }
    public static ProductsDetailPage pressSLBTitle(){
        waitForVisibility(SLBtitle);
        SLBtitle.click();
        return  new ProductsDetailPage();
    }
}