package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.cancelSearchClick();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_line);

            int amount_of_search_results = SearchPageObject.getNumberOfSearchResults();
            assertTrue(
                    "We found too few results",
                    amount_of_search_results > 0
            );
    }

    @Test
    public void testAmountOfEmptySearch(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "fghfdhgsdsfg";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoSearchResults();
    }

    @Test
    public void testDefaultSearchPresence(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForDefaultSearchTextBox();
        String default_search = SearchPageObject.getDefaultSearchText();

        assertEquals(
                "Unexpected Default Text in the Search Box",
                "Search…",
                default_search
        );
    }

    @Test
    public void testClickSearchInputAndCancel() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.cancelEmptySearch();
        SearchPageObject.waitForCancelSearchButtonToDisappear();
    }

    @Test
    //This test is same as modified in the previous lesson "testSearch". I would delete this test as a duplicate.
    public void testSearchRelevance() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "android";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult("Android");
    }


}
