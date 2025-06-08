package pageObject;

import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.Message;
import com.mailosaur.models.MessageSearchParams;
import com.mailosaur.models.SearchCriteria;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.cdimascio.dotenv.Dotenv;


import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasePage {
    public WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    public Dotenv dotenv;


    //Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
        this.dotenv = Dotenv.configure()
                .filename("credenials.env")
                .directory("src/testData")
                .load();
    }




//functions
    public void sendText(By locator, String text) {
        elementVisibility(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void send_video_pic(By locator, String path) throws InterruptedException {
        elementVisibility(locator);
        driver.findElement(locator).sendKeys(path);
    }


    public void click_on_btn(By locator) {
        elementVisibility(locator);
        WebElement element =driver.findElement(locator);
        element.click();
    }

    public void click_on_check_box(By locator) {
            elementVisibility(locator);
            WebElement element = driver.findElement(locator);
            if (!element.isSelected())
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void force_click(By locator) {
        WebElement element =driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }


    public void clear(By locator) {
        elementVisibility(locator);
        driver.findElement(locator).clear();
    }

    public boolean isDisplayed(By locator) {
        elementVisibility(locator);
        return driver.findElement(locator).isDisplayed();
    }

    public boolean is_present(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element is not present: " + locator, e);
        }
        return true;
    }

    public int findNumOfResults(By parent, By child) {
        this.elementVisibility(parent);
        List<WebElement> giftResults = driver.findElement(parent).findElements(child);
        return giftResults.size();
    }

    public void switch_to_a_new_tab(String originalWindow ){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public String getCssValue(By locator, String cssValue) {
        elementVisibility(locator);
        WebElement element = driver.findElement(locator);
        element.getCssValue(cssValue);//color

        String rgba = element.getCssValue("color");
        String actualHex = rgbaToHex(rgba);
        return actualHex;

    }

        public String get_text(By locator) {
           elementVisibility(locator);
           String text= driver.findElement(locator).getText();
            return text;

        }

    public boolean check_Select_category(By locator, List<String> expected) {
        try {
            int i = 0;
            elementVisibility(locator);
            Select select = new Select(driver.findElement(locator));
            List<WebElement> options = select.getOptions();
                for (WebElement option : options) {
                    //System.out.println("options: "+ option.getText());
                    //System.out.println("expected: "+ expected.get(i));
                    if (!option.getText().toLowerCase().equals(expected.get(i))){
                        return false;
                    } ;
                    i++;
                }
                return true;
        }
        catch (Exception e){
            throw e;
        }

    }

        public void select_insert_value(By locator, String value) {
            elementVisibility(locator);
            Select dropdown = new Select(driver.findElement(locator));
            System.out.println(dropdown.getOptions());
            dropdown.selectByValue(value);
            }

    public boolean is_select_clear(By locator) {
        elementVisibility(locator);
        Select dropdown = new Select(driver.findElement(locator));
        WebElement selectedOption = dropdown.getFirstSelectedOption();
        String selectedValue = selectedOption.getAttribute("value");
        System.out.println("Selected value: " + selectedValue);

        if (selectedValue.isEmpty()) {
            System.out.println("Select is cleared.");
            return true;
        } else {
            System.out.println("Select still has a value: " + selectedValue);
            return false;
        }
    }


    public void elementVisibility(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element is not visible: " + locator, e);
        }
    }


    public String rgbaToHex(String rgba) {
        rgba = rgba.replace("rgba(", "").replace(")", "");
        String[] parts = rgba.split(",");
        int r = Integer.parseInt(parts[0].trim());
        int g = Integer.parseInt(parts[1].trim());
        int b = Integer.parseInt(parts[2].trim());
        return String.format("#%02x%02x%02x", r, g, b);
    }

    public String email_phoneVerfication () throws MailosaurException, IOException, InterruptedException {
        String body;

        // Initialize Mailosaur client
        MailosaurClient mailosaur = new MailosaurClient(dotenv.get("API_KEY"));

        // Set search criteria
        MessageSearchParams params = new MessageSearchParams().withServer(dotenv.get("SERVER_ID"));
        SearchCriteria criteria = new SearchCriteria().withSentTo(dotenv.get("MAILOSAUR_EMAIL"));


        // Retrieve the email
        Thread.sleep(40000);
        Message message = mailosaur.messages().get(params, criteria);

        if (message.text() != null && message.text().body() != null && !message.text().body().isEmpty()) {
            body = message.text().body();
        } else if (message.html() != null && message.html().body() != null) {
            body = message.html().body();
            // Optional: strip HTML tags if needed
            body = body.replaceAll("<[^>]*>", " "); // removes HTML tags
        } else {
            throw new RuntimeException("Email has no text or HTML body!");
        }

        // Use regex to find a 6-digit number (adjust if your code is not 6 digits)
        Pattern pattern = Pattern.compile("\\b\\d{6}\\b");
        Matcher matcher = pattern.matcher(body);

        if (matcher.find()) {
            String verificationCode = matcher.group();
            return verificationCode;
        } else {
            throw new RuntimeException("No verification code found in email body!");
        }
    }

}
