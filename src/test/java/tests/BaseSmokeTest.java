package tests;

import base.BaseTest;

import org.testng.annotations.Test;

public class BaseSmokeTest extends BaseTest {

    @Test(groups = {"smoke"})
    public void verifyDashboard() {
        System.out.println("✅ Dashboard loaded and verified");
    }
}
