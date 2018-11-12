package com.appium.demo03.pageobject.pages;

import com.appium.demo03.pageobject.objects.InputControlsPageObject;
import com.appium.demo03.pageobject.utils.AppiumHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

/** Created by syamsasi on 07/02/17. */
public class InputControlsPage {

  AppiumDriver driver;
  InputControlsPageObject inputControlsPageObject;

  public InputControlsPage(AppiumDriver driver) {
    this.driver = driver;
    inputControlsPageObject = new InputControlsPageObject();
    PageFactory.initElements(new AppiumFieldDecorator(driver), inputControlsPageObject);
  }

  public InputControlsPage enterText(String keys) {
    AppiumHelper.clearTextField(driver, inputControlsPageObject.textField).sendKeys(keys);
    return new InputControlsPage(driver);
  }

  public String getCurrentTextValue() {
    AppiumHelper.waitForElementVisible(driver, inputControlsPageObject.textField);
    String currentTextValue = null;
    if (driver instanceof AndroidDriver) {
      currentTextValue = inputControlsPageObject.textField.getAttribute("text");
    } else if (driver instanceof IOSDriver) {
      currentTextValue = inputControlsPageObject.textField.getAttribute("value");
    }
    return currentTextValue;
  }
}
