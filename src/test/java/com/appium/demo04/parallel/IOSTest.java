package com.appium.demo04.parallel;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

/** Created by syamsasi on 07/11/18. */
public class IOSTest {
  AppiumDriver<MobileElement> driver;
  // AppiumDriverLocalService appiumDriverLocalService;

  @BeforeTest(alwaysRun = true)
  @Parameters({"platform", "deviceName", "wdaLocalPort"})
  public void setUp(String platform, String deviceName, String wdaLocalPort)
      throws MalformedURLException {

    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
    caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
    caps.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, Integer.parseInt(wdaLocalPort));

    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
    caps.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/build/aws.app");
    caps.setCapability("noReset", Boolean.TRUE);
    driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
  }

  @AfterTest
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
    String myText = "I love PhTestCon";
    el3.sendKeys(myText);
    assertThat(el3.getAttribute("value"))
        .as("Check the text has displayed or not")
        .isEqualTo(myText);
  }
}
