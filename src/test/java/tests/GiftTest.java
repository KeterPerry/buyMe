package tests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Optional;
import testsData.Constants;
import io.qameta.allure.*;
import static testsData.Constants.*;

@Epic("buyMe Automation")
@Feature("GiftPage Testing")

public class GiftTest extends Base {

    @BeforeMethod
    public void settingGiftPage() throws InterruptedException, IOException {
        driver.get(Constants.BASE_CATEGORY_URL);
        Thread.sleep(5000);
    }


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifies that a budget must be set before submitting a gift")
    public void verifyingBudgetIsMandatory() throws IOException, InterruptedException {
        verifyList_of_gifts();
        verify_voucherGift_screen();
        verifyErrorMessageVoucher();
    }


    //////// STEPS //////

    //test 1
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

    @Step("verifyErrorMessage")
    public void verifyErrorMessageVoucher() throws IOException, InterruptedException {
        giftPage.clickOnBtn(giftPage.choice_btn_submit);
        attachScreenshot(driver,"verifyErrorMessageVoucher");
        verifyError(giftPage.errorMessage, ERROR_MSG_REQUIRED, ERROR_COLOR, "verifyErrorMessageVoucher");
    }

}




