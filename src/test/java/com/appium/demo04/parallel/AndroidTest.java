package com.appium.demo04.parallel;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
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
public class AndroidTest {
  AppiumDriver driver;
  @BeforeTest(alwaysRun = true)
  @Parameters({"platform", "udid", "systemPort","avd"})
  public void setUp(String platform, String udid, String systemPort, String avd) throws MalformedURLException {

    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
    caps.setCapability(MobileCapabilityType.UDID, udid);
    caps.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort);
    caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel");
    caps.setCapability("avd", avd);
    caps.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/build/aws.apk");
    driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
  }

  @AfterTest
  public void tearDown() {
   driver.quit();
  }

  @Test
  public void enterStringonTextField() throws InterruptedException {

    MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("ReferenceApp");
    AppiumHelper.waitForElementVisible(driver, el1);
    el1.click();
    Thread.sleep(1000);
    MobileElement el2 =
        (MobileElement)
            driver.findElementByXPath("//android.widget.TextView[@text='Input Controls']");
    AppiumHelper.waitForElementVisible(driver, el2);
    el2.click();
    MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("Text Input Control");
    AppiumHelper.waitForElementVisible(driver, el3);
    AppiumHelper.clearTextField(driver, el3);
    String myText = "I love MANILA";
    el3.sendKeys(myText);
    assertThat(el3.getAttribute("text"))
        .as("Check the text has displayed or not")
        .isEqualTo(myText);
  }
}
