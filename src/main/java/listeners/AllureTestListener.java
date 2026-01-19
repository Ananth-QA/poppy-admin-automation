package listeners;

import base.DriverFactory;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.LogUtil;

public class AllureTestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        LogUtil.info("▶ STARTED TEST: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtil.info("✔ PASSED TEST: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtil.error("✖ FAILED TEST: " + result.getMethod().getMethodName());
        attachScreenshot();
        attachException(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtil.warn("⚠ SKIPPED TEST: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        LogUtil.info("🚀 TEST SUITE STARTED: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LogUtil.info("🏁 TEST SUITE FINISHED: " + context.getName());
    }

    /* ================= ALLURE ATTACHMENTS ================= */

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] attachScreenshot() {
        try {
            return ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            LogUtil.warn("Unable to capture screenshot");
            return new byte[0];
        }
    }

    @Attachment(value = "Exception Details", type = "text/plain")
    public String attachException(Throwable throwable) {
        return throwable == null ? "No exception" : throwable.toString();
    }
}
