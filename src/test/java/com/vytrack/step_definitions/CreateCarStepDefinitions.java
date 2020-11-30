package com.vytrack.step_definitions;

import com.vytrack.pages.CreateCarPage;
import com.vytrack.pages.VehiclesPage;
import com.vytrack.utilities.BrowserUtils;
import io.cucumber.java.en.Then;

import java.util.List;
import java.util.Map;

public class CreateCarStepDefinitions {


    CreateCarPage createCarPage = new CreateCarPage();
    VehiclesPage vehiclesPage = new VehiclesPage();

    @Then("user click on {string} button")
    public void user_click_on_button(String string) throws InterruptedException {
        vehiclesPage.waitUntilLoaderMaskDisappear();
        if (string.equals("Create Car")) {
        createCarPage.waitUntilLoaderMaskDisappear();
            //createCarPage.wait(10);
            vehiclesPage.clickToCreateACar();
        }
    }

    @Then("user adds new car information:")
    public void user_adds_new_car_information(List<Map<String, String>> dataTable) throws InterruptedException {
       createCarPage.waitUntilLoaderMaskDisappear();
       int  row=1;
        for (Map<String, String> map : dataTable) {
Thread.sleep(1000);
            createCarPage.licensePlateElement.sendKeys(map.get("Licence Plate"));
            createCarPage.driverElement.sendKeys(map.get("Driver"));
            createCarPage.locationElement.sendKeys(map.get("Location"));
            createCarPage.modelYearElement.sendKeys(map.get("Model Year"));
            createCarPage.colorElement.sendKeys(map.get("Color"));
            BrowserUtils.wait(2);
            if(row ==dataTable.size()){
                createCarPage.clickSaveAndClose();;
            }else{
                createCarPage.clickSaveAndNew();
            }
            //createCarPage.saveAndCloseButtonElement.click();
            BrowserUtils.wait(2);
            row++;
        }
    }
    @Then("user navigates to {string} then  to {string}")
public void user_navigates_to_then_to(String string, String string2) {

}


}
