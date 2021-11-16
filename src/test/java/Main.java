import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Main {
    private static ChromeDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void initialise() {
        System.setProperty("webdriver.chrome.driver", "/Users/batman/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    /* Ex. 1 */
    @Test
    public void waitsTest() {
        /* Using implicit wait */
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://dgotlieb.github.io/Selenium/synchronization.html");
        driver.findElementByCssSelector("button#btn").click();
        WebElement hiddenText = driver.findElement(By.cssSelector("p#message"));
        System.out.println(hiddenText.getText());

        /* Using thread.sleep */
        try {
            driver.findElement(By.cssSelector("button#hidden")).click();
            Thread.sleep(4000);
            WebElement hiddenElement = driver.findElement(By.cssSelector("div#finish1"));
            System.out.println(hiddenElement.getText());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /* Using explicit wait */
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(By.cssSelector("button#rendered")).click();
        WebElement afterRenderClick =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish2")));
        System.out.println(afterRenderClick.getText());
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
