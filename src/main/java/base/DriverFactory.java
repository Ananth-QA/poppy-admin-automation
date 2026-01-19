//package base;
//
//import java.io.File;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import utils.ConfigReader;
//
//public class DriverFactory {
//
//    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
//
//    public static void initDriver() {
//        WebDriverManager.chromedriver().setup();
//        driver.set(new ChromeDriver());
//        getDriver().manage().window().maximize();
//    }
//
//    public static WebDriver getDriver() {
//        return driver.get();
//    }
//
//    public static void quitDriver() {
//        if (getDriver() != null) {
//            getDriver().quit();
//            driver.remove();
//        }
//    
//    
//    Map<String, Object> prefs = new HashMap<>();
//
//    prefs.put("download.default_directory",
//            new File(ConfigReader.get("download.dir")).getAbsolutePath());
//
//    prefs.put("download.prompt_for_download", false);
//    prefs.put("download.directory_upgrade", true);
//    prefs.put("safebrowsing.enabled", true);
//
//    ChromeOptions options = new ChromeOptions();
//    options.setExperimentalOption("prefs", prefs);
//
//    WebDriver driver = new ChromeDriver(options);
//
//    
//    
//    
//    ChromeOptions options1 = new ChromeOptions();
//
//    options1.addArguments("--disable-notifications");
//    options1.addArguments("--disable-infobars");
//    options1.addArguments("--disable-save-password-bubble");
//    options1.addArguments("--disable-features=PasswordLeakDetection");
//    options1.addArguments("--disable-features=PasswordManagerOnboarding");
//    options1.setExperimentalOption("prefs", Map.of(
//            "credentials_enable_service", false,
//            "profile.password_manager_enabled", false
//    ));
//
//    
//    
//    }
//
//}


package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.ConfigReader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // 🔕 Disable browser interruptions
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-features=PasswordLeakDetection");
        options.addArguments("--disable-features=PasswordManagerOnboarding");

        // 📥 Download handling
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory",
                new File(ConfigReader.get("download.dir")).getAbsolutePath());
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);

        // 🔐 Disable password manager
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);

        // ✅ SINGLE driver creation
        driver.set(new ChromeDriver(options));

        getDriver().manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}



