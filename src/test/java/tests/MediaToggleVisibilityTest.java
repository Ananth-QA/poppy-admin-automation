package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class MediaToggleVisibilityTest extends BaseTest {

    @Test(groups = {"regression"})
    public void verifyMediaToggleHidesVideoAndPoster() {

        MediaPage mediaPage = new MediaPage(driver);
        mediaPage.openCreateVideoPopup();

        MediaTablePage tablePage =
                new MediaTablePage(driver);

        String[] data =
                tablePage.disableFirstRowAndGetDetails();

        String type = data[0];
        String title = data[1];

        if (type.equalsIgnoreCase("Video")) {

            VideosSearchPage videos =
                    new VideosSearchPage(driver);

            Assert.assertFalse(
                    videos.isVideoPresent(title),
                    "❌ Video still visible after toggle OFF"
            );

        } else if (type.equalsIgnoreCase("Poster")) {

            PostersSearchPage posters =
                    new PostersSearchPage(driver);

            Assert.assertFalse(
                    posters.isPosterPresent(title),
                    "❌ Poster still visible after toggle OFF"
            );
        }
    }
}
