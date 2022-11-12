package test.cps3230;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageObjects.WebsitePageObject;

public class ApiTests {

    WebDriver driver;
    WebsitePageObject websitePageObject;

    @BeforeEach
    public void setup() {
        websitePageObject = new WebsitePageObject(driver);
    }

    @Test
    public void testSendingAPostRequestToTheApi() throws Exception {
        websitePageObject.sendPostRequests("Batman: Year One","Frank Miller",
                "https://www.bookdepository.com/Batman-Year-One-Frank-Miller/9781401207526?ref",
                "https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/mid/9781/4012/9781401207526.jpg",
                1501);

        Assertions.assertNotNull(websitePageObject.response.body());
    }
}
