import com.aventstack.extentreports.Status;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class StudentTest extends Base {


    @BeforeClass
    public static void setting(){
        //driver.get("https://www.turnitin.com/newuser_join.asp?svr=6&session-id=81f759b99b574ea28ae840e1a1377260&lang=en_us&r=30.4749935945825");
   homePage.click(homePage.student_registration);
    }

    @Test
        public void test1() throws IOException, InterruptedException {
            test = extent.createTest("verifying studentTitle");
            String expectedResult = "Create a New Student Profile";
            verifyText(studentPage.titleForm, expectedResult);
    }

    @Test
    public void test2() throws IOException, InterruptedException {
            test = extent.createTest("Verifying classIDError");
            studentPage.setField(studentPage.classId, "123455677889875");
            studentPage.ClickOnBtn(studentPage.ibox_form_body);
            Thread.sleep(5000);
            verifyText(studentPage.error, "This field must be between 1-9 numbers.");

    }

    @Test
    public void test3() throws IOException, InterruptedException {

        test = extent.createTest("verifying  first name error.");
            studentPage.setField(studentPage.firstName,"");
            studentPage.ClickOnBtn(studentPage.ibox_form_body);
            verifyText(studentPage.errorFirstName,"This field is required.");

    }

    @Test
    public void test4() throws IOException, InterruptedException {
        test = extent.createTest("verifying email error.");
            studentPage.setField(studentPage.email,"absdfsd");
            studentPage.ClickOnBtn(studentPage.email_confirm);
            verifyText(studentPage.errorEmail,"This does not appear to be a valid email address.");
    }
    @Test
    public void test5() throws IOException, InterruptedException {
        test = extent.createTest("student page signing in");
         studentPage.login_successfully("123456789", "123456789", "keter", "perry", "keterav@gmail.com", "keterav@gmail.com", "keterP12345678@", "keterP12345678@","14", "ronali", studentPage.agreeBtn);
         verifyText(studentPage.errorRegister, "We are unable to locate this class. Please contact your instructor.");
    }

    }


