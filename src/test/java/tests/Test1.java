package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Test1 {

    @Test
    public void test1() throws MalformedURLException {
        // Desired capability
        // hangi cihaz ve hangi uygulamaya bağlanılacağı ayarlanıyor
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("appium:udid", "emulator-5554");
        capabilities.setCapability("appium:version", "11");
        capabilities.setCapability("appium:deviceName", "Pixel2");
        capabilities.setCapability("platformName", "Android");

        capabilities.setCapability("appium:appPackage", "com.touchboarder.android.api.demos");
        capabilities.setCapability("appium:Activity", "com.touchboarder.androidapidemos.MainActivity");

        AppiumDriver<MobileElement>driver;
        driver=new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='API Demos']")).click();
        driver.closeApp();

    }
}
