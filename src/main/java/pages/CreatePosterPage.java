package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.FileUtil;
import utils.LogUtil;

import java.io.File;
import java.time.LocalDateTime;

public class CreatePosterPage extends BasePage {

    private final By titleInput =
            By.xpath(ConfigReader.get("poster.title.input.xpath"));

    private final By imageUpload =
            By.xpath(ConfigReader.get("poster.image.upload.xpath"));

    private final By ratioDropdown =
            By.xpath(ConfigReader.get("poster.ratio.dropdown.xpath"));

    private final By ratioOption =
            By.xpath(ConfigReader.get("poster.ratio.option.xpath"));

    private final By saveBtn =
            By.xpath(ConfigReader.get("poster.save.button.xpath"));

    public CreatePosterPage(WebDriver driver) {
        super(driver);
    }

    public LocalDateTime createPoster() {

        LocalDateTime createdTime = LocalDateTime.now();
        LogUtil.info("🖼 Poster creation started at: " + createdTime);

        safeType1(titleInput,
                ConfigReader.get("poster.title.value"),
                "Poster Title");

        File posterImage = FileUtil.getValidFile(
                ConfigReader.get("media.files.folder"),
                FileUtil.getExtensions(
                        ConfigReader.get("poster.image.extensions")
                ),
                Long.parseLong(ConfigReader.get("poster.max.image.mb")),
                "Poster Image"
        );

        safeFileUpload(imageUpload, posterImage, "Poster Image Upload");

        safeClick(ratioDropdown, "Poster Ratio Dropdown");
        safeClick(ratioOption, "Poster Ratio Option");

        safeClick(saveBtn, "Save Poster Button");

        waitForLoaderToDisappear(
                By.xpath(ConfigReader.get("poster.upload.loader.xpath")),
                "Poster Upload Loader"
        );

        return createdTime;
    }
}
