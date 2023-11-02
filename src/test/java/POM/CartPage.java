package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;

public class CartPage {
    WebDriver driver;
    By cartButton = By.xpath("(//button[@title='Add to Cart'])[1]");
    By countryInputLocator = By.id("country");
    By stateInputLocator = By.id("region_id");
    By provinceInputLocator = By.id("region");
    By zipInputLocator = By.id("postcode");
    By estimateButton = By.xpath("(//span[contains(text(),'Estimate')])[1]");
    By flatRate = By.xpath("(//label[contains(text(),'Fixed')])[1]");
    By flatRateButton = By.xpath("(//input[@id='s_method_flatrate_flatrate'])[1]");
    By updateTotalButton = By.xpath("(//button[@title='Update Total'])[1]");
    By subtotalLocator = By.xpath("(//td)[13]");
    By shippingCostLocator = By.xpath("(//td[@class='a-right'])[6]");
    By grandTotalLocator = By.xpath("(//td[@class='a-right'])[2]");
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCartButton(){
        driver.findElement(cartButton).click();
    }

    public void enterCountry(String country){
        WebElement countryElement = driver.findElement(countryInputLocator);
        new Select (countryElement).selectByVisibleText(country);
    }

    public void enterState(String state){
        WebElement stateElement = driver.findElement(stateInputLocator);
        new Select (stateElement).selectByVisibleText(state);
    }

    public void enterProvince(String province){
        WebElement provinceElement = driver.findElement(provinceInputLocator);
        provinceElement.clear();
        provinceElement.sendKeys(province);
    }

    public void enterZip(String zip){
        WebElement zipElement = driver.findElement(zipInputLocator);
        zipElement.clear();
        zipElement.sendKeys(zip);
    }

    public void clickEstimateButton(){
        driver.findElement(estimateButton).click();
    }
    public String verifyGenerated() {
        String checkStr = driver.findElement(flatRate).getText();
        AssertJUnit.assertEquals("Fixed - $15.00", checkStr);
        return checkStr;
    }
    public void clickFlatRateButton() {
        driver.findElement(flatRateButton).click();
    }
    public void clickUpdateTotalButton() {
        driver.findElement(updateTotalButton).click();
    }
    public void verifyTotal() {
//        double subTotal = Double.parseDouble(driver.findElement(subtotalLocator).getText().substring(1));
//        double shippingCost = Double.parseDouble(driver.findElement(shippingCostLocator).getText().substring(1));
//        double grandTotal = Double.parseDouble(driver.findElement(grandTotalLocator).getText().substring(1));
//        System.out.println("subTotal: $" + subTotal);
//        System.out.println("shippingCost: $" + shippingCost);
//        System.out.println("grandTotal: $" +grandTotal);
//        AssertJUnit.assertEquals(grandTotal,subTotal+shippingCost);

        double subTotal = Double.parseDouble(driver.findElement(subtotalLocator).getText().substring(1).replace(",", ""));
        double shippingCost = Double.parseDouble(driver.findElement(shippingCostLocator).getText().substring(1).replace(",", ""));
        double grandTotal = Double.parseDouble(driver.findElement(grandTotalLocator).getText().substring(1).replace(",", ""));
        System.out.println("subTotal: $" + subTotal);
        System.out.println("shippingCost: $" + shippingCost);
        System.out.println("grandTotal: $" + grandTotal);
        AssertJUnit.assertEquals(grandTotal, subTotal + shippingCost);
    }
}