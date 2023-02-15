package tests.day01;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.App;
import utils.Device;
import utils.Driver;

public class Test4 {
    By lContinue = By.id("com.android.permissioncontroller:id/continue_button");
    By lOkButton = By.id("android:id/button1");
    By lUnknown = By.id("com.touchboarder.android.api.demos:id/buttonDefaultPositive");
    By lApiDemos = By.xpath("//*[@text='API Demos']");
    By lAccessibility=(By.xpath("//*[@text='Accessibility']"));
    AppiumDriver<MobileElement> driver;

    @BeforeTest
    public void beforeTest() {
        Driver.runAppium();
        driver = Driver.getDriver(Device.HUAWEI, App.APIDEMO);
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
        Driver.stopAppium();
    }

    @Test
    public void test1() {
        driver.findElement(lContinue).click();
        driver.findElement(lOkButton).click();
        driver.findElement(lUnknown).click();
        driver.findElement(lApiDemos).click();
        driver.findElement(lAccessibility).click();
    }
    @Test
    public void test2() {
        click(lContinue); // test 1 ile farkı aşağıda tanımladığımız click methodunu çağırarak
        click(lOkButton); // doğrudan locator a click yaptırdık..
        click(lUnknown);
        click(lApiDemos);
        click(lAccessibility);
    }
    void click(By locator) {
        Driver.getDriver().findElement(locator).click();
    }


}
