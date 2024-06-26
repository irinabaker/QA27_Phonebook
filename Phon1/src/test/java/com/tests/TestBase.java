package com.tests;

import com.phonebook.fw.ApplicationManager;
import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));

    Logger logger = LoggerFactory.getLogger(TestBase.class);
    @BeforeMethod
    public void startTest(Method method,Object[] p) {

        logger.info("Start test " + method.getName() + " with data: " + Arrays.asList(p));
    }

    @AfterMethod
    public void stopTest(ITestResult result) {
       if (result.isSuccess()) {
           logger.info("PASSED: " + result.getMethod().getMethodName());
       } else {
           logger.error("FAILED: " + result.getMethod().getMethodName() + " Screenshot: " + app.getHomepage().takeScreenshot());
       }
        logger.info("**************************************************");
    }
    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        app.init();
   //     System.out.println("Before suite");
    }

//    @BeforeTest
//    public void beforeTest() {
//        System.out.println("Before test");
//    }
//
//    @AfterTest
//    public void afterTest() {
//        System.out.println("After test");
//    }

    //@AfterMethod
    @AfterSuite(enabled = true)
    public void tearDown() {
        app.stop();
     //   System.out.println("After suite");
    }

}
