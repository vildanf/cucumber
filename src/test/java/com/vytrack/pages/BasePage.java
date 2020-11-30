package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

// everything that is common among pages
//can go here
//for ex. top menu elements don't belong to specific page
//top menu appears on every single page
//so keep them here
public class BasePage {
        @FindBy(css = "span.title-level-1")
    public List<WebElement> menuOptions;

    @FindBy(css = "div[class='loader-mask shown']")
    @CacheLookup
    protected WebElement loaderMask;

    @FindBy(css = "h1[class='oro-subtitle']")
    public WebElement pageSubTitle;

    @FindBy(css = "#user-menu > a")
    public WebElement userName;

    @FindBy(linkText = "Logout")
    public WebElement logOutLink;

    @FindBy(linkText = "My User")
    public WebElement myUser;
    /*
    @FindBy(css="div[class='loader-mask shown']")
    public WebElement loaderMask;
    @FindBy(css = "h1[class='oro-subtitle']")
    public WebElement pageSubTitle;

    @FindBy(id = "prependedInput")
    public WebElement userName;

    @FindBy(linkText = "Logout")
    public WebElement logOutLink;

    @FindBy(linkText = "My User")
    public WebElement myUser;
*/

    public BasePage() {

        PageFactory.initElements(Driver.get(), this);
    }

    /**while this loading screen present, html code is not complete
     * Some elements can be missing
     * Also you won't be able to interact with any elements
     *
     * Waits until loader mask disappear(loading bar spinning whell )
     * @return
     */
    public boolean waitUntilLoaderMaskDisappear(){
WebDriverWait wait=new WebDriverWait(Driver.get(),5);
try{
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loader-mask shown']")));
    return true;
}catch(NoSuchElementException e){
    System.out.println("Loader mask not found");
    e.printStackTrace();
    return true;
}catch(WebDriverException e){
    e.printStackTrace();

}
return false;
    }

//    public void waitUntilLoaderMaskDisappear() {
//        try {
//            WebDriverWait wait = new WebDriverWait(Driver.get(), 5);
//            wait.until(ExpectedConditions.invisibilityOf(loaderMask));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
/*
    public void navigateTo(String moduleName, String subModuleName){
        String moduleLocator="//*[normalize-space()='"+moduleName+"' and @class='title title-level-1']";
        String subModuleLocator="//*[normalize-space()='"+subModuleName+"' and @class='title title-level-2']";
        WebDriverWait wait=new WebDriverWait(Driver.get(),10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(moduleLocator)));

        WebElement module=Driver.get().findElement(By.xpath(moduleLocator));
        wait.until(ExpectedConditions.visibilityOf(module));
        wait.until(ExpectedConditions.elementToBeClickable(module));
        waitUntilLoaderMaskDisappear();
        module.click();//once we clicked on module sub module should be visisble

        WebElement subModule=Driver.get().findElement(By.xpath(subModuleLocator));
        wait.until(ExpectedConditions.visibilityOf(subModule));
        subModule.click();



    }*/
    public void navigateTo(String moduleName, String subModuleName) {
        Actions actions = new Actions(Driver.get());
        String moduleLocator = "//*[normalize-space()='" + moduleName + "' and @class='title title-level-1']";
        String subModuleLocator = "//*[normalize-space()='" + subModuleName + "' and @class='title title-level-2']";


        WebDriverWait wait = new WebDriverWait(Driver.get(), 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(moduleLocator)));

        WebElement module = Driver.get().findElement(By.xpath(moduleLocator));
        wait.until(ExpectedConditions.visibilityOf(module));
        wait.until(ExpectedConditions.elementToBeClickable(module));

        waitUntilLoaderMaskDisappear();
        BrowserUtils.clickWithWait(module); //if click is not working well
        WebElement subModule = Driver.get().findElement(By.xpath(subModuleLocator));

        if (!subModule.isDisplayed()) {
            actions.doubleClick(module).doubleClick().build().perform();
            try {
                wait.until(ExpectedConditions.visibilityOf(subModule));
            } catch (Exception ex) {
                ex.printStackTrace();
                BrowserUtils.clickWithJS(module);
            }
        }
        BrowserUtils.clickWithWait(subModule); //if click is not working well
        //it waits until page is loaded and ajax calls are done
        BrowserUtils.waitForPageToLoad(10);
    }
    public String getPageSubTitle(){
        waitUntilLoaderMaskDisappear();
        BrowserUtils.waitForStaleElement(pageSubTitle);
        return pageSubTitle.getText();

    }
    public String getUserName(){
        waitUntilLoaderMaskDisappear();
        BrowserUtils.waitForVisibility(userName,5);
        return userName.getText();

    }
    public void logOut(){
        BrowserUtils.wait(2);
        BrowserUtils.clickWithJS(userName);
        BrowserUtils.clickWithJS(logOutLink);

    }
    public void goToMyUser(){
        waitUntilLoaderMaskDisappear();
        BrowserUtils.waitForClickablility(userName, 5).click();
        BrowserUtils.waitForClickablility(myUser, 5).click();

    }
      public void waitForPageSubTitle(String pageSubtitleText) {
        new WebDriverWait(Driver.get(), 10).until(ExpectedConditions.textToBe(By.cssSelector("h1[class='oro-subtitle']"), pageSubtitleText));
    }
}
