package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveArticleToMyList(){

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();

        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning Programming";
        ArticlePageObject.waitForMenuInit(true);
        ArticlePageObject.addArticleTitleToNewList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesToMyListAndDeleteOne(){

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();

        String first_article_title = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Unexpected Article Title",
                "Java (programming language)",
                first_article_title
        );

        String name_of_folder = "Learning Programming";
        ArticlePageObject.articleOptionsMenuClick();
        ArticlePageObject.waitForMenuInit(true);
        ArticlePageObject.addArticleTitleToNewList(name_of_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickArticleWithSubstring("Java version history");

        String second_article_title = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Unexpected Title",
                "Java version history",
                second_article_title
        );

        ArticlePageObject.articleOptionsMenuClick();
        ArticlePageObject.waitForMenuInit(false);
        ArticlePageObject.addArticleTitleToExistingList();
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeArticleToDelete(first_article_title);
        MyListsPageObject.waitForArticleByTitleAndClick("Java version history");

        String remaining_article_title_in_my_lists = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Unexpected Title",
                "Java version history",
                remaining_article_title_in_my_lists
        );
    }
}
