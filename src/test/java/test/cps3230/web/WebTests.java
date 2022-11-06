package test.cps3230.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTests {

    WebDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/memos/webtesting/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearddown() {
        driver.quit();
    }

    @Test
    public void testWebsiteSearch() {
        driver.get("https://www.merchbar.com/");
    }
}
