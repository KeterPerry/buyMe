package flows;

import com.mailosaur.MailosaurException;
import io.grpc.MetricRecorder;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import pageObject.BasePage;
import pageObject.LoginPage;
import pageObject.RegistrationPage;

import java.io.IOException;

public class RegistrationFlows {

   //Attributes
   RegistrationPage registrationPage;
   BasePage basePage;

   //constructor
    public RegistrationFlows(WebDriver driver){
    this.registrationPage=new RegistrationPage(driver);
    this.basePage=new BasePage(driver);
    }

    //functions
    @Step("register_successfully")
    public boolean register_or_login_successfully(String validEmail, String fullName_, String telephone_num ) throws MailosaurException, IOException, InterruptedException {

        registrationPage.clickOnBtn(registrationPage.email_field);
        registrationPage.setField(registrationPage.email_field, validEmail);
        registrationPage.clickOnBtn(registrationPage.buttonSubmit);
        String verificationCode = basePage.email_phoneVerfication();
        registrationPage.clickOnBtn(registrationPage.email_code);
        registrationPage.setField(registrationPage.email_code, verificationCode);
        registrationPage.clickOnBtn(registrationPage.buttonSubmit);

        try{
            /////Login-ifDoubleAuthIsRequired
        if (registrationPage.is_displayed(registrationPage.send_phone_verification_code_btn)) {
            registrationPage.setField(registrationPage.telephone_num_field, telephone_num);
            registrationPage.clickOnBtn(registrationPage.send_phone_verification_code_btn);
            Thread.sleep(50000);//50 seconds
            registrationPage.clickOnBtn(registrationPage.phone_verification_otp_code_btn);
        } else {
            /////Registration
            if (registrationPage.is_displayed(registrationPage.fullNameField)) {
                registrationPage.setField(registrationPage.fullNameField, fullName_);
                registrationPage.setField(registrationPage.telephone_num_field, telephone_num);
                registrationPage.clickOnCheckBox(registrationPage.checkBox);
                registrationPage.clickOnBtn(registrationPage.register_button);
            }
        }
    }
        catch (NoSuchElementException e){
            /////Login-ifDoubleAuthIsNotRequired
         return true;
        }
    return true;
    }
}
