
import com.mailosaur.MailosaurException;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Optional;
import static testsData.Constants.*;



public class RegistrationTest extends Base {

        @Epic("buyMe Automation")
        @Feature("RegistrationPage Testing")

        @BeforeMethod
        public void registrationPageAppears(){
            verifyRegistrationPageLoads();
            verifyRegistrationPopupAppears();
        }


        @Test
        public void InvalidRegistartionFields() throws IOException, InterruptedException {
            verifyErrorMessageInRed();
        }

        @Test
        public void validRegistrationInDetails() throws IOException, InterruptedException, MailosaurException {
            verifyValidRegistration();
        }


        ///////STEPS/////////////

//Test 1

        @Step("verifyRegistrationPageLoads")
        public void verifyRegistrationPageLoads() {
            registrationPage.clickOnBtn(homePage.login_registration_btn);
            attachScreenshot(driver,"verifyRegistrationPageLoads");
            verifyOpenPage("login", "verifyLoginPageLoads", false, Optional.empty());

        }


        @Step("verifyRegistrationPopupAppears")
        public void verifyRegistrationPopupAppears() {
            try {
                registrationPage.clickOnBtn(registrationPage.email_btn);
                attachScreenshot(driver,"verifyRegistrationPopupAppears");
                Assert.assertTrue(registrationPage.isDisplayed(registrationPage.popUpLogin));
                Assert.assertTrue(registrationPage.isDisplayed(registrationPage.email_field));
                Assert.assertTrue(registrationPage.isDisplayed(registrationPage.popUpLogin));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Step("InvalidEmail")
        public void verifyErrorMessageInRed() throws IOException, InterruptedException {
            String invalidEmail = readFromFile("invalid_email1");
            registrationPage.clickOnBtn(registrationPage.email_field);
            registrationPage.setField(registrationPage.email_field, invalidEmail);
            registrationPage.clickOnBtn(registrationPage.buttonSubmit);
            attachScreenshot(driver,"verifyErrorMessageInRed");
            verifyError(registrationPage.error_invalid_email, "ערך זה צריך להיות כתובת אימייל.", ERROR_COLOR, "invalidEmail");

        }

//@Test 2


        @Step
        public void verifyValidRegistration() throws MailosaurException, IOException, InterruptedException {
            String validEmail = readFromFile("email");
            String fullName = readFromFile("fullName");
            String telephone_num = readFromFile("telephone_num");
            String website_name = readFromFile("websiteName");
            registrationPage.registerSuccessfully(validEmail,fullName,telephone_num);
//            registrationPage.clickOnBtn(registrationPage.email_btn);
//            registrationPage.setField(registrationPage.email_field, validEmail);
//            registrationPage.clickOnBtn(registrationPage.buttonSubmit);
            //String verificationCode = email_phoneVerfication();
            //registrationPage.clickOnBtn(homePage.login_registration_btn);
            //registrationPage.clickOnBtn(registrationPage.email_field);
//            registrationPage.setField(registrationPage.email_code, verificationCode);
//            registrationPage.clickOnBtn(registrationPage.buttonSubmit_mail_verification);
//            registrationPage.setField(registrationPage.fullName, fullName);
//            registrationPage.setField(registrationPage.telephone_num_field,telephone_num);
//            registrationPage.clickOnCheckBox(registrationPage.checkBox);
//            registrationPage.clickOnBtn(registrationPage.register_button);
            attachScreenshot(driver,"verifyValidRegistration");
            Assert.assertEquals(websiteName,website_name);

        }


    }










