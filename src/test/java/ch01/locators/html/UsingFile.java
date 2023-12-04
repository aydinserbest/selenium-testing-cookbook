package ch01.locators.html;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class UsingFile {
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

    @Test
    public void utilizeNearby() {
        driver.get(fileUrl);
        // ... Test codes
    }

    @Test
    public void useavaScript() {
        driver.get(fileUrl);
        // ... Test codes
    }
}
