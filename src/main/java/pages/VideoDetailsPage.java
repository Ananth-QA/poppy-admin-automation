//
//
//package pages;
//
//import base.BasePage;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import utils.ConfigReader;
//import utils.LogUtil;
//
//
//
//
//public class VideoDetailsPage extends BasePage {
//
//    private final By introDropdown =
//            By.xpath(ConfigReader.get("video.intro.dropdown.xpath"));
//    private final By introStart =
//            By.xpath(ConfigReader.get("video.intro.start.xpath"));
//    private final By company =
//            By.xpath(ConfigReader.get("video.company.xpath"));
//    private final By address =
//            By.xpath(ConfigReader.get("video.address.xpath"));
//    private final By email =
//            By.xpath(ConfigReader.get("video.email.xpath"));
//    private final By download =
//            By.xpath(ConfigReader.get("video.download.xpath"));
//    private final By player =
//            By.xpath(ConfigReader.get("video.player.xpath"));
//
//    public VideoDetailsPage(WebDriver driver) {
//        super(driver);
//    }
//
//    public void updateIntroAndVerify() {
//
//        safeClick(introDropdown, "Intro Position Dropdown");
//        safeClick(introStart, "Start of Video");
//
//        LogUtil.info("Company: " + safeGetText(company, "Company"));
//        LogUtil.info("Address: " + safeGetText(address, "Address"));
//        LogUtil.info("Email: " + safeGetText(email, "Email"));
//    }
//
//    public void downloadAndPlay() {
//        safeClick(download, "Download Video");
//        jsClick(player);
//        LogUtil.info("▶ Video playback verified");
//    }
//}



package pages;

import base.BasePage;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.FileUtil;
import utils.LogUtil;

public class VideoDetailsPage extends BasePage {

    private final By introDropdown =
            By.xpath(ConfigReader.get("video.intro.dropdown.xpath"));

    private final By introStart =
            By.xpath(ConfigReader.get("video.intro.start.xpath"));

    private final By company =
            By.xpath(ConfigReader.get("video.company.xpath"));

    private final By address =
            By.xpath(ConfigReader.get("video.address.xpath"));

    private final By email =
            By.xpath(ConfigReader.get("video.email.xpath"));

    private final By download =
            By.xpath(ConfigReader.get("video.download.xpath"));

    private final By player =
            By.xpath(ConfigReader.get("video.player.xpath"));

    public VideoDetailsPage(WebDriver driver) {
        super(driver);
    }

//    /**
//     * Change intro position & verify company details
//     */
//    public void updateIntroAndVerify() {
//
//        safeClick(introDropdown, "Intro Position Dropdown");
//        safeClick(introStart, "Intro Position → Start of Video");
//
//        LogUtil.info("Company: " + safeGetText(company, "Company Name"));
//        LogUtil.info("Address: " + safeGetText(address, "Address"));
//        LogUtil.info("Email: " + safeGetText(email, "Email"));
//    }
//
//    /**
//     * Download and play video
//     */
//    public void downloadAndPlay() {
//
//        safeClick(download, "Download Video");
//
//        jsClick(player);
//        LogUtil.info("▶ Video playback verified");
//    }
//}
//
    

    /**
     * Update intro position and print all field values
     */
    public void updateIntroAndVerify() {

        safeClick(introDropdown, "Intro Position Dropdown");
        safeClick(introStart, "Intro Posi	tion → Start of Video");


        // 🔹 UPDATE company name
        safeClear(company, "Company Name");
        // ✅ ENTER new value
        safeType1(company, "Ananth Company", "Company Name");
        
        
        LogUtil.info("Company: " + safeGetInputValue(company, "Company"));
        LogUtil.info("Address: " + safeGetInputValue(address, "Address"));
        LogUtil.info("Email: " + safeGetInputValue(email, "Email"));
    }

    /**
     * Download and play video safely
     */
//    public void downloadAndPlay() {
//
//        // Scroll into view first
//        scrollIntoView(download);
//
//        // Try normal click, fallback to JS
//        if (!safeClick(download, "Download Video")) {
//            jsClick(download);
//            LogUtil.warn("Download clicked using JS fallback");
//        }
//
//        // Play video
//        jsClick(player);
//        LogUtil.info("▶ Video playback verified");
//    }
    
    
    public void downloadAndPlay() {

        scrollIntoView(download);
        FileUtil.cleanDownloadFolder(ConfigReader.get("download.dir"));

        jsClick(download);
        LogUtil.info("Download initiated");

//        waitForDownloadReady();

        File downloadedVideo =
                FileUtil.waitForDownloadedFile(
                        ConfigReader.get("download.dir"),
                        ".mp4",
                        180
                );

        if (downloadedVideo == null) {
            throw new RuntimeException("❌ Video was not downloaded");
        }

        LogUtil.info("✅ Video downloaded successfully: " +
                downloadedVideo.getAbsolutePath());

    
    }

    /**
     * Get value from input fields (NOT getText)
     */
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

    
    
    
    
    
