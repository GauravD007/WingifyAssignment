package scripts;

import dataProvider.ConfigFileReader;
import dataProvider.ReadWriteExcelData;
import driverManager.DriverManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LogInPage;
import reports.ExtentReport;
import utils.Logging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class TestCase {
    ExtentReport extentReport;
    HomePage homePage;
    LogInPage logInPage;
    WebDriver driver;
    SoftAssert softAssert;
    ReadWriteExcelData excelData;

    @BeforeSuite
    public void beforeSuitSetup() throws IOException {
        extentReport = new ExtentReport();
        driver = DriverManager.getDriver();
        driver.get(ConfigFileReader.GetUrl());
        logInPage = new LogInPage(driver);
        homePage = new HomePage(driver);
        softAssert = new SoftAssert();
        excelData = new ReadWriteExcelData();
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
    }

    @AfterSuite
    public void afterSuit() {

        softAssert.assertAll();
        extentReport.flush();
        driver.quit();
    }

    @BeforeMethod()
    public void beforemethod() {
    }

    @AfterMethod()
    public void afterMethod() {
    }

    @Test(description = "Test Case 001 : Testing the LogIn page logo", enabled = true, priority = 1)
    public void TestCase001() throws InterruptedException, IOException {

        extentReport.createTest("Test Case 001 : Testing user after clicking on Login page logo it redirects user to the next page");
        extentReport.info("User is on the Login page");
        logInPage.logo.click();
        extentReport.info("User click on the Login page logo");
        Thread.sleep(2000);
        String actual_link = "https://sakshingp.github.io/assignment/index.html";
        String current_link = driver.getCurrentUrl();

        if (actual_link.equals(current_link)) {
            softAssert.assertEquals(actual_link, current_link, "Expected URL is not equal to expected URL");
            if (actual_link.equals(current_link)) {
                extentReport.info("After clicking on the logo of the web page user is redirected to the next page");
                Logging.info("After clicking on the logo of the web page user is redirected to the next page");
                extentReport.addScreenshot(driver);
                extentReport.pass("Test Case Passed");
            } else {
                extentReport.info("After clicking on the logo of the web page user is not redirected to the next page");
                Logging.info("After clicking on the logo of the web page user is not redirected to the next page");
                extentReport.addScreenshot(driver);
                extentReport.fail("Test Case Failed");
            }

            driver.get(ConfigFileReader.GetUrl());

        }
    }

    @Test(description = "Test Case 002 : Testing Login functionality without credentials", enabled = true, priority = 2)
    public void TestCase002() throws InterruptedException {
        try {

            extentReport.createTest("Test Case 002 : Testing Login functionality without entering credentials");
            extentReport.info("User is on the Login page");
            logInPage.loginbtn.click();
            extentReport.info("User click on Login button without entering credentials");
            Thread.sleep(1000);
            if (logInPage.blankCredentials.isDisplayed()) {
                extentReport.info("\"Both Username and Password must be present\" error message is displayed on the page");
                extentReport.addScreenshot(driver);
                extentReport.pass("Test Case Passed ");
            } else {
                extentReport.info("\"Both Username and Password must be present\" error message is not displayed on the page");
                extentReport.addScreenshot(driver);
                extentReport.fail("Test Case Failed ");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        driver.get(ConfigFileReader.GetUrl());

    }

    @Test(description = "Test Case 003 : Testing Login functionality without entering password", enabled = true, priority = 3)
    public void TestCase003() {
        try {
            XSSFSheet sheet = excelData.Sheet("Sheet1");
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                extentReport.createTest(" Test Case 003 : Testing Login functionality without entering password ");
                extentReport.info("User is on the Login page");
                logInPage.username.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
                extentReport.info("User entered username ");
                Thread.sleep(1000);
                logInPage.loginbtn.click();
                extentReport.info("User click on Login button without entering password");
                if (logInPage.withoutPass.isDisplayed()) {
                    extentReport.info("\"Password must be present\" error message is displayed on the page");
                    extentReport.addScreenshot(driver);
                    extentReport.pass("Test Case Passed");
                } else {
                    extentReport.info("\"Password must be present\" error message is not displayed on the page");
                    extentReport.addScreenshot(driver);
                    extentReport.fail("Test Failed");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        driver.get(ConfigFileReader.GetUrl());

    }

    @Test(description = "Test Case 004 : Testing Login functionality without entering username", enabled = true, priority = 4)
    public void TestCase004() {
        try {
            XSSFSheet sheet = excelData.Sheet("Sheet2");
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                extentReport.createTest(" Test Case 004 : Testing Login functionality without entering username");
                extentReport.info("User is on the Login page");
                logInPage.password.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
                extentReport.info("User entered password ");
                Thread.sleep(1000);
                logInPage.loginbtn.click();
                extentReport.info("User click on Login button");
                if (logInPage.withoutUsername.isDisplayed()) {
                    extentReport.info("\"Username must be present\" error message displayed on the page");
                    extentReport.addScreenshot(driver);
                    extentReport.pass(" Test Case Passed");
                } else {
                    extentReport.info("\"Username must be present\" error message is not displayed on the page");
                    extentReport.addScreenshot(driver);
                    extentReport.fail("Test Case Failed");
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        driver.get(ConfigFileReader.GetUrl());

    }

    @Test(description = "Test Case 005 : Testing Login functionality with integer values as credentials", enabled = true, priority = 5)
    public void TestCase005() {
        try {
            extentReport.createTest(" Test Case 005 : Testing Login functionality with numeric values as credential");
            extentReport.info("User is on the Login page");
            XSSFSheet sheet = excelData.Sheet("Sheet3");
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                logInPage.username.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
                extentReport.info("User enter numeric values as username");
                Thread.sleep(1000);
                logInPage.password.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
                extentReport.info("User enter numeric values as password ");
                Thread.sleep(1000);
                logInPage.loginbtn.click();
                extentReport.info("User click on Login button");
                if (homePage.userName.isDisplayed()) {
                    extentReport.info("User is successfully logged in into the application");
                    extentReport.addScreenshot(driver);
                    extentReport.pass("Test Case Passed");
                } else {
                    extentReport.info("User is unable to logged in into the application");
                    extentReport.addScreenshot(driver);
                    extentReport.fail("Test Case Failed");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        driver.get(ConfigFileReader.GetUrl());
    }

    @Test(description = " Test Case 006 : Testing Login functionality with special characters credentials", enabled = true, priority = 6)
    public void TestCase006() {

        try {
            XSSFSheet sheet = excelData.Sheet("Sheet4");
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                extentReport.createTest(" Test Case 006 : Testing Login functionality with special characters credentials");
                extentReport.info("User is on the Login page");
                logInPage.username.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
                extentReport.info("User entere special characters as username");
                Thread.sleep(1000);
                logInPage.password.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
                extentReport.info("User enter special characters as password");
                Thread.sleep(1000);
                logInPage.loginbtn.click();
                extentReport.info("User click on Login button");
                if (homePage.userName.isDisplayed()) {
                    extentReport.info("User is successfully logged in into the application");
                    extentReport.addScreenshot(driver);
                    extentReport.pass("Test Case Passed");
                } else {
                    extentReport.info("User is unable logged in into the application");
                    extentReport.addScreenshot(driver);
                    extentReport.fail("Test Case Failed");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        driver.get(ConfigFileReader.GetUrl());

    }

    @Test(description = " Test Case 007 : Testing Remember me checkbox is clickable or not", enabled = true, priority = 7)
    public void TestCase007() {
        try {
            extentReport.createTest(" Test Case 007 : Testing Remember me checkbox is clickable or not");
            extentReport.info("User is on the Login page");
            logInPage.checkbox.click();
            extentReport.info("User click on the Remember me checkbox");
            Thread.sleep(1000);
            if (logInPage.checkbox.isSelected()) {
                extentReport.info("Remember me checkbox is clickable");
                extentReport.addScreenshot(driver);
                extentReport.pass("Test Case Passed");
            } else {
                extentReport.info("Remember me checkbox is not clickable");
                extentReport.addScreenshot(driver);
                extentReport.fail("Test Case Failed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        driver.get(ConfigFileReader.GetUrl());

    }

    @Test(description = " Test Case 008 : Testing Twitter logo is clickable or not", enabled = true, priority = 8)
    public void TestCase008() {
        try {
            extentReport.createTest(" Test Case 008 : Testing twitter logo is clickable or not");
            extentReport.info("User is on the Login page");
            logInPage.twitterLogo.click();
            extentReport.info("User click on the Twitter logo");
            if (driver.findElements(By.xpath("//span[text()='Sign in to Twitter']")).size() > 0) {
                extentReport.info("User redirected to the twitter login page");
                extentReport.addScreenshot(driver);
                extentReport.pass("Test Passed");
            } else {
                extentReport.info("User is not redirected to the twitter login page");
                extentReport.addScreenshot(driver);
                extentReport.fail("Test Failed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        driver.get(ConfigFileReader.GetUrl());

    }

    @Test(description = " Test Case 009 : Testing Facebook logo is clickable or not", enabled = true, priority = 9)
    public void TestCase009() {
        try {
            extentReport.createTest(" Test Case 009 : Testing twitter logo is clickable or not");
            extentReport.info("User is on the Login page");
            logInPage.facebookLogo.click();
            extentReport.info("User click on the facebook logo");
            if (driver.findElements(By.xpath("//div[text()='Log in to Facebook']")).size() > 0) {
                extentReport.info("User redirected to the facebook login page");
                extentReport.addScreenshot(driver);
                extentReport.pass("Test Passed");
            } else {
                extentReport.info("User is not redirected to the facebook login page");
                extentReport.addScreenshot(driver);
                extentReport.fail("Test Failed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        driver.get(ConfigFileReader.GetUrl());

    }

    @Test(description = " Test Case 010 : Testing Linkedin logo ", enabled = true, priority = 10)
    public void TestCase010() {
        try {
            extentReport.createTest("Testing twitter logo is clickable or not");
            extentReport.info("User is on the Login page");
            logInPage.linkedinLogo.click();
            extentReport.info("User click on the linkedin logo");
            if (driver.findElements(By.xpath("//h1[text()='Sign in']")).size() > 0) {
                extentReport.info("User redirected to the linkedin login page");
                extentReport.addScreenshot(driver);
                extentReport.pass("Test Passed");
            } else {
                extentReport.info("User is not redirected to the linkedin login page");
                extentReport.addScreenshot(driver);
                extentReport.fail("Test Failed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test(description = " Test Case 011 : Testing the AMOUNT header in the transaction table values get sorted or not after click on it", enabled = true, priority = 11)
    public void TestCase011() {

        try {
            XSSFSheet sheet = excelData.Sheet("Sheet5");
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                extentReport.createTest(" Test Case 011 : Testing the AMOUNT header in the transaction table ");
                extentReport.info("User is on the Login page");
                logInPage.username.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
                extentReport.info("User enter username");
                Thread.sleep(1000);
                logInPage.password.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
                extentReport.info("User enter password");
                logInPage.loginbtn.click();
                extentReport.info("User click on login button");
            }
            Thread.sleep(5000);
            List<WebElement> beforefilterprice = driver.findElements(By.xpath("//table[@id='transactionsTable']/tbody/tr/td[5]"));
            Thread.sleep(2000);

            List<String> beforfilterpricelist = new ArrayList<>();
            for( WebElement p : beforefilterprice)
            {
                beforfilterpricelist.add(p.getText());
            }
            extentReport.info("Getting the values from table before click on the Amount header");
         //   Collections.sort(beforfilterpricelist);
            Thread.sleep(2000);

            driver.findElement(By.xpath("//th[@id='amount']")).click();
            Thread.sleep(3000);
            List<WebElement> afterfilter =driver.findElements(By.xpath("//table[@id='transactionsTable']/tbody/tr/td[5]"));
            Thread.sleep(2000);

            List<String> afterfilterprice = new ArrayList<>();
            for( WebElement p : afterfilter)
            {
                afterfilterprice.add(p.getText());
            }

            extentReport.info("Getting the values from table after click on the Amount header");
            extentReport.info("Camparing the values are sorted or not");

            if (beforfilterpricelist.equals(afterfilterprice))
            {
                extentReport.info("After click on the AMOUNT header in the transaction table the values are not get sorted");
                extentReport.addScreenshot(driver);
                extentReport.fail("Test Case Failed");
            }
            else
            {
                extentReport.info("After click on the AMOUNT header in the transaction table the values are get sorted");
                extentReport.addScreenshot(driver);
                extentReport.pass("Test Case Passed");

            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}