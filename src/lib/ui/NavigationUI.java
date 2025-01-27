package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject{

    protected static  String
    MY_LISTS_LINK;

    public NavigationUI(AppiumDriver driver){

        super(driver);
    }

    public void clickMyLists(){

       this.waitForElementAndClick(
                MY_LISTS_LINK,
                "My Lists Entry Point Not found",
                5
        );
    }
}
