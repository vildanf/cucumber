package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class VehiclesPage extends BasePage {

//    @FindBy(css = "[title='Create Car']")
//    public WebElement createACarElement;
//
//    /**
//     * Use this method to click on "Create a Car" button
//     * Method already contains waits to handle synchronization issues
//     */
//    public void clickToCreateACar(){
//        waitUntilLoaderMaskDisappear();
//        BrowserUtils.waitForVisibility(createACarElement, 5);
//        BrowserUtils.waitForClickablility(createACarElement, 5);
//
//        createACarElement.click();

    @FindBy(css = "[title='Create Car']")
    public WebElement createACarElement;

    public void clickToCreateACar() throws InterruptedException {
        waitUntilLoaderMaskDisappear();
        BrowserUtils.waitForVisibility(createACarElement, 10);
        BrowserUtils.waitForClickablility(createACarElement, 10);
        Thread.sleep(5000);
        createACarElement.click();
    }
}
