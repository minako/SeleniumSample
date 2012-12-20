package samplecode;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class IETest2 {

  private WebDriver driver = null;

  @Before
  public void createDriver() {
	  DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
//	  caps.setCapability("platform", "Windows 2012");
	  caps.setCapability("version", "9");
    driver = new InternetExplorerDriver(caps);
  }

  @After
  public void quitDriver() {
//    driver.quit();
  }

  @Test
  public void shouldBeAbleToPerformAGoogleSearch() {
    driver.get("http://www.google.com");
    driver.findElement(By.name("q")).sendKeys("webdriver");
    driver.findElement(By.name("btnG")).click();
    new WebDriverWait(driver, 5)
        .until(ExpectedConditions.titleIs("webdriver - Google 検索"));
    TakesScreenshot sc = (TakesScreenshot) driver;
    File srcFile = sc.getScreenshotAs(OutputType.FILE);
    try {
		FileUtils.copyFile(srcFile, new File("./screenshots/IE/test003.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}