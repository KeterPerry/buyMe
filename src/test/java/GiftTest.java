import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Optional;
import testsData.Constants;
import io.qameta.allure.*;
import static testsData.Constants.*;

public class GiftTest extends Base {

    @Epic("buyMe Automation")
    @Feature("GiftPage Testing")

    @BeforeMethod
    public void settingGiftPage() throws InterruptedException, IOException {
        driver.get(Constants.BASE_CATEGORY_URL);
        Thread.sleep(5000);
        verifyList_of_gifts();
        verify_voucherGift_screen();
    }


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifies that a budget must be set before submitting a gift")
    public void verifyingBudgetIsMandatory() throws IOException, InterruptedException {
        verifyErrorMessageVoucher();
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Simulates purchasing a gift for someone else")
    public void PurchasingAGiftForSomebody() throws IOException, InterruptedException {
        verifyingOpenPurchaseScreen();
        verifyingFields();
        verifyingHowToSendPageOpen();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Simulates purchasing a gift for myself")
    public void PurchasingAGiftForMyself() throws IOException, InterruptedException {
        verifyingOpenPurchaseScreen();
        verifyText();
    }

    //////// STEPS //////


    @Step("verifyList_of_gifts")
    public void verifyList_of_gifts() throws IOException, InterruptedException {
        boolean isGiftsListEmpty = giftPage.find_gifts_not_empty(giftPage.gift_results_parent, giftPage.gift_results_children);
        attachScreenshot(driver,"verifyList_of_gifts");
        Assert.assertTrue(isGiftsListEmpty);
    }

    @Step("verify_voucherGift_screen")
    public void verify_voucherGift_screen() throws IOException, InterruptedException {
        giftPage.forceClick(giftPage.chef_voucher);
        attachScreenshot(driver,"verify_voucherGift_screen");
        verifyOpenPage(VOUCHER_SCREEN_URL_PREFIX, "verify_voucherGift_screen", false, Optional.empty());
    }


    //test 1
    @Step("verifyErrorMessage")
    public void verifyErrorMessageVoucher() throws IOException, InterruptedException {
        giftPage.clickOnBtn(giftPage.choice_btn_submit);
        attachScreenshot(driver,"verifyErrorMessageVoucher");
        verifyError(giftPage.errorMessage, ERROR_MSG_REQUIRED, ERROR_COLOR, "verifyErrorMessageVoucher");
    }

    //test2and3
    @Step("verifyingOpenPurchaseScreen")
    public void verifyingOpenPurchaseScreen() throws IOException, InterruptedException {
        driver.get(PURCHASE_SCREEN_URL);
        giftPage.setField(giftPage.price_field, PRICE_300);
        giftPage.clickOnBtn(giftPage.choice_btn_submit);
        Thread.sleep(1000);
        attachScreenshot(driver,"verifyErrorMessageVoucher");
        verifyOpenPage(PURCHASE_URL_STEP1_PREFIX + PRICE_300, "verifyingOpenPurchaseScreen", false, Optional.empty());
        Assert.assertEquals(giftPage.getText(giftPage.first_step), STEP1_TEXT);
    }

//test2
    @Step("verifyingFields")
    public void verifyingFields() throws IOException, InterruptedException {
        attachScreenshot(driver,"verifyingFields");
        Assert.assertTrue(giftPage.is_displayed(giftPage.gift_reciever));
        Assert.assertTrue(giftPage.is_displayed(giftPage.events_dropdown));
        Assert.assertTrue(giftPage.is_displayed(giftPage.bless_textarea));
        Assert.assertTrue(giftPage.is_displayed(giftPage.video_or_pic));
        Assert.assertTrue(giftPage.is_displayed(giftPage.next_btn));
    }
//test2
    @Step("verifyingHowToSendPageOpen")
    public void verifyingHowToSendPageOpen() throws IOException, InterruptedException {
        giftPage.settingFieldsPhaseOne(VIDEO_PIC_PATH,GIFT_RECEIVER_NAME,BLESSING_TEXT);
        attachScreenshot(driver,"verifyingHowToSendPageOpen");
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains(PURCHASE_URL_STEP2_PREFIX));
    }
//test3
    @Step("verifyText")
    public void verifyText() throws IOException, InterruptedException {
        giftPage.clickOnBtn(giftPage.getForMyself);
        attachScreenshot(driver,"verifyText");
        Assert.assertEquals(giftPage.get_text(giftPage.label_messege_fun), MESSAGE_FUN_TEXT);
    }
}




