package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class Driver {
    private static AppiumDriver<MobileElement> driver;
    static AppiumDriverLocalService service;
    public static void runAppium(){
        service = new AppiumServiceBuilder()
                //.withLogFile(new File("appium.log"))
                .withIPAddress("127.0.0.1")
                .usingAnyFreePort()
                .build();
        service.clearOutPutStreams();
        //appiumun test run edildiğinde consola debug vb codelarının yazılmaması için eklendi (clearOutPutStreams)
        service.start();
    }
    public static void stopAppium(){
        service.stop();
    }
    public static AppiumDriver<MobileElement> getDriver(Device device, App app){
        driver = new AndroidDriver<>(service.getUrl(), setCaps(device, app));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        return driver;
    }
    private static DesiredCapabilities setCaps(Device device, App app){
        String apkDest = "src/main/resources/";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:udid", device.udid);
        capabilities.setCapability("appium:version", device.version);
        capabilities.setCapability("appium:deviceName", device.deviceName);
        capabilities.setCapability("platformName", device.platformName);

        if (app.apk.length()>0)
            capabilities.setCapability("appium:app", apkDest + app.apk);

        capabilities.setCapability("appium:appPackage", app.appPackage);
        capabilities.setCapability("appium:appActivity", app.appActivity);
        return capabilities;
    }
    public static AppiumDriver<?> getDriver(){
        return driver;
    }


    /*
            AppiumDriver
            - AndroidDriver
            - iOSDriver

                java-client8

     */


}
