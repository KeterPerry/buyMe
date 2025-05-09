package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class BasePage {
    static WebDriver driver;
    WebDriverWait wait;

    //Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait= new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    public void sendText(By elementLocation, String text) {
        elementVisibility(elementLocation);
        driver.findElement(elementLocation).clear();
        driver.findElement(elementLocation).sendKeys(text);
    }


    public void click(By elementLocation) {
        elementVisibility(elementLocation);
        driver.findElement(elementLocation).click();
    }

    public void clear(By elementLocation) {
        elementVisibility(elementLocation);
        driver.findElement(elementLocation).clear();
    }

//    public boolean isDisplayed(By elementLocation) {
//        elementVisibility(elementLocation);
//        return driver.findElement(elementLocation).isDisplayed();
//    }

        public String get_Text(By element) {
           elementVisibility(element);
           String text= driver.findElement(element).getText();
            return text;

        }

    public boolean check_Select_category(By element) {
        elementVisibility(element);
        driver.findElement(element).click();
        if (driver.findElement(element).isDisplayed()) {
            Select select = new Select(driver.findElement(element));
            List<WebElement> options = select.getOptions();
            if (!options.isEmpty()) {
             return true;
            }
        }
    }

        public void select_insert_value(By element, String value) {
            Select select = new Select(driver.findElement(element));
            select.selectByValue(value);

            }



    public void elementVisibility(By element) {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }





}
