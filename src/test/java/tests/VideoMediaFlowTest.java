package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.*;

public class VideoMediaFlowTest extends BaseTest {
//
//    @Test(groups = {"regression"})
//    public void verifyVideoMediaFlow() throws InterruptedException {
//
//        MediaPage media = new MediaPage(driver);
//        media.openCreateVideoPopup();
//
//        CreateVideoPage create = new CreateVideoPage(driver);
//        create.createVideo();
//
//        VideosGridPage videos = new VideosGridPage(driver);
//        videos.openVideosPage();
//        videos.openFirstVideo();
//
//        VideoDetailsPage details = new VideoDetailsPage(driver);
//        details.verifyDetails();
//        details.downloadAndOpen();
//    }
//}
	
	
	
	
	 @Test(groups = {"regression"})
	    public void verifyVideoMediaFlow() throws InterruptedException {

	        MediaPage media = new MediaPage(driver);
	        media.openCreateVideoPopup();

	        new CreateVideoPage(driver).createVideo();

	        Thread.sleep(5000); // Wait for video processing (adjust as needed)
	        
	        new VideosGridPage(driver).openLatestVideo();

	        VideoDetailsPage details = new VideoDetailsPage(driver);
	        details.updateIntroAndVerify();
	        details.downloadAndPlay();
	    }
	}