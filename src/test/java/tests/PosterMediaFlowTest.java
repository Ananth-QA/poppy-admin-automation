package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.*;

public class PosterMediaFlowTest extends BaseTest {

    @Test(groups = {"regression"})
    public void verifyPosterMediaFlow() {

        MediaPage media = new MediaPage(driver);
        media.openCreatePosterPopup();

        CreatePosterPage create = new CreatePosterPage(driver);
        create.createPoster();

        PostersGridPage posters = new PostersGridPage(driver);
        posters.openPostersPage();
        posters.openLatestPoster();

        PosterDetailsPage details = new PosterDetailsPage(driver);
        details.updateIntroAndVerify();
        details.downloadPoster();
    }
}
