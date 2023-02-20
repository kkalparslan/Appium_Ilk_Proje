package tests.ahmetHocaExtra;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class BasicAuth {

    String url = "https://the-internet.herokuapp.com/basic_auth";
    String urlPass = "https://admin:admin@the-internet.herokuapp.com/basic_auth";
    By text = By.xpath("//h3[text()='Basic Auth']");
    WebDriver driver;

    @BeforeTest
    public void beforeTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void basicAuth(){
        driver.get(urlPass);

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(text));


    }


    @Test
    public void basicAuthWithRobot() throws AWTException {
        driver.get(url);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_D);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyPress(KeyEvent.VK_D);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyPress(KeyEvent.VK_TAB);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(text));


    }

    @Test
    public void federalAuth() {
        driver.get(urlPass);
        driver.get(driver.getCurrentUrl().replace("://", "://Admin:admin@"));
    }



    @AfterTest
    public void afterTest(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }
}
