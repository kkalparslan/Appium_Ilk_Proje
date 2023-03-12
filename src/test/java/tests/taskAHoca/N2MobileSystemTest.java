package tests.taskAHoca;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class N2MobileSystemTest {

    AppiumDriver<MobileElement> driver;
    WebDriverWait wait;
    By n2Mobil = By.xpath("//*[@text='N2 Ats']");

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

    @Test
    public void test2() throws MalformedURLException {

        AppiumDriverLocalService service;

        // appium icin servis ayarlari yapildi.
        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                //.usingPort(1111)
                .usingAnyFreePort()
                .build();
        // appium servisi baslatiliyor.
        service.start();

        // Desired capability
        // hangi cihaz ve hangi uygulamaya bağlanılacağı ayarladım.
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("appium:udid", "NNR6R20618007244");
        capabilities.setCapability("appium:version", "12");
        capabilities.setCapability("appium:deviceName", "Huawei-ec");
        capabilities.setCapability("platformName", "Android");

        capabilities.setCapability("appium:appPackage", "com.n2mobil.ats.v2");
        capabilities.setCapability("appium:appActivity", "com.n2mobil.ats.v2.MainActivity");

        AppiumDriver<MobileElement> driver;
        driver = new AndroidDriver<>(service.getUrl(), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        driver.findElement(By.xpath("//*[@text='GEÇ']")).click();
        MobileElement username=driver.findElement(By.xpath("(//*[contains(@resource-id,'lbl-')]/parent::android.view.View//android.widget.EditText)[1]"));
        username.sendKeys("taksi");
        MobileElement userpassword=driver.findElement(By.xpath("(//*[contains(@resource-id,'lbl-')]/parent::android.view.View//android.widget.EditText)[2]"));
        username.sendKeys("");
        driver.findElement(By.xpath("//*[@text='GIRIŞ YAP']")).click();

        driver.closeApp();

        // appium service durduruluyor
        service.stop();
    }


}
