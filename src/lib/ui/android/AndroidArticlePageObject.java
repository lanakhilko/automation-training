package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
            TITLE = "id:org.wikipedia:id/view_page_title_text";
            FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
            OPTIONS_BUTTON = "xpath:/android.widget.ImageView[@content-desc='More options']";
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://android.widget.TextView[@text='Add to reading list']";
            ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
            MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
            CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
            ARTICLE_OPTIONS_MENU_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
            LEARNING_PROGRAMMING_LIST = "xpath://*[@resource-id='org.wikipedia:id/item_container']//*[@text='Learning Programming']";
            ARTICLE_CHANGE_LANGUAGE_OPTION = "xpath://*[@text='Change language']";
            ARTICLE_SHARE_LINK_OPTION = "xpath://*[@text='Share link']";
            ARTICLE_ADD_TO_READING_LIST_OPTION = "xpath://*[@text='Add to reading list']";
            ARTICLE_FIND_IN_PAGE_OPTION = "xpath://*[@text='Find in page']";
            ARTICLE_SIMILAR_PAGES_OPTION= "xpath://*[@text='Similar pages']";
            ARTICLE_FONT_AND_THEME_OPTION= "xpath://*[@text='Font and theme']";
    }

    public AndroidArticlePageObject(AppiumDriver driver){

    super(driver);
    }

}
