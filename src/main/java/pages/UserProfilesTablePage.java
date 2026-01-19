package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ConfigReader;
import utils.LogUtil;

import java.util.List;

public class UserProfilesTablePage extends BasePage {

	
	
	
    private final By rows =
            By.xpath(ConfigReader.get("user.table.rows.xpath"));

    public UserProfilesTablePage(WebDriver driver) {
        super(driver);
    }

    public void verifyUserPresent(String email) {

        List<WebElement> list = driver.findElements(rows);

        for (WebElement row : list) {
            if (row.getText().contains(email)) {
                LogUtil.info("✅ User found in table: " + email);
                return;
            }
        }
        throw new RuntimeException("❌ User not found in table: " + email);
    }

//    public void clickEditForUser(String email) {
//
//        List<WebElement> list = driver.findElements(rows);
//
//        for (WebElement row : list) {
//            if (row.getText().contains(email)) {
//                row.findElement(By.xpath(".//button[contains(@class,'edit')]"))
//                        .click();
//                LogUtil.info("✏ Edit clicked for user: " + email);
//                return;
//            }
//        }
//        throw new RuntimeException("❌ Edit button not found for user: " + email);
//    }

    public void verifyUpdatedName(String updatedName) {

        List<WebElement> list = driver.findElements(rows);

        for (WebElement row : list) {
            if (row.getText().contains(updatedName)) {
                LogUtil.info("✅ Updated name verified: " + updatedName);
                return;
            }
        }
        throw new RuntimeException("❌ Updated name not reflected in table");
    }
    
    
    
    public void clickEditForUser(String email) {

        List<WebElement> rowsList = driver.findElements(rows);

        for (WebElement row : rowsList) {

            if (row.getText().contains(email)) {

                WebElement editBtn = row.findElement(
                        By.xpath(ConfigReader.get("user.table.edit.icon.xpath"))
                );

                safeClick(editBtn, "Edit User Profile");
                LogUtil.info("✏ Edit icon clicked for user: " + email);
                return;
            }
        }

        throw new RuntimeException("❌ Edit icon not found for user: " + email);
    }

    
    public String[] getSecondRowCredentials() {

        String email =
                safeGetText(
                        By.xpath("(//table//tbody//tr)[2]//td[3]"),
                        "Second Row Email"
                );

        String password =
                safeGetText(
                        By.xpath("(//table//tbody//tr)[2]//td[4]"),
                        "Second Row Password"
                );

        return new String[]{email, password};
    }

    
    
}
