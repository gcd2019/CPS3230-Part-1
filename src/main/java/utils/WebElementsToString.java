package utils;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WebElementsToString {
    protected List<String> titles = new ArrayList<>();
    protected List<String> descriptions = new ArrayList<>();
    protected List<String> urls = new ArrayList<>();
    protected List<String> imageUrls = new ArrayList<>();
    protected List<Integer> prices = new ArrayList<>();

    public boolean scrapeTitles(List<WebElement> titlesList, int n) {
        if (titlesList == null)
            return false;

        for (int i = 0; i < n; i++) {
            titles.add(i, titlesList.get(i).getText());
        }
        return true;
    }

    public boolean scrapeDescriptions(List<WebElement> descriptionsList, int n) {
        if (descriptionsList == null)
            return false;

        for (int i = 0; i < n; i++) {
            descriptions.add(i, descriptionsList.get(i).getText());
        }

        return true;
    }

    public boolean scrapeUrls(List<WebElement> urlsList, int n) {
        if (urlsList == null)
            return false;

        for (int i = 0; i < n; i++) {
            urls.add(i, urlsList.get(i).getAttribute("href"));
        }

        return true;
    }

    public boolean scrapeImageUrls(List<WebElement> imageUrlsList, int n) {
        if (imageUrlsList == null)
            return false;

        for (int i = 0; i < n; i++) {
            imageUrls.add(i, imageUrlsList.get(i).getAttribute("src"));
        }

        return true;
    }

    public boolean scrapePrices(List<WebElement> pricesList, int n) {
        if (pricesList == null)
            return false;

        for (int i = 0; i < n; i++) {
            String newString = pricesList.get(i).getText().replace("€","");
            String newString2 = newString.replace(",", ".");
            double d = Double.parseDouble(newString2)*100;
            prices.add(i, (int) d);
        }

        return true;
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
