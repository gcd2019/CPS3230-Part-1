package test.cps3230.website.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WebsitePageObject {

    WebDriver driver;

    List<String> titles = new ArrayList<>();
    List<String> urls = new ArrayList<>();
    List<String> imageUrls = new ArrayList<>();
    List<String> prices = new ArrayList<>();

    public WebsitePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void searchForProduct(String product) {
        WebElement searchField = driver.findElement(By.name("searchTerm"));
        searchField.sendKeys(product);
        WebElement searchButton = driver.findElement(By.className("header-search-btn"));
        searchButton.submit();

        List<WebElement> titlesList = driver.findElements(By.className("title"));
        scrapeTitles(titlesList);

        List<WebElement> urlsList = driver.findElements(By.xpath("//h3[@class = 'title']/a"));
        scrapeUrls(urlsList);

        List<WebElement> imageUrlsList = driver.findElements(By.xpath("//img[@class = 'lazy loaded']"));
        scrapeImageUrls(imageUrlsList);

        List<WebElement> pricesList = driver.findElements(By.className("sale-price"));
        scrapePrices(pricesList);
    }

    public void scrapeTitles(List<WebElement> productList) {
        for (int i = 0; i < 5; i++) {
            titles.add(i, productList.get(i).getText());
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
            prices.add(i, productList.get(i).getText());
        }
    }
}
