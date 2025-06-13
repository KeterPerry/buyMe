package tests;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testsData.Constants;
import java.io.IOException;
import java.util.Optional;

@Epic("buyMe Automation")
@Feature("LoginPage Testing")
    ////loginViaGoogleTesting///

    public class LoginTest extends Base {

    @BeforeMethod
    public void LoginPageAppears(){
        verifyLoginPageLoads();
        verifyGoogleloginPopupAppears();
    }

    @Test
    public void InvalidLoginInFields() throws IOException, InterruptedException {
        verifyErrorMessageInRed();
    }

    @Test
    public void validLoginInDetails() throws IOException, InterruptedException {
        verifyValidLogin();
    }

    ////////STEPS/////


    @Step("verifyLoginPageLoads")
    public void verifyLoginPageLoads() {
        loginPage.clickOnBtn(homePage.login_registration_btn);
        attachScreenshot(driver,"verifyLoginPageLoads");
        verifyOpenPage("login", "verifyLoginPageLoads", false, Optional.empty());

    }


    @Step("verifyGoogleloginPopupAppears")
    public void verifyGoogleloginPopupAppears() {
        String originalWindow = driver.getWindowHandle();
        loginPage.clickOnBtn(loginPage.google_btn_login);
        loginPage.switchToAnewTab(originalWindow);
        attachScreenshot(driver,"verifyGoogleloginPopupAppears");
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains(Constants.GOOGLE_LOGIN_URL));

    }


    @Step("verifyErrorMessageInRed")
    public void verifyErrorMessageInRed() throws IOException, InterruptedException {
        String invalidEmail= readFromFile("invalid_email1");
        int trials=0;
        do {
            loginPage.setField(loginPage.mail_field, invalidEmail);
            loginPage.clickOnBtn(loginPage.next_btn);
            if(trials<2)
            {loginPage.clickOnBtn(loginPage.try_again_message);}
            trials++;
        }
        while (trials<3);

        attachScreenshot(driver,"verifyErrorMessageInRed");
        verifyError(loginPage.error_message, loginPage.getText(loginPage.error_message),"#b3261e","verifyErrorMessageInRed");

    }

    //test2

    @Step("verifyValidLogin")
    public void verifyValidLogin() throws IOException, InterruptedException {
        String validEmail= readFromFile("valid_email");
        loginPage.setField(loginPage.mail_field,validEmail);
        loginPage.clickOnBtn(loginPage.next_btn);
        attachScreenshot(driver,"verifyValidLogin");
        verifyOpenPage(websiteName,"verifyValidLogin",false,Optional.empty());

    }



}
