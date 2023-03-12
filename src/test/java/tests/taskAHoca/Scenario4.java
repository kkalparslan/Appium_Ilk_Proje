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

public class Scenario4 {
    /**
     4.    Scenario 4
     a.    OS'e tiklayin
     b.    SMS messaging'e tiklayin
     c.    Recipent kismina kendi telefon numaranizi girin
     d.    Message Body kismina herhangi bir message girin
     e.    Send butonuna basin
     f.    Buton altinda cikacak sucess notification'i asset edin.
     g.    Bu Scenario'da Recipent kismina text girip tekrar edin ve mesajin gönderilmedigini assert edin
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

        click(xpathOfText("OS"));
        driver.navigate().back();
        click(xpathOfText("OS"));
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
