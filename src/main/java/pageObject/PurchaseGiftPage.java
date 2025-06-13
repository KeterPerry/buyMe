package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PurchaseGiftPage extends BasePage{

    public WebDriver driver;

    //elements
    public By choice_btn_submit= By.xpath("//button[@class='button button--medium button--solid' and @aria-label='בחירה']");
    public By price_field= By.cssSelector("input[name=\"cardPrice\"]");
    public By first_step= By.xpath("//div[@class=\"steps-line__step__title\" and contains(text(), 'למי לשלוח')]");
    public By getForSomeone= By.cssSelector("input[id=\"למישהו אחר-forMyself\"]");
    public By getForMyself= By.cssSelector("input[id=\"לעצמי-forMyself\"]");
    public By gift_reciever= By.cssSelector("input[id=\"שם מקבל המתנה-friendName\"]");
    public By events_dropdown= By.xpath("//div[@class=\"b2c-dropdown__wrapper\"]");
    public By event_selected= By.xpath("//ul[@id=\"b2c-dropdown-listbox\"]//li[contains(text(),'יום הולדת')]");
    public By bless_textarea= By.xpath("//textarea[@id=\"greeting-b2c-textarea\"]");
    public By video_or_pic= By.cssSelector("input[id='add-pic-selection_button__input']");
    public By next_btn= By.xpath("//button[@class=\"button button--large button--solid\"]");
    public By label_messege_fun= By.xpath("//div[@class='purchase-flow-step-1__for-myself']/label");

    //constructor

    public PurchaseGiftPage(WebDriver driver){
      super(driver);
    }
    //functions


    public PurchaseGiftPage clickOnBtn(By Locator){
        this.click_on_btn(Locator);
        return this;
    }

    public PurchaseGiftPage forceClick(By locator){
        this.force_click(locator);
        return this;
    }

    public PurchaseGiftPage setField(By locator, String text_val){
        this.sendText(locator, text_val);
        return this;
    }
    public String getText(By locator){
        {return get_text(locator);}
    }

    public boolean is_displayed(By locator){
        return this.isDisplayed(locator);

    }

    public PurchaseGiftPage sendVideoPic(By locator, String path) throws InterruptedException {
        this.send_video_pic(locator, path);
        return this;
    }

}
