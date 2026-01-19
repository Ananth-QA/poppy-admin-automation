package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

public class HeaderPage extends BasePage {

    private final By profileIcon =
            By.xpath(ConfigReader.get("header.profile.icon.xpath"));

    private final By logoutBtn =
            By.xpath(ConfigReader.get("header.logout.xpath"));

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    public void logout() {
        safeClick(profileIcon, "Profile Icon");
        safeClick(logoutBtn, "Logout Button");
    }
}
