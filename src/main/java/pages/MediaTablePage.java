package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ConfigReader;
import utils.LogUtil;

import java.util.List;

public class MediaTablePage extends BasePage {

    private final By tableRows =
            By.xpath(ConfigReader.get("media.table.rows.xpath"));

    private final By mediaType =
            By.xpath(ConfigReader.get("media.table.type.xpath"));

    private final By title =
            By.xpath(ConfigReader.get("media.table.title.xpath"));

    private final By toggle =
            By.xpath(ConfigReader.get("media.table.toggle.xpath"));

    public MediaTablePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Disable first row media and return its type + title
     */
    public String[] disableFirstRowAndGetDetails() {

        List<WebElement> rows = driver.findElements(tableRows);
        WebElement firstRow = rows.get(0);

        String typeText =
                firstRow.findElement(mediaType).getText().trim();

        String titleText =
                firstRow.findElement(title).getText().trim();

        WebElement toggleBtn =
                firstRow.findElement(toggle);

        safeClick(toggleBtn, "Media Toggle OFF");

        LogUtil.info("Media disabled → Type: " + typeText +
                " | Title: " + titleText);

        return new String[]{typeText, titleText};
    }
}
