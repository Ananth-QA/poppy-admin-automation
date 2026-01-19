package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.LogUtil;

public class AddEditUserProfilePage extends BasePage {

    private final By name =
            By.xpath(ConfigReader.get("user.name.input.xpath"));
    private final By email =
            By.xpath(ConfigReader.get("user.email.input.xpath"));
    private final By password =
            By.xpath(ConfigReader.get("user.password.input.xpath"));
    private final By mobile =
            By.xpath(ConfigReader.get("user.mobile.input.xpath"));

    private final By addNewBtn =
            By.xpath(ConfigReader.get("user.add.button.xpath"));
    private final By updateBtn =
            By.xpath(ConfigReader.get("user.update.button.xpath"));

    public AddEditUserProfilePage(WebDriver driver) {
        super(driver);
    }

    public void addNewUser() {

        safeType1(name, ConfigReader.get("user.name.value"), "User Name");
        safeType1(email, ConfigReader.get("user.email.value"), "User Email");
        safeType1(password, ConfigReader.get("user.password.value"), "Password");
        safeType1(mobile, ConfigReader.get("user.mobile.value"), "Mobile Number");

        safeClick(addNewBtn, "Add New User");
        waitForPageToStabilize();
    }

    public void verifyExistingValues() {

        LogUtil.info("Name: " + safeGetInputValue(name, "Name"));
        LogUtil.info("Email: " + safeGetInputValue(email, "Email"));
        LogUtil.info("Mobile: " + safeGetInputValue(mobile, "Mobile"));
    }

    public void updateUserName() {

        safeClear(name, "User Name");
        safeType1(name, ConfigReader.get("user.updated.name.value"),
                "Updated User Name");

        safeClick(updateBtn, "Update User");
        waitForPageToStabilize();
    }
    
    private String safeGetInputValue(By locator, String fieldName) {
        try {
            String value = driver.findElement(locator).getAttribute("value");
            LogUtil.info(fieldName + " value: " + value);
            return value;
        } catch (Exception e) {
            LogUtil.error("Unable to read value from " + fieldName);
            return null;
        }
    }
    
}
