package test.cps3230.website.pageObjects;

import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.cps3230.website.AlertRequest;

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

    public void searchForProduct(String product) throws Exception {
        WebElement searchField = driver.findElement(By.name("searchTerm"));
        searchField.sendKeys(product);
        WebElement searchButton = driver.findElement(By.className("header-search-btn"));
        searchButton.submit();

        // Scrapping titles
        List<WebElement> titlesList = driver.findElements(By.className("title"));
        if (titlesList.size() >= 5)
            scrapeTitles(titlesList);

        // Scrapping authors
        List<WebElement> descriptionsList = driver.findElements(By.xpath("//p[@class = 'author']/span/a/span"));
        if (descriptionsList.size() >= 5)
            scrapeDescriptions(descriptionsList);

        // Scrapping urls
        List<WebElement> urlsList = driver.findElements(By.xpath("//h3[@class = 'title']/a"));
        if (urlsList.size() >= 5)
            scrapeUrls(urlsList);

        // Scrapping image urls
        // This is checking if the number of image urls in the website is bigger than 7
        // since when there are no results there are already another 2 image urls used in other parts of the website.
        List<WebElement> imageUrlsList = driver.findElements(By.xpath("//img[@class = 'lazy loaded']"));
        if (imageUrlsList.size() >= 7)
            scrapeImageUrls(imageUrlsList);

        // Scrapping prices
        List<WebElement> pricesList = driver.findElements(By.className("sale-price"));
        if (pricesList.size() >= 5)
            scrapePrices(pricesList);

        // We need to send post requests
        if (titles.size() != 0 || descriptions.size() != 0 || urls.size() != 0 || imageUrls.size() != 0
                || prices.size() != 0){
            for (int i = 0; i < 5; i++) {
                sendPostRequests(titles.get(i), descriptions.get(i), urls.get(i), imageUrls.get(i), prices.get(i));
            }
        }
    }

    public void scrapeTitles(List<WebElement> productList) {
        for (int i = 0; i < 5; i++) {
            titles.add(i, productList.get(i).getText());
        }
    }

    public void scrapeDescriptions(List<WebElement> productList) {
        for (int i = 0; i < 5; i++) {
            descriptions.add(i, productList.get(i).getText());
        }
    }

    public void scrapeUrls(List<WebElement> productList) {
        for (int i = 0; i < 5; i++) {
            urls.add(i, productList.get(i).getAttribute("href"));
        }
    }

    public void scrapeImageUrls(List<WebElement> productList) {
        for (int i = 0; i < 5; i++) {
            imageUrls.add(i, productList.get(i).getAttribute("src"));
        }
    }

    public void scrapePrices(List<WebElement> productList) {
        for (int i = 0; i < 5; i++) {
            String newString = productList.get(i).getText().replace("€","");
            String newString2 = newString.replace(",", ".");
            double d = Double.parseDouble(newString2)*100;
            prices.add(i, (int) d);
        }
    }

    public void sendPostRequests(String title, String description, String bookUrl, String imageUrl, int price) throws Exception {
        AlertRequest alertRequest = new AlertRequest();

        alertRequest.setAlertType(6);
        alertRequest.setHeading(title);
        alertRequest.setDescription(description);
        alertRequest.setUrl(bookUrl);
        alertRequest.setImageUrl(imageUrl);
        alertRequest.setPostedBy("e7ee93d2-cf55-45da-a41e-6581361e3f20");
        alertRequest.setPriceInCents(price);

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
