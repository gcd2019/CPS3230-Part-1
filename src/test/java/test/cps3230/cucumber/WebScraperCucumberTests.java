package test.cps3230.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.WebsitePageObject;
import test.cps3230.spies.WebsiteElementsServiceSpy;

public class WebScraperCucumberTests {

    WebsitePageObject websitePageObject;
    WebsiteElementsServiceSpy websiteElementsService;
    String userKey;

    @Given("I am a user of marketalertum")
    public void iAmAUserOfMarketalertum() {
        websitePageObject = new WebsitePageObject();
        websiteElementsService = new WebsiteElementsServiceSpy();
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
}
