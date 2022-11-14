package test.cps3230.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.Website;
import utils.ApiService;
import utils.WebElementsToString;
import pageObjects.ScraperPageObject;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

public class WebsiteTests {
    Website website;
    ScraperPageObject scrapingService;
    WebElementsToString webElementsToString;
    ApiService apiService;

    WebDriver driver;

    @BeforeEach
    public void setup() {
        website = new Website();
        scrapingService = new ScraperPageObject(driver);
        webElementsToString = new WebElementsToString();
        apiService = new ApiService();

        website.setScrapingService(webElementsToString);
        website.setWebsiteElementsService(scrapingService);
        website.setApiService(apiService);
    }

    @Test
    public void testCheckTheCurrentWebsiteUrl() {
        WebDriver driver = Mockito.mock(WebDriver.class);
        Mockito.when(driver.getCurrentUrl()).thenReturn("https://www.bookdepository.com/");

        Assertions.assertEquals("https://www.bookdepository.com/", driver.getCurrentUrl());
    }

    @Test
    public void testCheckTheCurrentWebsiteTitle() {
        WebDriver driver = Mockito.mock(WebDriver.class);
        Mockito.when(driver.getTitle())
                .thenReturn(
                        "Book Depository is the world's most international online bookstore offering over 20 million " +
                                "books with free delivery worldwide.");

        Assertions.assertEquals("Book Depository is the world's most international online bookstore offering " +
                "over 20 million books with free delivery worldwide.", driver.getTitle());
    }


    @Test
    public void testBookSearch() throws Exception {
        Website mock = Mockito.mock(Website.class);

        Mockito.when(mock.productScrape(anyString(), anyInt())).thenReturn(true);

        boolean isScrapeSuccessful = mock.productScrape("batman", 5);

        Assertions.assertTrue(isScrapeSuccessful);
    }

    @Test
    public void testSimpleWebSearch(){
        ScraperPageObject mock = Mockito.mock(ScraperPageObject.class);

        Mockito.when(mock.searchForProduct(anyString())).thenReturn(true);

        boolean isSearchSuccessful = mock.searchForProduct("harry potter");

        Assertions.assertTrue(isSearchSuccessful);
    }

    @Test
    public void testFailingSimpleWebSearch(){
        ScraperPageObject mock = Mockito.mock(ScraperPageObject.class);

        Mockito.when(mock.searchForProduct(null)).thenReturn(false);

        boolean isSearchSuccessful = mock.searchForProduct(null);

        Assertions.assertFalse(isSearchSuccessful);
    }

    @Test
    public void testScrapingTitlesString(){
        WebElementsToString mock = Mockito.mock(WebElementsToString.class);

        List<WebElement> list = new ArrayList<>();
        Mockito.when(mock.scrapeTitles(eq(list), anyInt())).thenReturn(true);

        boolean isSuccessful = mock.scrapeTitles(list, 2);

        Assertions.assertTrue(isSuccessful);
    }

    @Test
    public void testScrapingDescriptionsString(){
        WebElementsToString mock = Mockito.mock(WebElementsToString.class);

        List<WebElement> list = new ArrayList<>();
        Mockito.when(mock.scrapeDescriptions(eq(list), anyInt())).thenReturn(true);

        boolean isSuccessful = mock.scrapeDescriptions(list, 2);

        Assertions.assertTrue(isSuccessful);
    }
    @Test
    public void testScrapingUrlsString(){
        WebElementsToString mock = Mockito.mock(WebElementsToString.class);

        List<WebElement> list = new ArrayList<>();
        Mockito.when(mock.scrapeUrls(eq(list), anyInt())).thenReturn(true);

        boolean isSuccessful = mock.scrapeUrls(list, 2);

        Assertions.assertTrue(isSuccessful);
    }
    @Test
    public void testScrapingImageUrlsString(){
        WebElementsToString mock = Mockito.mock(WebElementsToString.class);

        List<WebElement> list = new ArrayList<>();
        Mockito.when(mock.scrapeImageUrls(eq(list), anyInt())).thenReturn(true);

        boolean isSuccessful = mock.scrapeImageUrls(list, 2);

        Assertions.assertTrue(isSuccessful);
    }

    @Test
    public void testScrapingPricesInts(){
        WebElementsToString mock = Mockito.mock(WebElementsToString.class);

        List<WebElement> list = new ArrayList<>();
        Mockito.when(mock.scrapePrices(eq(list), anyInt())).thenReturn(true);

        boolean isSuccessful = mock.scrapePrices(list, 2);

        Assertions.assertTrue(isSuccessful);
    }

    @Test
    public void testScrapingTitlesStringWhenListIsNull(){
        WebElementsToString mock = Mockito.mock(WebElementsToString.class);

        List<WebElement> list = new ArrayList<>();
        Mockito.when(mock.scrapeTitles(eq(null), anyInt())).thenReturn(false);

        boolean isSuccessful = mock.scrapeTitles(list, 2);

        Assertions.assertFalse(isSuccessful);
    }

    @Test
    public void testScrapingDescriptionsStringWhenListIsNull(){
        WebElementsToString mock = Mockito.mock(WebElementsToString.class);

        List<WebElement> list = new ArrayList<>();
        Mockito.when(mock.scrapeDescriptions(eq(null), anyInt())).thenReturn(false);

        boolean isSuccessful = mock.scrapeDescriptions(list, 2);

        Assertions.assertFalse(isSuccessful);
    }
    @Test
    public void testScrapingUrlsStringWhenListIsNull(){
        WebElementsToString mock = Mockito.mock(WebElementsToString.class);

        List<WebElement> list = new ArrayList<>();
        Mockito.when(mock.scrapeUrls(eq(null), anyInt())).thenReturn(false);

        boolean isSuccessful = mock.scrapeUrls(list, 2);

        Assertions.assertFalse(isSuccessful);
    }
    @Test
    public void testScrapingImageUrlsStringWhenListIsNull(){
        WebElementsToString mock = Mockito.mock(WebElementsToString.class);

        List<WebElement> list = new ArrayList<>();
        Mockito.when(mock.scrapeImageUrls(eq(null), anyInt())).thenReturn(false);

        boolean isSuccessful = mock.scrapeImageUrls(list, 2);

        Assertions.assertFalse(isSuccessful);
    }

    @Test
    public void testScrapingPricesIntsWhenListIsNull(){
        WebElementsToString mock = Mockito.mock(WebElementsToString.class);

        List<WebElement> list = new ArrayList<>();
        Mockito.when(mock.scrapePrices(eq(null), anyInt())).thenReturn(false);

        boolean isSuccessful = mock.scrapePrices(list, 2);

        Assertions.assertFalse(isSuccessful);
    }

    @Test
    public void testSendAPostRequest() throws Exception {
        ApiService mock = Mockito.mock(ApiService.class);

        Mockito.when(mock.sendPostRequests(anyInt(), anyString(), anyString(), anyString(),
                anyString(), anyInt())).thenReturn(true);

        boolean isSuccessful = mock.sendPostRequests(6, "title", "descrip", "hhajehjshjes"
                ,"sjajjajaj", 1000);

        Assertions.assertTrue(isSuccessful);
    }

    @Test
    public void testSendADeleteRequest() throws Exception {
        ApiService mock = Mockito.mock(ApiService.class);

        Mockito.when(mock.sendDeleteRequests()).thenReturn(true);

        boolean isSuccessful = mock.sendDeleteRequests();

        Assertions.assertTrue(isSuccessful);
    }
}
