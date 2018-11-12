package com.appium.demo01.textfield;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/** Created by syamsasi on 07/11/18. */
public class IOSTest {
  AppiumDriver<MobileElement> driver;
  // AppiumDriverLocalService appiumDriverLocalService;

  @Before
  public void setUp() throws MalformedURLException {

    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
    caps.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/build/aws.app");
    caps.setCapability("noReset", Boolean.TRUE);
    driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void enterStringOnTextField() throws InterruptedException {

    MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("Inputs");
    AppiumHelper.waitForElementVisible(driver, el2);
    el2.click();
    MobileElement el3 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeTextView");
    AppiumHelper.waitForElementVisible(driver, el3);
    AppiumHelper.clearTextField(driver, el3);
    String myText = "I love STP";
    el3.sendKeys(myText);
    assertThat(el3.getAttribute("value"))
        .as("Check the text has displayed or not")
        .isEqualTo(myText);
  }
}
