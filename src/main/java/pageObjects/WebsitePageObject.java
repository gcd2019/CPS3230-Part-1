package pageObjects;

import com.google.gson.Gson;
import jsonConstructor.AlertRequest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class WebsitePageObject {

    WebDriver driver;

    public List<String> titles = new ArrayList<>();
    public List<String> descriptions = new ArrayList<>();
    public List<String> urls = new ArrayList<>();
    public List<String> imageUrls = new ArrayList<>();
    public List<Integer> prices = new ArrayList<>();

    public WebsitePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void productScrape(String product) throws Exception {
        searchForProduct(product);

        // Scrapping titles
        List<WebElement> titlesElementList = getTitles();
        if (titlesElementList.size() > 0)
            scrapeTitles(titlesElementList);

        // Scrapping authors
        List<WebElement> descriptionsElementList = getDescriptions();
        if (descriptionsElementList.size() > 0)
            scrapeDescriptions(descriptionsElementList);

        // Scrapping urls
        List<WebElement> urlsElementList = getUrls();
        if (urlsElementList.size() > 0)
            scrapeUrls(urlsElementList);

        // Scrapping image urls
        // This is checking if the number of image urls in the website is bigger than 2
        // since when no results are found there are already another 2 image urls used in other parts of the website.
        List<WebElement> imageUrlsElementList = getImageUrls();
        if (imageUrlsElementList.size() > 2)
            scrapeImageUrls(imageUrlsElementList);

        // Scrapping prices
        List<WebElement> pricesElementList = getPrices();
        if (pricesElementList.size() > 0)
            scrapePrices(pricesElementList);

        // We need to send post requests
        // Alerts are only sent if the results scraped from the website are equal or bigger than 5
        if (titles.size() >= 5 && descriptions.size() >= 5 && urls.size() >= 5 && imageUrls.size() >= 5
                && prices.size() >= 5){
            sendPostRequests();
        }
    }

    public void searchForProduct(String product) {
        WebElement searchField = driver.findElement(By.name("searchTerm"));
        searchField.sendKeys(product);
        WebElement searchButton = driver.findElement(By.className("header-search-btn"));
        searchButton.submit();
    }

    public List<WebElement> getTitles(){
        return driver.findElements(By.className("title"));
    }

    public List<WebElement> getDescriptions(){
        return driver.findElements(By.xpath("//p[@class = 'author']/span/a/span"));
    }

    public List<WebElement> getUrls(){
        return driver.findElements(By.xpath("//h3[@class = 'title']/a"));
    }

    public List<WebElement> getImageUrls(){
        return driver.findElements(By.xpath("//img[@class = 'lazy loaded']"));
    }

    public List<WebElement> getPrices(){
        return driver.findElements(By.className("sale-price"));
    }

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

    public void sendPostRequests() throws Exception {
        for (int i = 0; i < 5; i++){
            AlertRequest alertRequest = new AlertRequest();

            alertRequest.setAlertType(6);
            alertRequest.setHeading(titles.get(i));
            alertRequest.setDescription(descriptions.get(i));
            alertRequest.setUrl(urls.get(i));
            alertRequest.setImageUrl(imageUrls.get(i));
            alertRequest.setPostedBy("e7ee93d2-cf55-45da-a41e-6581361e3f20");
            alertRequest.setPriceInCents(prices.get(i));

            Gson gson = new Gson();
            String jsonRequest = gson.toJson(alertRequest);

            System.out.println(jsonRequest);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://api.marketalertum.com/Alert"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse <String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());
        }
    }
}
