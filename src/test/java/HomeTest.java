import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Optional;

@Epic("buyMe Automation")
@Feature("Homepage Testing")

public class HomeTest extends Base {

//    @Test(description = "Verify Home Page Header Text")
//    @Story("Header Verification")
//    @Severity(SeverityLevel.CRITICAL)
//    @Step("Verify the title header on homepage")
//    public void verifyHeader() throws IOException, InterruptedException {
//        String expectedResult = "Create a User Profile";
//        verifyText(homePage.titleForm, expectedResult);
//    }

//    @Test(description = "Compare Homepage and Student Registration Page Titles")
//    @Story("Title Comparison")
//    @Severity(SeverityLevel.NORMAL)
//    public void checkingTitles() throws IOException {
//        String home_title = driver.getTitle();
//        //homePage.registrationClick(homePage.student_registration);
//        String student_title = driver.getTitle();
//        Allure.step("Home title: " + home_title);
//        Allure.step("Student title: " + student_title);
//        Assert.assertEquals(home_title, student_title);
//    }

    @Test(description = "verifyingHomePage")
    @Story("verifyingHomePage")
    public void verifyingHomePage() throws IOException, InterruptedException {
        verifyOpenPage("buyme","verifyingHomePage",true, Optional.of("buyMe"));
    }


    @Test(description = "verifyingLoginPage")
    @Story("verifyingLoginPage")
    public void verifyingLoginPage() throws IOException, InterruptedException {
        verifyOpenPage("login","verifyingLoginPage", false, Optional.empty());
    }

    @Test(description = "checking filterning and searching")
    @Story("checking filterning and searching")
    public void checking_filterning _and_searching() throws IOException, InterruptedException {
        Assert.assertTrue(homePage.checkSelectCategory(homePage.select_amount,"99"));
     homePage.checkSelectCategory(homePage.select_area,"דרום");
     homePage.checkSelectCategory(homePage.select_category,"המתנות האהובות של 2025");
     homePage.seacrhClick();
     verifyOpenPage("search","checking_filterning_and_searching", false, Optional.empty());

    }
}
