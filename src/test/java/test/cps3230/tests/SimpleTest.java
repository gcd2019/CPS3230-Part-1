package test.cps3230.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageObjects.Website;
import utils.ApiService;
import pageObjects.ScraperPageObject;
import utils.WebElementsToString;

public class SimpleTest {
    Website website;
    ScraperPageObject scraperPageObject;
    WebElementsToString webElementsToString;
    ApiService apiService;

    WebDriver driver;

    @BeforeEach
    public void setup() {
        website = new Website();
        scraperPageObject = new ScraperPageObject(driver);
        webElementsToString = new WebElementsToString();
        apiService = new ApiService();

        website.setScrapingService(webElementsToString);
        website.setWebsiteElementsService(scraperPageObject);
        website.setApiService(apiService);
    }

    // Simple test to demonstrate the functionality of the scraper
    @Test
    public void simpleTest() throws Exception{
        boolean isSuccessful = website.productScrape("batman",5);
        Assertions.assertTrue(isSuccessful);
    }
}
