package test.cps3230;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.WebsitePageObject;

import java.util.List;

public class WebDriverTests {
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
    public void testScrapingTitleWebElementsFromSearchWithFiveResultsOrMore() {
        websitePageObject.searchForProduct("Harry Potter");

        List<WebElement> titlesElementList = websitePageObject.getTitles();
        Assertions.assertTrue(titlesElementList.size() >= 5);
    }

    @Test
    public void testScrapingDescriptionWebElementsFromSearchWithFiveResultsOrMore() {
        websitePageObject.searchForProduct("Harry Potter");

        List<WebElement> descriptionsElementList = websitePageObject.getDescriptions();
        Assertions.assertTrue(descriptionsElementList.size() >= 5);
    }

    @Test
    public void testScrapingUrlsWebElementsFromSearchWithFiveResultsOrMore() {
        websitePageObject.searchForProduct("Harry Potter");

        List<WebElement> urlsElementList = websitePageObject.getUrls();
        Assertions.assertTrue(urlsElementList.size() >= 5);
    }

    @Test
    public void testScrapingImageUrlsWebElementsFromSearchWithFiveResultsOrMore() {
        websitePageObject.searchForProduct("Harry Potter");

        List<WebElement> imageUrlsElementList = websitePageObject.getImageUrls();

        // Technically it should be >= 5 but since there are another 2 extra imageUrls
        // used in other areas of the website then +2 is added since these extra imageUrls
        // are also scraped by the driver
        Assertions.assertTrue(imageUrlsElementList.size() >= 7);
    }

    @Test
    public void testScrapingPricesWebElementsFromSearchWithFiveResultsOrMore() {
        websitePageObject.searchForProduct("Harry Potter");

        List<WebElement> pricesElementList = websitePageObject.getPrices();
        Assertions.assertTrue(pricesElementList.size() >= 5);
    }

    @Test
    public void testScrapingTitleWebElementsFromSearchWithLessThanFiveResults() {
        websitePageObject.searchForProduct("Angel David Revilla");

        List<WebElement> titlesElementList = websitePageObject.getTitles();
        Assertions.assertEquals(4,titlesElementList.size());
    }

    @Test
    public void testScrapingDescriptionWebElementsFromSearchWithLessThanFiveResults() {
        websitePageObject.searchForProduct("Angel David Revilla");

        List<WebElement> descriptionsElementList = websitePageObject.getDescriptions();
        Assertions.assertEquals(4, descriptionsElementList.size());
    }

    @Test
    public void testScrapingUrlsWebElementsFromSearchWithLessThanFiveResults() {
        websitePageObject.searchForProduct("Angel David Revilla");

        List<WebElement> urlsElementList = websitePageObject.getUrls();
        Assertions.assertEquals(4, urlsElementList.size());
    }

    @Test
    public void testScrapingImageUrlsWebElementsFromSearchWithLessThanFiveResults() {
        websitePageObject.searchForProduct("Angel David Revilla");

        List<WebElement> imageUrlsElementList = websitePageObject.getImageUrls();

        // Technically what is expected should be 4 but since there are another 2 extra imageUrls
        // used in other areas of the website then +2 is added since these extra imageUrls
        // are also scraped by the driver
        Assertions.assertEquals(6, imageUrlsElementList.size());
    }

    @Test
    public void testScrapingPricesWebElementsFromSearchWithLessThanFiveResults() {
        websitePageObject.searchForProduct("Angel David Revilla");

        List<WebElement> pricesElementList = websitePageObject.getPrices();
        Assertions.assertEquals(2, pricesElementList.size());
    }

    @Test
    public void testScrapingTitleWebElementsFromSearchWithNoResults() {
        websitePageObject.searchForProduct("");

        List<WebElement> titlesElementList = websitePageObject.getTitles();
        Assertions.assertEquals(0,titlesElementList.size());
    }

    @Test
    public void testScrapingDescriptionWebElementsFromSearchWithNoResults() {
        websitePageObject.searchForProduct("");

        List<WebElement> descriptionsElementList = websitePageObject.getDescriptions();
        Assertions.assertEquals(0, descriptionsElementList.size());
    }

    @Test
    public void testScrapingUrlsWebElementsFromSearchWithNoResults() {
        websitePageObject.searchForProduct("");

        List<WebElement> urlsElementList = websitePageObject.getUrls();
        Assertions.assertEquals(0, urlsElementList.size());
    }

    @Test
    public void testScrapingImageUrlsWebElementsFromSearchWithNoResults() {
        websitePageObject.searchForProduct("");

        List<WebElement> imageUrlsElementList = websitePageObject.getImageUrls();

        // Technically what is expected should be 2 but since there are another 2 extra imageUrls
        // used in other areas of the website then +2 is added since these extra imageUrls
        // are also scraped by the driver
        Assertions.assertEquals(2, imageUrlsElementList.size());
    }

    @Test
    public void testScrapingPricesWebElementsFromSearchWithNoResults() {
        websitePageObject.searchForProduct("");

        List<WebElement> pricesElementList = websitePageObject.getPrices();
        Assertions.assertEquals(0, pricesElementList.size());
    }
}
