package tests.day01;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.App;
import utils.Device;
import utils.Driver;

import java.text.MessageFormat;

public class Test5 {
    By lContinue = By.id("com.android.permissioncontroller:id/continue_button");
    By lOkButton = By.id("android:id/button1");
    By lUnknown = By.id("com.touchboarder.android.api.demos:id/buttonDefaultPositive");
    By lApiDemos = By.xpath("//*[@text='API Demos']");
    By lAccessibility=(By.xpath("//*[@text='Accessibility']"));
    String textXpath="//*[@text=\"{0}\"]"; //message methodu tek tırnak kullanmıyor, escape \ karakteri ile
                                           // birlikte çift tırnak kullanıyor.
    AppiumDriver<MobileElement> driver;
    WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        Driver.runAppium();
        driver = Driver.getDriver(Device.HUAWEI, App.APIDEMO);
        wait=new WebDriverWait(driver,10);
        click(lContinue);
        click(lOkButton);
        click(lUnknown);
        click(lApiDemos);
    }
    @AfterTest
    public void afterTest() {
        driver.quit();
        Driver.stopAppium();
    }

    @Test
    public void test1() {
        click(xpathOfText("Accessibility"));
        driver.navigate().back();
        click(xpathOfText("Animation"));
        driver.navigate().back();
        click(xpathOfText("Text"));
        driver.navigate().back();
    }

    @Test
    public void test2() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpathOfText("Accessibility"))).click();
        driver.navigate().back();
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpathOfText("Animation"))).click();
        driver.navigate().back();
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpathOfText("Text"))).click();
        driver.navigate().back();
    }
    void click(By locator) { // bu click methodu içerisinde de bir wait clickable kullanılabilir.
        Driver.getDriver().findElement(locator).click();
    }

    By xpathOfText(String text){
        return By.xpath(MessageFormat.format(textXpath, text)); // bu mesaj formatı ile birden fazla değişken girilebilir
        // yukarıdaki stingXPath dinamic olarak ayarlanarak aşağıda text bölümüne static By xpathOfText(String...text){
        // return By.xpath(MessageFormat.format(textXpath,text));}                                           varargs
    }


}
