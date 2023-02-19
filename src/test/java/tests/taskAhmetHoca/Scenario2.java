package tests.taskAhmetHoca;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.App;
import utils.Device;
import utils.Driver;

import javax.swing.*;
import java.text.MessageFormat;
import java.time.Duration;
import java.util.List;

public class Scenario2 {
    /**
    2.    Scenario 2
    a.    Views->Expendable List'e tiklayin
    b.    Custom Adaptor'e tiklayin
    c.    People Names'e tiklayin ve 4 adet ismin visible oldugunu assert edin
    d.    People Names'e tekrar tiklayin ve 4 adet ismin invisible oldugunu assert edin
    e.    Fish Names'e tiklayin ve ikinci siradaki ismin Bubbles oldugunu assert edin.
     */
    By lContinue = By.id("com.android.permissioncontroller:id/continue_button");
    By lOkButton = By.id("android:id/button1");
    By lUnknown = By.id("com.touchboarder.android.api.demos:id/buttonDefaultPositive");
    By lApiDemos = By.xpath("//*[@text='API Demos']");
    //By lAccessibility = By.xpath("//*[@text='Accessibility']");

    String textXpath = "//*[@text=\"{0}\"]";

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
    public void test1() throws InterruptedException {
        click(xpathOfText("Views"));
        click(xpathOfText("Expandable Lists"));
        click(xpathOfText("1. Custom Adapter"));

        //click(xpathOfText("People Names"));  //1. yöntem ile click yapamadım.
        new Actions(driver).moveToElement(driver.findElement(xpathOfText("People Names")), 5,5).click().build().perform();
//        List<MobileElement> people = driver.findElements(By.className("android.widget.TextView"));
//        people.get(0).click();
        //driver.navigate().back();//2. yöntem ile de "People Names" e bir türlü click yapmadı. locate olsada burada da görmedi

//        MobileElement arnold = driver.findElement(xpathOfText("Arnold"));
//        Assert.assertTrue(arnold.isDisplayed());
//        MobileElement barry = driver.findElement(xpathOfText("Barry"));
//        Assert.assertTrue(barry.isDisplayed());
//        MobileElement chuck = driver.findElement(xpathOfText("Chuck"));
//        Assert.assertTrue(chuck.isDisplayed());
//        MobileElement david = driver.findElement(xpathOfText("David"));
//        Assert.assertTrue(david.isDisplayed());
//
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpathOfText("Arnold")));
        Boolean arnold1 = wait.until(ExpectedConditions.invisibilityOfElementLocated(xpathOfText("Arnold")));
        Assert.assertTrue(arnold1);
        Boolean barry1 = wait.until(ExpectedConditions.invisibilityOfElementLocated(xpathOfText("Barry")));
        Assert.assertTrue(barry1);
        Boolean chuck1 = wait.until(ExpectedConditions.invisibilityOfElementLocated(xpathOfText("Chuck")));
        Assert.assertTrue(chuck1);
        Boolean david1 = wait.until(ExpectedConditions.invisibilityOfElementLocated(xpathOfText("David")));
        Assert.assertTrue(david1);

        click(xpathOfText("Fish Names"));
        MobileElement bubbles = driver.findElement(xpathOfText("Bubbles"));
        Assert.assertEquals(bubbles.getText(),"Bubbles");

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
