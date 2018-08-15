package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

abstract public class SearchPageObject extends MainPageObject{

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL ,
            SEARCH_RESULT_ELEMENTS,
            SEARCH_EMPTY_RESULT_LABEL,
            SEARCH_BOX_DEFAULT_TEXT,
            SEARCH_CLOSE_BUTTON;

    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

    /* Template Methods */
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* Template Methods */

    public void initSearchInput(){
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot Find Search Wikipedia input", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Can't find search input after clicking Search Init element", 5);
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Can't find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON,"Search cancel button is still on screen", 5);
    }

    public void cancelSearchClick(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Can't find and click serach cancel button", 5);
    }

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Can't input search query", 5);
    }

    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath,"Can't find search result with substring " + substring);
    }

    public void clickArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath,"Can't find and click search result with substring " + substring, 10);
    }

    public int getNumberOfSearchResults(){
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENTS,
                "No search results found for ",
                15
        );

        return this.getAmountOfElements(SEARCH_RESULT_ELEMENTS);
    }

    public void waitForEmptyResultsLabel(){

        this.waitForElementPresent(SEARCH_EMPTY_RESULT_LABEL, "Can't find empty result element", 15);
    }

    public void assertThereIsNoSearchResults(){

        this.assertElementNotPresent(SEARCH_RESULT_ELEMENTS, "Some search results found when it shouldn't be present");
    }

    public WebElement waitForDefaultSearchTextBox(){
       return this.waitForElementPresent(SEARCH_BOX_DEFAULT_TEXT, "Can't find default Search Box Text", 15);
    }

    public String getDefaultSearchText(){
        WebElement default_search_text = waitForDefaultSearchTextBox();
        return default_search_text.getAttribute("text");
    }

    public void cancelEmptySearch(){
        this.waitForElementAndClick(SEARCH_CLOSE_BUTTON, "Can't Find X/Cancel search button", 5);
    }

    public void waitForCancelSearchButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_CLOSE_BUTTON, "x/Cancel search button is still on screen", 15);
    }
}
