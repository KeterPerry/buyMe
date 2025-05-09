//package pageObject;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.Select;
//
//public class RegistrationPage extends BasePage{
//    static WebDriver driver;
//    By titleCombo= By.xpath("//*[@id=\"Title\"]");
//    public By FirstName= By.id("FirstName");
//    public By LastName= By.id("LastName");
//    public By Email= By.xpath("//*[@id='Email']");
//    public By Email_error= By.id("Email-error");
//    By Password= By.xpath("//*[@id=\"Password\"]");
//    By PhoneNumber= By.xpath("//*[@id=\"PhoneNumber\"]");
//    By HomeLine= By.id("AddressLine2");
//    By cityLine= By.id("AddressLine4");
//    By zipCode= By.id("AddressLine6");
//
//    By checkByEmail= By.xpath("//*[@for=\"ChkByEmail\"]");
//    By SignupButton= By.xpath("//*[@id=\"SignupButton\"]");
//
//
//
//    //constructor
//
//    public RegistrationPage(WebDriver driver){
//        super(driver);
//    }
//
//    //functions
//
//    public RegistrationPage setField(By element, String text_val){
//        sendText(element, text_val);
//        return this;
//    }
//
//    public RegistrationPage ClickOnBtn(By element){
//        click(element);
//        return this;
//    }
//
//    public RegistrationPage ClickOnChkBox(By element){
//        ClickOnCheckBox(element);
//        return this;
//    }
//
//    public RegistrationPage selectE(By element, String value){
//        selectClickOnValue(element, value);
//        return this;
//    }
//
//    public RegistrationPage registerSuccessfully(String value_, String FirstName_,String LastName_, String email_, String password_, String Phone_num_, String homeLine_, String cityLine_, String zipCode_) throws InterruptedException {
//        this.selectE(titleCombo,value_);
//        this.setField(FirstName, FirstName_);
//        this.setField(LastName, LastName_);
//        this.setField(Email, email_);
//        this.setField(Password, password_);
//        this.setField(PhoneNumber, Phone_num_);
//        this.setField(HomeLine, homeLine_);
//        this.setField(cityLine, cityLine_);
//        this.setField(zipCode, zipCode_);
//        this.ClickOnChkBox(checkByEmail);
//        this.ClickOnBtn(SignupButton);
//        return this;
//    }
//
//
//}
//
