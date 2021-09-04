package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.android.AndroidArticlePageObject;

public class IOSArticlePageObject extends AndroidArticlePageObject {
    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://XCUIElementTypeButton[@name= 'Save for later']";
        CLOSE_ARTICLE_BUTTON = "xpath://XCUIElementTypeButton[@name = 'Search']";
        RETURN_TO_MAIN_PAGE_BUTTON = "xpath://XCUIElementTypeButton[@name = 'W']";
    }

    public IOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
