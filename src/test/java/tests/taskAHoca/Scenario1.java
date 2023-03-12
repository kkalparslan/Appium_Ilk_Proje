package tests.taskAHoca;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.App;
import utils.Device;
import utils.Driver;

import java.text.MessageFormat;
import java.time.Duration;

public class Scenario1 {
    /**
    1.    Scenario 1
    a.    Views->Controls'a tiklayin
    b.    Light Theme'e tiklayin
    c.    Inputbox'a "Controls" metnini send ediniz
    d.    Checkbox1'e tiklayin ve checked oldugunu assert edin
    e.    RadioButton1'e tiklayin  ve checked oldugunu assert edin
    f.    Star'a  tiklayin  ve checked oldugunu assert edin
    g.    Ilk button'a  tiklayin ve textinin ON, statüsünün checked oldugunu assert edin
    h.    Ikinci button'un textinin OFF, statüsünün unchecked oldugunu assert edin
    i.    Selectbox'dan Mars secenegini seciniz.
     */
    By lContinue = By.id("com.android.permissioncontroller:id/continue_button");
    By lOkButton = By.id("android:id/button1");
    By lUnknown = By.id("com.touchboarder.android.api.demos:id/buttonDefaultPositive");
    By lApiDemos = By.xpath("//*[@text='API Demos']");
    By lAccessibility = By.xpath("//*[@text='Accessibility']");
   // By lControls= By.xpath("//*[@text='Controls']");
    //By lInput=By.id("com.touchboarder.android.api.demos:id/edit");
    String textXpath = "//*[@text=\"{0}\"]"; //message methodu tek tırnak kullanmıyor, escape \ karakteri ile
    // birlikte çift tırnak kullanıyor.
    AppiumDriver<MobileElement> driver;
    WebDriverWait wait;
    @BeforeTest
    public void beforeTest() {
        Driver.runAppium();
        driver = Driver.getDriver(Device.HUAWEI, App.APIDEMO);
        wait = new WebDriverWait(driver, 10);
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
        click(xpathOfText("Views"));
        waitForVisibility(xpathOfText("Controls"));
        click(xpathOfText("Controls"));
        click(xpathOfText("01. Light Theme"));
        driver.findElement(By.id("com.touchboarder.android.api.demos:id/edit")).sendKeys("Controls");
        MobileElement checkBox1 = driver.findElement(xpathOfText("Checkbox 1"));
        click(xpathOfText("Checkbox 1"));
       // Assert.assertTrue(checkBox.isSelected());
       Assert.assertEquals(checkBox1.getAttribute("checked"),"true");
       Assert.assertTrue(Boolean.valueOf(checkBox1.getAttribute("checked")));
       Assert.assertTrue(Boolean.parseBoolean(checkBox1.getAttribute("checked")));
       MobileElement RadioButton1=driver.findElement(By.id("com.touchboarder.android.api.demos:id/radio1"));
       RadioButton1.click();
       Assert.assertTrue(Boolean.valueOf(RadioButton1.getAttribute("checked")));
       click(xpathOfText("Star"));
       MobileElement StarButton=driver.findElement(xpathOfText("Star"));
       Assert.assertTrue(Boolean.valueOf(StarButton.getAttribute("checked")));
       MobileElement IlkButton= driver.findElement(By.id("com.touchboarder.android.api.demos:id/toggle1"));
       IlkButton.click();
       Assert.assertEquals(checkBox1.getAttribute("checked"),"true");
       Assert.assertTrue(Boolean.valueOf(IlkButton.getAttribute("checked")));
       MobileElement IkinciButton=driver.findElement(By.id("com.touchboarder.android.api.demos:id/toggle2"));
       Assert.assertEquals(IkinciButton.getAttribute("checked"),"false");
       Assert.assertFalse(Boolean.valueOf(IkinciButton.getAttribute("checked")));
       swipeUntil(xpathOfText("Mercury"),.6, .4);
       click(xpathOfText("Mercury"));
       click(xpathOfText("Mars"));

    }
    void click(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
    }
    void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    By xpathOfText(String text) {
        return By.xpath(MessageFormat.format(textXpath, text));
    }
    public void swipeV(double startPoint, double stopPoint) {
        int w = driver.manage().window().getSize().width;
        int h = driver.manage().window().getSize().height;

        new TouchAction<>(driver)
                .press(PointOption.point(w / 2, (int) (h * startPoint)))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(w / 2, (int) (h * stopPoint)))
                .release()
                .perform();
    }
    public void swipeUntil(By locator, double start, double end) {
        while (driver.findElements(locator).size() <= 0)
            swipeV(start, end);
    }


}
