package test.cps3230.website.pageObjects;

import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.cps3230.website.Information;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class WebsitePageObject {

    WebDriver driver;

    List<String> titles = new ArrayList<>();
    List<String> descriptions = new ArrayList<>();
    List<String> urls = new ArrayList<>();
    List<String> imageUrls = new ArrayList<>();
    List<Integer> prices = new ArrayList<>();

    public WebsitePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void searchForProduct(String product) throws Exception {
        WebElement searchField = driver.findElement(By.name("searchTerm"));
        searchField.sendKeys(product);
        WebElement searchButton = driver.findElement(By.className("header-search-btn"));
        searchButton.submit();

        // Scrapping 5 titles
        List<WebElement> titlesList = driver.findElements(By.className("title"));
        scrapeTitles(titlesList);

        // Scrapping 5 authors
        List<WebElement> descriptionsList = driver.findElements(By.xpath("//p[@class = 'author']/span/a/span"));
        scrapeDescriptions(descriptionsList);

        // Scrapping 5 urls
        List<WebElement> urlsList = driver.findElements(By.xpath("//h3[@class = 'title']/a"));
        scrapeUrls(urlsList);

        // Scrapping 5 image urls
        List<WebElement> imageUrlsList = driver.findElements(By.xpath("//img[@class = 'lazy loaded']"));
        scrapeImageUrls(imageUrlsList);

        // Scrapping 5 prices
        List<WebElement> pricesList = driver.findElements(By.className("sale-price"));
        scrapePrices(pricesList);

        // We need to send post requests
        for (int i = 0; i < 5; i++) {
            sendPostRequests(titles.get(i), descriptions.get(i), urls.get(i), imageUrls.get(i), prices.get(i));
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
        Information information = new Information();

        information.setAlertType(6);
        information.setHeading(title);
        information.setDescription(description);
        information.setUrl(bookUrl);
        information.setImageUrl(imageUrl);
        information.setPostedBy("e7ee93d2-cf55-45da-a41e-6581361e3f20");
        information.setPriceInCents(price);

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(information);

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
