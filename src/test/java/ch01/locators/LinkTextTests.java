package ch01.locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;


public class LinkTextTests {
	/*
	You can locate a link either by its text or by partial text.
	Locating links with partial text comes in handy when links have dynamic text.
	 */

	WebDriver driver;

	@BeforeClass
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}
	@Before
	public void setup(){
		// Launch Chrome
		driver = new ChromeDriver();
		// Maximize the browser window
		driver.manage().window().maximize();
		// Navigate to
		driver.get("http://cookbook.seleniumacademy.com/Locators.html");
	}
	/*
	Selenium WebDriver's By class provides the linkText() method to locate links
	using the text displayed for the link.
	 */
	@Test
	public void testLinkText(){
		WebElement gMailLink = driver.findElement(By.linkText("GMail"));
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

	@Test
	public void testPartialLinkText() {
		WebElement inboxLink = driver.findElement(By.partialLinkText("Inbox"));
		System.out.println(inboxLink.getText());
		assertEquals("http://mail.google.com/",
				inboxLink.getAttribute("href"));
	}
	@After
	public void teardown() {
		driver.quit();
	}
}
	/*
	This partialLinkText Locator, is useful where developers create links with dynamic text.
	In this example, a link is provided to open inbox.
	This link also displays the number of new e-mails which may change dynamically.
	Here we can use the partialLinkText() method to locate the link
	using a fixed or known portion of the link text, in this case it would be inbox.
	 */