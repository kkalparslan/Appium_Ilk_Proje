package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class Utils {


    public static AppiumDriver<?> openApp(Device device, App app){
        Driver.runAppium();
        return Driver.getDriver(device, app);
    }

    public static By getXPathOfTextView(String text){
        return By.xpath("//android.widget.TextView[@content-desc='" + text+ "']");
    }
}
