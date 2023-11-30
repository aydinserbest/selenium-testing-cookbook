package ch01.locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class ActionsPerform {
    WebDriver driver;
    @BeforeClass
    public static void setupClass(){
        WebDriverManager.chromedriver().setup();
    }
    @Before
    public void setup(){driver = new ChromeDriver();
    }
    @Test
    public void testHover(){
        driver.get(
                "https://the-internet.herokuapp.com/jqueryui/menu");
        WebElement enabledMenuOption = driver.findElement(By.cssSelector("li#ui-id-3"));
        Actions actions = new Actions(driver);
        actions.moveToElement(enabledMenuOption).perform();

        WebElement backToButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li#ui-id-8")));
        backToButton.click();
        WebElement body = driver.findElement(By.tagName("body"));
        String bodyText = body.getText();
        System.out.println(bodyText);
        assertThat(bodyText).contains("Widgets");
    }
    @After
    public void teardown(){
        driver.quit();
    }
}
