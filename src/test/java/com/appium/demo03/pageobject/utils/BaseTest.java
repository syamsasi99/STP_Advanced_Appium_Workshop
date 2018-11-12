package com.appium.demo03.pageobject.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;

/** Created by syamsasi on 07/11/18. */
public class BaseTest {
  public AppiumDriver driver;
  DesiredCapabilities caps;
  public WebDriverWait wait;

  @BeforeSuite
  public void setUp() throws MalformedURLException {

    // Change to false to run iOS test
    boolean isAndroid = true;
    caps = new DesiredCapabilities();
    caps.setCapability("noReset", Boolean.TRUE);
    if (isAndroid) {
      caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
      caps.setCapability("avd", "Pixel");
      caps.setCapability("platformName", "android");
      caps.setCapability(
          MobileCapabilityType.APP, System.getProperty("user.dir") + "/build/aws.apk");
      driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
    } else {
      caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
      caps.setCapability("platformName", "iOS");
      caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
      caps.setCapability(
          MobileCapabilityType.APP, System.getProperty("user.dir") + "/build/aws.app");
      driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
    }
  }

  @AfterSuite
  public void tearDown() {
    driver.quit();
  }
}
