package test.cps3230.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.WebsitePageObject;
import utils.ApiService;
import utils.ScrapingService;
import utils.WebsiteElementsService;

import java.util.List;

public class WebScraperCucumberTests {

    WebsitePageObject websitePageObject;
    WebsiteElementsService websiteElementsService;
    ScrapingService scrapingService;
    ApiService apiService;

    String userKey;

    @Given("I am a user of marketalertum")
    public void iAmAUserOfMarketalertum() {
        websitePageObject = new WebsitePageObject();
        websiteElementsService = new WebsiteElementsService();
        websitePageObject.setWebsiteElementsService(websiteElementsService);

        userKey = "e7ee93d2-cf55-45da-a41e-6581361e3f20";
        websiteElementsService.setUpDriver("https://www.marketalertum.com/Alerts/Login");
    }

    @When("I login using valid credentials")
    public void iLoginUsingValidCredentials() {
        WebElement searchField = websiteElementsService.getDriver().findElement(By.name("UserId"));
        searchField.sendKeys(userKey);
        WebElement searchButton = websiteElementsService.getDriver()
                .findElement(By.xpath("//form[@action ='/Alerts/LoginForm']/input"));
        searchButton.submit();
    }

    @Then("I should see my alerts")
    public void iShouldSeeMyAlerts() {
        String myAlerts = websiteElementsService.getDriver()
                .findElement(By.xpath("//main[@class ='pb-3']/h1"))
                .getText();
        websiteElementsService.quitDriver();
        Assertions.assertEquals("Latest alerts for Guillermo Alejandro Castro Diaz", myAlerts);
    }

    @When("I login using invalid credentials")
    public void iLoginUsingInvalidCredentials() {
        WebElement searchField = websiteElementsService.getDriver().findElement(By.name("UserId"));
        searchField.sendKeys("ajalsjakslajljas");
        WebElement searchButton = websiteElementsService.getDriver()
                .findElement(By.xpath("//form[@action ='/Alerts/LoginForm']/input"));
        searchButton.submit();
    }

    @Then("I should see the login screen again")
    public void iShouldSeeTheLoginScreenAgain() {
        String userId = websiteElementsService.getDriver()
                .findElement(By.xpath("//form[@action ='/Alerts/LoginForm']/b"))
                .getText();
        websiteElementsService.quitDriver();
        Assertions.assertEquals("User ID:", userId);
    }

    @Given("I am an administrator of the website and I upload {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAlerts(int arg0) throws Exception {
        websitePageObject = new WebsitePageObject();
        apiService = new ApiService();
        websitePageObject.setApiService(apiService);

        // Clear any previous alerts
        apiService.sendDeleteRequests();

        for (int i = 0; i < arg0; i++)
            apiService.sendPostRequests(5, "Batman", "Jeph Loeb",
                    "https://www.bookdepository.com/Batman-Long-Halloween-Jeph-Loeb/9781401232597?ref=grid-view&qid=1668366982667&sr=1-2",
                    "https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/mid/9781/4012/9781401232597.jpg",
                    1500);
    }

    @When("I view a list of alerts")
    public void iViewAListOfAlerts() {
        //Login
        WebElement searchField = websiteElementsService.getDriver().findElement(By.name("UserId"));
        searchField.sendKeys(userKey);
        WebElement searchButton = websiteElementsService.getDriver()
                .findElement(By.xpath("//form[@action ='/Alerts/LoginForm']/input"));
        searchButton.submit();

        //See my alerts
        WebElement alertsList = websiteElementsService.getDriver().findElement(By.className("pb-3"));
        String alertListRoleName = alertsList.getAttribute("role");

        Assertions.assertEquals("main", alertListRoleName);
    }

    @Then("each alert should contain an icon")
    public void eachAlertShouldContainAnIcon() {
        List<WebElement> icons = websiteElementsService.getDriver()
                .findElements(By.xpath("//table[@border = 1]/tbody/tr/td/h4/img"));

        Assertions.assertEquals(3, icons.size());
    }

    @And("each alert should contain a heading")
    public void eachAlertShouldContainAHeading() {
        scrapingService = new ScrapingService();
        websitePageObject.setScrapingService(scrapingService);

        List<WebElement> headingElements = websiteElementsService.getDriver()
                .findElements(By.xpath("//table[@border = 1]/tbody/tr/td/h4/img"));

        scrapingService.scrapeTitles(headingElements, headingElements.size());

        Assertions.assertEquals(3, scrapingService.getTitles().size());
    }

    @And("each alert should contain a description")
    public void eachAlertShouldContainADescription() {
        //
    }

    @And("each alert should contain an image")
    public void eachAlertShouldContainAnImage() {
        //
    }

    @And("each alert should contain a price")
    public void eachAlertShouldContainAPrice() {
        //
    }

    @And("each alert should contain a link to the original product website")
    public void eachAlertShouldContainALinkToTheOriginalProductWebsite() {
        //
    }


    @Given("I am an administrator of the website and I upload more than {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadMoreThanAlerts(int arg0) throws Exception {
        websitePageObject = new WebsitePageObject();
        apiService = new ApiService();
        websitePageObject.setApiService(apiService);

        // Clear any previous alerts
        apiService.sendDeleteRequests();

        for (int i = 0; i < arg0+1; i++)
            apiService.sendPostRequests(5, "Batman", "Jeph Loeb",
                    "https://www.bookdepository.com/Batman-Long-Halloween-Jeph-Loeb/9781401232597?ref=grid-view&qid=1668366982667&sr=1-2",
                    "https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/mid/9781/4012/9781401232597.jpg",
                    1500);
    }

    @When("I view a list of alerts I should see {int} alerts")
    public void iViewAListOfAlertsIShouldSeeAlerts(int arg0) {
        //Login
        WebElement searchField = websiteElementsService.getDriver().findElement(By.name("UserId"));
        searchField.sendKeys(userKey);
        WebElement searchButton = websiteElementsService.getDriver()
                .findElement(By.xpath("//form[@action ='/Alerts/LoginForm']/input"));
        searchButton.submit();

        //See my alerts
        List<WebElement> alertsList = websiteElementsService.getDriver().findElements(By.xpath("//table[@border = 1]"));

        websiteElementsService.quitDriver();
        Assertions.assertEquals(arg0, alertsList.size());
    }

    @Given("I am an administrator of the website and I upload an alert of type {int}")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfTypeAlertType(int alertType) throws Exception {
        websitePageObject = new WebsitePageObject();
        apiService = new ApiService();
        websitePageObject.setApiService(apiService);

        // Clear any previous alerts
        apiService.sendDeleteRequests();

        apiService.sendPostRequests(alertType, "Batman", "Jeph Loeb",
                "https://www.bookdepository.com/Batman-Long-Halloween-Jeph-Loeb/9781401232597?ref=grid-view&qid=1668366982667&sr=1-2",
                "https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/mid/9781/4012/9781401232597.jpg",
                1500);
    }

    @Then("I should see {int} alerts")
    public void iShouldSeeAlerts(int arg0) {
        List<WebElement> alertsList = websiteElementsService.getDriver().findElements(By.xpath("//table[@border = 1]"));

        Assertions.assertEquals(arg0, alertsList.size());
    }

    @And("the icon displayed should be {string}")
    public void theIconDisplayedShouldBeIconFileName(String filename) {
        String icon = websiteElementsService.getDriver()
                .findElement(By.xpath("//table[@border = 1]/tbody/tr/td/h4/img")).getAttribute("src");

        websiteElementsService.quitDriver();
        Assertions.assertEquals(filename, icon);
    }
}
