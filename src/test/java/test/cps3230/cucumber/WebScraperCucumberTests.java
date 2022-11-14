package test.cps3230.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.Website;
import utils.ApiService;
import utils.WebElementsToString;
import pageObjects.ScraperPageObject;

import java.util.List;

public class WebScraperCucumberTests {

    Website website;
    WebDriver webDriver;
    ScraperPageObject scrapingService;
    WebElementsToString webElementsToString;
    ApiService apiService;

    String userKey;

    @Given("I am a user of marketalertum")
    public void iAmAUserOfMarketalertum() {
        website = new Website();
        scrapingService = new ScraperPageObject(webDriver);
        website.setWebsiteElementsService(scrapingService);

        userKey = "e7ee93d2-cf55-45da-a41e-6581361e3f20";
        scrapingService.setUpDriver("https://www.marketalertum.com/Alerts/Login");
    }

    @When("I login using valid credentials")
    public void iLoginUsingValidCredentials() {
        WebElement searchField = scrapingService.getDriver().findElement(By.name("UserId"));
        searchField.sendKeys(userKey);
        WebElement searchButton = scrapingService.getDriver()
                .findElement(By.xpath("//form[@action ='/Alerts/LoginForm']/input"));
        searchButton.submit();
    }

    @Then("I should see my alerts")
    public void iShouldSeeMyAlerts() {
        String myAlerts = scrapingService.getDriver()
                .findElement(By.xpath("//main[@class ='pb-3']/h1"))
                .getText();
        scrapingService.quitDriver();
        Assertions.assertEquals("Latest alerts for Guillermo Alejandro Castro Diaz", myAlerts);
    }

    @When("I login using invalid credentials")
    public void iLoginUsingInvalidCredentials() {
        WebElement searchField = scrapingService.getDriver().findElement(By.name("UserId"));
        searchField.sendKeys("ajalsjakslajljas");
        WebElement searchButton = scrapingService.getDriver()
                .findElement(By.xpath("//form[@action ='/Alerts/LoginForm']/input"));
        searchButton.submit();
    }

    @Then("I should see the login screen again")
    public void iShouldSeeTheLoginScreenAgain() {
        String userId = scrapingService.getDriver()
                .findElement(By.xpath("//form[@action ='/Alerts/LoginForm']/b"))
                .getText();
        scrapingService.quitDriver();
        Assertions.assertEquals("User ID:", userId);
    }

    @Given("I am an administrator of the website and I upload {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAlerts(int arg0) throws Exception {
        website = new Website();
        apiService = new ApiService();
        scrapingService = new ScraperPageObject(webDriver);
        webElementsToString = new WebElementsToString();

        website.setApiService(apiService);
        website.setWebsiteElementsService(scrapingService);
        website.setScrapingService(webElementsToString);

        // Clear any previous alerts
        apiService.sendDeleteRequests();

        boolean isSuccessful = website.productScrape("batman", arg0);
        Assertions.assertTrue(isSuccessful);
    }

    @When("I view a list of alerts")
    public void iViewAListOfAlerts() {
        //Login
        WebElement searchField = scrapingService.getDriver().findElement(By.name("UserId"));
        searchField.sendKeys(userKey);
        WebElement searchButton = scrapingService.getDriver()
                .findElement(By.xpath("//form[@action ='/Alerts/LoginForm']/input"));
        searchButton.submit();

        //See my alerts
        WebElement alertsList = scrapingService.getDriver().findElement(By.className("pb-3"));
        String alertListRoleName = alertsList.getAttribute("role");

        Assertions.assertEquals("main", alertListRoleName);
    }

    @Then("each alert should contain an icon")
    public void eachAlertShouldContainAnIcon() {
        List<WebElement> icons = scrapingService.getDriver()
                .findElements(By.xpath("//table[@border = 1]/tbody/tr/td/h4/img"));

        Assertions.assertEquals(3, icons.size());
    }

    @And("each alert should contain a heading")
    public void eachAlertShouldContainAHeading() {
        webElementsToString = new WebElementsToString();
        website.setScrapingService(webElementsToString);

        List<WebElement> headingElements = scrapingService.getDriver()
                .findElements(By.xpath("//table[@border = 1]/tbody/tr/td/h4/img"));

        webElementsToString.scrapeTitles(headingElements, headingElements.size());

        Assertions.assertEquals(3, webElementsToString.getTitles().size());
    }

    @And("each alert should contain a description")
    public void eachAlertShouldContainADescription() {
        List<WebElement> descriptionElements = scrapingService.getDriver()
                .findElements(By.xpath("//tbody[@border = 1]/tr[2]/td"));

        webElementsToString.scrapeDescriptions(descriptionElements, descriptionElements.size());

        Assertions.assertEquals(3, webElementsToString.getDescriptions().size());
    }

    @And("each alert should contain an image")
    public void eachAlertShouldContainAnImage() {
        List<WebElement> imageElements = scrapingService.getDriver()
                .findElements(By.xpath("//td[@rowspan = 4]/img"));

        webElementsToString.scrapeImageUrls(imageElements, imageElements.size());

        Assertions.assertEquals(3, webElementsToString.getImageUrls().size());
    }

    @And("each alert should contain a price")
    public void eachAlertShouldContainAPrice() {
        List<WebElement> priceElements = scrapingService.getDriver()
                .findElements(By.xpath("//tbody[@border = 1]/tr[4]/td"));

        Assertions.assertEquals(3, priceElements.size());
    }

    @And("each alert should contain a link to the original product website")
    public void eachAlertShouldContainALinkToTheOriginalProductWebsite() {
        List<WebElement> urlElements = scrapingService.getDriver()
                .findElements(By.xpath("//tbody[@border = 1]/tr/td/a"));

        webElementsToString.scrapeUrls(urlElements, urlElements.size());

        scrapingService.quitDriver();

        Assertions.assertEquals(3, webElementsToString.getUrls().size());
    }


    @Given("I am an administrator of the website and I upload more than {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadMoreThanAlerts(int arg0) throws Exception {
        website = new Website();
        apiService = new ApiService();
        scrapingService = new ScraperPageObject(webDriver);
        webElementsToString = new WebElementsToString();

        website.setApiService(apiService);
        website.setScrapingService(webElementsToString);
        website.setWebsiteElementsService(scrapingService);

        // Clear any previous alerts
        apiService.sendDeleteRequests();

        website.productScrape("batman", arg0+1);
    }

    @When("I view a list of alerts I should see {int} alerts")
    public void iViewAListOfAlertsIShouldSeeAlerts(int arg0) {
        //Login
        WebElement searchField = scrapingService.getDriver().findElement(By.name("UserId"));
        searchField.sendKeys(userKey);
        WebElement searchButton = scrapingService.getDriver()
                .findElement(By.xpath("//form[@action ='/Alerts/LoginForm']/input"));
        searchButton.submit();

        //See my alerts
        List<WebElement> alertsList = scrapingService.getDriver().findElements(By.xpath("//table[@border = 1]"));

        scrapingService.quitDriver();
        Assertions.assertEquals(arg0, alertsList.size());
    }

    @Given("I am an administrator of the website and I upload an alert of type {int}")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfTypeAlertType(int alertType) throws Exception {
        website = new Website();
        apiService = new ApiService();

        website.setApiService(apiService);

        // Clear any previous alerts
        apiService.sendDeleteRequests();

        apiService.sendPostRequests(alertType, "Batman", "Jeph Loeb",
                "https://www.bookdepository.com/Batman-Long-Halloween-Jeph-Loeb/9781401232597?ref=grid-view&qid=1668366982667&sr=1-2",
                "https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/mid/9781/4012/9781401232597.jpg",
                1500);
    }

    @Then("I should see {int} alerts")
    public void iShouldSeeAlerts(int arg0) {
        List<WebElement> alertsList = scrapingService.getDriver().findElements(By.xpath("//table[@border = 1]"));

        Assertions.assertEquals(arg0, alertsList.size());
    }

    @And("the icon displayed should be {string}")
    public void theIconDisplayedShouldBeIconFileName(String filename) {
        String icon = scrapingService.getDriver()
                .findElement(By.xpath("//table[@border = 1]/tbody/tr/td/h4/img")).getAttribute("src");

        scrapingService.quitDriver();
        Assertions.assertEquals(filename, icon);
    }
}
