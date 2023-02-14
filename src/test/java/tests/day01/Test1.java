package tests.day01;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Test1 {

    @Test
    public void test1() throws MalformedURLException {
        // Desired capability
        // hangi cihaz ve hangi uygulamaya bağlanılacağı ayarlanıyor
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("appium:udid", "NNR6R20618007244");
        capabilities.setCapability("appium:version", "12");
        capabilities.setCapability("appium:deviceName", "Huawei-ec");
        capabilities.setCapability("platformName", "Android");

        capabilities.setCapability("appium:appPackage", "com.touchboarder.android.api.demos");
        capabilities.setCapability("appium:appActivity", "com.touchboarder.androidapidemos.MainActivity");

        AppiumDriver<MobileElement>driver;
        driver=new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        // AppiumJavaClient 8.x.x sürümleri ile birlikte driver tanımı url için /wd/hub kısmı da kaldırılmış.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("com.android.permissioncontroller:id/continue_button")).click();
        driver.findElement(By.id("android:id/button1")).click();
        driver.findElement(By.id("com.touchboarder.android.api.demos:id/buttonDefaultPositive")).click();
        driver.findElement(By.xpath("//*[@text='API Demos']")).click();
        driver.closeApp();
    }

    @Test
    public void test2() throws MalformedURLException {
        // Desired capability
        // hangi cihaz ve hangi uygulamaya bağlanılacağı ayarlanıyor
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("appium:udid", "emulator-5554");
        capabilities.setCapability("appium:version", "11");
        capabilities.setCapability("appium:deviceName", "Pixel2");
        capabilities.setCapability("platformName", "Android");

        capabilities.setCapability("appium:appPackage", "com.touchboarder.android.api.demos");
        capabilities.setCapability("appium:appActivity", "com.touchboarder.androidapidemos.MainActivity");

        AppiumDriver<MobileElement>driver;
        driver=new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        // AppiumJavaClient 8.x.x sürümleri ile birlikte driver tanımı url için /wd/hub kısmı da kaldırılmış.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("com.android.permissioncontroller:id/continue_button")).click();
        driver.findElement(By.id("android:id/button1")).click();
        driver.findElement(By.id("com.touchboarder.android.api.demos:id/buttonDefaultPositive")).click();
        driver.findElement(By.xpath("//*[@text='API Demos']")).click();
        driver.closeApp();
    }
    /**
    driver, dest.
    driver, cihaz, app

    RemoteWebDriver
    WebDriver
                        appium-java-client
                        7.x.x   8.x.x
    AppiumDriver        50      5
        AndroidDriver   5       50
        IOSDriver       5       50
 */
}
