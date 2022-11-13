package test.cps3230.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.WebsitePageObject;
import test.cps3230.spies.ApiServiceSpy;
import utils.ApiService;

public class ApiTests {

    WebsitePageObject websitePageObject;
    ApiServiceSpy apiService;

    @BeforeEach
    public void setup() {
        websitePageObject = new WebsitePageObject();
        apiService = new ApiServiceSpy();
        websitePageObject.setApiService(apiService);
    }

    @Test
    public void testSendingAPostRequestToTheApi() throws Exception {
        apiService.sendPostRequests("test","description",
                "https://www.google.com/",
                "https://imgresizer.eurosport.com/unsafe/1200x0/filters:format(jpeg):focal(1011x335:1013x333)/origin-imgresizer.eurosport.com/2022/06/01/3385867-69197608-2560-1440.jpg",
                30000);

        // Making sure we receive a response
        Assertions.assertNotNull(apiService.getResponse().body());
    }
}