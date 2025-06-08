
import com.mailosaur.models.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.w3c.dom.NodeList;
import pageObject.*;
import io.github.cdimascio.dotenv.Dotenv;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.qameta.allure.Attachment;

import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.MessageSearchParams;

import static testsData.Constants.SENDING_GIFTS_TO_EMPLOYEES_PAGE;


@Listeners({io.qameta.allure.testng.AllureTestNg.class})

public class Base {
    //attributes
    public WebDriver driver;
    protected Dotenv dotenv;
    protected HomePage homePage;
    protected RegistrationPage registrationPage;
    protected LoginPage loginPage;
    protected GiftPage giftPage;
    protected BasePage basePage;
    protected String websiteName;


    @BeforeSuite
    public void setUp() {

        dotenv = Dotenv.configure().filename("credenials.env").directory("src/testData").load();
        String browserName = readFromFile("browserType");
        String headless = System.getProperty("headless", "false");
        ChromeOptions options = new ChromeOptions();
        if (headless.equalsIgnoreCase("true")) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

        }
        else{
            options.addArguments("--start-maximized");
            options.addArguments("--incognito");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
            options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
            options.setExperimentalOption("useAutomationExtension", false);

        }

        if ("Chrome".equalsIgnoreCase(browserName)) {
            WebDriverManager.chromedriver().setup();
            //System.setProperty(dotenv.get("CHROME_DRIVER_KEY"),dotenv.get("CHROME_DRIVER_PATH"));
            driver = new ChromeDriver(options);
        } else {
            throw new RuntimeException("Unsupported browser: " + browserName);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ((JavascriptExecutor) driver).executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined});");
        websiteName= readFromFile("websiteName");
        basePage=new BasePage(driver);
        registrationPage = new RegistrationPage(driver);
        giftPage=new GiftPage(driver);
        homePage=new HomePage(driver);
        loginPage=new LoginPage(driver);

    }



    @BeforeMethod
    public void verifyHomePageLoads() throws IOException, InterruptedException {
        driver.get(websiteName);
        Thread.sleep(30000);//captcha
        verifyOpenPage("buyme", "verifyingHomePage", true, Optional.of("buyMe"));
        verifyingHomePageHasNoErrors();
    }

    @AfterMethod
    public void ifFails(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            attachScreenshot(driver,result.getName());
        }
    }

    @AfterMethod
    public void closeBrowser(){
       driver.quit();
    }


    //Genaral functions

    public String readFromFile(String keyData) {
        try {
            // Load the XML file
            File xmlFile = new File(dotenv.get("CONFIG_PATH"));
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.parse(xmlFile); // Directly use org.w3c.dom.Document

            // Normalize the document
            doc.getDocumentElement().normalize();

            // Extract values from XML
            NodeList nodeList = doc.getElementsByTagName(keyData);
            if (nodeList.getLength() > 0) {
                String value = nodeList.item(0).getTextContent();
                System.out.println(keyData + ":"+ value);
                return value;
            } else {
                System.out.println("Element not found for key: " + keyData);
                return null;
            }

        } catch (Exception e) {
            System.out.println("Error reading the file: " + e.getMessage());

        }
        return null;
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public void attachScreenshot(WebDriver driver, String stepName) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(stepName, new ByteArrayInputStream(screenshot));
        }catch (Exception e){
            System.out.println("attachScreenshot"+ e.getMessage());
            throw e;
        }
    }

public void verifyError(By error_element, String expectedErrorMesseage, String expectedColor, String stepName) throws IOException, InterruptedException {
    //System.out.println("expected message:" + expectedErrorMesseage);
    //System.out.println("expected color:" + expectedColor);
    //System.out.println("actual result message:" + actualErrorMessage);
    //System.out.println("actual result color:" + actualColor);
    try {
        String actualErrorMessage = basePage.get_text(error_element);
        String actualColor = basePage.getCssValue(error_element,"color");
        attachScreenshot(driver, stepName);
        Assert.assertEquals(expectedErrorMesseage, actualErrorMessage);
        Assert.assertEquals(expectedColor,actualColor);

    } catch (AssertionError e) {
        System.out.println("verifyError:"+ e.getMessage());
        throw e;
    }

}


    public void verifyingHomePageHasNoErrors() {
        try {
            List<WebElement> uiErrors = driver.findElements(By.xpath("//*[contains(@class, 'error') or contains(@class, 'alert-danger')]"));
            Assert.assertTrue(uiErrors.isEmpty(), "UI error messages found.");
        } catch (AssertionError e) {
            attachScreenshot(driver, "verifyingHomePageHasNoErrors");
        } catch (Exception e) {
            Allure.addAttachment("Unexpected Exception", e.toString());
            System.out.println("Unexpected error while verifying homepage: " + e.getMessage());
            throw e;
        }
    }

 public void getAndAssertWindowHandle(String originalWindow){
     try {
         for (String windowHandle : driver.getWindowHandles()) {
             if (!windowHandle.equals(originalWindow)) {
                 driver.switchTo().window(windowHandle);
                 break;
             }
         }
         String newTab = driver.getCurrentUrl();
         String newTitle = driver.getTitle();
         Assert.assertEquals(SENDING_GIFTS_TO_EMPLOYEES_PAGE, newTab);
         Assert.assertTrue(newTitle.contains("שליחת מתנות לעובדים"));
     }
     catch (Exception e){
         System.out.println("getAndAssertWindowHandle"+ e.getMessage());
     }
     catch (AssertionError e){
         System.out.println("getAndAssertWindowHandle assertion error:"+ e.getMessage());
     }
 }


public void verifyOpenPage(String urlText, String test_name, boolean is_title,Optional<String> titleText){
    try {
        if (is_title) {
            Assert.assertTrue(driver.getTitle().toLowerCase().contains(titleText.get().toLowerCase()));
        }
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains(urlText));
    } catch (AssertionError e) {
        System.out.println("verifyopenPage:"+e.getMessage());
    }
}
}


