package com.vytrack.step_definitions;


import com.vytrack.pages.LoginPage;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.Map;


public class LoginStepDefinitions {
    LoginPage loginPage = new LoginPage();

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        System.out.println("I am on the login page");


        Driver.get().get(ConfigurationReader.getProperty("url"));
    }

    @Then("user logs in as store manager")
    public void user_logs_in_as_store_manager() {
        System.out.println("Login as store manager");
        String userName = ConfigurationReader.getProperty("user_name");
        String password = ConfigurationReader.getProperty("password");
        loginPage.login(userName, password);
    }

    //any string word in "" will become a parameter for step definition method
    @Then("user verifies that {string} page subtitle is displayed")
    public void user_verifies_that_page_subtitle_is_displayed(String string) {
        System.out.println("Verifying page subtitle: " + string);
        loginPage.getPageSubTitle();
        BrowserUtils.wait(5);
        Assert.assertEquals(string, loginPage.getPageSubTitle());
    }

    @Then("user logs in as driver")
    public void user_logs_in_as_driver() {
        System.out.println("Login as driver");
    }

    @Then("user logs in as sales manager")
    public void user_logs_in_as_sales_manager() {
        System.out.println("Login as sales manager");
    }

    @Then("user enters {string} and {string} password")
    public void user_enters_and_password(String string, String string2) {
        System.out.println("Login with " + string + " username and " + string2 + " password.");
        loginPage.login(string,string2);
    }

    @Then("user verifies that {string} message is displayed")
    public void user_verifies_that_message_is_displayed(String string) {
        System.out.println("Verified that warning message is displayed: " + string);
    }

    @Then("user logs in as driver with following credential")
    public void user_logs_in_as_driver_with_following_credential(Map<String, String> dataTable) {

        System.out.println(dataTable);
        loginPage.login(dataTable.get("username"), dataTable.get("password"));

    }

    @Then("user logs in as {string}")
    public void user_logs_in_as(String role) {
    loginPage.login(role);
    }

    @Then("the page title should be {string}")
public void the_page_title_should_be(String string) {
        BrowserUtils.waitForPageTitle(string);

    Assert.assertEquals("Title is not correct",string,Driver.get().getTitle());
}

}

