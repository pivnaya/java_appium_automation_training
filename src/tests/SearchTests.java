package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testCancelSearchResult() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue("Count of articles less than 2", amount_of_search_results > 1);

        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForSearchResultDisappear();
    }

    @Test
    public void testCompareSearchInputText() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.assertSearchInputTextEquals("Search Wikipedia");
    }

    @Test
    public void testCompareSearchResult() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        String search_line = "Java";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        int amount_of_search_results_with_value = SearchPageObject.getAmountOfFoundArticlesWithSubstringContains(search_line);

        assertEquals("Not all items contains search value", amount_of_search_results, amount_of_search_results_with_value);
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_line);

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue("We found too few results!",amount_of_search_results > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "hffjfjyfjf";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
}
