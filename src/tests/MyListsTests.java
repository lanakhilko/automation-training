package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String name_of_folder = "Learning Programming";

    @Test
    public void testSaveArticleToMyList(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        ArticlePageObject.waitForTitleElement();

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.articleOptionsMenuClick();
            ArticlePageObject.waitForMenuInit(true);
            ArticlePageObject.addArticleTitleToNewListAndroid(name_of_folder);
        } else{
            ArticlePageObject.addArticleToMySavedIOS();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        if(Platform.getInstance().isIOS()){
            ArticlePageObject.dismissSavedArticlesFirstTimeUserOverlay();
        }

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        MyListsPageObject.swipeArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesToMyListAndDeleteOne() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();

        String first_article_title = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Unexpected First Article Title",
                "Java (programming language)",
                first_article_title
        );

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.articleOptionsMenuClick();
            ArticlePageObject.waitForMenuInit(true);
            ArticlePageObject.addArticleTitleToNewListAndroid(name_of_folder);
        } else{
            ArticlePageObject.addArticleToMySavedIOS();
        }

        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickArticleWithSubstring("Java version history");
        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.waitForTitleElement();

            String second_article_title = ArticlePageObject.getArticleTitle();
            assertEquals(
                    "Unexpected Second Title",
                    "Java version history",
                    second_article_title
            );
        }

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.articleOptionsMenuClick();
            ArticlePageObject.waitForMenuInit(false);
            ArticlePageObject.addArticleTitleToExistingList();
        } else{
            ArticlePageObject.addArticleToMySavedIOS();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        if(Platform.getInstance().isIOS()){
            ArticlePageObject.dismissSavedArticlesFirstTimeUserOverlay();
        }

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        MyListsPageObject.swipeArticleToDelete(first_article_title);


        if(Platform.getInstance().isAndroid()) {
            MyListsPageObject.waitForArticleByTitleAndClick("Java version history");
            String remaining_article_title_in_my_lists = ArticlePageObject.getArticleTitle();
            assertEquals(
                    "Unexpected Title",
                    "Java version history",
                    remaining_article_title_in_my_lists
            );
        } else{
            MyListsPageObject.waitForArticleByTitleAndClick("Java version history");
            MyListsPageObject.articleSavedIndicatorIsOnScreen();
            MyListsPageObject.javaVersionsHistoryTableOfContentsVerification();
        }
    }
}