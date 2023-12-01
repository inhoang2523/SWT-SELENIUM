/*  Verify Discount Coupon works correctly

Test Case Description:

1. Go to http://live.techpanda.org/

2. Go to Mobile and add IPHONE to cart

3. Enter Coupon Code

4. Verify the discount generated

TestData:  Coupon Code: GURU50

Expected result:

1) Price is discounted by 5%

*/
package BAITAP;



import POM.AddProductToCart;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/*  Verify Discount Coupon works correctly

Test Case Description:

1. Go to http://live.techpanda.org/

2. Go to Mobile and add IPHONE to cart

3. Enter Coupon Code

4. Verify the discount generated

TestData:  Coupon Code: GURU50

Expected result:

1) Price is discounted by 5%

*/
public class TEST09 {
    @Test
    public void testCase09(){
        String couponCode = "GURU50";
        WebDriver driver = driverFactory.getChromeDriver();
        AddProductToCart addProductToCart = new AddProductToCart(driver);
        try {
            //1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");
            //2. Go to Mobile and add IPHONE to cart
            driver.findElement(By.linkText("MOBILE")).click();
            addProductToCart.addMobile();
            String beforGrandTotal = driver.findElement(By.cssSelector("strong span[class='price']")).getText();
            //3. Enter Coupon Code
            addProductToCart.inputCoupon(couponCode);
            addProductToCart.clickApply();
            Thread.sleep(3000);
            String afterGrandTotal = driver.findElement(By.cssSelector("strong span[class='price']")).getText();
            //4. Verify the discount generated
            System.out.println("beforGrandTotal" + beforGrandTotal);
            System.out.println("afterGrandTotal"+afterGrandTotal);
            addProductToCart.verifyTheDiscount(afterGrandTotal, beforGrandTotal);
            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File("C:\\KI 5\\SWT301\\selenium-webdriver-java\\screenshot9.png"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        } finally {
            driver.quit();
        }
    }
}