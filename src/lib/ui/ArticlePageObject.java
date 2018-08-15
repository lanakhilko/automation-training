package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {

   protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            ARTICLE_OPTIONS_MENU_BUTTON,
            LEARNING_PROGRAMMING_LIST,
            ARTICLE_CHANGE_LANGUAGE_OPTION,
            ARTICLE_SHARE_LINK_OPTION,
            ARTICLE_ADD_TO_READING_LIST_OPTION,
            ARTICLE_FIND_IN_PAGE_OPTION,
            ARTICLE_SIMILAR_PAGES_OPTION,
            RETURN_TO_EXPLORE_BUTTON,
            ARTICLE_FONT_AND_THEME_OPTION,
            SAVED_ARTICLES_EDUCATIONAL_OVERLAY_DISMISS_BUTTON;


    public ArticlePageObject(AppiumDriver driver){

        super(driver);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(TITLE, "Can't find article title on page", 15);
    }

    public String getArticleTitle(){
        WebElement title_element = waitForTitleElement();
        if(Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else{
            return title_element.getAttribute("name");
        }
    }

    public void swipeToFooter(){
        if (Platform.getInstance().isAndroid()){
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Can't find the end of the article",
                40
        );
        } else {
            this.swipeUpTillElementAppears(
                    FOOTER_ELEMENT,
                    "Can't find the end of the article",
                    40
            );
        }
    }

    public void addArticleTitleToNewListAndroid(String name_of_folder){

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Can't find 'Add to reading list' option",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Can't find 'Got it' tip overlay",
                5
        );

       this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Can't find text input",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Can't type text into input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Can't press 'OK' button",
                5
        );
    }

    public void addArticleTitleToExistingList(){

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Can't find 'Add to reading list' option",
                5
        );

        this.waitForElementAndClick(
                LEARNING_PROGRAMMING_LIST,
                "Can't find Learning programming list",
                5
        );
    }

    public void addArticleToMySavedIOS(){

        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Can't find add to Saved articles button", 5);
    }

    public void closeArticle(){
        if(Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Android Dismiss button not found",
                    5
            );
        } else{
            this.waitForElementPresent(RETURN_TO_EXPLORE_BUTTON, "Return to explore button not found", 10);
            this.waitForElementAndClick(
                    RETURN_TO_EXPLORE_BUTTON,
                    "iOS Return to Explore button not found",
                    15
            );

        }
    }

    public void articleOptionsMenuClick() {
        this.waitForElementAndClick(
                ARTICLE_OPTIONS_MENU_BUTTON,
                "Can't find and click Article options hamburger menu",
                5
        );
    }

    public void waitForMenuInit(boolean is_option_available) {

        waitForElementPresent(
                ARTICLE_CHANGE_LANGUAGE_OPTION,
                "Cannot find Change language option",
                5
        );

        waitForElementPresent(
                ARTICLE_SHARE_LINK_OPTION,
                "Cannot find Share link option",
                5
        );

        waitForElementPresent(
                ARTICLE_ADD_TO_READING_LIST_OPTION,
                "Cannot find Add to reading list option",
                5
        );

        waitForElementPresent(
                ARTICLE_FIND_IN_PAGE_OPTION,
                "Cannot find Find in page option",
                5
        );

        if (is_option_available){

            waitForElementPresent(
                    ARTICLE_SIMILAR_PAGES_OPTION,
                    "Cannot find Similar pages option",
                    5
            ); }

            waitForElementPresent(
                    ARTICLE_FONT_AND_THEME_OPTION,
                    "Cannot find Font and theme option",
                    5
            );
    }

    public void dismissSavedArticlesFirstTimeUserOverlay() {

        this.waitForElementAndClick(
                SAVED_ARTICLES_EDUCATIONAL_OVERLAY_DISMISS_BUTTON,
                "Can't dismiss first time user educational overlay",
                5
        );
    }
}
