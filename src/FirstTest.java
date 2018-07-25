import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FirstTest extends CoreTestCase {

    //Delete after refactoring
    private MainPageObject MainPageObject;

    protected void setUp() throws Exception{
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }
    //Delete after refactoring till here


    @Test
    public void testDefaultSearchPresence(){
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot Find Search Wikipedia input",
                5
        );

        WebElement searchBox_element = MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Can't find Search Box Default Text Block",
                15
        );

        String default_search = searchBox_element.getAttribute("text");

        assertEquals(
                "Unexpected Default Text in the Search Box",
                "Search…",
                default_search
        );

    }

    @Test
    public void testSearchArticlesAndCancel() {

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "android",
                "Can't find search input",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@index=0]"),
                "No First Search Result Found",
                15
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@index=1]"),
                "No Second Search Result Found",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can't find Cancel Search button",
                5
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@index=0]"),
                "At least one search result is still on screen",
                15
        );

    }

    @Test
    public void testSearchRelevance() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "android",
                "Can't find search input",
                5
        );

        WebElement search_results = MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Can't find Search Results Title",
                15
        );

        String search_text = search_results.getAttribute("text");

        //String search_term = "Android";
        //if (search_text.contains(search_term)) {
        //    System.out.println("'Android' found in title");
       // } else {
        //    System.out.println("Can't find " + search_term);
        //}

        assertEquals(
               "'Android' is not present in search results Title",
               "Android",
                search_text
        );
    }


    @Test
    public void testSaveTwoArticlesToMyListAndDeleteOne(){
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot Find Search Wikipedia input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Search Input not found",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot Find Java Article in first search attempt",
                5
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find 'Object-oriented programming language' article Title",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Article hamburger menu button not found",
                5
        );

        MainPageObject.waitForMenuInit(true);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[contains(@text,'Add to reading list')]"),
                "Can't find 'Add to reading list' option",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Can't find 'Got it' tip overlay",
                5
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Can't find text input",
                5
        );

        String name_of_folder = "Learning Programming";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Can't type text into input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Can't press 'OK' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Dismiss button not found",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot Find Search Wikipedia input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Search Input not found",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java version history']"),
                "Cannot Find 'Java version history' Article",
                5
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find Second article Title",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Article hamburger menu button not found",
                5
        );

        MainPageObject.waitForMenuInit(false);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[contains(@text,'Add to reading list')]"),
                "Can't find 'Add to reading list' option",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Learning Programming']"),
                "Can't find newly created 'Learning Programming' list in options available",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Dismiss button not found",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "'My List' button not found",
                5
        );


        String saved_lists_locator = "//*[@resource-id='org.wikipedia:id/item_container']//*[@text='Learning Programming']";
        MainPageObject.waitForElementAndClick(
                By.xpath(saved_lists_locator),
                "'Learning Programming' list not found",
                5
        );

        MainPageObject.swipeElementLeft(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Can't find 'Java (programming language)' saved article"
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Can not Delete 'Java (programming language)' article",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Java version history']"),
                "Can't find 'Java version history article'",
                5
        );

        WebElement title_element = MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find article Title",
                15
        );

        String article_title = title_element.getAttribute("text");

        assertEquals(
                "Unexpected Title",
                "Java version history",
                article_title
        );
    }

    @Test
    public void testAssertTitle(){
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot Find Search Wikipedia input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Search Input not found",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot Find Java Article",
                5
        );

        MainPageObject.assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Article Title Not found "
        );
    }
}
