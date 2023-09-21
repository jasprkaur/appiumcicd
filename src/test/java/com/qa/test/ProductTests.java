package com.qa.test;

import com.qa.main.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsDetailPage;
import com.qa.pages.ProductsPage;
import com.qa.pages.SettingPage;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class ProductTests extends BaseTest {
    LoginPage loginPage;
    ProductsPage productsPage;
    JSONObject loginUsers;
    SettingPage settingPage;

    ProductsDetailPage productsDetailPage;


    @BeforeClass
    public void beforeClass() throws IOException {
        InputStream datais = null;
        try {

            String dataFileName = "data/loginUsers.json";
            datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
            JSONTokener tokner = new JSONTokener(datais);
            loginUsers = new JSONObject(tokner);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (datais != null)
                datais.close();
        }
        closeApp();
        launchApp();
    }

    @AfterClass
    public void afterClass() {

    }

    @BeforeMethod
    public void beforMethod(Method m) {
        loginPage = new LoginPage();
        System.out.println("Starting Point:  " + m.getName());

    }

    @AfterMethod
    public void afterMethod() {

    }

    @Test
    public void validateProductsOnProductsPage() {
        SoftAssert sa=new SoftAssert();
        productsPage=loginPage.login(loginUsers.getJSONObject("validUser").getString("username"),loginUsers.getJSONObject("validUser").getString("password"));
        String slbTitle=productsPage.getSLBTitle();
        String slbPrice=productsPage.getSLBPrice();
        sa.assertEquals(slbTitle, strings.get("products_page_slb_title"));
        sa.assertEquals(slbPrice, strings.get("products_page_slb_price"));
        settingPage=productsPage.pressSettingBtn();
        loginPage=settingPage.pressLogOutButton();
        sa.assertAll();
    }

    @Test
    public void validateProductsOnProductDetailsPage() {
        SoftAssert sa=new SoftAssert();
        productsPage=loginPage.login(loginUsers.getJSONObject("validUser").getString("username"),loginUsers.getJSONObject("validUser").getString("password"));
        productsDetailPage=productsPage.pressSLBTitle();
        sa.assertEquals( productsDetailPage.getSLBTitle(), strings.get("products_detail_page_slb_title"));
        sa.assertEquals( productsDetailPage.getSLBTxt(), strings.get("products_detail_page_slb_text"));
        productsPage=productsDetailPage.backToHome();
        settingPage=productsPage.pressSettingBtn();
        loginPage=settingPage.pressLogOutButton();
        sa.assertAll();
    }


}
