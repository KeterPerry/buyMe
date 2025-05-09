
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;

import java.io.IOException;

public class LoginTest extends Base{

    @Epic("buyMe Automation")
    @Feature("LoginPage Testing")

    @BeforeClass
    public static void settingPage(){
    driver.get("https://buyme.co.il/?modal=login");
    }


    @Test
    public void invalid_email_address() {

        try {
            String email= readFromFile("invalid_email", configPath);
            //Thread.sleep(5000);
            String expected_result="ערך זה אינ ו תקין";
            verifyText(loginPage.error_invalid_email,expected_result);
        } catch (InterruptedException | IOException e ) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void loginNonExistent() {

        try {
            loginPage.loginSuccessfully("kate@gmail.com", "kateP@1234456789",loginPage.buttonSubmit);
            Thread.sleep(5000);
            verifyText(loginPage.error_non_existent,"The password for this account has expired.\nTo log in, please reset your password.");
        } catch (IOException | InterruptedException e) {
        }

    }

    @Test
    public void valid_user_loginCheck() {

        try {
            String email= readFromFile("email", configPath);
            String code= readFromFile("code", configPath);
            String telephone_num= readFromFile("telephone_num", configPath);
            //String password= readFromFile("password", configPath);
            loginPage.loginSuccessfully(email,code,telephone_num);
            Thread.sleep(5000);
        } catch (InterruptedException | IOException e ) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void redirectToHomePage() throws InterruptedException {
        try {
            String currentUrl=driver.getCurrentUrl();
            compareUrls(currentUrl,loginPage.newUser);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void redirectToPasswordPage2() throws InterruptedException {
        try {
            String currentUrl = driver.getCurrentUrl();
            compareUrls(currentUrl, loginPage.forgotPassword);
            Thread.sleep(2000);
        }
      catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void passwordPage() throws IOException, InterruptedException {
            loginPage.click(loginPage.forgotPassword);
            String email= readFromFile("email", configPath);
            loginPage.setField(loginPage.forgotPasswordEmail,email);
            loginPage.click(loginPage.forgotPasswordSubmitbtn);
            verifyText(loginPage.changePasswordNote,"If the email you provided exists in our system you will soon receive an email containing a password reset link. Please check your inbox and spam folder.");
            Thread.sleep(5000);
    }
    }


