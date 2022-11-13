package test.cps3230.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pageObjects.WebsitePageObject;
import test.cps3230.spies.ScrapingServiceSpy;
import test.cps3230.spies.WebsiteElementsServiceSpy;
import utils.WebsiteElementsProvider;

public class WebsiteTests {
    WebsitePageObject websitePageObject;
    WebsiteElementsServiceSpy websiteElementsService;
    ScrapingServiceSpy scrapingService;

    @BeforeEach
    public void setup() {
        websitePageObject = new WebsitePageObject();
        //websiteElementsService = new WebsiteElementsServiceSpy();
        scrapingService = new ScrapingServiceSpy();

        websitePageObject.setScrapingService(scrapingService);
        websitePageObject.setWebsiteElementsService(websiteElementsService);

        //websiteElementsService.setUpDriver();
    }

//    @AfterEach
//    public void teardown() {
//        websiteElementsService.quitDriver();
//    }

    @Test
    public void testSearchFunctionalityForAString(){
        WebsiteElementsProvider websiteElementsProvider = Mockito.mock(WebsiteElementsProvider.class);
        Mockito.when(websiteElementsProvider.getResult("batman")).thenReturn(websiteElementsProvider.result1);

        Assertions.assertEquals("Search results for batman", websiteElementsProvider.getResult("batman"));
    }
//    @Test
//    public void testSearchFunctionalityForAString() {
//        websiteElementsService.searchForProduct("batman");
//
//        String resultString = websiteElementsService.getDriver().
//                findElement(By.xpath("//div[@class = 'main-content search-page']/h1")).getText();
//
//        Assertions.assertEquals("Search results for batman", resultString);
//    }

    @Test
    public void testSearchFunctionalityForAnEmptyString() {
        WebsiteElementsProvider websiteElementsProvider = Mockito.mock(WebsiteElementsProvider.class);
        Mockito.when(websiteElementsProvider.getResult("")).thenReturn(websiteElementsProvider.result2);

        Assertions.assertEquals("Advanced Search", websiteElementsProvider.getResult(""));
    }

//    @Test
//    public void testSearchFunctionalityForAnEmptyString() {
//        websiteElementsService.searchForProduct("");
//        String resultString = websiteElementsService.getDriver().
//                findElement(By.xpath("//div[@class = 'content']/h1")).getText();
//
//        Assertions.assertEquals("Advanced Search", resultString);
//    }

      @Test
      public void testScrapeTitlesOfWebsiteSearchWithFiveResultsOrMore() {
          WebsiteElementsProvider websiteElementsProvider = Mockito.mock(WebsiteElementsProvider.class);
          Mockito.when(websiteElementsProvider.getTitles("batman")).
                  thenReturn(websiteElementsProvider.titles);

          String[] result = {"batman 1", "batman 2", "batman 3", "batman 4", "batman 5"};
          Assertions.assertArrayEquals(result, websiteElementsProvider.getTitles("batman"));
      }
//    @Test
//    public void testTitlesOfWebsiteSearchWithFiveResultsOrMore() throws Exception {
//        websitePageObject.productScrape("batman");
//        Assertions.assertTrue(scrapingService.getTitles().size() >= 5);
//    }

      @Test
      public void testScrapeDescriptionsOfWebsiteSearchWithFiveResultsOrMore(){
          WebsiteElementsProvider websiteElementsProvider = Mockito.mock(WebsiteElementsProvider.class);
          Mockito.when(websiteElementsProvider.getDescriptions("batman")).
                  thenReturn(websiteElementsProvider.titles);

          String[] result = {"description1", "description2", "description3", "description4", "description5"};
          Assertions.assertArrayEquals(result, websiteElementsProvider.getDescriptions("batman"));
      }
//    @Test
//    public void testDescriptionsOfWebsiteSearchWithFiveResultsOrMore() throws Exception {
//        websitePageObject.productScrape("batman");
//        Assertions.assertTrue(scrapingService.getDescriptions().size() >= 5);
//    }
//
//    @Test
//    public void testUrlsOfWebsiteSearchWithFiveResultsOrMore() throws Exception {
//        websitePageObject.productScrape("batman");
//        Assertions.assertTrue(scrapingService.getUrls().size() > 0);
//    }
//
//    @Test
//    public void testImageUrlsOfWebsiteSearchWithFiveResultsOrMore() throws Exception {
//        websitePageObject.productScrape("batman");
//        Assertions.assertTrue(scrapingService.getImageUrls().size() > 0);
//    }
//
//    @Test
//    public void testPricesOfWebsiteSearchWithFiveResultsOrMore() throws Exception {
//        websitePageObject.productScrape("batman");
//        Assertions.assertTrue(scrapingService.getPrices().size() > 0);
//    }
//
//    @Test
//    public void testTitlesOfEmptyWebsiteSearch() throws Exception {
//        websitePageObject.productScrape("");
//        Assertions.assertEquals(0, scrapingService.getTitles().size());
//    }
//
//    @Test
//    public void testDescriptionsOfEmptyWebsiteSearch() throws Exception {
//        websitePageObject.productScrape("");
//        Assertions.assertEquals(0, scrapingService.getDescriptions().size());
//    }
//
//    @Test
//    public void testUrlsOfEmptyWebsiteSearch() throws Exception {
//        websitePageObject.productScrape("");
//        Assertions.assertEquals(0, scrapingService.getUrls().size());
//    }
//
//    @Test
//    public void testImageUrlsOfEmptyWebsiteSearch() throws Exception {
//        websitePageObject.productScrape("");
//        Assertions.assertEquals(0, scrapingService.getImageUrls().size());
//    }
//
//    @Test
//    public void testPricesOfEmptyWebsiteSearch() throws Exception {
//        websitePageObject.productScrape("");
//        Assertions.assertEquals(0, scrapingService.getPrices().size());
//    }
//
//    @Test
//    public void testTitlesOfWebsiteSearchWithLessThanFiveResults() throws Exception {
//        websitePageObject.productScrape("Angel David Revilla");
//        Assertions.assertEquals(4, scrapingService.getTitles().size());
//    }
//
//    @Test
//    public void testDescriptionsOfWebsiteSearchWithLessThanFiveResults() throws Exception {
//        websitePageObject.productScrape("Angel David Revilla");
//        Assertions.assertEquals(4,scrapingService.getDescriptions().size());
//    }
//
//    @Test
//    public void testUrlsOfWebsiteSearchWithLessThanFiveResults() throws Exception {
//        websitePageObject.productScrape("Angel David Revilla");
//        Assertions.assertEquals(4, scrapingService.getUrls().size());
//    }
//
//    @Test
//    public void testImageUrlsOfWebsiteSearchWithLessThanFiveResults() throws Exception {
//        websitePageObject.productScrape("Angel David Revilla");
//        Assertions.assertEquals(4, scrapingService.getImageUrls().size());
//    }
//
//    @Test
//    public void testPricesOfWebsiteSearchWithLessThanFiveResults() throws Exception {
//        websitePageObject.productScrape("Angel David Revilla");
//        Assertions.assertEquals(2, scrapingService.getPrices().size());
//    }
}
