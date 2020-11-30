package com.vytrack.pages;
// according to page object model design we have to crate
//corresponded page class for each page of application
//every page class will store webelements and methods

import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BasePage {
    @FindBy(id = "prependedInput")// will initialize web element
    public WebElement userNameInput;
    @FindBy(id = "prependedInput2")// without findby web element will be null

    public WebElement passwordInput;
    @FindBy(id = "_submit")
    public WebElement loginButton;
    @FindBy(css = "[class='alert alert-error']")
    public WebElement warningMessage;

    public LoginPage() {
        // it is mantatory if you want to use @FindBy annotation
        // this means LoginPage class
        // Driver.get() return web driver object
        PageFactory.initElements(Driver.get(), this);
    }

    /**
     * resuable login method
     * just call this method  to login provide
     * username and password as parameters
     *
     * @param userName
     * @param passwordStr
     */
    public void login(String userName, String passwordStr) {
        userNameInput.sendKeys(userName);
        passwordInput.sendKeys(passwordStr, Keys.ENTER);

    }
    public void login(){
        login(ConfigurationReader.getProperty("username"),ConfigurationReader.getProperty("password"));
    }


    public void login(String role) {
        String userName = "";
        String password=ConfigurationReader.getProperty("password");
        switch (role) {
            case "driver":
                userName = ConfigurationReader.getProperty("driver.username");
                break;
            case "sales manager":
                userName = ConfigurationReader.getProperty("sales.manager.username");
                break;
            case "store manager":
                userName = ConfigurationReader.getProperty("store.manager.username");
                break;
            default:
                throw new RuntimeException("Invalid role");
        }
        login(userName,password);
    }
}