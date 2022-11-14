package test.cps3230.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageObjects.WebsitePageObject;
import utils.ApiService;
import utils.ScrapingService;
import utils.WebElementsToString;

public class Test2 {
    WebsitePageObject websitePageObject;
    ScrapingService scrapingService;
    WebElementsToString webElementsToString;
    ApiService apiService;

    @BeforeEach
    public void setup() {
        websitePageObject = new WebsitePageObject();
        scrapingService = new ScrapingService();
        webElementsToString = new WebElementsToString();
        apiService = new ApiService();

        websitePageObject.setScrapingService(webElementsToString);
        websitePageObject.setWebsiteElementsService(scrapingService);
        websitePageObject.setApiService(apiService);
    }

    @Test
    public void simpleTest() throws Exception{
        boolean isSuccessful = websitePageObject.productScrape("batman",5);
        Assertions.assertTrue(isSuccessful);
    }
}
