package test.cps3230;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.WebsitePageObject;

public class WebsiteTests {

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
    public void testTitlesOfWebsiteSearchWithFiveResultsOrMore() throws Exception {
        websitePageObject.searchForProduct("batman");
        Assertions.assertTrue(websitePageObject.titles.size() >= 5);
    }

    @Test
    public void testDescriptionsOfWebsiteSearchWithFiveResultsOrMore() throws Exception {
        websitePageObject.searchForProduct("batman");
        Assertions.assertTrue(websitePageObject.descriptions.size() >= 5);
    }

    @Test
    public void testUrlsOfWebsiteSearchWithFiveResultsOrMore() throws Exception {
        websitePageObject.searchForProduct("batman");
        Assertions.assertTrue(websitePageObject.urls.size() > 0);
    }

    @Test
    public void testImageUrlsOfWebsiteSearchWithFiveResultsOrMore() throws Exception {
        websitePageObject.searchForProduct("batman");
        Assertions.assertTrue(websitePageObject.imageUrls.size() > 0);
    }

    @Test
    public void testPricesOfWebsiteSearchWithFiveResultsOrMore() throws Exception {
        websitePageObject.searchForProduct("batman");
        Assertions.assertTrue(websitePageObject.prices.size() > 0);
    }

    @Test
    public void testTitlesOfEmptyWebsiteSearch() throws Exception {
        websitePageObject.searchForProduct("");
        Assertions.assertEquals(0, websitePageObject.titles.size());
    }

    @Test
    public void testDescriptionsOfEmptyWebsiteSearch() throws Exception {
        websitePageObject.searchForProduct("");
        Assertions.assertEquals(0, websitePageObject.descriptions.size());
    }

    @Test
    public void testUrlsOfEmptyWebsiteSearch() throws Exception {
        websitePageObject.searchForProduct("");
        Assertions.assertEquals(0, websitePageObject.urls.size());
    }

    @Test
    public void testImageUrlsOfEmptyWebsiteSearch() throws Exception {
        websitePageObject.searchForProduct("");
        Assertions.assertEquals(0, websitePageObject.imageUrls.size());
    }

    @Test
    public void testPricesOfEmptyWebsiteSearch() throws Exception {
        websitePageObject.searchForProduct("");
        Assertions.assertEquals(0, websitePageObject.prices.size());
    }

    @Test
    public void testTitlesOfWebsiteSearchWithLessThanFiveResults() throws Exception {
        websitePageObject.searchForProduct("Angel David Revilla");
        Assertions.assertTrue(websitePageObject.titles.size() < 5);
    }

    @Test
    public void testDescriptionsOfWebsiteSearchWithLessThanFiveResults() throws Exception {
        websitePageObject.searchForProduct("Angel David Revilla");
        Assertions.assertTrue(websitePageObject.descriptions.size() < 5);
    }

    @Test
    public void testUrlsOfWebsiteSearchWithLessThanFiveResults() throws Exception {
        websitePageObject.searchForProduct("Angel David Revilla");
        Assertions.assertTrue(websitePageObject.urls.size() < 5);
    }

    @Test
    public void testImageUrlsOfWebsiteSearchWithLessThanFiveResults() throws Exception {
        websitePageObject.searchForProduct("Angel David Revilla");
        Assertions.assertTrue(websitePageObject.imageUrls.size() < 5);
    }

    @Test
    public void testPricesOfWebsiteSearchWithLessThanFiveResults() throws Exception {
        websitePageObject.searchForProduct("Angel David Revilla");
        Assertions.assertTrue(websitePageObject.prices.size() < 5);
    }
}
