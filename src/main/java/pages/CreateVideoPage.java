


package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.FileUtil;
import utils.LogUtil;

import java.io.File;
import java.time.LocalDateTime;

public class CreateVideoPage extends BasePage {

    public CreateVideoPage(WebDriver driver) {
        super(driver);
    }
        private final By titleInput =
                By.xpath(ConfigReader.get("video.title.input.xpath"));

        private final By thumbnailUpload =
                By.xpath(ConfigReader.get("video.thumbnail.upload.xpath"));

        private final By videoUpload =
                By.xpath(ConfigReader.get("video.file.upload.xpath"));

        private final By ratioDropdown =
                By.xpath(ConfigReader.get("video.ratio.dropdown.xpath"));

        private final By ratioOption =
                By.xpath(ConfigReader.get("video.ratio.option.xpath"));

        private final By saveBtn =
                By.xpath(ConfigReader.get("video.save.button.xpath"));





//    public LocalDateTime createVideo() {

       public LocalDateTime createVideo() throws InterruptedException {

LocalDateTime createdTime = LocalDateTime.now();
LogUtil.info("🎬 Video creation started at: " + createdTime);

// Title
safeType1(titleInput,
        ConfigReader.get("video.title.value"),
        "Video Title");

// Thumbnail upload
File thumbnail = FileUtil.getValidFile(
        ConfigReader.get("media.files.folder"),
        FileUtil.getExtensions(ConfigReader.get("video.thumbnail.extensions")),
        Long.parseLong(ConfigReader.get("video.max.thumbnail.mb")),
        "Thumbnail Image"
);

safeFileUpload(thumbnailUpload, thumbnail, "Thumbnail Upload");

// Video upload
File video = FileUtil.getValidFile(
        ConfigReader.get("media.files.folder"),
        FileUtil.getExtensions(ConfigReader.get("video.extensions")),
        Long.parseLong(ConfigReader.get("video.max.video.mb")),
        "Video File"
);

safeFileUpload(videoUpload, video, "Video Upload");

       
safeClick(ratioDropdown, "Ratio Dropdown");
safeClick(ratioOption, "Ratio Option");
safeClick(saveBtn, "Save Video Button");
waitForLoaderToDisappear(
        By.xpath(ConfigReader.get("video.upload.loader.xpath")),
        "Video Upload Loader"
);

// wait for success toast if configured
        try {
            String toastXpath = ConfigReader.get("video.create.success.xpath");
            boolean toastFound = false;

            // First try the configured locator if present
            if (toastXpath != null && !toastXpath.trim().isEmpty()) {
                try {
                    waitForElement(By.xpath(toastXpath), "Video success toast (configured)");
                    LogUtil.info("Success toast detected (configured)");
                    toastFound = true;
                } catch (Exception ignored) {}
            }

            // If not found via config, try a series of common fallback locators
            if (!toastFound) {
                String[] fallbacks = new String[] {
                        "//div[contains(@class,'toast') and (contains(.,'created') or contains(.,'success'))]",
                        "//div[contains(@class,'alert') and (contains(.,'created') or contains(.,'success'))]",
                        "//div[contains(.,'Video') and (contains(.,'created') or contains(.,'success'))]",
                        "//*[@role='alert']"
                };

                for (String fx : fallbacks) {
                    try {
                        waitForElement(By.xpath(fx), "Video success toast (fallback)");
                        LogUtil.info("Success toast detected (fallback): " + fx);
                        toastFound = true;
                        break;
                    } catch (Exception ignored) {}
                }
            }

            if (!toastFound) {
                LogUtil.warn("Did not detect a success toast after video creation — proceeding after stabilization");
                waitForPageToStabilize();
            }
        } catch (Exception e) {
            LogUtil.warn("Error while waiting for success toast – proceeding: " + e.getMessage());
            waitForPageToStabilize();
        }

        // Wait for create modal to disappear if configured
        try {
            String modalXpath = ConfigReader.get("video.create.modal.xpath");
            if (modalXpath != null && !modalXpath.trim().isEmpty()) {
                waitForLoaderToDisappear(By.xpath(modalXpath), "Create Video Modal");
                LogUtil.info("Create video modal disappeared");
            } else {
                // small buffer
                Thread.sleep(800);
            }
        } catch (Exception e) {
            LogUtil.warn("Create video modal did not disappear in time: " + e.getMessage());
        }

        return createdTime;
        
        // small buffer to ensure stability
        
        
    }
       
}