package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']";
        CLOSE_INFORM_POPUP = "xpath://XCUIElementTypeButton[@name='Close']";
        ARTICLE_ELEMENT = "xpath://XCUIElementTypeCell[./XCUIElementTypeOther and ./XCUIElementTypeOther[./XCUIElementTypeStaticText and ./XCUIElementTypeStaticText and ./XCUIElementTypeOther]]";
        ARTICLE_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://XCUIElementTypeOther[./XCUIElementTypeStaticText[@name='{TITLE}'] " +
                "and ./XCUIElementTypeStaticText[@name='{DESCRIPTION}']]";
    }

    public IOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
