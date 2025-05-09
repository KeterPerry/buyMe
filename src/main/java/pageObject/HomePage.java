package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.tracing.opentelemetry.SeleniumSpanExporter;

public class HomePage extends BasePage {

    static WebDriver driver;

    //Elements
    public By titleForm= By.className("saFormHeaderProd");
    public By registration= By.cssSelector("button[class='btn btn-theme btn-login']");
    public By login_here= By.linkText("here");
    public By select_amount= By.xpath("//select[@data-parsley-id='16']");
    public By select_area= By.xpath("//select[@data-parsley-id='18']");
    public By select_category= By.xpath("//select[@name=\"category\"]");
    public By search_btn_link= By.xpath("//a[@href=\"https://buyme.co.il/search?budget=3&category=5&region=11\"]");
    public By clear_fields_btn= By.xpath("//button[@gtm=\"נקה\"]");

    //constructor

    public HomePage(WebDriver driver){
        super(driver);
    }

    //functions

    public HomePage registrationClick(By element){
        this.click(element);
        return this;
    }

//    public  HomePage selectCategory(By element, String value){
//        this.selectClickOnValue(element,value);
//        return this;
//    }

    public  HomePage seacrhClick(){
        this.click(search_btn_link);
        return this;
    }
    public  HomePage clear_field(){
        this.clear(clear_fields_btn);
        return this;
    }
    public boolean checkSelectCategory(By element, String value){
       if(this.check_Select_category(element));
        return true;
    }





}
