import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import static testsData.Constants.*;

@Epic("buyMe Automation")
@Feature("Homepage Testing")

public class HomeTest extends Base {



    @Test
    public void searchingAndFiltering() throws IOException, InterruptedException {
        verifyBudgetDropdownAppears();
        verifyRegionsDropdownAppears();
        verifyCategoriesListAppears();
        verifySearchResultsMatchFilter();
    }

    @Test
    public void clearingFields() throws IOException, InterruptedException {
        verifyBudgetDropdownAppears();
        verifyRegionsDropdownAppears();
        verifyCategoriesListAppears();
        verifyCleaningFields();
    }

    @Test
    public void invalidVoucherCode() throws IOException, InterruptedException {
        verifyVoucherScreenOpen();
        verifyErrorMessageInvalidVoucherCode();
    }

    @Test
    public void checkingOutOfDateVoucher() throws IOException, InterruptedException {
        verifyVoucherScreenOpen();
        verifyErrorMessageInvalidVoucherDate();
    }

    @Test
    public void checkingValidVoucher() throws IOException, InterruptedException {
        verifyVoucherScreenOpen();  //cant check valid voucher

    }

    @Test
    public void verifying_sending_gifts_tab_open() throws IOException, InterruptedException {
        verifying_sending_gifts_tab();
    }

    ////////STEPS/////



//test 1
    @Step("verifyBudgetDropdownAppears")
    public void verifyBudgetDropdownAppears() throws IOException, InterruptedException {
        homePage.forceClick(homePage.select_final_budget);
        attachScreenshot(driver,"verifyBudgetDropdownAppears");
        Assert.assertTrue(homePage.isDisplayed(homePage.select_final_budget));
        Assert.assertTrue(homePage.isPresent(homePage.select_final_budget));
        Assert.assertTrue(homePage.checkSelectCategory(homePage.select_final_budget, BUDGET_LIST));
        homePage.select_insert_value(homePage.select_final_budget,budget_value);
    }


    @Step("verifyRegionsDropdownAppears")
    public void verifyRegionsDropdownAppears () throws IOException, InterruptedException {
        homePage.forceClick(homePage.select_final_area);
        attachScreenshot(driver,"verifyRegionsDropdownAppears");
        Assert.assertTrue(homePage.isDisplayed(homePage.select_final_area));
        Assert.assertTrue(homePage.isPresent(homePage.select_final_area));
        Assert.assertTrue(homePage.checkSelectCategory(homePage.select_final_area, AREA_LIST));
        homePage.select_insert_value(homePage.select_final_area,area_value);
    }


    @Step("verifyCategoriesListAppears")
    public void  verifyCategoriesListAppears () throws IOException, InterruptedException {
        homePage.forceClick(homePage.select_final_category);
        attachScreenshot(driver, "verifyCategoriesListAppears");
        Assert.assertTrue(homePage.isDisplayed(homePage.select_final_category));
        Assert.assertTrue(homePage.isPresent(homePage.select_final_category));
        Assert.assertTrue(homePage.checkSelectCategory(homePage.select_final_category, CATEGORY_LIST));
        homePage.select_insert_value(homePage.select_final_category,category_value);
    }


    @Step("verifySearchResultsMatchFilter")
    public void verifySearchResultsMatchFilter () throws IOException, InterruptedException {
        homePage.forceClick(homePage.select_final_budget);
        homePage.select_insert_value(homePage.select_final_budget,budget_value);
        homePage.forceClick(homePage.select_final_area);
        homePage.select_insert_value(homePage.select_final_area,area_value);
        homePage.forceClick(homePage.select_final_category);
        homePage.select_insert_value(homePage.select_final_category,category_value);
        homePage.clickOnBtn(homePage.search_btn_link);
        attachScreenshot(driver,"verifySearchResultsMatchFilter");
        Assert.assertEquals(driver.getCurrentUrl(),"https://buyme.co.il/search?budget="+budget_value+"&category="+category_value+"&region="+area_value);
    }


    //test 2
    @Test
    @Step("verifyCleaningFields")
    public void  verifyCleaningFields() throws IOException, InterruptedException {
        homePage.forceClick(homePage.select_final_budget);
        homePage.select_insert_value(homePage.select_final_budget,budget_value);
        homePage.forceClick(homePage.select_final_area);
        homePage.select_insert_value(homePage.select_final_area,area_value);
        homePage.forceClick(homePage.select_final_category);
        homePage.select_insert_value(homePage.select_final_category,category_value);
       homePage.forceClick(homePage.clear_fields_btn);
        attachScreenshot(driver,"verifyCleaningFields");
        Assert.assertTrue(homePage.isSelectClear(homePage.select));

    }

    //test3

    @Step("verifyVoucherScreenOpen")
    public void verifyVoucherScreenOpen(){
        homePage.clickOnBtn(homePage.checking_voucher_balance_btn);
        attachScreenshot(driver,"verifyVoucherScreenOpen");
        Assert.assertTrue(homePage.isDisplayed(homePage.voucher_code_field));
        Assert.assertTrue(homePage.isDisplayed(homePage.voucher_expired_date_field));
    }


    @Test
    @Step("verifyErrorMessageInvalidVoucherCode")
    public void  verifyErrorMessageInvalidVoucherCode() throws IOException, InterruptedException {
        homePage.clickOnBtn(homePage.checking_voucher_balance_btn);
        homePage.setField(homePage.voucher_code_field, invalid_voucher_code);
        homePage.setField(homePage.voucher_expired_date_field, valid_voucher_date_code);
        homePage.clickOnBtn(homePage.checking_balance_button_submit);
        attachScreenshot(driver,"verifyErrorMessageInvalidVoucherCode");
        verifyError(homePage.errorMessage,"אחד או יותר מהפרטים שהזנת שגויים","#f01f39","verifyErrorMessageInvalidVoucher");
    }

    //test4
    @Test
    @Step("verifyErrorMessageInvalidVoucherDate")
    public void  verifyErrorMessageInvalidVoucherDate() throws IOException, InterruptedException {
        homePage.clickOnBtn(homePage.checking_voucher_balance_btn);
        homePage.setField(homePage.voucher_code_field, valid_voucher_code);
        homePage.setField(homePage.voucher_expired_date_field, invalid_voucher_date_code);
        homePage.clickOnBtn(homePage.checking_balance_button_submit);
        attachScreenshot(driver,"verifyErrorMessageInvalidVoucherDate");
        verifyError(homePage.errorMessage,"אחד או יותר מהפרטים שהזנת שגויים",ERROR_COLOR,"verifyErrorMessageInvalidVoucher");
    }


    //test5


    //test6
    @Test
    @Step("verifying_sending_gifts_tab_open")
    public void verifying_sending_gifts_tab(){
        String originalWindow = driver.getWindowHandle();
        homePage.clickOnBtn(homePage.sendingGifts_btn);
        attachScreenshot(driver,"verifying_sending_gifts_tab");
        getAndAssertWindowHandle(originalWindow);
    }
}
