package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {

    static{
        ARTICLE_BY_TITLE_TPL ="xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
        TABLE_OF_CONTENTS_MENU_BUTTON ="id:Table of contents";
        ARTICLE_SAVED_ACTIVE_BUTTON = "id:Saved. Activate to unsave.";
        JAVA_VERSION_ARTICLE_CONTENTS = "id:Java version history";
    }
    public iOSMyListsPageObject(AppiumDriver driver){

        super(driver);
    }
}
