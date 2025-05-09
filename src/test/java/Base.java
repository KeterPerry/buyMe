
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.w3c.dom.NodeList;
import pageObject.BasePage;
import pageObject.HomePage;
import pageObject.LoginPage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import io.qameta.allure.*;

public class Base {

    //attributes
    public static WebDriver driver;
    //protected static JavascriptExecutor js;
    protected LoginPage loginPage;
    protected static HomePage homePage;
    protected BasePage basePage;
    protected static String websiteName;


    @BeforeClass
    public static void setUp() {

        String browserName = readFromFile("browserType", "configPath");
        String headless = System.getProperty("headless", "true");
        ChromeOptions options = new ChromeOptions();
        if (headless.equalsIgnoreCase("true")) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }
        else{
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-popup-blocking");}

        if ("Chrome".equalsIgnoreCase(browserName)) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\keter\\Desktop\\Automation Dev\\automation drivers\\Drivers\\chromedriver-win64 (3)\\chromedriver-win64\\chromedriver.exe");
            driver = new ChromeDriver(options);
        } else {
            throw new RuntimeException("Unsupported browser: " + browserName);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //js = (JavascriptExecutor) driver; // Initialize JavaScript Executor
        websiteName= readFromFile("websiteName", "configPath");
        driver.get(websiteName);
        homePage=new HomePage(driver);
    }

    @BeforeMethod
    public void openWebsite(){
        loginPage= new LoginPage(driver);
        basePage=new BasePage(driver);
    }

    @AfterClass
    public static void close() {
        driver.quit();
    }

    static String readFromFile(String keyData, String pathName) {
        try {
            // Load the XML file
            File xmlFile = new File(pathName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.parse(xmlFile); // Directly use org.w3c.dom.Document

            // Normalize the document
            doc.getDocumentElement().normalize();

            // Extract values from XML
            NodeList nodeList = doc.getElementsByTagName(keyData);
            if (nodeList.getLength() > 0) {
                String value = nodeList.item(0).getTextContent();
                System.out.println("Keydata: " + value);
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

    public static String takeScreenshot(String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "test-output/screenshots/" + testName + ".png";
        try {
            Files.createDirectories(Path.of("test-output/screenshots/")); // Ensure directory exists
            Files.copy(srcFile.toPath(), Path.of(screenshotPath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return screenshotPath;
    }

//    @Step("Verify text at element {element}")
//    public void verifyText(By element, String expectedResult) throws IOException, InterruptedException {
//        System.out.println("expected result:" + expectedResult);
//        String actualResult = basePage.get_Text(element);
//        System.out.println("actual result:" + actualResult);
//
//        try {
//            Assert.assertEquals("headers should be the same", actualResult, expectedResult);
//
//        } catch (AssertionError e) {
//
//           String screenshotPath = takeScreenshot("StudentScreen");
//            throw e;
//        }
//    }

    @Step("verifying opening of page")
    public void verifyOpenPage(String urlText, String test_name, boolean is_title,Optional<String> titleText){
        try {
            if (is_title) {
                Assert.assertTrue(driver.getTitle().toLowerCase().contains(titleText.get()));
            }
            Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains(urlText));
            Assert.assertTrue(driver.getPageSource().contains("error"));
            Assert.assertTrue(driver.getPageSource().contains("שגיאה"));
            String screenshotPath = takeScreenshot(test_name);
        } catch (AssertionError e) {
            throw e;
        }
    }
}
