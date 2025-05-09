package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StudentPage extends BasePage {

    static WebDriver driver;

    //Elements
    public By ibox_form_body= By.id("ibox_form_body");
    public By titleForm= By.className("saFormHeaderProd");
    public By classId= By.id("id");
    public By join_password= By.id("join_password");
    public By firstName= By.id("firstname");
    public By lastname= By.id("lastname");
    public By email= By.id("email");
    public By email_confirm= By.id("email_confirm");
    public By password1= By.id("password1");
    public By password2= By.id("password2");
    public By agreeBtn= By.cssSelector("input[value=\"I Agree -- Create Profile\"]");
    //public By error= By.linkText("This field must be between 1-9 numbers.");
    public By error= By.xpath("//*[@id=\"ibox_form_body\"]/div[1]/div[1]/div/div/ul/li");
    //public By errorFirstName= By.linkText("This field is required.");
    public By errorFirstName= By.xpath("//*[@id=\"ibox_form_body\"]/div[2]/div[1]/div/div/ul/li");
    //public By errorEmail= By.linkText("This does not appear to be a valid email address.");
    public By errorEmail= By.xpath("//*[@id=\"ibox_form_body\"]/div[2]/div[4]/div/div/ul/li");
    //public By errorRegister= By.linkText("We are unable to locate this class. Please contact your instructor.");
    public By errorRegister= By.xpath("//*[@id=\"ibox_form_body\"]/span/table/tbody/tr/th[2]/font");
    public By selectSecret= By.name("secret_question");
    public By secret_answer= By.name("secret_answer");



    //constructor

    public StudentPage(WebDriver driver){
        super(driver);
    }


    //functions
//    public StudentPage setEmail(String emailValue){
//        sendText(email, emailValue);
//        return this;
//    }
//
//    public StudentPage setPassword(String passwordValue){
//        sendText(password1, passwordValue);
//        sendText(password2, passwordValue);
//        return this;
//    }
//
//    public StudentPage setFirstName(String first_Name){
//        sendText(firstName, first_Name);
//        return this;
//    }

//    public StudentPage setClassId(String id){
//        sendText(classId, id);
//        return this;
//    }
    public StudentPage setField(By element, String text_val){
        sendText(element, text_val);
        return this;
    }

    public  StudentPage login_successfully(String classIDVal, String enrollment, String first_name, String last_name, String email_, String confirm, String password, String password_conf, String select, String ans, By elementSubmit) throws InterruptedException {
        this.setField(classId, classIDVal);
        this.setField(join_password, enrollment);
        this.setField(firstName, first_name);
        this.setField(lastname, last_name);
        this.setField(lastname, last_name);
        this.setField(email, email_);
        this.setField(email_confirm, confirm);
        this.setField(password1, password);
        this.setField(password2, password_conf);
        this.selectClickOnValue(selectSecret, select);
        this.setField(secret_answer, ans);
        Thread.sleep(10000);
        this.ClickOnBtn(elementSubmit);
        return this;
    }



    public StudentPage ClickOnBtn(By element){
        click(element);

        return this;
    }
    public String Base_getText(By element_name){
        System.out.println(element_name);
        {return get_Text(element_name);}
    }
    public void setSelect( By element_name, String value){
        selectClickOnValue(element_name, value);
    }



}
