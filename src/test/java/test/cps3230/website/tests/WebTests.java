package test.cps3230.website.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import test.cps3230.website.pageObjects.WebsitePageObject;

public class WebTests {

    WebDriver driver;
    WebsitePageObject websitePageObject;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/memos/webtesting/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.bookdepository.com/");

        websitePageObject = new WebsitePageObject(driver);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void testSimpleWebsiteSearch() throws Exception {
        websitePageObject.searchForProduct("batman");
        Assertions.assertTrue(websitePageObject.titles.size() > 0);
        Assertions.assertTrue(websitePageObject.descriptions.size() > 0);
        Assertions.assertTrue(websitePageObject.urls.size() > 0);
        Assertions.assertTrue(websitePageObject.imageUrls.size() > 0);
        Assertions.assertTrue(websitePageObject.prices.size() > 0);
    }

    @Test
    public void testEmptyWebsiteSearch() throws Exception {
        websitePageObject.searchForProduct("");
        Assertions.assertEquals(0, websitePageObject.titles.size());
        Assertions.assertEquals(0, websitePageObject.descriptions.size());
        Assertions.assertEquals(0, websitePageObject.urls.size());
        Assertions.assertEquals(0, websitePageObject.imageUrls.size());
        Assertions.assertEquals(0, websitePageObject.prices.size());
    }
}
