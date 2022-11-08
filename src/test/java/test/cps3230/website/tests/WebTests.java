package test.cps3230.website.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import test.cps3230.website.pageObjects.WebsitePageObject;

public class WebTests {

    WebDriver driver;
    WebsitePageObject websitePageObject;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/memos/webtesting/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();

        websitePageObject = new WebsitePageObject(driver);
    }

    @AfterEach
    public void tearddown() {
        driver.quit();
    }

    @Test
    public void testWebsiteSearch() throws Exception {
        driver.get("https://www.scanmalta.com/shop/");
        websitePageObject.pressSearch("laptop");
        Thread.sleep(10000);
    }
}
