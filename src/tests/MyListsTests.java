package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();

        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";

        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesToMyList() {
        String search_line = "Java";
        String article_title_for_save = "Java (programming language)";
        String article_title_for_delete = "JavaScript";
        String name_of_folder = "Learning programming";

        MainPageObject MainPageObject = new MainPageObject(driver);

        MainPageObject.searchPhraseAndOpenArticleWithWait(search_line, article_title_for_save);
        MainPageObject.saveArticleToTheNewList(name_of_folder);
        MainPageObject.closeArticle();
        MainPageObject.searchPhraseAndOpenArticleWithWait(search_line, article_title_for_delete);
        MainPageObject.saveArticleToTheList(name_of_folder);
        MainPageObject.closeArticle();

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc = 'My lists']"),
                "Cannot find navigation button to My lists",
                5
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/item_container"),
                "Cannot find list",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text ='" + name_of_folder + "']"),
                "Cannot open list",
                5
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Cannot find article in list",
                15
        );

        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + article_title_for_delete + "']"),
                "Cannot find saved article"
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='" + article_title_for_delete + "']"),
                "Cannot delete article",
                5
        );

        int amount_of_articles = MainPageObject.getAmountOfElements(
                By.xpath("//*[@resource-id='org.wikipedia:id/reading_list_contents']//*[@resource-id='org.wikipedia:id/page_list_item_container']")
        );

        assertEquals(
                "We see unexpected count of article after deleting",
                1,
                amount_of_articles
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']"),
                "Cannot find saved article",
                5
        );

        WebElement title_element = MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        String article_title = title_element.getAttribute("text");

        assertEquals(
                "We see unexpected title",
                article_title_for_save,
                article_title
        );
    }
}
