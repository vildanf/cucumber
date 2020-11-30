package com.vytrack.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class BrowserUtils {
    public static void wait(int seconds){
        try{
            Thread.sleep(1000*seconds);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    /**
     * Waits for element to be not stale
     *
     * @param element
     */
    public static void waitForStaleElement(WebElement element) {
        int y = 0;
        while (y <= 15) {
            try {
                element.isDisplayed();
                break;
            } catch (StaleElementReferenceException st) {
                y++;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            break;
        }
    }
    /**
     * Waits for the provided element to be visible on the page
     *
     * @param element
     * @param timeToWaitInSec
     * @return
     */
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Clicks on an element using JavaScript
     *
     * @param element
     */
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].click();", element);
    }
    /**
     * Waits for provided element to be clickable
     *
     * @param element
     * @param timeout
     * @return
     */
    public static WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    public static String getScreenshot(String name){
      //  String date= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        SimpleDateFormat df=new SimpleDateFormat("-yyyy-MM-dd HH:mm");
        String date=df.format(new Date());

        TakesScreenshot ts= (TakesScreenshot) Driver.get();
        File source=ts.getScreenshotAs(OutputType.FILE);

        String target= System.getProperty("user.dir")+"/test-output/Screenshots/"+name+date+".png";

        File finalDestination=new File(target);
    try{
        FileUtils.copyFile(source,finalDestination);
    }catch(IOException e){
        e.printStackTrace();

    }
return target;

    }
public static void clickWithWait(WebElement webElement) {
        Wait wait = new FluentWait<>(Driver.get())
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotVisibleException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(WebDriverException.class);
        WebElement element = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> webElement);
        try {
            element.click();
        } catch (WebDriverException e) {
            System.out.println(e.getMessage());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            element.click();
        }
    }
      public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        ExpectedCondition<Boolean> expectation2=driver ->((JavascriptExecutor) driver).executeScript("return Jquery active =00").equals(true);
        try {
            WebDriverWait wait = new WebDriverWait(Driver.get(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    /**
     * Wait for proper page title
     * @param pageTitle
     */
    public static void waitForPageTitle(String pageTitle){
        WebDriverWait wait = new WebDriverWait(Driver.get(), 10);
        wait.until(ExpectedConditions.titleIs(pageTitle));
    }
    //This method will convert listof Webelements into list of strings
    public static List<String> getListOfString(List<WebElement> listOfWebElements){
        List<String> listOfStrings=new ArrayList<>();
        for(WebElement element :listOfWebElements){
            String value=element.getText().trim();
            //if there is no text do not add this blank text into list
            if(value.length()>0){
                listOfStrings.add(value);
            }
        }
    return listOfStrings;
    }

}
