package tests;
import flows.PurchaseGiftFlows;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Optional;

import static testsData.Constants.*;
import static testsData.Constants.MESSAGE_FUN_TEXT;

@Epic("buyMe Automation")
@Feature("PurchaseGiftPage Testing")
public class PurchaseGiftTest extends Base {

    PurchaseGiftFlows purchaseGiftFlows;


    @BeforeMethod
    public void settingUpPurchaseGiftPage() throws IOException, InterruptedException {
        this.purchaseGiftFlows=new PurchaseGiftFlows(driver);
        driver.get(PURCHASE_SCREEN_URL);
        verifyingOpenPurchaseScreen();
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Simulates purchasing a gift for someone else")
    public void PurchasingAGiftForSomebody() throws IOException, InterruptedException {
        verifyingFields();
        verifyingHowToSendPageOpen();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Simulates purchasing a gift for myself")
    public void PurchasingAGiftForMyself() throws IOException, InterruptedException {
        verifyText();
    }


////////STEPS///////



    @Step("verifyingOpenPurchaseScreen")
    public void verifyingOpenPurchaseScreen() throws IOException, InterruptedException {
        purchaseGiftPage.setField(purchaseGiftPage.price_field, PRICE_300);
        purchaseGiftPage.clickOnBtn(purchaseGiftPage.choice_btn_submit);
        Thread.sleep(1000);
        attachScreenshot(driver, "verifyErrorMessageVoucher");
        verifyOpenPage(PURCHASE_URL_STEP1_PREFIX + PRICE_300, "verifyingOpenPurchaseScreen", false, Optional.empty());
        Assert.assertEquals(purchaseGiftPage.getText(purchaseGiftPage.first_step), STEP1_TEXT);
    }

    //test1
    @Step("verifyingFields")
    public void verifyingFields() throws IOException, InterruptedException {
        attachScreenshot(driver, "verifyingFields");
        Assert.assertTrue(purchaseGiftPage.is_displayed(purchaseGiftPage.gift_reciever));
        Assert.assertTrue(purchaseGiftPage.is_displayed(purchaseGiftPage.events_dropdown));
        Assert.assertTrue(purchaseGiftPage.is_displayed(purchaseGiftPage.bless_textarea));
        Assert.assertTrue(purchaseGiftPage.is_displayed(purchaseGiftPage.video_or_pic));
        Assert.assertTrue(purchaseGiftPage.is_displayed(purchaseGiftPage.next_btn));
    }

    //test1
    @Step("verifyingHowToSendPageOpen")
    public void verifyingHowToSendPageOpen() throws IOException, InterruptedException {
        purchaseGiftFlows.settingFieldsPhaseOne(VIDEO_PIC_PATH, GIFT_RECEIVER_NAME, BLESSING_TEXT);
        attachScreenshot(driver, "verifyingHowToSendPageOpen");
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains(PURCHASE_URL_STEP2_PREFIX));
    }

    //test2
    @Step("verifyText")
    public void verifyText() throws IOException, InterruptedException {
        purchaseGiftPage.clickOnBtn(purchaseGiftPage.getForMyself);
        attachScreenshot(driver, "verifyText");
        Assert.assertEquals(purchaseGiftPage.get_text(purchaseGiftPage.label_messege_fun), MESSAGE_FUN_TEXT);
    }
}