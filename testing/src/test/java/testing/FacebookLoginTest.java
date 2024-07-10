package testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FacebookLoginTest {

    private WebDriver driver;
    private Properties props;

    @BeforeEach
    public void setup() {
        // Load properties file
        props = new Properties();
        try {
            props.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as per your application's error handling strategy
            // For now, let's assume failing to load properties is critical and stop the test
            throw new RuntimeException("Failed to load config.properties");
        }

        // Set path to ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "D:\\eclipse\\testing\\drivers\\chromedriver.exe");

        // Initialize ChromeDriver
        driver = new ChromeDriver();
    }

    @Test
    public void testFacebookLogin() {
        // Navigate to Facebook login page
        driver.get("https://www.facebook.com");

        // Enter username (email or phone number)
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys(props.getProperty("facebook.username"));

        // Enter password
        WebElement passwordInput = driver.findElement(By.id("pass"));
        passwordInput.sendKeys(props.getProperty("facebook.password"));

        // Click on the login button
        WebElement loginButton = driver.findElement(By.name("login"));
        loginButton.click();

        // Wait for the home page to load
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.titleContains("Facebook"));

        // Wait for logout button to be visible
    
        try {
            Thread.sleep(4000); // Sleep for 40 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       
        driver.quit();
        // Verify login success by checking presence of logout button
      
    }

//    @AfterEach
//    public void tearDown() {
//        // Close WebDriver session after 40 seconds of wait
//        try {
//            Thread.sleep(4000); // Sleep for 40 seconds
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}
