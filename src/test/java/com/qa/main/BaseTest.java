package com.qa.main;


import com.qa.util.TestUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;


public class BaseTest {
    protected static AppiumDriver driver;
    protected static Properties prop;
    protected static HashMap<String, String> strings = new HashMap<String, String>();
    InputStream stringis;
    TestUtil utils;

    public static   String platform2;
    public BaseTest() {

        // PageFactory.initElements(new AppiumFieldDecorator(driver),this);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }


    @Parameters({"emulator", "platformName", "platformVersion","udid", "deviceName"})
    @BeforeTest
    public void beforeTest(String emulator, String platformName, String platformVersion,String udid, String deviceName) throws Exception {
        utils = new TestUtil();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        String xmlFileName = "strings/strings.xml";
        stringis = getClass().getClassLoader().getResourceAsStream(xmlFileName);
        strings = utils.parseStringXML(stringis);
        platform2=platformName;
        try {
            prop = new Properties();
            prop.load(inputStream);
            String appPath = System.getProperty("user.dir");//+ File.separator + "TDDFramework";
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);

            //URL url = new URL("http://0.0.0.0:4723");
            URL url = new URL(prop.getProperty("appiumURL"));
            switch (platformName) {
                case "Android":
                    System.out.println("Entered switch Android");
                    capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
                    if (emulator.equalsIgnoreCase("true")) {
                        capabilities.setCapability("avd", deviceName);
                        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
                    }
                    else {
                        capabilities.setCapability("udid", udid);
                    }
                    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.get("androidAutomationName"));
                    capabilities.setCapability("newCommandTimeout", 300);
                    capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, prop.get("androidAppPackage"));
                    capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, prop.get("androidAppActivity"));
                    System.out.println("App FilePath is ****" + System.getProperty("user.dir") + File.separator + "src" + File.separator +
                            "test" + File.separator + "resources" + File.separator + "app" + File.separator + "SauceLabs.apk");
                    //  String appUrl = getClass().getResource(prop.getProperty("androidAppLocation")).getFile();
                    //  System.out.println("AppURL IS *******"+appUrl);
                    //   capabilities.setCapability(MobileCapabilityType.APP, appUrl);
                    capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + File.separator + "src" + File.separator +
                            "test" + File.separator + "resources" + File.separator + "app" + File.separator + "SauceLabs.apk");
                    driver = new AndroidDriver(url, capabilities);
                    break;


                case "iOS":
                    capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
                    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 11");
                    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.get("iosAutomationName"));
                    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
                    capabilities.setCapability("newCommandTimeout", 300);
                    capabilities.setCapability(MobileCapabilityType.APP, appPath + File.separator + "src" + File.separator + "" +
                            "test" + File.separator + "resources" + File.separator + "iOS.RealDevice.SauceLabs.Mobile.Sample.app.2.7.1.ipa");

                    driver = new IOSDriver(url, capabilities);
                    break;

                default:
                    throw new Exception("Invalid Platform" + platformName);


            }
        } catch (Exception e) {

            throw e;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (stringis != null) {
                stringis.close();
            }
        }

    }


    /*  public HashMap<String, String> getStrings() {
          return strings.get();
      }

      public void setStrings(HashMap<String, String> strings2) {
          strings.set(strings2);
      }*/
    public static void waitForVisibility(WebElement e) {
        WebDriverWait wait = new WebDriverWait(driver, TestUtil.wait);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    public void setDriver(AppiumDriver driver2) {
        this.driver = driver2;
    }

    public void closeApp(){
        switch (getPlatform()){
            case "Android":
                ((InteractsWithApps) getDriver()).terminateApp(prop.getProperty("androidAppPackage"));
                 break;
            case "iOS":
                ((InteractsWithApps) getDriver()).terminateApp(prop.getProperty("iOSBundleId"));
                break;
        }
    }

    public void launchApp(){
        switch (getPlatform()){
            case "Android":
                ((InteractsWithApps) getDriver()).activateApp(prop.getProperty("androidAppPackage"));
                break;
            case "iOS":
                ((InteractsWithApps) getDriver()).activateApp(prop.getProperty("iOSBundleId"));
                break;
        }
    }

    public String getPlatform() {
        System.out.println("Platform2 is "+platform2);
        return platform2;
    }


    /*public void setPlatform(String platform2) {
        platform.set(platform2);
    }*/
}
