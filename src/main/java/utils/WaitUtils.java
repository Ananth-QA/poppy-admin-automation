package utils;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    public static WebElement waitForVisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(
                        Integer.parseInt(ConfigReader.get("explicit.wait"))
                )
        );
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForInvisibility(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(
                        Integer.parseInt(ConfigReader.get("explicit.wait"))
                )
        );
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    
    
    public static WebElement waitForClickable(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(
                        Integer.parseInt(ConfigReader.get("explicit.wait"))
                )
        );
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

	public static WebElement waitForClickable(WebDriver driver, WebElement latest) {
		// TODO Auto-generated method stub
		return null;
	}
}
