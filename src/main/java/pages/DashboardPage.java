package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

public class DashboardPage extends BasePage {

    private final By dashboardHeader =
            By.xpath(ConfigReader.get("dashboard.header.xpath"));

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDashboardVisible() {
        return safeIsDisplayed(dashboardHeader, "Dashboard Header");
    }
}
