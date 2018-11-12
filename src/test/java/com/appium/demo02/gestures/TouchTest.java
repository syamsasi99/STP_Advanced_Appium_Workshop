package com.appium.demo02.gestures;

import com.appium.demo01.textfield.AppiumHelper;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;

/** Created by syamsasi on 07/11/18. */
public class TouchTest extends BaseTest {

  private void doSwipe(String direction) throws InterruptedException {

    TouchAction touch = new TouchAction((MobileDriver) driver);
    TouchAction swipe = null;
    WebElement rootElement = getRootElement();

    if (direction.equals("down")) {

      int x1 = rootElement.getSize().width / 2;
      int y1 = rootElement.getSize().height / 2;
      int x2 = rootElement.getSize().width / 2;
      int y2 = (rootElement.getSize().height * 9) / 10;

      swipe =
          touch
              .press(ElementOption.element(rootElement, x2, y2))
              .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
              .moveTo(ElementOption.element(rootElement, x1, y1))
              .release();
    } else if (direction.equals("up")) {

      int x1 = rootElement.getSize().width / 2;
      int y1 = (rootElement.getSize().height * 8) / 10;
      int x2 = rootElement.getSize().width / 2;
      int y2 = rootElement.getSize().height / 2;

      swipe =
          touch
              .press(ElementOption.element(rootElement, x2, y2))
              .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
              .moveTo(ElementOption.element(rootElement, x1, y1))
              .release();
    } else if (direction.equals("right")) {

      int x1 = rootElement.getSize().width / 10;
      int y1 = rootElement.getSize().height / 2;
      int x2 = (rootElement.getSize().width * 9) / 10;
      int y2 = rootElement.getSize().height / 2;

      swipe =
          touch
              .press(ElementOption.element(rootElement, x2, y2))
              .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
              .moveTo(ElementOption.element(rootElement, x1, y1))
              .release();
    } else if (direction.equals("left")) {

      int x1 = (rootElement.getSize().width * 9) / 10;
      int y1 = rootElement.getSize().height / 2;
      int x2 = rootElement.getSize().width / 10;
      int y2 = rootElement.getSize().height / 2;

      swipe =
          touch
              .press(ElementOption.element(rootElement, x2, y2))
              .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
              .moveTo(ElementOption.element(rootElement, x1, y1))
              .release();
    }

    swipe.perform();
  }

  private WebElement getRootElement() throws InterruptedException {

    WebElement rootElement = null;
    By xpathLocator = By.xpath("//*");
    try {
      rootElement = driver.findElement(xpathLocator);
    } catch (StaleElementReferenceException e) {
      Thread.sleep(5000);
      rootElement = driver.findElement(xpathLocator);
    }
    return rootElement;
  }

  @Test
  public void swipeRightTest() throws InterruptedException {
    goToNativeComponents();
    if (driver instanceof AndroidDriver) {

      doSwipe("right");
      doSwipe("down");
      doSwipe("up");
      doSwipe("left");
    } else {
      doSwipe("down");
      doSwipe("up");
    }
  }

  private void goToTextInputControl() throws InterruptedException {

    if (driver instanceof AndroidDriver) {
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

    } else {

      MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("Inputs");
      AppiumHelper.waitForElementVisible(driver, el2);
      el2.click();
      MobileElement el3 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeTextView");
      AppiumHelper.waitForElementVisible(driver, el3);
    }
  }

  private void goToNativeComponents() throws InterruptedException {

    if (driver instanceof AndroidDriver) {
      MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("ReferenceApp");
      AppiumHelper.waitForElementVisible(driver, el1);
      el1.click();
      Thread.sleep(1000);
      MobileElement el2 =
          (MobileElement)
              driver.findElementByXPath("//android.widget.TextView[@text='Native Components']");
      AppiumHelper.waitForElementVisible(driver, el2);
      el2.click();
      MobileElement el3 =
          (MobileElement)
              driver.findElementByXPath("//android.widget.TextView[@text='Image Collection']");

    } else {

      MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("Native");
      AppiumHelper.waitForElementVisible(driver, el2);
      el2.click();
      MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("Scrolling View");
      AppiumHelper.waitForElementVisible(driver, el3);
      el3.click();
      Thread.sleep(2000);
    }
  }

  private void scroll(String direction) throws InterruptedException {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    HashMap<String, String> scrollObject = new HashMap<>();
    scrollObject.put("direction", direction);
    js.executeScript("mobile: scroll", scrollObject);
    Thread.sleep(1000);
  }
}
