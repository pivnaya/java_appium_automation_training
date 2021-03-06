package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends  MainPageObject{
    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            FOLDER_BY_NAME_TPL,
            CLOSE_ARTICLE_BUTTON,
            RETURN_TO_MAIN_PAGE_BUTTON;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    /* TEMPLATES METHODS */

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getText();
        }
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT,"Cannot find the end of the article",40);
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find the end of the article", 40);
        }
    }

    public void addArticleToNewList(String name_of_folder) {
        this.waitForElementAndClick(OPTIONS_BUTTON, "Cannot find button to open article options", 5);
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY, "Cannot find 'Got it' tip overlay", 5);
        this.waitForElementAndClear(MY_LIST_NAME_INPUT, "Cannot find input to set name of articles folder", 5);
        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT, name_of_folder, "Cannot put text into articles folder input", 5);
        this.waitForElementAndClick(MY_LIST_OK_BUTTON, "Cannot press OK button", 5);
    }

    public void addArticleToList(String name_of_folder) {
        this.waitForElementAndClick(OPTIONS_BUTTON, "Cannot find button to open article options", 5);
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
        String folder_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(folder_xpath, "Cannot find and click folder by name " + name_of_folder, 5);
    }

    public void addArticleToMySaved() {
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
    }

    public void closeArticle() {
        if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON, "Cannot close article, cannot find X link",5);
        } else {
            this.waitForElementAndClick(RETURN_TO_MAIN_PAGE_BUTTON, "Cannot close article, cannot find W link",5);
        }
    }

    public void assertArticleHasTitle() {
        this.assertElementPresent(TITLE, "Cannot find article title");
    }
}
