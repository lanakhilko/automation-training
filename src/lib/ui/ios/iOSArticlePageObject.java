package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {

    static {

        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Search";
        RETURN_TO_EXPLORE_BUTTON = "id:Wikipedia, return to Explore";
        SAVED_ARTICLES_EDUCATIONAL_OVERLAY_DISMISS_BUTTON = "xpath://XCUIElementTypeButton[@name='Close']";
    }

    public iOSArticlePageObject(AppiumDriver driver){

        super(driver);
    }
}
