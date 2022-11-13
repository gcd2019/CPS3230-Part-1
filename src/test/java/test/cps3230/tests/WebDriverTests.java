package test.cps3230.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.WebsitePageObject;
import test.cps3230.spies.WebsiteElementsServiceSpy;

import java.util.List;

public class WebDriverTests {
    WebDriver driver;
    WebsitePageObject websitePageObject;
    WebsiteElementsServiceSpy websiteElementsService;

    @BeforeEach
    public void setup() {
        websitePageObject = new WebsitePageObject();
        websiteElementsService = new WebsiteElementsServiceSpy();

        websitePageObject.setWebsiteElementsService(websiteElementsService);

        websiteElementsService.setUpDriver();
    }

    @AfterEach
    public void teardown() {
        websiteElementsService.quitDriver();
    }

    @Test
    public void testScrapingTitleWebElementsFromSearchWithFiveResultsOrMore() {
        websiteElementsService.searchForProduct("Harry Potter");

        List<WebElement> titlesElementList = websiteElementsService.getTitlesElements();
        Assertions.assertTrue(titlesElementList.size() >= 5);
    }

    @Test
    public void testScrapingDescriptionWebElementsFromSearchWithFiveResultsOrMore() {
        websiteElementsService.searchForProduct("Harry Potter");

        List<WebElement> descriptionsElementList = websiteElementsService.getDescriptionsElements();
        Assertions.assertTrue(descriptionsElementList.size() >= 5);
    }

    @Test
    public void testScrapingUrlsWebElementsFromSearchWithFiveResultsOrMore() {
        websiteElementsService.searchForProduct("Harry Potter");

        List<WebElement> urlsElementList = websiteElementsService.getUrlsElements();
        Assertions.assertTrue(urlsElementList.size() >= 5);
    }

    @Test
    public void testScrapingImageUrlsWebElementsFromSearchWithFiveResultsOrMore() {
        websiteElementsService.searchForProduct("Harry Potter");

        List<WebElement> imageUrlsElementList = websiteElementsService.getImageUrlsElements();

        // Technically it should be >= 5 but since there are another 2 extra imageUrls
        // used in other areas of the website then +2 is added since these extra imageUrls
        // are also scraped by the driver
        Assertions.assertTrue(imageUrlsElementList.size() >= 7);
    }

    @Test
    public void testScrapingPricesWebElementsFromSearchWithFiveResultsOrMore() {
        websiteElementsService.searchForProduct("Harry Potter");

        List<WebElement> pricesElementList = websiteElementsService.getPricesElements();
        Assertions.assertTrue(pricesElementList.size() >= 5);
    }

    @Test
    public void testScrapingTitleWebElementsFromSearchWithLessThanFiveResults() {
        websiteElementsService.searchForProduct("Angel David Revilla");

        List<WebElement> titlesElementList = websiteElementsService.getTitlesElements();
        Assertions.assertEquals(4,titlesElementList.size());
    }

    @Test
    public void testScrapingDescriptionWebElementsFromSearchWithLessThanFiveResults() {
        websiteElementsService.searchForProduct("Angel David Revilla");

        List<WebElement> descriptionsElementList = websiteElementsService.getDescriptionsElements();
        Assertions.assertEquals(4, descriptionsElementList.size());
    }

    @Test
    public void testScrapingUrlsWebElementsFromSearchWithLessThanFiveResults() {
        websiteElementsService.searchForProduct("Angel David Revilla");

        List<WebElement> urlsElementList = websiteElementsService.getUrlsElements();
        Assertions.assertEquals(4, urlsElementList.size());
    }

    @Test
    public void testScrapingImageUrlsWebElementsFromSearchWithLessThanFiveResults() {
        websiteElementsService.searchForProduct("Angel David Revilla");

        List<WebElement> imageUrlsElementList = websiteElementsService.getImageUrlsElements();

        // Technically what is expected should be 4 but since there are another 2 extra imageUrls
        // used in other areas of the website then +2 is added since these extra imageUrls
        // are also scraped by the driver
        Assertions.assertEquals(6, imageUrlsElementList.size());
    }

    @Test
    public void testScrapingPricesWebElementsFromSearchWithLessThanFiveResults() {
        websiteElementsService.searchForProduct("Angel David Revilla");

        List<WebElement> pricesElementList = websiteElementsService.getPricesElements();
        Assertions.assertEquals(2, pricesElementList.size());
    }

    @Test
    public void testScrapingTitleWebElementsFromSearchWithNoResults() {
        websiteElementsService.searchForProduct("");

        List<WebElement> titlesElementList = websiteElementsService.getTitlesElements();
        Assertions.assertEquals(0,titlesElementList.size());
    }

    @Test
    public void testScrapingDescriptionWebElementsFromSearchWithNoResults() {
        websiteElementsService.searchForProduct("");

        List<WebElement> descriptionsElementList = websiteElementsService.getDescriptionsElements();
        Assertions.assertEquals(0, descriptionsElementList.size());
    }

    @Test
    public void testScrapingUrlsWebElementsFromSearchWithNoResults() {
        websiteElementsService.searchForProduct("");

        List<WebElement> urlsElementList = websiteElementsService.getUrlsElements();
        Assertions.assertEquals(0, urlsElementList.size());
    }

    @Test
    public void testScrapingImageUrlsWebElementsFromSearchWithNoResults() {
        websiteElementsService.searchForProduct("");

        List<WebElement> imageUrlsElementList = websiteElementsService.getImageUrlsElements();

        // Technically what is expected should be 2 but since there are another 2 extra imageUrls
        // used in other areas of the website then +2 is added since these extra imageUrls
        // are also scraped by the driver
        Assertions.assertEquals(2, imageUrlsElementList.size());
    }

    @Test
    public void testScrapingPricesWebElementsFromSearchWithNoResults() {
        websiteElementsService.searchForProduct("");

        List<WebElement> pricesElementList = websiteElementsService.getPricesElements();
        Assertions.assertEquals(0, pricesElementList.size());
    }
}
