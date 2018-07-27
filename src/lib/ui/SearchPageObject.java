package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject{

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENTS = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULT_LABEL = "//android.widget.TextView[@text='No results found']",
            SEARCH_BOX_DEFAULT_TEXT = "org.wikipedia:id/search_src_text",
            SEARCH_X_BUTTON =  "org.wikipedia:id/search_close_btn";

    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

    /* Template Methods */
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* Template Methods */

    public void initSearchInput(){
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot Find Search Wikipedia input", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Can't find search input after clicking Search Init element", 5);
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Can't find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON),"Search cancel button is still on screen", 5);
    }

    public void cancelSearchClick(){
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Can't find and click serach cancel button", 5);
    }

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Can't input search query", 5);
    }

    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath),"Can't find search result with substring " + substring);
    }

    public void clickArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath),"Can't find and click search result with substring " + substring, 10);
    }

    public int getNumberOfSearchResults(){
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENTS),
                "No search results found for ",
                15
        );

        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENTS));
    }

    public void waitForEmptyResultsLabel(){

        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_LABEL), "Can't find empty result element", 15);
    }

    public void assertThereIsNoSearchResults(){

        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENTS), "Some search results found when it shouldn't be present");
    }

    public WebElement waitForDefaultSearchTextBox(){
       return this.waitForElementPresent(By.id(SEARCH_BOX_DEFAULT_TEXT), "Can't find default Search Box Text", 15);
    }

    public String getDefaultSearchText(){
        WebElement default_search_text = waitForDefaultSearchTextBox();
        return default_search_text.getAttribute("text");
    }

    public void cancelEmptySearch(){
        this.waitForElementAndClick(By.id(SEARCH_X_BUTTON), "Can't Find X/Cancel search button", 5);
    }

    public void waitForCancelSearchButtonToDisappear(){
        this.waitForElementNotPresent(By.xpath(SEARCH_X_BUTTON), "x/Cancel search button is still on screen", 15);
    }
}
