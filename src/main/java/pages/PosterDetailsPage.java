package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.LogUtil;

public class PosterDetailsPage extends BasePage {


    private final By company =
            By.xpath(ConfigReader.get("poster.company.xpath"));

    private final By address =
            By.xpath(ConfigReader.get("poster.address.xpath"));

    private final By email =
            By.xpath(ConfigReader.get("poster.email.xpath"));

    private final By download =
            By.xpath(ConfigReader.get("poster.download.xpath"));

    public PosterDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void updateIntroAndVerify() {

        safeClear(company, "Company Name");
        safeType1(company, "Ananth Company", "Company Name");

        LogUtil.info("Company: " +
                safeGetInputValue(company, "Company"));

        LogUtil.info("Address: " +
                safeGetInputValue(address, "Address"));

        LogUtil.info("Email: " +
                safeGetInputValue(email, "Email"));
    }

    public void downloadPoster() {

        scrollIntoView(download);
        jsClick(download);

        LogUtil.info("⬇ Poster download initiated");
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
