package pageObjects;

import org.openqa.selenium.WebElement;
import utils.ApiService;
import utils.WebElementsToString;

import java.util.List;

public class Website {

    protected ApiService apiService;
    protected WebElementsToString webElementsToString;
    protected ScraperPageObject scrapingService;

    public void setScrapingService(WebElementsToString webElementsToString) {
        this.webElementsToString = webElementsToString;
    }

    public void setApiService(ApiService apiService) {
        this.apiService = apiService;
    }

    public void setWebsiteElementsService(ScraperPageObject scrapingService) {
        this.scrapingService = scrapingService;
    }

    public Boolean productScrape(String product, int n) throws Exception {
        if (scrapingService == null)
            return false;

        scrapingService.setUpDriver("https://www.bookdepository.com/");
        if (scrapingService.searchForProduct(product)){

            // Scrapping titles
            List<WebElement> titlesElementList = scrapingService.getTitlesElements();
            if (webElementsToString == null)
                return false;

            if (titlesElementList.size() > 0){
                webElementsToString.scrapeTitles(titlesElementList, n);
            }

            // Scrapping authors
            List<WebElement> descriptionsElementList = scrapingService.getDescriptionsElements();
            if (webElementsToString == null)
                return false;

            if (descriptionsElementList.size() > 0){
                webElementsToString.scrapeDescriptions(descriptionsElementList, n);
            }

            // Scrapping urls
            List<WebElement> urlsElementList = scrapingService.getUrlsElements();
            if (webElementsToString == null)
                return false;

            if (urlsElementList.size() > 0){
                webElementsToString.scrapeUrls(urlsElementList, n);
            }

            // Scrapping image urls
            List<WebElement> imageUrlsElementList = scrapingService.getImageUrlsElements();
            if (webElementsToString == null)
                return false;

            // This is checking if the number of image urls in the website is bigger than 2
            // since when no results are found there are already another 2 image urls used in other parts of the website.
            if (imageUrlsElementList.size() > 2){
                webElementsToString.scrapeImageUrls(imageUrlsElementList, n);
            }

            // Scrapping prices
            List<WebElement> pricesElementList = scrapingService.getPricesElements();
            if (webElementsToString == null)
                return false;

            if (pricesElementList.size() > 0){
                webElementsToString.scrapePrices(pricesElementList, n);
            }

            if (apiService == null)
                return false;

            // We need to send post requests
            for (int i = 0; i < n; i++){
                apiService.sendPostRequests(5, webElementsToString.getTitles().get(i),
                        webElementsToString.getDescriptions().get(i), webElementsToString.getUrls().get(i),
                        webElementsToString.getImageUrls().get(i), webElementsToString.getPrices().get(i));
            }
            scrapingService.quitDriver();

            return true;

        }else{
            return false;
        }
    }
}
