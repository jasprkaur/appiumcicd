package com.qa.pages;

import com.qa.main.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class MenuPage extends BaseTest {


    @AndroidFindBy (xpath="//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView\n" +
            "")

    private WebElement  settingBtn;

    public  SettingPage pressSettingBtn(){
        waitForVisibility(settingBtn);
         settingBtn.click();
         return  new SettingPage();
    }
}
