package tests.taskAHoca;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.App;
import utils.Device;
import utils.Driver;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.Random;

public class Scenario3 {
    /**
     3.    Scenario 3
     a.    10 ile 20 arasinda random bir sayi secin
     b.    Views->TextSwitcher'a tiklayin
     c.    Secilen random sayiya gelinceye kadar Next butonuna tiklayin
     d.    Istenilen sayiya ulasilinca navigate().back() ile geri gelin.
     */
    By lContinue = By.id("com.android.permissioncontroller:id/continue_button");
    By lOkButton = By.id("android:id/button1");
    By lUnknown = By.id("com.touchboarder.android.api.demos:id/buttonDefaultPositive");
    By lApiDemos = By.xpath("//*[@text='API Demos']");
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
        Random rand = new Random();
        int min = 10;
        int max = 20;
        int randomNum = rand.nextInt((max - min) + 1) + min;

        click(xpathOfText("Views"));
        swipeUntil(xpathOfText("TextSwitcher"),.7, .3);
        click(xpathOfText("TextSwitcher"));
        for (int i = 1; i <= randomNum; i++) {
            click(xpathOfText("NEXT"));
        }
        driver.navigate().back();


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
