package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class GiftPage extends BasePage{

    public WebDriver driver;

    //elements
    public By gift_results_parent= By.xpath("//ul[@class='grid bm-product-cards']");
    public By gift_results_children= By.xpath("//ul[@class='grid bm-product-cards']//div[@uarole='article']");
    public By chef_voucher= By.xpath("//a[@href=\"https://buyme.co.il/supplier/752649?catName=%D7%9E%D7%AA%D7%A0%D7%95%D7%AA%20%D7%9C%D7%99%D7%95%D7%9D%20%D7%94%D7%95%D7%9C%D7%93%D7%AA&catUrl=%D7%9E%D7%AA%D7%A0%D7%95%D7%AA%20%D7%9C%D7%99%D7%95%D7%9D%20%D7%94%D7%95%D7%9C%D7%93%D7%AA\"]");
    public By choice_btn_submit= By.xpath("//button[@class='button button--medium button--solid' and @aria-label='בחירה']");
    public By errorMessage= By.xpath("//div[@class='text-field__error-message']");
    public By price_field= By.cssSelector("input[name=\"cardPrice\"]");
    public By first_step= By.xpath("//div[@class=\"steps-line__step__title\" and contains(text(), 'למי לשלוח')]");
    //to send_page elements
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
    public GiftPage(WebDriver driver) {
        super(driver);
    }

    //functions

    public boolean find_gifts_not_empty(By parent, By children) {
        if (findNumOfResults(parent, children)>0)
            return true;
        else
            return false;
    }

    public GiftPage clickOnBtn(By Locator){
        this.click_on_btn(Locator);
        return this;
    }

    public GiftPage forceClick(By locator){
        this.force_click(locator);
        return this;
    }

    public GiftPage setField(By locator, String text_val){
        this.sendText(locator, text_val);
        return this;
    }
    public String getText(By locator){
        {return get_text(locator);}
    }

    public boolean is_displayed(By locator){
        return this.isDisplayed(locator);

    }

    public GiftPage sendVideoPic(By locator, String path) throws InterruptedException {
        this.send_video_pic(locator, path);
        return this;
    }

    public GiftPage settingFieldsPhaseOne(String VIDEO_PIC_PATH, String  GIFT_RECEIVER_NAME, String  BLESSING_TEXT) throws InterruptedException {
        clickOnBtn(getForSomeone);
        setField(gift_reciever, GIFT_RECEIVER_NAME);
        clickOnBtn(events_dropdown);
        clickOnBtn(event_selected);
        sendText(bless_textarea, BLESSING_TEXT);
        sendVideoPic(video_or_pic, VIDEO_PIC_PATH);
        clickOnBtn(next_btn);
        return this;
    }


}
