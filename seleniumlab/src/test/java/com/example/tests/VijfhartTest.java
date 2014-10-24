package com.example.tests;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class VijfhartTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver(); // local;works
    
    // for Jenkins
   //DesiredCapabilities capabilities = new DesiredCapabilities("firefox","29", Platform.WIN8);
    
   //driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities );
    // end for jenkins
    
    baseUrl = "https://www.google.nl/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testVijfhart() throws Exception {
    driver.get(baseUrl + "/?gfe_rd=cr&ei=mzY0VO-RHcuvOqf7geAN");
    try {
      Assert.assertEquals("Google", driver.getTitle());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    Assert.assertTrue(isElementPresent(By.id("gbqfq")));
    driver.findElement(By.id("gbqfq")).clear();
    driver.findElement(By.id("gbqfq")).sendKeys("vijfhart");
    driver.findElement(By.id("gbqfb")).click();
    Assert.assertTrue(isElementPresent(By.linkText("Vijfhart IT Opleiding Cursus en Training - Oracle - Java ...")));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      Assert.fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
