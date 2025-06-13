package flows;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObject.PurchaseGiftPage;

public class PurchaseGiftFlows {
    PurchaseGiftPage purchaseGiftPage;

    //constructor
    public PurchaseGiftFlows(WebDriver driver){
        this.purchaseGiftPage= new PurchaseGiftPage(driver);
    }

    //functions
    @Step("settingFieldsPhaseOne")
    public void settingFieldsPhaseOne(String VIDEO_PIC_PATH, String  GIFT_RECEIVER_NAME, String  BLESSING_TEXT) throws InterruptedException {
        purchaseGiftPage.clickOnBtn(purchaseGiftPage.getForSomeone);
        purchaseGiftPage.setField(purchaseGiftPage.gift_reciever, GIFT_RECEIVER_NAME);
        purchaseGiftPage.clickOnBtn(purchaseGiftPage.events_dropdown);
        purchaseGiftPage.clickOnBtn(purchaseGiftPage.event_selected);
        purchaseGiftPage.sendText(purchaseGiftPage.bless_textarea, BLESSING_TEXT);
        purchaseGiftPage.sendVideoPic(purchaseGiftPage.video_or_pic, VIDEO_PIC_PATH);
        purchaseGiftPage.clickOnBtn(purchaseGiftPage.next_btn);

    }
}
