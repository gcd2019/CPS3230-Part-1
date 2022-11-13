package utils;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface ScrapingService {
    void scrapeTitles(List<WebElement> titlesList);

    void scrapeDescriptions(List<WebElement> descriptionsList);

    void scrapeUrls(List<WebElement> urlsList);

    void scrapeImageUrls(List<WebElement> imageUrlsList);

    void scrapePrices(List<WebElement> pricesList);

    List<String> getTitles();

    List<String> getDescriptions();

    List<String> getUrls();

    List<String> getImageUrls();

    List<Integer> getPrices();
}
