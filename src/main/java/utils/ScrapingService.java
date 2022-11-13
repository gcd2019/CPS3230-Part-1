package utils;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ScrapingService {
    protected List<String> titles = new ArrayList<>();
    protected List<String> descriptions = new ArrayList<>();
    protected List<String> urls = new ArrayList<>();
    protected List<String> imageUrls = new ArrayList<>();
    protected List<Integer> prices = new ArrayList<>();

    public void scrapeTitles(List<WebElement> titlesList, int n) {
        for (int i = 0; i < n; i++) {
            titles.add(i, titlesList.get(i).getText());
        }
    }

    public void scrapeDescriptions(List<WebElement> descriptionsList, int n) {
        for (int i = 0; i < n; i++) {
            descriptions.add(i, descriptionsList.get(i).getText());
        }
    }

    public void scrapeUrls(List<WebElement> urlsList, int n) {
        for (int i = 0; i < n; i++) {
            urls.add(i, urlsList.get(i).getAttribute("href"));
        }
    }

    public void scrapeImageUrls(List<WebElement> imageUrlsList, int n) {
        for (int i = 0; i < n - 2; i++) {
            imageUrls.add(i, imageUrlsList.get(i).getAttribute("src"));
        }
    }

    public void scrapePrices(List<WebElement> pricesList, int n) {
        for (int i = 0; i < n; i++) {
            String newString = pricesList.get(i).getText().replace("€","");
            String newString2 = newString.replace(",", ".");
            double d = Double.parseDouble(newString2)*100;
            prices.add(i, (int) d);
        }
    }

    public List<String> getTitles(){
        return titles;
    }

    public List<String> getDescriptions(){
        return descriptions;
    }

    public List<String> getUrls(){
        return urls;
    }

    public List<String> getImageUrls(){
        return imageUrls;
    }

    public List<Integer> getPrices(){
        return prices;
    }

}
