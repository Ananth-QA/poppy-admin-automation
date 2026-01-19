package base;

import listeners.AllureTestListener;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import pages.LoginPage;
import utils.ConfigReader;
import utils.LogUtil;

@Listeners(AllureTestListener.class)
public class BaseTest {

    protected WebDriver driver;

    /**
     * Framework bootstrap
     * - Initializes driver
     * - Launches application
     * - Performs global login
     */
    
    @BeforeSuite
    public void createDownloadDir() {
        File dir = new File(ConfigReader.get("download.dir"));
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    
    
    @BeforeClass(alwaysRun = true)
    public void setUp() {

        LogUtil.info("========== TEST SETUP STARTED ==========");

        try {
            DriverFactory.initDriver();
            driver = DriverFactory.getDriver();
            LogUtil.info("WebDriver initialized successfully");

        } catch (Exception e) {
            LogUtil.error("Critical failure: Unable to initialize WebDriver");
            throw e; // 🔴 This IS a critical failure
        }

        try {
            driver.get(ConfigReader.get("base.url"));
            LogUtil.info("Navigated to URL: " + ConfigReader.get("base.url"));

        } catch (Exception e) {
            LogUtil.error("Critical failure: Unable to launch application URL");
            throw e; // 🔴 Critical failure
        }

        // ---------- Global Login ----------
        try {
            LoginPage loginPage = new LoginPage(driver);
            boolean loginStatus = loginPage.login();

            if (!loginStatus) {
                LogUtil.warn("Login completed with issues. Tests will continue as non-critical.");
            } else {
                LogUtil.info("Login successful. Proceeding with tests.");
            }

        } catch (Exception e) {
            LogUtil.error("Unexpected exception during login flow");
            // ❌ DO NOT throw → as per your requirement
        }

        LogUtil.info("========== TEST SETUP COMPLETED ==========");
    }

    
    
    
    
   
}
