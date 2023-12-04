package ch01.locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Contains_Text {
    /*
    While testing a web application,
    we need to verify that elements are displaying correct values or text on the page.
     */
    /*
   locating elements based on their text contents.
   This approach comes in handy when elements don't have enough attributes
   or when no other strategies work when attempting to locate these elements.
    */
    WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    /*
    Here we are using the contains function along with the text() function.
    The text() function returns the complete text from the element
    and the contains() function checks for the specific value that we have mentioned.
     */
    @Test
    public void testTextLocator() {
        driver.get(
                "http://cookbook.seleniumacademy.com/Locators.html");
        WebElement cell = driver.findElement(By.xpath("//td[contains(text(),'Item 1')]"));
        System.out.println(cell.getText());
    }
    /*

    //td[text()='Item 1']
      we can use  . instead of  text()
    //td[.='Item 1']

    contains requires (string, string)
    //td[contains(.,'Item 1')]
     */

    @Test
    public void testLinkText(){
        driver.get("http://cookbook.seleniumacademy.com/Locators.html");

        //WebElement gMailLink = driver.findElement(By.linkText("GMail"));
        WebElement gMailLink = driver.findElement(By.xpath("//a[text()='GMail']"));
        //assertj-core dependency for assertThat
        assertThat(gMailLink.getTagName()).isEqualTo("a");
        System.out.println(gMailLink.getText());
        assertEquals("http://mail.google.com/",
                gMailLink.getAttribute("href"));
    }
	/*
	//a[text()='GMail']
	//a[.='GMail']
	//a[contains(.,'GMail')]
	//a[contains(text(),'GMail')]
	 */
    //td:contains('Nash') //td[contains(text(),'Nash')]
    //td[text()='Nash']
    @Test
    public void test4() {
        driver.get(
                "http://cookbook.seleniumacademy.com/Locators.html");

        //First, find the td containing Nash's name
        WebElement nashRow = driver.findElement(By.xpath("//td[text()='Nash']"));

        //Find the checkbox in Nash's row that corresponds to 'Admin' access
        WebElement adminCheckbox = nashRow.findElement(By.xpath("following-sibling::td//input[@id='user128_admin']"));
        /*
        What you're trying to do above is
        narrow down the scope to find another WebElement within a WebElement.

        we can re-write it without scope narrowing:
        WebElement adminCheckbox = driver.findElement(By.xpath("//td[text()='Nash']/ancestor::tr//input[@id='user128_admin']"));
        */
        //Perform a checkbox check
        boolean isAdminChecked = adminCheckbox.isSelected();

        //Test verification
        assertTrue("Nash should have admin access", isAdminChecked);
    }
    @Test
    public void test5_css() {
        driver.get(
                "http://cookbook.seleniumacademy.com/Locators.html");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement nashRow = (WebElement) js.executeScript("return jQuery('td:contains(\"Nash\")')[0];");

       // TODO: There is an explanation below for this :contains
        //WebElement nashRow = driver.findElement(By.cssSelector("td:contains('Nash')"));


        WebElement adminCheckbox = nashRow.findElement(By.xpath("following-sibling::td//input[@id='user128_admin']"));
        /*
        What you're trying to do above is
        narrow down the scope to find another WebElement within a WebElement.

        we can re-write it without scope narrowing:
        WebElement adminCheckbox = driver.findElement(By.xpath("//td[text()='Nash']/ancestor::tr//input[@id='user128_admin']"));
        */
        //Perform a checkbox check
        boolean isAdminChecked = adminCheckbox.isSelected();

        //Test verification
        assertTrue("Nash should have admin access", isAdminChecked);
    }
    private void injectjQueryIfNeeded() {
        if (!jQueryLoaded())
            injectjQuery();
    }

    public Boolean jQueryLoaded() {
        Boolean loaded;
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            loaded = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery()!=null");
        } catch (WebDriverException e) {
            loaded = false;
        }
        return loaded;
    }

    public void injectjQuery() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        ((JavascriptExecutor) driver).executeScript(" var headID = document.getElementsByTagName('head')[0];"
                + "var newScript = document.createElement('script');"
                + "newScript.type = 'text/javascript';"
                + "newScript.src = 'http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js';"
                + "headID.appendChild(newScript);");
    }
    @After
    public void teardown(){
        driver.quit();
    }
    /*
    for the line :        WebElement nashRow = driver.findElement(By.cssSelector("td:contains('Nash')"));
        there is a special situation:
        here are standard CSS selectors, and :contains is not one of these standard selectors.

    Standard CSS selectors are defined by the CSS3 specification and are supported by all modern web browsers.
    These include element selectors, class selectors, ID selectors, attribute selectors,
    and pseudo-classes like :checked, :first-child, :hover, etc.

    On the other hand, the :contains selector is not part of the CSS specification.
    It was initially included in the CSS3 draft but was later removed.
    Today, the :contains selector is predominantly used in the jQuery library to select elements
    containing specific text content. Therefore,
    it falls outside the scope of Selenium WebDriver's built-in CSS selector capabilities
    and cannot be used directly with Selenium.

    This means if you want to use the :contains selector in your Selenium WebDriver tests,
    you would need to inject jQuery into the page
    or use the JavaScript Executor to achieve similar functionality.

     */

}
