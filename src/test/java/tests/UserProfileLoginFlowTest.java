package tests;
import org.testng.Assert;
import base.BaseTest;
import org.testng.annotations.Test;
import pages.*;

public class UserProfileLoginFlowTest extends BaseTest {

    @Test(groups = {"regression"})
    public void verifyUserCanLoginWithProfileCredentials() {

        // 1️⃣ Navigate to User Profiles
        UserProfilesPage profilesPage = new UserProfilesPage(driver);
        profilesPage.openUserProfiles();

        // 2️⃣ Get 2nd row email & password
        UserProfilesTablePage tablePage =
                new UserProfilesTablePage(driver);

        String[] credentials =
                tablePage.getSecondRowCredentials();

        String email = credentials[0];
        String password = credentials[1];

        // 3️⃣ Logout admin
        HeaderPage header = new HeaderPage(driver);
        header.logout();

        // 4️⃣ Login using fetched credentials	
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        // 5️⃣ Verify dashboard
        DashboardPage dashboard = new DashboardPage(driver);

        Assert.assertTrue(
                dashboard.isDashboardVisible(),
                "❌ Dashboard not visible after login"
        );
    }
}
