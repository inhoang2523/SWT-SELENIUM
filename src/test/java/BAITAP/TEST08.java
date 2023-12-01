/*--------------TESTCASE08-------------------------/

        *  Verify you are able to change or reorder previously added product

        *  Test Data = QTY = 10

        Test Steps:

        1. Go to http://live.techpanda.org/

        2. Click on my account link

        3. Login in application using previously created credential

        4. Click on 'REORDER' link , change QTY & click Update

        5. Verify Grand Total is changed

        6. Complete Billing & Shipping Information

        7. Verify order is generated and note the order number

        Expected outcomes:

        1) Grand Total is Changed

        2) Order number is generated
*/
package BAITAP;

import POM.CartPage;
import POM.CheckOutPage;
import POM.LoginPage;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class TEST08 {
    @Test
    public void Testcase08() {
        String email = "Luu123456@gmail.com";
        String password = "123456789";
        String billingAddress = "123 Main Street";
        String billingCity = "US";
        String billingRegion = "43";
        String billingZip = "10001";
        String billingTelephone = "1234567890";
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            //1: Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //2: Click on my account link
            LoginPage loginPage = new LoginPage(driver);
            loginPage.clickMyAccountLink();

            //3: Login using previously created credential
            loginPage.enterEmail(email);
            loginPage.enterPassword(password);
            loginPage.clickLoginButton();

            //4: Click on 'REORDER' link, change QTY & click Update
            driver.findElement(By.xpath("//a[normalize-space()='Reorder']")).click();

            String GrandTotal = driver.findElement(By.cssSelector("strong span[class='price']")).getText();

            WebElement quantityInput = driver.findElement(By.xpath("//input[@title='Qty']"));
            quantityInput.clear();
            quantityInput.sendKeys("10");
            driver.findElement(By.xpath("//button[@title='Update']//span//span[contains(text(),'Update')]")).click();


            //5: Verify Grand Total is changed
            String updatedGrandTotal = driver.findElement(By.cssSelector("strong span[class='price']")).getText();
            Assert.assertNotEquals(updatedGrandTotal, GrandTotal);

            //6: Complete Billing & Shipping Information
            CartPage cartPage = new CartPage(driver);
            cartPage.selectCountry(billingCity);
            cartPage.selectRegion(billingRegion);
            cartPage.enterZip(billingZip);
            cartPage.clickEstimate();
            cartPage.selectShippingCost();

            cartPage.clickUpdateTotal();
            Thread.sleep(1000);

            cartPage.clickProceedToCheckout();
            Thread.sleep(1000);

            CheckOutPage checkoutPage = new CheckOutPage(driver);
            checkoutPage.BillingNewAddress();
            checkoutPage.enterBilling(billingAddress, billingCity, billingRegion, billingZip, billingTelephone);
            checkoutPage.clickDifferentAddess();
            checkoutPage.clickBillingContinue();
            Thread.sleep(1000);

            checkoutPage.ShippingNewAddress();
            checkoutPage.enterShipping(billingAddress + "123", billingCity, billingRegion, billingZip + "123", billingTelephone + "999");
            checkoutPage.clickShippingContinue();
            Thread.sleep(3000);

            checkoutPage.clickShippingMethodContinue();
            Thread.sleep(3000);

            checkoutPage.selectCheckMoneyOrderPaymentMethod();
            checkoutPage.clickPaymentContinue();
            Thread.sleep(3000);

            checkoutPage.clickPlaceOrder();
            Thread.sleep(3000);

            WebElement orderID = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/p[1]/a[1]"));
            System.out.println("Số đơn hàng đã được tạo: " + orderID.getText());

            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File("C:\\KI 5\\SWT301\\selenium-webdriver-java\\screenshot12.png"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}