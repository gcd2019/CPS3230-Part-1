package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class ScraperPageObject {
    protected WebDriver driver;

    public ScraperPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void setUpDriver(String url){
        System.setProperty("webdriver.chrome.driver", "C:/Users/memos/webtesting/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
    }

    public void quitDriver(){
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public boolean searchForProduct(String product) {
        WebElement searchField = driver.findElement(By.name("searchTerm"));
        searchField.sendKeys(product);
        WebElement searchButton = driver.findElement(By.className("header-search-btn"));
        searchButton.submit();

        String resultString = getDriver().
                findElement(By.xpath("//div[@class = 'main-content search-page']/h1")).getText();

        // We check that after making the search, we are redirected to the actual results page.
        String expectedString = "Search results for " + product;

        return resultString.equals(expectedString);
    }

    public List<WebElement> getTitlesElements(){
        return driver.findElements(By.className("title"));
    }

    public List<WebElement> getDescriptionsElements(){
        return driver.findElements(By.xpath("//p[@class = 'author']/span/a/span"));
    }

    public List<WebElement> getUrlsElements(){
        return driver.findElements(By.xpath("//h3[@class = 'title']/a"));
    }

    public List<WebElement> getImageUrlsElements(){
        return driver.findElements(By.xpath("//img[@class = 'lazy loaded']"));
    }

    public List<WebElement> getPricesElements(){
        return driver.findElements(By.className("sale-price"));
    }
}
