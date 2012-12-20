package samplecode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IETest  {
    public static void main(String[] args) {
        // ここのところでFirefoxからIEに切りかえます。
        WebDriver driver = new InternetExplorerDriver();

        // 以下は同じです。
        driver.get("http://www.google.com");

        WebElement element = driver.findElement(By.name("q"));
        
        element.sendKeys("Cheese!");
        
        element.submit();
        
        System.out.println("Page title is: " + driver.getTitle());
        
        (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().startsWith("cheese!");
            }
        });

        System.out.println("Page title is: " + driver.getTitle());
        
        driver.quit();
    }
}