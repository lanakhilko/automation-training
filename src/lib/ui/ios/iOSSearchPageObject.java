package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "id:Cancel";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains (@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENTS = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_LABEL = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_BOX_DEFAULT_TEXT = "//XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_CLOSE_BUTTON = "id:Cancel";
    }

    public iOSSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
