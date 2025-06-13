package tests;
import com.mailosaur.MailosaurException;
import flows.RegistrationFlows;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Optional;
import static testsData.Constants.*;


@Epic("buyMe Automation")
@Feature("RegistrationPage Testing")

public class RegistrationTest extends Base {

    RegistrationFlows registrationFlows;

        @BeforeMethod
        public void registrationPageAppears(){
            this.registrationFlows=new RegistrationFlows(driver);
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
            if (registrationFlows.register_or_login_successfully(validEmail,fullName,telephone_num)) {
                attachScreenshot(driver, "verifyValidRegistration");
                Assert.assertEquals(websiteName, website_name);
            }
        }


    }










