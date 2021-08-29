package lib.ui;

import io.appium.java_client.AppiumDriver;

public class MyListsPageObject extends MainPageObject{
    private static final String
            FOLDER_BY_NAME_TPL = "xpath://*[@text= '{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']",
            ARTICLE_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/reading_list_contents']//*[@resource-id='org.wikipedia:id/page_list_item_container']";

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    /* TEMPLATES METHODS */

    public void waitForFolderToAppearByName(String name_of_folder) {
        String folder_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementPresent(folder_xpath, "Cannot find folder by name " + name_of_folder, 15);
    }

    public void openFolderByName(String name_of_folder) {
        this.waitForFolderToAppearByName(name_of_folder);
        String folder_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(folder_xpath, "Cannot find and click folder by name " + name_of_folder, 5);
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find saved article by title " + article_title, 15);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved article still present with title " + article_title, 15);
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(article_xpath, "Cannot find saved article");
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public int getAmountOfArticlesInList() {
        this.waitForElementsPresent(ARTICLE_ELEMENT, "Cannot find any articles in list", 15);
        return getAmountOfElements(ARTICLE_ELEMENT);
    }

    public void openArticleFromList() {
        this.waitForElementAndClick(ARTICLE_ELEMENT, "Cannot find and click article", 5);
    }
}
