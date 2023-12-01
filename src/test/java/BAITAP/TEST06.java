/*/* Verify user is able to purchase product using registered email id (USE CHROME BROWSER)

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
import POM.WishlistPage;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class TEST06 {
    @Test
    public void Testcase06() {
        String email = "yenle2212@gmail.com";
        String password = "123456";
        String billingAddress = "123 Main Street";
        String billingCity = "US";
        String billingRegion = "43";
        String billingZip = "10001";
        String billingTelephone = "1234567890";

        WebDriver driver = driverFactory.getChromeDriver();
        try {

            driver.get("http://live.techpanda.org/");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.clickMyAccountLink();
            loginPage.enterEmail(email);
            loginPage.enterPassword(password);
            loginPage.clickLoginButton();

//            for (String handle : driver.getWindowHandles()) {
//                driver.switchTo().window(handle);
//            }
            WebElement myWishlistLink = driver.findElement(By.linkText("MY WISHLIST"));
            myWishlistLink.click();

            WishlistPage wishlistPage = new WishlistPage(driver);
            wishlistPage.clickAddToCart();

            CartPage cartPage = new CartPage(driver);
            cartPage.selectCountry(billingCity);
            cartPage.selectRegion(billingRegion);
            cartPage.enterZip(billingZip);
            cartPage.clickEstimate();
            cartPage.selectShippingCost();
            //8. Verify Shipping cost generated
            String shipType = driver.findElement(By.xpath("//dt[normalize-space()='Flat Rate']")).getText();
            Assert.assertEquals(shipType, "Flat Rate");

            //String shipCost = driver.findElement(By.cssSelector("label[for='s_method_flatrate_flatrate']")).getText();

            cartPage.clickUpdateTotal();
            Thread.sleep(1000);

            //10. Verify shipping cost is added to total
            String shipTotal = driver.findElement(By.xpath("//td[normalize-space()='Shipping & Handling (Flat Rate - Fixed)']")).getText();
            Assert.assertTrue(!shipTotal.isEmpty());

            cartPage.clickProceedToCheckout();
            Thread.sleep(1000);

            CheckOutPage checkoutPage = new CheckOutPage(driver);
            checkoutPage.BillingNewAddress();
            checkoutPage.enterBilling(billingAddress, billingCity, billingRegion, billingZip, billingTelephone);
            checkoutPage.clickDifferentAddess();
            checkoutPage.clickBillingContinue();
            Thread.sleep(1000);

            checkoutPage.ShippingNewAddress();
            checkoutPage.enterShipping(billingAddress+"123", billingCity,billingRegion, billingZip+"123", billingTelephone+"999");
            checkoutPage.clickShippingContinue();
            Thread.sleep(1000);

            String shipMethod = driver.findElement(By.xpath("//dt[normalize-space()='Flat Rate']")).getText();
            Assert.assertEquals(shipMethod, "Flat Rate");

            checkoutPage.clickShippingMethodContinue();
            Thread.sleep(1000);

            checkoutPage.selectCheckMoneyOrderPaymentMethod();
            checkoutPage.clickPaymentContinue();
            Thread.sleep(1000);

            checkoutPage.clickPlaceOrder();
            Thread.sleep(3000);

            WebElement orderID = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/p[1]/a[1]"));
            System.out.println("Số đơn hàng đã được tạo: " + orderID.getText());

            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File("C:\\KI 5\\SWT301\\selenium-webdriver-java\\screenshot11.png"));
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}