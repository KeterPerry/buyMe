package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    static WebDriver driver;

    //Elements

    protected By email_btn= By.cssSelector(".social-btn");
    protected By email_field= By.xpath("//label[@class=\"ember-view bm-field bm-input empty blur with-icon md reverse no-label\"]//input[@type=\"email\"]");
    public By error_invalid_email= By.cssSelector("ul[class='parsley-errors-list filled']");

    protected By code= By.cssSelector("#otp-code");
    public By telephone_num_field= By.xpath("//input[@type=\"tel\"]");
    protected By buttonSubmit= By.xpath("//button[@gtm=\"כניסה\" and @type=\"submit\"]");
    protected By buttonSubmit_phone_verification= By.xpath("//button[@gtm='שלחו לי קוד אימות']");
    //protected By error_non_existent = By.xpath("//div[@class=\"error\"]/p");
    //protected By newUser = By.xpath("//*[@id=\"ibox_form_body\"]/p[2]/a");
    //protected By forgotPassword = By.xpath(" //*[@id=\"ibox_form_body\"]/p[1]/a[1]");

    ///passwordPage
//    public By forgotPasswordEmail = By.id("useremail");
//    public By forgotPasswordSubmitbtn = By.cssSelector("input[value='Next']");
//    public By changePasswordNote = By.xpath("//*[@id=\"ibox_form_body\"]/p[1]/strong");





    //Constructor
    public LoginPage(WebDriver driver){
        super(driver);
    }


    //functions

    public LoginPage setField(By element, String text_val){
        sendText(element, text_val);
        return this;
    }

   public  LoginPage clickOnBtn(By element){
      click(element);
       return this;
   }

   public  LoginPage loginSuccessfully(String email_, String code_, String telephone_num_field_){
       this.setField(email_field, email_);
        this.clickOnBtn(buttonSubmit);
        this.setField(code,code_);
        this.clickOnBtn(buttonSubmit);
        this.setField(telephone_num_field, telephone_num_field_);
        this.clickOnBtn(buttonSubmit_phone_verification);
        return this;
   }

    public String Base_getText(By element_name){
        System.out.println(element_name);
        {return get_Text(element_name);}
    }
}
