package tests.day01;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import utils.App;
import utils.Device;

import java.util.concurrent.TimeUnit;

public class Test3 {
    AppiumDriverLocalService service;
    Device device = Device.HUAWEI;
    App app = App.APIDEMO;

    @Test
    public void test1(){
        // appium icin servis ayarlari yapiliyor.
        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                //.usingPort(1111)
                .usingAnyFreePort()
                .build();
        // appium servisi baslatilyor.
        service.start();

        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("appium:udid", device.udid);
        capabilities.setCapability("appium:version", device.version);
        capabilities.setCapability("appium:deviceName", device.deviceName);
        capabilities.setCapability("platformName", device.platformName);

        capabilities.setCapability("appium:appPackage", app.appPackage);
        capabilities.setCapability("appium:appActivity", app.appActivity);

        AppiumDriver<MobileElement> driver;
        driver = new AndroidDriver<>(service.getUrl(), capabilities);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("com.android.permissioncontroller:id/continue_button")).click();
        driver.findElement(By.id("android:id/button1")).click();
        driver.findElement(By.id("com.touchboarder.android.api.demos:id/buttonDefaultPositive")).click();
        driver.findElement(By.xpath("//*[@text='API Demos']")).click();
        driver.closeApp();

        // appium service durduruluyor
        service.stop();



    }


}
