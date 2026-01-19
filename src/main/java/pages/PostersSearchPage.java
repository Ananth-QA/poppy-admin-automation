package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ConfigReader;
import utils.LogUtil;

import java.util.List;

public class PostersSearchPage extends BasePage {

    private final By postersMenu =
            By.xpath(ConfigReader.get("menu.posters.xpath"));

    private final By searchBox =
            By.xpath(ConfigReader.get("posters.search.xpath"));

    public PostersSearchPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPosterPresent(String title) {

        safeClick(postersMenu, "Posters Menu");

        safeClear(searchBox, "Poster Search");
        safeType1(searchBox, title, "Poster Search");

        // ✅ PRESS ENTER (NO helper method needed)
        driver.findElement(searchBox).sendKeys(Keys.ENTER);

        // ✅ CHECK TEXT PRESENCE SAFELY
        List<WebElement> results =
                driver.findElements(
                        By.xpath("//*[contains(text(),'" + title + "')]")
                );

        boolean present = !results.isEmpty();

        LogUtil.info("Poster visible after toggle: " + present);
        return present;
    }
}
