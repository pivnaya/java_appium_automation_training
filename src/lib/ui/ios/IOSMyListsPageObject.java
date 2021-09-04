package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']";
        CLOSE_INFORM_POPUP = "xpath://XCUIElementTypeButton[@name='Close']";
    }

    public IOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
