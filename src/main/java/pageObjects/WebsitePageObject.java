package pageObjects;

import org.openqa.selenium.WebElement;
import utils.ApiService;
import utils.ScrapingService;
import utils.WebsiteElementsService;

import java.util.List;

public class WebsitePageObject {

    protected ApiService apiService;
    protected ScrapingService scrapingService;
    protected WebsiteElementsService websiteElementsService;

    public void setScrapingService(ScrapingService scrapingService) {
        this.scrapingService = scrapingService;
    }

    public void setApiService(ApiService apiService) {
        this.apiService = apiService;
    }

    public void setWebsiteElementsService(WebsiteElementsService websiteElementsService) {
        this.websiteElementsService = websiteElementsService;
    }

    public void productScrape(String product) throws Exception {
        if (websiteElementsService != null)
            websiteElementsService.searchForProduct(product);

        // Scrapping titles
        assert websiteElementsService != null;
        List<WebElement> titlesElementList = websiteElementsService.getTitlesElements();
        if (titlesElementList.size() > 0){
            if (scrapingService != null)
                scrapingService.scrapeTitles(titlesElementList);
        }

        // Scrapping authors
        List<WebElement> descriptionsElementList = websiteElementsService.getDescriptionsElements();
        if (descriptionsElementList.size() > 0){
            if (scrapingService != null)
                scrapingService.scrapeDescriptions(descriptionsElementList);
        }

        // Scrapping urls
        List<WebElement> urlsElementList = websiteElementsService.getUrlsElements();
        if (urlsElementList.size() > 0){
            if (scrapingService != null)
                scrapingService.scrapeUrls(urlsElementList);
        }

        // Scrapping image urls
        // This is checking if the number of image urls in the website is bigger than 2
        // since when no results are found there are already another 2 image urls used in other parts of the website.
        List<WebElement> imageUrlsElementList = websiteElementsService.getImageUrlsElements();
        if (imageUrlsElementList.size() > 2){
            if (scrapingService != null)
                scrapingService.scrapeImageUrls(imageUrlsElementList);
        }

        // Scrapping prices
        List<WebElement> pricesElementList = websiteElementsService.getPricesElements();
        if (pricesElementList.size() > 0){
            if (scrapingService != null)
                scrapingService.scrapePrices(pricesElementList);
        }

        // We need to send post requests
        // Alerts are only sent if the results scraped from the website are equal or bigger than 5
        assert scrapingService != null;
        if (scrapingService.getTitles().size() >= 5 && scrapingService.getDescriptions().size() >= 5 &&
                scrapingService.getUrls().size() >= 5 && scrapingService.getImageUrls().size() >= 5 &&
                scrapingService.getPrices().size() >= 5){
            for (int i = 0; i < 5; i++){
                if (apiService != null)
                    apiService.sendPostRequests(scrapingService.getTitles().get(i),
                            scrapingService.getDescriptions().get(i), scrapingService.getUrls().get(i),
                            scrapingService.getImageUrls().get(i), scrapingService.getPrices().get(i));
            }
        }
    }
}
