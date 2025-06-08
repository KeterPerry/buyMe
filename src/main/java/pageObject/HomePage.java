package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class HomePage extends BasePage {

    public WebDriver driver;

    //elements
    public By login_registration_btn= By.xpath("//li[@class='notSigned']");
    public By select= By.xpath("//select[@class=\"sr-only-focus\"]");
    public By select_final_area= By.xpath("//select[@class=\"sr-only-focus\" and contains(@aria-label, \"אזור\")]");
    public By select_final_budget= By.xpath("//select[@class=\"sr-only-focus\" and contains(@aria-label, \"סכום\")]");
    public By select_final_category= By.xpath("//select[@class=\"sr-only-focus\" and contains(@aria-label, \"קטגוריה\")]");
    public By search_btn_link= By.xpath("//div[@class='inner']//a[@rel=\"nofollow\" and contains(@href, \"https://buyme.co.il/search\")]");
    public By clear_fields_btn= By.xpath("//button[@gtm='נקה חיפוש']");
    //voucher
    public By checking_voucher_balance_btn= By.xpath("//ul[@class='nav-bar buttons nav-bar-box nav-bar-left']//li[2]/a");
    public By voucher_popUp= By.xpath("//div[@class=\"inner popout\"]");
    public By voucher_code_field= By.xpath("//div[@class=\"inner popout\"]//input[@data-parsley-required-message=\"יש להזין קוד שובר\"]");
    public By voucher_expired_date_field= By.xpath("//div[@class=\"inner popout\"]//input[@data-parsley-required-message=\"יש להזין תוקף שובר\"]");
    public By checking_balance_button_submit= By.xpath("//button[@gtm=\"לבדיקת היתרה\"]");
    public By errorMessage= By.xpath("//div[@class=\"mx-12 top-xs error-message center\"]/span");
    //sending gifts
    public By sendingGifts_btn= By.xpath("//ul[@class=\"nav-bar links nav-bar-box nav-bar-right\"]/li");


    //constructor

    public HomePage(WebDriver driver){
        super(driver);
    }

    //functions

    public  HomePage clickOnBtn(By locator){
        this.click_on_btn(locator);
        return this;
    }

    public  HomePage forceClick(By locator){
        this.force_click(locator);
        return this;
    }

    public HomePage setField(By locator, String text_val){
        this.sendText(locator, text_val);
        return this;
    }


    public  boolean isPresent(By locator){
        return this.is_present(locator);

    }


    public boolean checkSelectCategory(By locator, List<String> expected ){
       if(this.check_Select_category(locator,expected));
        return true;
    }


    public boolean isSelectClear(By locator){
        if(this.is_select_clear(locator));
        return true;
    }

    public  HomePage selectInsertValue(By locator, String value){
        this.select_insert_value(locator,value);
        return this;
    }








}
