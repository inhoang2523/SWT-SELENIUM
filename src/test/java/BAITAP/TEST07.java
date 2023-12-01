package BAITAP;
import POM.LoginPage;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.File;

    /*--------------TESTCASE07-------------------------/*

Verify that you will be able to save previously placed order as a pdf file

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on My Account link

3. Login in application using previously created credential

4. Click on 'My Orders'

5. Click on 'View Order'

6. Click on 'Print Order' link
*/
public class TEST07 {
        @Test
        public void Testcase07() {
            String email = "hoangle@gmail.com";
            String password = "123456";

            WebDriver driver = driverFactory.getChromeDriver();
            try {
                driver.get("http://live.techpanda.org/");

                LoginPage loginPage = new LoginPage(driver);
                loginPage.clickMyAccountLink();
                loginPage.enterEmail(email);
                loginPage.enterPassword(password);
                loginPage.clickLoginButton();

                driver.findElement(By.linkText("MY ORDERS")).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("//a[normalize-space()='View Order']")).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("//a[@class='link-print']")).click();
                Thread.sleep(5000);

                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshotFile, new File("C:\\KI 5\\SWT301\\SWT-SELENIUM\\screenshot7.png"));

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                driver.quit();
            }
        }
    }

