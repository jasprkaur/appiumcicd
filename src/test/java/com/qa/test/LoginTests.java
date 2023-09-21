package com.qa.test;

import com.qa.main.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.*;

public class LoginTests extends BaseTest {
    LoginPage loginPage;
    ProductsPage productsPage;
    JSONObject loginUsers;

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
    }

    @AfterClass
    public void afterClass() {

    }

    @BeforeMethod
    public void beforMethod(Method m) {
        loginPage = new LoginPage();
        productsPage = new ProductsPage();
        System.out.println("Starting Point:  " + m.getName());

    }

    @AfterMethod
    public void afterMethod() {

    }

    @Test
    public void invalidUserName() {
        loginPage.enterUserName(loginUsers.getJSONObject("invalidUser").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("invalidUser").getString("password"));
        loginPage.pressLoginBtn();
       // Assert.assertEquals(loginPage.getErrorText(), "Username and password do not match any user in this service.");
        Assert.assertEquals(loginPage.getErrorText(), strings.get("err_invalid_username_password"));
    }

    @Test
    public void invalidPassword() {
        loginPage.enterUserName(loginUsers.getJSONObject("invalidPassword").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("invalidPassword").getString("password"));
        loginPage.pressLoginBtn();
        Assert.assertEquals(loginPage.getErrorText(), strings.get("err_invalid_username_password"));

    }

    @Test
    public void validUserName() {
        loginPage.enterUserName(loginUsers.getJSONObject("validUser").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
        loginPage.pressLoginBtn();
        Assert.assertEquals(ProductsPage.getTitle(), strings.get("products_title"));

    }
}
