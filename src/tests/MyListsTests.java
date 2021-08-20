package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;

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

        ArticlePageObject.addArticleToNewList(name_of_folder);
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

        //Adding first article to list

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(article_title_for_save);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToNewList(name_of_folder);
        ArticlePageObject.closeArticle();

        //Adding second article to list

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(article_title_for_delete);

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToList(name_of_folder);
        ArticlePageObject.closeArticle();

        //Deleting article from list

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title_for_delete);

        int amount_of_articles = MyListsPageObject.getAmountOfArticlesInList();

        assertEquals("We see unexpected count of article after deleting", 1, amount_of_articles);

        MyListsPageObject.openArticleFromList();

        String article_title = ArticlePageObject.getArticleTitle();

        assertEquals("We see unexpected title", article_title_for_save, article_title);
    }
}
