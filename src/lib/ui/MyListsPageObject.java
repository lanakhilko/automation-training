package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            TABLE_OF_CONTENTS_MENU_BUTTON,
            ARTICLE_SAVED_ACTIVE_BUTTON,
            JAVA_VERSION_ARTICLE_CONTENTS;

    private static String getFolderXpathByName(String name_of_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title){
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public MyListsPageObject (AppiumDriver driver){

        super(driver);
    }

    public void openFolderByName(String name_of_folder){

        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Can't find folder by name " + name_of_folder,
                5
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title){

        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Deleted article is still on screen with title " + article_title,
                15
        );
    }

    public void waitForArticleToAppearByTitle(String article_title){

        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Can't find saved article by title " + article_title,
                15
        );
    }

    public void waitForArticleByTitleAndClick(String article_title){

        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick(
                article_xpath,
                "Can't find saved article by title " + article_title,
                15
        );
    }

    public void swipeArticleToDelete(String article_title){

        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementLeft(
                article_xpath,
                "Can't find saved article"
        );

        if (Platform.getInstance().isIOS()){

            this.clickDeleteElementInRightUpperCorner(article_xpath, "Can't find saved article to delete");
        }

        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void articleSavedIndicatorIsOnScreen(){

        waitForElementPresent(
                ARTICLE_SAVED_ACTIVE_BUTTON,
                "No indicator that current article is in the saved list found",
                5
        );
    }

    public void javaVersionsHistoryTableOfContentsVerification(){
        waitForElementAndClick(TABLE_OF_CONTENTS_MENU_BUTTON, "Can't find and click Table Of Contents button", 5);
        waitForElementPresent(
                JAVA_VERSION_ARTICLE_CONTENTS,
                "Can not find Java version history in article contents",
                5
        );
    }
}
