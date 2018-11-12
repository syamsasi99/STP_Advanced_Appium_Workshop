package com.appium.demo03.pageobject.tests;

import com.appium.demo03.pageobject.pages.HomePage;
import com.appium.demo03.pageobject.pages.InputControlsPage;
import com.appium.demo03.pageobject.utils.BaseTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.Test;

/** Created by syamsasi on 07/11/18. */
public class EnterTextTest extends BaseTest {

  HomePage homePage;

  @Test
  public void enterText() throws InterruptedException {
    homePage = new HomePage(driver);
    String inputString = "This is a page object design";
    InputControlsPage inputControlsPage =
        homePage.clickMenu().clickInputControls().enterText(inputString);
    Thread.sleep(2000);
    assertThat(inputControlsPage.getCurrentTextValue())
        .as("Check the text has displayed or not")
        .isEqualTo(inputString);
  }
}
