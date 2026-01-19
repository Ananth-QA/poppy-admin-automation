

package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

public class MediaPage extends BasePage {

    private final By mediaMenu =
            By.xpath(ConfigReader.get("menu.media.xpath"));

    private final By createVideoBtn =
            By.xpath(ConfigReader.get("button.create.video.xpath"));

    public MediaPage(WebDriver driver) {
        super(driver);
    }

    public void openCreateVideoPopup() {
        safeClick(mediaMenu, "Media Menu");
        waitForPageToStabilize();
        safeClick(createVideoBtn, "Create Video Button");
    }
    
    public void openCreatePosterPopup() {
        safeClick(mediaMenu, "Media Menu");
        waitForPageToStabilize();
        safeClick(
            By.xpath(ConfigReader.get("button.create.poster.xpath")),
            "Create Poster Button"
        );
    }

    
}

