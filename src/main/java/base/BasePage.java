//package base;
//
//import java.io.File;
//
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import utils.LogUtil;
//import utils.WaitUtils;
//
//public class BasePage {
//
//    protected WebDriver driver;
//
//    public BasePage(WebDriver driver) {
//        this.driver = driver;
//    }
//
//    protected boolean safeType(By locator, String value, String fieldName) {
//        try {
//            WebElement element = WaitUtils.waitForVisible(driver, locator);
//            element.clear();
//            element.sendKeys(value);
//            LogUtil.info(fieldName + " entered successfully");
//            return true;
//        } catch (Exception e) {
//            LogUtil.error("Unable to enter value in " + fieldName);
//            return false;
//        }
//    }
//
//    protected boolean safeClick(By locator, String elementName) {
//        try {
//            WaitUtils.waitForClickable(driver, locator).click();
//            LogUtil.info(elementName + " clicked successfully");
//            return true;
//        } catch (Exception e) {
//            LogUtil.error("Unable to click on " + elementName);
//            return false;
//        }
//    }
//    
//    
//    protected boolean safeType1(By locator, String value, String fieldName) {
//        try {
//            WaitUtils.waitForVisible(driver, locator).sendKeys(value);
//            LogUtil.info(fieldName + " entered successfully");
//            return true;
//        } catch (Exception e) {
//            LogUtil.error("Failed to enter value in " + fieldName);
//            return false;
//        }
//    }
//
//    protected boolean safeClick1(By locator, String elementName) {
//        try {
//            WaitUtils.waitForClickable(driver, locator).click();
//            LogUtil.info(elementName + " clicked successfully");
//            return true;
//        } catch (Exception e) {
//            LogUtil.error("Failed to click " + elementName);
//            return false;
//        }
//    }
//
//    protected boolean safeAcceptAlertIfPresent(String alertName) {
//        try {
//            Alert alert = driver.switchTo().alert();
//            LogUtil.info(alertName + " detected: " + alert.getText());
//            alert.accept();
//            LogUtil.info(alertName + " accepted successfully");
//            return true;
//        } catch (Exception e) {
//            LogUtil.info(alertName + " not present – continuing");
//            return false;
//        }
//    }
//
//    protected String safeGetText(By locator, String elementName) {
//        try {
//            String text = WaitUtils.waitForVisible(driver, locator).getText();
//            LogUtil.info(elementName + " value: " + text);
//            return text;
//        } catch (Exception e) {
//            LogUtil.error("Unable to fetch text from " + elementName);
//            return null;
//        }
//    }
//
//    protected boolean safeIsDisplayed(By locator, String elementName) {
//        try {
//            boolean displayed = WaitUtils.waitForVisible(driver, locator).isDisplayed();
//            LogUtil.info(elementName + " is displayed");
//            return displayed;
//        } catch (Exception e) {
//            LogUtil.error(elementName + " is NOT displayed");
//            return false;
//        }
//    }
//    
//    protected boolean safeFileUpload(By locator, File file, String fieldName) {
//        try {
//            WaitUtils.waitForVisible(driver, locator)
//                    .sendKeys(file.getAbsolutePath());
//            LogUtil.info(fieldName + " uploaded successfully → " + file.getName());
//            return true;
//        } catch (Exception e) {
//            LogUtil.error("Failed to upload " + fieldName);
//            return false;
//        }
//    }
//
//    
//    protected void waitForLoaderToDisappear(By loader, String loaderName) {
//        try {
//            WaitUtils.waitForInvisibility(driver, loader);
//            LogUtil.info(loaderName + " disappeared");
//        } catch (Exception e) {
//            LogUtil.warn(loaderName + " still visible, proceeding cautiously");
//        }
//    }
//    
//    
//    
//    protected void waitForPageToStabilize() {
//        try {
//            Thread.sleep(10000); // buffer after heavy async UI actions
//            LogUtil.info("Page stabilization wait completed");
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            LogUtil.warn("Page stabilization interrupted");
//        }
//    }
//
//    
//    
//    
//}



package base;

import java.io.File;


import org.openqa.selenium.*;
import utils.LogUtil;
import utils.WaitUtils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected boolean safeClick(By locator, String name) {
        try {
            WaitUtils.waitForClickable(driver, locator).click();
            LogUtil.info(name + " clicked");
            return true;
        } catch (Exception e) {
            LogUtil.warn("Unable to click " + name + " with normal click: " + e.getMessage());
            // Try JS click fallback
            try {
                jsClick(locator);
                LogUtil.info(name + " clicked via JS fallback");
                return true;
            } catch (Exception jsEx) {
                LogUtil.error("Unable to click " + name + " via JS fallback: " + jsEx.getMessage());
                return false;
            }
        }
    }

    
    
    protected boolean safeClick(WebElement element, String elementName) {
        try {
            element.click();
            LogUtil.info(elementName + " clicked successfully");
            return true;
        } catch (Exception e) {
            LogUtil.error("Unable to click on " + elementName);
            return false;
        }
    }

    
    
    protected String safeGetText(By locator, String name) {
        try {
            return WaitUtils.waitForVisible(driver, locator).getText();
        } catch (Exception e) {
            LogUtil.error("Unable to read " + name);
            return null;
        }
    }

    protected void waitForPageToStabilize() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException ignored) {}
    }

    protected void jsClick(By locator) {
        WebElement el = WaitUtils.waitForVisible(driver, locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    protected void jsClick(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    protected void waitForElement(By locator, String name) {
        try {
            WaitUtils.waitForVisible(driver, locator);
            LogUtil.info(name + " is visible");
        } catch (Exception e) {
            LogUtil.warn(name + " not visible: " + e.getMessage());
        }
    }

    protected boolean safeIsDisplayed(By locator, String elementName) {
      try {
          boolean displayed = WaitUtils.waitForVisible(driver, locator).isDisplayed();
          LogUtil.info(elementName + " is displayed");
          return displayed;
      } catch (Exception e) {
          LogUtil.error(elementName + " is NOT displayed");
          return false;
      }
  }
    
    
    protected boolean safeAcceptAlertIfPresent(String alertName) {
      try {
          Alert alert = driver.switchTo().alert();
          LogUtil.info(alertName + " detected: " + alert.getText());
          alert.accept();
          LogUtil.info(alertName + " accepted successfully");
          return true;
      } catch (Exception e) {
          LogUtil.info(alertName + " not present – continuing");
          return false;
      }
  }
    
    
    protected boolean safeType1(By locator, String value, String fieldName) {
      try {
          WaitUtils.waitForVisible(driver, locator).sendKeys(value);
          LogUtil.info(fieldName + " e	ntered successfully");
          return true;
      } catch (Exception e) {
          LogUtil.error("Failed to enter value in " + fieldName);
          return false;
      }
  }
    
    
    protected boolean safeFileUpload(By locator, File file, String fieldName) {
        try {
            if (file == null || !file.exists()) {
                LogUtil.error("❌ File not found for " + fieldName);
                return false;
            }

            WebElement uploadInput = WaitUtils.waitForVisible(driver, locator);
            uploadInput.sendKeys(file.getAbsolutePath());

            LogUtil.info("📁 " + fieldName + " uploaded successfully → " + file.getName());
            return true;

        } catch (Exception e) {
            LogUtil.error("❌ Failed to upload " + fieldName + " : " + e.getMessage());
            return false;
        }
    }


    
    protected void waitForLoaderToDisappear(By loader, String loaderName) {
        try {
            if (loader == null) {
                LogUtil.warn(loaderName + " locator is null. Skipping loader wait.");
                return;
            }
            WaitUtils.waitForInvisibility(driver, loader);
            LogUtil.info(loaderName + " disappeared");
        } catch (Exception e) {
            LogUtil.warn(loaderName + " not detected or still visible. Proceeding cautiously.");
        }
    }
    
    protected void scrollIntoView(By locator) {
        try {
            WebElement element = driver.findElement(locator);

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
                    element
            );

            LogUtil.info("Scrolled element into view");

        } catch (Exception e) {
            LogUtil.warn("Unable to scroll element into view");
        }
    }

    protected void waitForDownloadReady() {
        try {
            Thread.sleep(3000); // minimal buffer
            LogUtil.info("Download buffer wait completed");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    
    protected boolean safeClear(By locator, String fieldName) {
        try {
            WaitUtils.waitForVisible(driver, locator).clear();
            LogUtil.info(fieldName + " cleared");
            return true;
        } catch (Exception e) {
            LogUtil.error("Unable to clear " + fieldName);
            return false;
        }
    }

    protected String safeGetAttribute(By locator, String attr, String elementName) {
        try {
            String value = WaitUtils.waitForVisible(driver, locator)
                    .getAttribute(attr);
            LogUtil.info(elementName + " value: " + value);
            return value;
        } catch (Exception e) {
            LogUtil.error("Unable to fetch attribute from " + elementName);
            return null;
        }
    }

    
    
}