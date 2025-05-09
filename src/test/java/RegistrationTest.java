//import com.aventstack.extentreports.Status;
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//public class RegistrationTest extends Base {
//public static String registration_url="https://account.next.co.il/he/CustomerRegistration";
//
//    @BeforeClass
//    public static void SettingUp(){
//        driver.get(registration_url);
//
//    }
//
//    @Test
//    public void Test1Registration(){
//        test= extent.createTest("valid registration");
//        try {
//            registrationPage.registerSuccessfully("Mrs","keter", "Danon", "keterav@gmail.com", "keterPe12345@", "0507744085", "Aya 6", "Meitar", "8502500");
//            test.log(Status.INFO, "register Successfully operation started");
//            Assert.assertEquals("https://www.next.co.il/he", websiteName);
//        }
//        catch (Exception e){
//            e.getMessage();
//        }
//    }
//
//    @Test
//    public void Test2ExistRegistant(){
//        test= extent.createTest("ExistRegistant");
//        try {
//            registrationPage.registerSuccessfully("Mrs","yali", "Danon", "keterav@gmail.com", "keterPe12345@", "0507744085", "Aya 6", "Meitar", "8502500");
//            registrationPage.setField(registrationPage.FirstName,"yali");
//            registrationPage.setField(registrationPage.LastName,"Bam");
//            registrationPage.setField(registrationPage.Email,"keterav@gmail.com");
//            test.log(Status.INFO, "exist registrator notification");
//            verifyText(registrationPage.Email_error, "דוא\"ל זה כבר נמצא בשימוש. אנא היכנס/י כדי להשתמש בחשבון נקסט הקיים שלך." );
//        }
//        catch (Exception e){
//            e.getMessage();
//        }
//    }
//}
