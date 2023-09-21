package com.qa.pages;

import com.qa.main.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage extends BaseTest {
    @AndroidFindBy (accessibility = "test-Username")
    private WebElement usernameTextid;
    @AndroidFindBy (accessibility = "test-Password")
    private WebElement passwordTextid;

    @AndroidFindBy (accessibility = "test-LOGIN")
    private WebElement loginButton;

    @AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
    private  WebElement errTxt;



    public LoginPage enterUserName(String uname ){
        System.out.println("Logged In With "+uname);
        waitForVisibility(usernameTextid);
        usernameTextid.sendKeys(uname);
        return  this;
    }

    public LoginPage enterPassword(String password ){
        System.out.println("Logged In With password"+password);
        waitForVisibility(passwordTextid);
        passwordTextid.sendKeys(password);
        return this;
    }

    public ProductsPage pressLoginBtn(){
        waitForVisibility(loginButton);
        loginButton.click();

        return  new ProductsPage();
    }

    public  String getErrorText(){
        return errTxt.getText();
    }

    public  ProductsPage login(String uname,String passowrd){
        waitForVisibility(usernameTextid);
        waitForVisibility(passwordTextid);
        waitForVisibility(loginButton);
        enterUserName(uname);
        enterPassword(passowrd);
        return  pressLoginBtn();
    }
}
