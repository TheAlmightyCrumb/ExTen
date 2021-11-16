import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Main {
    private static ChromeDriver driver;

    @BeforeClass
    public static void initialise() {
        System.setProperty("webdriver.chrome.driver", "/Users/batman/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void waitsTest() {
        /* Using implicit wait */
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://dgotlieb.github.io/Selenium/synchronization.html");
//        driver.findElementByCssSelector("button#btn").click();
//        WebElement hiddenText = driver.findElement(By.cssSelector("p#message"));
//        System.out.println(hiddenText.getText());

        /* Usin thread.sleep */
        try {
            driver.findElement(By.cssSelector("button#hidden")).click();
            Thread.sleep(4000);
            WebElement hiddenElement = driver.findElement(By.cssSelector("div#finish1"));
            System.out.println(hiddenElement.getText());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
