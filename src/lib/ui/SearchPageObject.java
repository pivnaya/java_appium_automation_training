package lib.ui;

import io.appium.java_client.AppiumDriver;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INIT_ELEMENT_TEXT = "xpath://*[@resource-id='org.wikipedia:id/search_container']//*[@class='android.widget.TextView']",
            SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]",
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_EQUALS_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text= '{SUBSTRING}']",
            SEARCH_RESULT_BY_SUBSTRING_CONTAINS_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text, '{SUBSTRING}')]",
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container' " +
                    "and .//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text = '{TITLE}'] " +
                    "and .//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text = '{DESCRIPTION}']]",
            SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            EMPTY_RESULT_LABEL = "xpath://*[@text='No results found']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElementWithSubstringEquals(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_EQUALS_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementWithSubstringContains(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_CONTAINS_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementByTitleAndDescription(String title, String description) {
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INPUT, "Cannot find input after clicking search init element", 5);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResultBySubstring(String substring) {
        String search_result_xpath = getResultSearchElementWithSubstringEquals(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring, 15);
    }

    public void waitForSearchResultByTitleAndDescription(String title, String description) {
        String search_result_xpath = getResultSearchElementByTitleAndDescription(title, description);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with title " + title + " and description " + description, 15);
    }

    public void waitForSearchResultDisappear() {
        this.waitForElementNotPresent(SEARCH_RESULT_ELEMENT, "Search result is still present", 15);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElementWithSubstringEquals(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 15);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementsPresent(SEARCH_RESULT_ELEMENT, "Cannot find anything by the request", 15);
        return getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public int getAmountOfFoundArticlesWithSubstringContains(String substring) {
        this.waitForElementsPresent(SEARCH_RESULT_ELEMENT, "Cannot find anything by the request", 15);
        String search_result_xpath = getResultSearchElementWithSubstringContains(substring);
        return getAmountOfElements(search_result_xpath);
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementsPresent(EMPTY_RESULT_LABEL, "Cannot find empty result label by the request",15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }

    public void assertSearchInputTextEquals(String text) {
        this.assertElementHasText(SEARCH_INIT_ELEMENT_TEXT, text, "We see unexpected text in input");
    }
}
