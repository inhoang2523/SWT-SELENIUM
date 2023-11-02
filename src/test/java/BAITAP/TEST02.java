/*

Test Steps:

1. Goto http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)

4. Click on Sony Xperia mobile

5. Read the Sony Xperia mobile from detail page.

6. Compare Product value in list and details page should be equal ($100).

*/
package BAITAP;
import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
@Test
public class TEST02 {


    private static final String url = "http://live.techpanda.org/";
    private static final String destFile = "C:\\KI 5\\SWT301\\selenium-webdriver-java\\";
    public static void testCase02() throws InterruptedException, IOException {
        int src = 0;
        StringBuilder verificationError = new StringBuilder();
        //1.It gets a Chrome WebDriver instance
        WebDriver driver = driverFactory.getChromeDriver();
        driver.get(url);
        System.out.println(driver.getTitle());
        //2.finds and clicks a link with the text "MOBILE" on the webpage.
        driver.findElement(By.linkText("MOBILE")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        //3.It finds the price element of a specific product ("SonyXperiaPrice") using CSS selectors.
        WebElement SonyXperiaPrice = driver.findElement(By.cssSelector("span[id='product-price-1'] span[class='price']"));
        //WebElement SonyXperiaPrice = driver.findElement(By.id("product-price-1"));
        System.out.println(SonyXperiaPrice.getText());

        WebElement SonyXperiaImg = driver.findElement(By.id("product-collection-image-1"));
        SonyXperiaImg.click();

        //4.finds and prints the text of an element with class "price"
        WebElement price = driver.findElement(By.cssSelector(".price"));
        System.out.println(price.getText());
        //5.takes a screenshot of the current page
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        String autoAllocate = "screenshot2.png";
        FileHandler.copy(srcFile, new File(destFile + autoAllocate));
    }

}


