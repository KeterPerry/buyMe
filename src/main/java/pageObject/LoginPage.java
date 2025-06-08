package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    public WebDriver driver;

    //elements
    public By google_btn_login= By.xpath("//div[@class='option easy']//div[@class=\"social-btn google\"]");
    public By mail_field=By.xpath("//input[@type=\"email\"]");
    public By next_btn=By.cssSelector("button[id='identifierNext']");
    public By error_message=By.xpath("//div[@jsname=\"dWPKW\"]//span[@jsslot]");
    public By try_again_message=By.xpath("//a[@id=\"next\"]");


    //constructor
    public LoginPage(WebDriver driver){
        super(driver);
    }

    //functions
    public LoginPage clickOnBtn(By locator){
        this.click_on_btn(locator);
        return this;
    }

    public LoginPage switchToAnewTab(String originalWindow){
        this.switch_to_a_new_tab(originalWindow);
        return this;
    }

    public LoginPage setField(By locator, String text_val){
        this.sendText(locator, text_val);
        return this;
    }
    public String getText(By locator){
        return get_text(locator);
    }

}
