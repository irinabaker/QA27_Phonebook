package com.phonebook.tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CreateAccountTests extends TestBase{

    //precondition: user should be log out
    @BeforeMethod
    public void ensurePrecondition() {
        // if login link not present
        if (!isElementPresent(By.cssSelector("[href='/login']"))){
            //click on Sign out button
            driver.findElement(By.xpath("//button[.='Sign Out']")).click();
        }
    }

    @Test
    public void registerExistedUserNegativeTest() {
        //click on the Login link
        driver.findElement(By.cssSelector("[href='/login']")).click();
        //enter email
        //email -> By.name
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("manuel@gm.com");
        //enter password
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("Manuel1234$");
        //click on the Registration button
        driver.findElement(By.name("registration")).click();
        //assert: Alert is appeared
        Assert.assertTrue(isAlertAppears());
    }

    public boolean isAlertAppears() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.alertIsPresent());
        if (alert==null) {
            return false;
        }else {
            return true;
        }
    }

}
