package test.cps3230.website.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebsitePageObject {

    WebDriver driver;

    public WebsitePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void pressSearch(String product) {
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys(product);
        WebElement searchButton = driver.findElement(By.className("actions"));
        searchButton.submit();
    }
}
