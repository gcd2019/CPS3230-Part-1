package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface WebsiteElementsService {
    void searchForProduct(String product);

    List<WebElement> getTitlesElements();

    List<WebElement> getDescriptionsElements();

    List<WebElement> getUrlsElements();

    List<WebElement> getImageUrlsElements();

    List<WebElement> getPricesElements();
}
