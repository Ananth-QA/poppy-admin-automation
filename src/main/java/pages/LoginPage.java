//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import base.BasePage;
//import utils.ConfigReader;
//import utils.LogUtil;
//
//public class LoginPage extends BasePage {
//
//    public LoginPage(WebDriver driver) {
//        super(driver);
//    }
//
//    private final By email =
//            By.xpath(ConfigReader.get("auth.email.xpath"));
//    private final By password =
//            By.xpath(ConfigReader.get("auth.password.xpath"));
//    private final By signInBtn =
//            By.xpath(ConfigReader.get("auth.signin.xpath"));
//
//    /**
//     * Performs login action
//     * @return true if all login steps succeed, false otherwise
//     */
//    public boolean login() {
//
//        boolean status = true;
//
//        status &= safeType(
//                email,
//                ConfigReader.get("auth.email.value"),
//                "Email Field"
//        );
//
//        status &= safeType(
//                password,
//                ConfigReader.get("auth.password.value"),
//                "Password Field"
//        );
//
//        status &= safeClick(
//                signInBtn,
//                "Sign In Button"
//        );
//        
//
//        if (status) {
//            LogUtil.info("Login completed successfully");
//        } else {
//            LogUtil.warn("Login completed with errors");
//        }
//
//        return status;
//    }
//}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;
import utils.ConfigReader;
import utils.LogUtil;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /* ---------- Login Locators ---------- */
    private final By email =
            By.xpath(ConfigReader.get("auth.email.xpath"));

    private final By password =
            By.xpath(ConfigReader.get("auth.password.xpath"));

    private final By signInBtn =
            By.xpath(ConfigReader.get("auth.signin.xpath"));

    /* ---------- Post Login / Dashboard Locators ---------- */
    private final By dashboardHeader =
            By.xpath(ConfigReader.get("dashboard.header.xpath"));

    private final By totalMediaCount =
            By.xpath(ConfigReader.get("dashboard.total.media.count.xpath"));

    /**
     * Complete login flow with post-login validation
     */
    public boolean login() {

        LogUtil.info("Starting login flow");
        boolean status = true;

        status &= safeType1(email,
                ConfigReader.get("auth.email.value"),
                "Email Field");

        status &= safeType1(password,
                ConfigReader.get("auth.password.value"),
                "Password Field");

        status &= safeClick(signInBtn,
                "Sign In Button");

        // Handle browser confirmation if present
        safeAcceptAlertIfPresent("Post Login Browser Confirmation");

        // Verify dashboard landed
        if (!safeIsDisplayed(dashboardHeader, "Dashboard Header")) {
            LogUtil.warn("Dashboard not confirmed after login");
            status = false;
        }

        // Fetch Total Media Count
        String mediaCount = safeGetText(
                totalMediaCount,
                "Total Media Count"
        );

        if (mediaCount != null) {
            LogUtil.info("Total Media Count after login: " + mediaCount);
        } else {
            LogUtil.warn("Unable to fetch Total Media Count");
        }

        if (status) {
            LogUtil.info("Login + dashboard verification completed successfully");
        } else {
            LogUtil.warn("Login flow completed with issues");
        }

        return status;
    }
    
    
    
    
    
    /**
     * Login using dynamic credentials (used for User Profile login flow)
     */
    public boolean login(String userEmail, String userPassword) {

        LogUtil.info("Starting login using profile credentials");

        boolean status = true;

        status &= safeType1(
                email,
                userEmail,
                "Email Field"
        );

        status &= safeType1(
                password,
                userPassword,
                "Password Field"
        );

        status &= safeClick(
                signInBtn,
                "Sign In Button"
        );

        // Handle browser confirmation if present
        safeAcceptAlertIfPresent("Post Login Browser Confirmation");

        // Verify dashboard
        if (!safeIsDisplayed(dashboardHeader, "Dashboard Header")) {
            LogUtil.warn("Dashboard not visible after profile login");
            status = false;
        }

        if (status) {
            LogUtil.info("Profile login successful");
        } else {
            LogUtil.warn("Profile login failed");
        }

        return status;
    }

    
    
    
    
    
    
    
    
    
    
}










