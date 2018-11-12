package com.appium.demo03.pageobject.objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.springframework.stereotype.Component;

/**
 * Created by syamsasi on 07/02/17.
 */
@Component
public class HomePageObject {

    @AndroidFindBy(accessibility = "ReferenceApp")
    public MobileElement menuButton;

    @iOSFindBy(accessibility = "Inputs")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Input Controls']")
    public MobileElement inputControls;

}
