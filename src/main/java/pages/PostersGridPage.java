package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.LogUtil;

public class PostersGridPage extends BasePage {

    private final By postersMenu =
            By.xpath(ConfigReader.get("menu.posters.xpath"));

    private final By latestPosterCard =
            By.xpath(ConfigReader.get("poster.latest.card.xpath"));

    public PostersGridPage(WebDriver driver) {
        super(driver);
    }

    public void openPostersPage() {
        safeClick(postersMenu, "Posters Menu");
        waitForPageToStabilize();
    }

    public void openLatestPoster() {
        safeClick(latestPosterCard, "Latest Poster Card");
        LogUtil.info("✅ Opened latest poster");
    }
}
