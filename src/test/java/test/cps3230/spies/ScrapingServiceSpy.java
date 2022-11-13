package test.cps3230.spies;

import org.openqa.selenium.WebElement;
import utils.ScrapingService;

import java.util.ArrayList;
import java.util.List;

public class ScrapingServiceSpy implements ScrapingService {
    protected List<String> titles = new ArrayList<>();
    protected List<String> descriptions = new ArrayList<>();
    protected List<String> urls = new ArrayList<>();
    protected List<String> imageUrls = new ArrayList<>();
    protected List<Integer> prices = new ArrayList<>();

    public void scrapeTitles(List<WebElement> titlesList) {
        if (titlesList.size() < 5){
            for (int i = 0; i < titlesList.size(); i++) {
                titles.add(i, titlesList.get(i).getText());
            }
        }else{
            for (int i = 0; i < 5; i++) {
                titles.add(i, titlesList.get(i).getText());
            }
        }
    }

    public void scrapeDescriptions(List<WebElement> descriptionsList) {
        if (descriptionsList.size() < 5){
            for (int i = 0; i < descriptionsList.size(); i++) {
                descriptions.add(i, descriptionsList.get(i).getText());
            }
        }else{
            for (int i = 0; i < 5; i++) {
                descriptions.add(i, descriptionsList.get(i).getText());
            }
        }
    }

    public void scrapeUrls(List<WebElement> urlsList) {
        if (urlsList.size() < 5){
            for (int i = 0; i < urlsList.size(); i++) {
                urls.add(i, urlsList.get(i).getAttribute("href"));
            }
        } else {
            for (int i = 0; i < 5; i++) {
                urls.add(i, urlsList.get(i).getAttribute("href"));
            }
        }
    }

    public void scrapeImageUrls(List<WebElement> imageUrlsList) {
        if (imageUrlsList.size() < 7){
            for (int i = 0; i < imageUrlsList.size() - 2; i++) {
                imageUrls.add(i, imageUrlsList.get(i).getAttribute("src"));
            }
        } else {
            for (int i = 0; i < 5; i++) {
                imageUrls.add(i, imageUrlsList.get(i).getAttribute("src"));
            }
        }
    }

    public void scrapePrices(List<WebElement> pricesList) {
        if (pricesList.size() < 5){
            for (int i = 0; i < pricesList.size(); i++) {
                String newString = pricesList.get(i).getText().replace("€","");
                String newString2 = newString.replace(",", ".");
                double d = Double.parseDouble(newString2)*100;
                prices.add(i, (int) d);
            }
        } else {
            for (int i = 0; i < 5; i++) {
                String newString = pricesList.get(i).getText().replace("€","");
                String newString2 = newString.replace(",", ".");
                double d = Double.parseDouble(newString2)*100;
                prices.add(i, (int) d);
            }
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
