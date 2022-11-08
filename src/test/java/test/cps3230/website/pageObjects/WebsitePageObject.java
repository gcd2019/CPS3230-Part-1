package test.cps3230.website.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebsitePageObject {

    WebDriver driver;

    List<String> titles;
    List<String> urls;
    List<String> imageUrls;
    List<String> prices;

    public WebsitePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void searchForProduct(String product) {
        WebElement searchField = driver.findElement(By.name("searchTerm"));
        searchField.sendKeys(product);
        WebElement searchButton = driver.findElement(By.className("header-search-btn"));
        searchButton.submit();

//        List<WebElement> productList = driver.findElements(By.className("item product product-item"));
//
//        titles = scrapeTitles(productList);
//        urls = scrapeUrls(productList);
//        imageUrls = scrapeImageUrls(productList);
//        prices = scrapePrices(productList);
    }

//    public List<String> scrapeTitles(List<WebElement> productList) {
//        for (int i = 0; i < 5; i++) {
//            titles.get(i) = productList.get(i).findElement(By.className("aaaaa")).;
//        }
//    }
//
//    public List<String> scrapeUrls(List<WebElement> productList) {
//
//    }
//
//    public List<String> scrapeImageUrls(List<WebElement> productList) {
//
//    }
//
//    public List<String> scrapePrices(List<WebElement> productList) {
//
//    }
}
