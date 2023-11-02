/*
Test Steps:

1. Go to http://live.techpanda.org/

2. Click on my account link

3. Login in application using previously created credential

4. Click on MY WISHLIST link

5. In next page, Click ADD TO CART link

6. Enter general shipping country, state/province and zip for the shipping cost estimate

7. Click Estimate

8. Verify Shipping cost generated

9. Select Shipping Cost, Update Total

10. Verify shipping cost is added to total

11. Click "Proceed to Checkout"

12a. Enter Billing Information, and click Continue

12b. Enter Shipping Information, and click Continue

13. In Shipping Method, Click Continue

14. In Payment Information select 'Check/Money Order' radio button. Click Continue

15. Click 'PLACE ORDER' button

16. Verify Oder is generated. Note the order number
 */
package BAITAP;

import POM.CartPage;
import POM.CheckOutPage;
import POM.LoginPage;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;

public class TEST06 {
    @Test
    public static void testcase6(){
        String email = "yle405960@gmail.com";
        String password = "15081205lvhy";
        String country = "United States";
        String region = "Armed Forces Africa";
        String zip = "2000";
        String address = "tayninh";
        String city = "Tay Ninh";
        String telephone = "9898989";

        WebDriver driver = driverFactory.getChromeDriver();
        LoginPage loginPage = new LoginPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        try {
            //1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");
            //2. Click on my account link
            loginPage.clickMyAccountLink();
            //3. Login in application using previously created credential
            loginPage.enterEmail(email);
            loginPage.enterPassword(password);
            Thread.sleep(2000);
            loginPage.clickLoginButton();
            Thread.sleep(2000);
            //4. Click on MY WISHLIST link
            driver.findElement(By.linkText("TV")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//a[@class='link-wishlist'][normalize-space()='Add to Wishlist'])[1]")).click();
            Thread.sleep(2000);
            //5. In next page, Click ADD TO CART link
            cartPage.clickCartButton();
            //6. Enter general shipping country, state/province and zip for the shipping cost estimate
            cartPage.enterCountry(country);
            if(country.equals("United States")){
                cartPage.enterState(region);
            } else {
                cartPage.enterProvince(region);
            }
            cartPage.enterZip(zip);
            Thread.sleep(2000);
            //7. Click Estimate
            cartPage.clickEstimateButton();
            Thread.sleep(2000);
            //8. Verify Shipping cost generated
            String shippingVerifyStr = cartPage.verifyGenerated();
            System.out.println("Shipping cost: "+shippingVerifyStr);
            //9. Select Shipping Cost, Update Total
            cartPage.clickFlatRateButton();
            cartPage.clickUpdateTotalButton();
            //10. Verify shipping cost is added to total
            cartPage.verifyTotal();
            //11. Click "Proceed to Checkout"
            checkOutPage.clickCheckOutButton();
            //12a. Enter Billing Information, and click Continue

            checkOutPage.enterAddress(address);
            checkOutPage.enterCity(city);
            checkOutPage.enterCountry(country);
            if(country.equals("United States")){
                checkOutPage.enterState(region);
            } else {
                checkOutPage.enterProvince(region);
            }
            checkOutPage.enterZip(zip);
            checkOutPage.enterTelephone(telephone);
            Thread.sleep(2000);
            checkOutPage.clickContinueBillInfoButton();
            Thread.sleep(2000);
            //12b. Enter Shipping Information, and click Continue
            checkOutPage.clickEditInfo();
            checkOutPage.clickContinueShipInfoButton();
            Thread.sleep(2000);
            //13. In Shipping Method, Click Continue
            checkOutPage.clickContinueShipButton();
            Thread.sleep(2000);
            //14. In Payment Information select 'Check/Money Order' radio button. Click Continue
            checkOutPage.clickMoneyOrderButton();
            checkOutPage.clickContinuePaymentButton();
            Thread.sleep(2000);
            //15. Click 'PLACE ORDER' button
            checkOutPage.clickPlaceOrderButton();
            Thread.sleep(2000);
            //16. Verify Oder is generated. Note the order number
            String orderVerifyStr = driver.findElement(By.cssSelector("h1")).getText();
            System.out.println(orderVerifyStr);
            AssertJUnit.assertEquals("YOUR ORDER HAS BEEN RECEIVED.",orderVerifyStr);
            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File("C:\\KI 5\\SWT301\\selenium-webdriver-java\\screenshot4.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}