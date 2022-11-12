package test.cps3230;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
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
    public void testSearchFunctionalityForAString() {
        websitePageObject.searchForProduct("batman");

        String resultString = driver.findElement(By.xpath("//div[@class = 'main-content search-page']/h1"))
                .getText();

        Assertions.assertEquals("Search results for batman", resultString);
    }

    @Test
    public void testSearchFunctionalityForAnEmptyString() {
        websitePageObject.searchForProduct("");
        String resultString = driver.findElement(By.xpath("//div[@class = 'content']/h1"))
                .getText();

        Assertions.assertEquals("Advanced Search", resultString);
    }

    @Test
    public void testTitlesOfWebsiteSearchWithFiveResultsOrMore() throws Exception {
        websitePageObject.productScrape("batman");
        Assertions.assertTrue(websitePageObject.titles.size() >= 5);
    }

    @Test
    public void testDescriptionsOfWebsiteSearchWithFiveResultsOrMore() throws Exception {
        websitePageObject.productScrape("batman");
        Assertions.assertTrue(websitePageObject.descriptions.size() >= 5);
    }

    @Test
    public void testUrlsOfWebsiteSearchWithFiveResultsOrMore() throws Exception {
        websitePageObject.productScrape("batman");
        Assertions.assertTrue(websitePageObject.urls.size() > 0);
    }

    @Test
    public void testImageUrlsOfWebsiteSearchWithFiveResultsOrMore() throws Exception {
        websitePageObject.productScrape("batman");
        Assertions.assertTrue(websitePageObject.imageUrls.size() > 0);
    }

    @Test
    public void testPricesOfWebsiteSearchWithFiveResultsOrMore() throws Exception {
        websitePageObject.productScrape("batman");
        Assertions.assertTrue(websitePageObject.prices.size() > 0);
    }

    @Test
    public void testTitlesOfEmptyWebsiteSearch() throws Exception {
        websitePageObject.productScrape("");
        Assertions.assertEquals(0, websitePageObject.titles.size());
    }

    @Test
    public void testDescriptionsOfEmptyWebsiteSearch() throws Exception {
        websitePageObject.productScrape("");
        Assertions.assertEquals(0, websitePageObject.descriptions.size());
    }

    @Test
    public void testUrlsOfEmptyWebsiteSearch() throws Exception {
        websitePageObject.productScrape("");
        Assertions.assertEquals(0, websitePageObject.urls.size());
    }

    @Test
    public void testImageUrlsOfEmptyWebsiteSearch() throws Exception {
        websitePageObject.productScrape("");
        Assertions.assertEquals(0, websitePageObject.imageUrls.size());
    }

    @Test
    public void testPricesOfEmptyWebsiteSearch() throws Exception {
        websitePageObject.productScrape("");
        Assertions.assertEquals(0, websitePageObject.prices.size());
    }

    @Test
    public void testTitlesOfWebsiteSearchWithLessThanFiveResults() throws Exception {
        websitePageObject.productScrape("Angel David Revilla");
        Assertions.assertEquals(4, websitePageObject.titles.size());
    }

    @Test
    public void testDescriptionsOfWebsiteSearchWithLessThanFiveResults() throws Exception {
        websitePageObject.productScrape("Angel David Revilla");
        Assertions.assertEquals(4,websitePageObject.descriptions.size());
    }

    @Test
    public void testUrlsOfWebsiteSearchWithLessThanFiveResults() throws Exception {
        websitePageObject.productScrape("Angel David Revilla");
        Assertions.assertEquals(4, websitePageObject.urls.size());
    }

    @Test
    public void testImageUrlsOfWebsiteSearchWithLessThanFiveResults() throws Exception {
        websitePageObject.productScrape("Angel David Revilla");
        Assertions.assertEquals(4, websitePageObject.imageUrls.size());
    }

    @Test
    public void testPricesOfWebsiteSearchWithLessThanFiveResults() throws Exception {
        websitePageObject.productScrape("Angel David Revilla");
        Assertions.assertEquals(2, websitePageObject.prices.size());
    }
}
