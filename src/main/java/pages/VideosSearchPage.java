package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ConfigReader;
import utils.LogUtil;

import java.util.List;

public class VideosSearchPage extends BasePage {

    private final By videosMenu =
            By.xpath(ConfigReader.get("menu.videos.xpath"));

    private final By searchBox =
            By.xpath(ConfigReader.get("videos.search.xpath"));

    public VideosSearchPage(WebDriver driver) {
        super(driver);
    }

    public boolean isVideoPresent(String title) {

        safeClick(videosMenu, "Videos Menu");

        safeClear(searchBox, "Video Search");
        safeType1(searchBox, title, "Video Search");

        driver.findElement(searchBox).sendKeys(Keys.ENTER);

        List<WebElement> results =
                driver.findElements(
                        By.xpath("//*[contains(text(),'" + title + "')]")
                );

        boolean present = !results.isEmpty();

        LogUtil.info("Video visible after toggle: " + present);
        return present;
    }
}
