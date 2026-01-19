package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.LogUtil;

public class UserProfilesPage extends BasePage {

    private final By userProfilesMenu =
            By.xpath(ConfigReader.get("menu.user.profiles.xpath"));

    private final By newProfileBtn =
            By.xpath(ConfigReader.get("user.new.profile.button.xpath"));

    public UserProfilesPage(WebDriver driver) {
        super(driver);
    }

    public void openUserProfiles() {
        safeClick(userProfilesMenu, "User Profiles Menu");
        waitForPageToStabilize();
    }

    public void openAddProfilePopup() {
        safeClick(newProfileBtn, "New Profile Button");
    }
}
