
package pages;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;
import utils.ConfigReader;
import utils.LogUtil;
import utils.WaitUtils;

public class VideosGridPage extends BasePage {


   private By videosMenu;
   private By cards;
   private By publishedTime;

   public VideosGridPage(WebDriver driver) {
       super(driver);

       // videos menu
       try {
           String videosXpath = ConfigReader.get("menu.videos.xpath");
           if (videosXpath != null && !videosXpath.trim().isEmpty()) {
               videosMenu = By.xpath(videosXpath);
           } else {
               videosMenu = By.xpath("//span[text()='Videos' or contains(.,'Videos')]");
           }
       } catch (Exception e) {
           videosMenu = By.xpath("//span[text()='Videos' or contains(.,'Videos')]");
       }

       // cards locator: try multiple config keys then fallback
       try {
           String[] keys = new String[]{"videos.card.xpath", "videos.card.list.xpath", "videos.card.list.xpath"};
           String cardsXpath = null;
           for (String k : keys) {
               String v = ConfigReader.get(k);
               if (v != null && !v.trim().isEmpty()) { cardsXpath = v; break; }
           }
           if (cardsXpath != null) {
               cards = By.xpath(cardsXpath);
           } else {
               LogUtil.warn("videos.card.xpath not present in config — using fallback locator");
               cards = By.xpath("//div[.//p[contains(text(),'Published')]]");
           }
       } catch (Exception e) {
           LogUtil.warn("Error reading videos.card.xpath from config: " + e.getMessage());
           cards = By.xpath("//div[.//p[contains(text(),'Published')]]");
       }
   }

    public void openLatestVideo() throws InterruptedException {
		LogUtil.info("Opening Videos Page");

    	
    	 waitForPageToStabilize();
//    	 Thread.sleep(5000);
        safeClick(videosMenu, "Videos Menu");
        
        
      

            By latestVideoCard = By.xpath(
                    "//body/div[@id='layout-wrapper']/div[@class='main-content']" +
                    "/div[@class='page-content']/div[@class='container-fluid']/div[2]/div[1]"
            );

            // Wait until the card is clickable
            WaitUtils.waitForClickable(driver, latestVideoCard).click();

            LogUtil.info("✅ Opened latest video (top-most card)");
        }
    }
       

