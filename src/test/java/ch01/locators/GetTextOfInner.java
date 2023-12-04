package ch01.locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class GetTextOfInner {
    WebDriver driver;
    String fileUrl;
    @BeforeClass
    public static void setupClass(){
        WebDriverManager.chromedriver().setup();
    }
    @Before
    public void setup(){
        driver = new ChromeDriver();
    }
    private String getFileUrl(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            return "file://" + file.getAbsolutePath();
        } else {
            throw new RuntimeException("File not found: " + fileName);
        }
    }
}
