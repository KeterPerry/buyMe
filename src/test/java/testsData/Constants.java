package testsData;
import java.util.Arrays;
import java.util.List;

public class Constants {

    //loginTest
    public static final String GOOGLE_ERROR_COLOR ="#b3261e";

    //giftTests
    // URLs
    public static final String BASE_CATEGORY_URL = "https://buyme.co.il/categories/%D7%9E%D7%AA%D7%A0%D7%95%D7%AA%20%D7%9C%D7%99%D7%95%D7%9D%20%D7%94%D7%95%D7%9C%D7%93%D7%AA";
    public static final String VOUCHER_SCREEN_URL_PREFIX = "https://buyme.co.il/supplier/";
    public static final String PURCHASE_SCREEN_URL = "https://buyme.co.il/supplier/752649?catUrl=%D7%9E%D7%AA%D7%A0%D7%95%D7%AA+%D7%9C%D7%99%D7%95%D7%9D+%D7%94%D7%95%D7%9C%D7%93%D7%AA&catName=%D7%9E%D7%AA%D7%A0%D7%95%D7%AA+%D7%9C%D7%99%D7%95%D7%9D+%D7%94%D7%95%D7%9C%D7%93%D7%AA";
    public static final String PURCHASE_URL_STEP2_PREFIX = "https://buyme.co.il/money/752649?price=300&step=2";
    public static final String PURCHASE_URL_STEP1_PREFIX = "https://buyme.co.il/money/752649?price=";
    public static final String SENDING_GIFTS_TO_EMPLOYEES_PAGE = "https://buyme.co.il/business?utm_source=buyme_site&utm_medium=top_link&utm_campaign=clicks";


    // Price
    public static final String PRICE_300 = "300";

    // Error message and color
    public static final String ERROR_MSG_REQUIRED = "ערך זה דרוש";
    public static final String ERROR_COLOR = "#f01f39";

    // UI Texts
    public static final String STEP1_TEXT = "למי לשלוח";
    public static final String MESSAGE_FUN_TEXT = "הכי הכי כיף להתפנק במתנה לעצמך!";

    // Gift receiver and blessing texts
    public static final String GIFT_RECEIVER_NAME = "shalom";
    public static final String BLESSING_TEXT = "מזל טוב";

    // File key for reading path
    public static final String VIDEO_PIC_PATH = "C:\\Users\\keter\\Desktop\\keter\\Igal's Wedding pics\\16538494923981284423470061930410.jpg";

    //homeTests

    public static final String GOOGLE_LOGIN_URL = "https://accounts.google.com/";

    //test1
    public static final List<String> BUDGET_LIST = Arrays.asList(
            "סכום",
            "עד 99 ש\"ח",
            "100-199 ש\"ח",
            "200-299 ש\"ח",
            "300-499 ש\"ח",
            "500-750 ש\"ח",
            "מעל 750 ש\"ח"
    );

    public  static String budget_value="2";

    public static final List<String> AREA_LIST = Arrays.asList(
            "אזור",
            "מרכז",
            " ת\"א והסביבה",
            "צפון",
            "דרום",
            " ירושלים"

    );
    public  static String area_value="12";

    public static final List<String> CATEGORY_LIST = Arrays.asList(
            "קטגוריה",
            "המתנות האהובות של 2025",
            "גיפט קארד למותגי אופנה",
            "משלוחי פרחים",
            "גיפט קראד למתנות יומולדת וצעצועים",
            "גיפט קארד לבתי ספא",
            "גיפט קארד למסעדות שף",
            "גיפט קארד למסעדות",
            "גיפט קארד לארוחות בוקר ובתי קפה",
            "גיפט קראד לבית,מטבח וגאדג'טים",
            "גיפט קראד לנופש ומלונות",
            "גיפט קראד למתנות קולינריות",
            "גיפט קראד לתרבות ופנאי",
            "גיפט קראד לחוויות משותפות",
            "גיפט קראד לבריאות,ספורט ואקסטרים",
            "גיפט קראד לסדנאות העשרה",
            "גיפט קראד ליופי וטיפוח"

    );
    public  static String category_value="541";
//test3
public  static String invalid_voucher_code="243";
public  static String valid_voucher_date_code="20/04/2027";
//test4
public  static String valid_voucher_code="1243-1234-1234-1234";
public  static String invalid_voucher_date_code="20/04/2020";


}
