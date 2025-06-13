package pageObject;

import com.mailosaur.MailosaurException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/////registrationAndLoginViaEnteringAMail//////////
public class RegistrationPage extends BasePage {

    public WebDriver driver;

    //elements
    public By email_btn= By.xpath("//div[@class=\"option easy\"]//div[2]");
    public By email_field= By.xpath("//label[@class=\"ember-view bm-field bm-input empty blur with-icon md reverse no-label\"]//input[@type=\"email\"]");
    public By error_invalid_email= By.cssSelector("ul[class='parsley-errors-list filled']");
    public By popUpLogin=By.xpath("//div[@class= 'login-step-2']");
    public By email_code= By.cssSelector("#otp-code");
    public By telephone_num_field= By.xpath("//input[@type=\"tel\"]");
    public By buttonSubmit= By.xpath("//button[@gtm=\"כניסה\" and @type=\"submit\"]");
    public By send_phone_verification_code_btn= By.xpath("//button[@gtm='שלחו לי קוד אימות']");
    public By phone_verification_otp_code_field= By.xpath("////input[@id=\"otp-code\"]");
    public By phone_verification_otp_code_btn= By.xpath("//button[@gtm=\"אימות טלפון\"]");
    public By buttonSubmit_mail_verification= By.xpath("//button[@gtm=\"אימות מייל\"]");
    public By fullNameField= By.xpath("//input[@data-parsley-namecheck=\"full-name\"]");
    //public By phone_num= By.xpath("//input[@tuaandiinputdiscrp=\"טלפון נייד\"]");
    public By checkBox= By.xpath("//div[@class=\"login-options register-text terms\"]//div[@class=\"inner\"]/span[@role=\"checkbox\"]");
    public By register_button= By.xpath("//button[@gtm=\"הרשמה\"]");




    //Constructor
    public RegistrationPage(WebDriver driver){
        super(driver);
    }


    //functions
    public RegistrationPage setField(By locator, String text_val){
        this.sendText(locator, text_val);
        return this;
    }


   public RegistrationPage clickOnBtn(By locator){
      this.click_on_btn(locator);
       return this;
   }

    public RegistrationPage clickOnCheckBox(By locator){
        this.click_on_check_box(locator);
        return this;
    }

    }





