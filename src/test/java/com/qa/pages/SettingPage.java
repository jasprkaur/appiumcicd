package com.qa.pages;

import com.qa.main.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class SettingPage  extends BaseTest {
    @AndroidFindBy(accessibility ="test-LOGOUT")
    private WebElement logoutBtn;

    public LoginPage pressLogOutButton(){
        logoutBtn.click();
        return new LoginPage();
    }
}
