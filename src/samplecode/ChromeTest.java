package samplecode;


import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

@RunWith(BlockJUnit4ClassRunner.class)
public class ChromeTest extends TestCase {

  private static ChromeDriverService service;
  private WebDriver driver;

  @BeforeClass
  public static void createAndStartService() {
    service = new ChromeDriverService.Builder()
        .usingChromeDriverExecutable(new File("driver/chromedriver.exe"))
        .usingAnyFreePort()
        .build();
    try {
		service.start();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  @AfterClass
  public static void createAndStopService() {
    service.stop();
  }

  @Before
  public void createDriver() {
//	  DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//	  capabilities.setCapability("chrome.binary", "/usr/lib/chromium-browser/chromium-browser");
//    driver = new RemoteWebDriver(service.getUrl(), capabilities);
	  System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
      driver = new ChromeDriver();
  }

  @After
  public void quitDriver() {
    driver.quit();
  }

  @Test
  public void testGoogleSearch() {
    driver.get("http://www.google.com");
    WebElement searchBox = driver.findElement(By.name("q"));
    searchBox.sendKeys("webdriver");
    searchBox.submit();
    try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    assertEquals("webdriver - Google 検索", driver.getTitle());
    TakesScreenshot sc = (TakesScreenshot) driver;
    File srcFile = sc.getScreenshotAs(OutputType.FILE);
    try {
		FileUtils.copyFile(srcFile, new File("./test003.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}