package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.*;
import utils.ConfigReader;

public class UserProfilesFlowTest extends BaseTest {

    @Test(groups = {"regression"})
    public void verifyUserProfilesFlow() {

        UserProfilesPage profilesPage =
                new UserProfilesPage(driver);
        profilesPage.openUserProfiles();
        profilesPage.openAddProfilePopup();

        AddEditUserProfilePage addEdit =
                new AddEditUserProfilePage(driver);
        addEdit.addNewUser();

        UserProfilesTablePage table =
                new UserProfilesTablePage(driver);
        table.verifyUserPresent(
                ConfigReader.get("user.email.value")
        );

        table.clickEditForUser(
                ConfigReader.get("user.email.value")
        );

        addEdit.verifyExistingValues();
        addEdit.updateUserName();

        table.verifyUpdatedName(
                ConfigReader.get("user.updated.name.value")
        );
    }
}
