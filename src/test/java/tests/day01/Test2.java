package tests.day01;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Test2 {

    // appiumu java ile baslatmak icin service
    AppiumDriverLocalService service;

    @Test
    public void test1() throws MalformedURLException {

        // appium icin servis ayarlari yapiliyor.
        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                //.usingPort(1111)
                .usingAnyFreePort()
                .build();
        // appium servisi baslatilyor.
        service.start();

        // Desired capability
        // hangi cihaz ve hangi uygulamaya bağlanılacağı ayarlanıyor
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("appium:udid", "emulator-5554");
        capabilities.setCapability("appium:version", "11");
        capabilities.setCapability("appium:deviceName", "Pixel2");
        capabilities.setCapability("platformName", "Android");

        capabilities.setCapability("appium:appPackage", "com.touchboarder.android.api.demos");
        capabilities.setCapability("appium:appActivity", "com.touchboarder.androidapidemos.MainActivity");

        AppiumDriver<MobileElement> driver;
        driver = new AndroidDriver<>(service.getUrl(), capabilities);
        // AppiumJavaClient 8.x.x sürümleri ile birlikte driver tanımı url için /wd/hub kısmı da kaldırılmış.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("com.android.permissioncontroller:id/continue_button")).click();
        driver.findElement(By.id("android:id/button1")).click();
        driver.findElement(By.id("com.touchboarder.android.api.demos:id/buttonDefaultPositive")).click();
        driver.findElement(By.xpath("//*[@text='API Demos']")).click();
        driver.closeApp();

        // appium service durduruluyor
        service.stop();
    }
    /**
    adb shell
    dumpsys window | grep -E mCurrentFocus
    Yukarıdaki komutu CMD de run edersek hangi sayfada isek o sayfanın appPackage ve appActivity sini veriyor..

    (adb -s DeviceSerial shell)
    getprop getprop komutu da cihazla ilgili neredeyse tüm bilgileri veriyor

     adb kill-server ve adb start-server adb yi kapatıp tekrar açıyor.
     */
    @Test
    public void test2() throws MalformedURLException {

        // appium icin servis ayarlari yapiliyor.
        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                //.usingPort(1111)
                .usingAnyFreePort()
                .build();
        // appium servisi baslatilyor.
        service.start();

        // Desired capability
        // hangi cihaz ve hangi uygulamaya bağlanılacağı ayarlanıyor
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("appium:udid", "NNR6R20618007244");
        capabilities.setCapability("appium:version", "12");
        capabilities.setCapability("appium:deviceName", "Huawei-ec");
        capabilities.setCapability("platformName", "Android");

        capabilities.setCapability("appium:appPackage", "com.touchboarder.android.api.demos");
        capabilities.setCapability("appium:appActivity", "com.touchboarder.androidapidemos.MainActivity");

        AppiumDriver<MobileElement> driver;
        driver = new AndroidDriver<>(service.getUrl(), capabilities);
        // AppiumJavaClient 8.x.x sürümleri ile birlikte driver tanımı url için /wd/hub kısmı da kaldırılmış.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("com.android.permissioncontroller:id/continue_button")).click();
        driver.findElement(By.id("android:id/button1")).click();
        driver.findElement(By.id("com.touchboarder.android.api.demos:id/buttonDefaultPositive")).click();
        driver.findElement(By.xpath("//*[@text='API Demos']")).click();
        driver.findElement(By.xpath("//*[@text='Accessibility']")).click();

        driver.closeApp();

        // appium service durduruluyor
        service.stop();
    }


}
