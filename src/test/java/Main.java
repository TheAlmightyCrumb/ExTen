import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
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
        driver.findElement(By.cssSelector("button#btn")).click();
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

    /* Ex. 2 */
    @Test
    public void angularTest() {
        NgWebDriver ngWebDriver = new NgWebDriver(driver);
        ngWebDriver.waitForAngularRequestsToFinish();
        driver.get("https://dgotlieb.github.io/AngularJS/main.html");
        WebElement firstNameInput = driver.findElement(ByAngular.model("firstName"));
        firstNameInput.clear();
        firstNameInput.sendKeys("Mate :D");
    }

    /* Ex. 3 */
    /*
    What PageLoadTimeOut is used for?
    --> Specifies the time interval in which web page needs to be loaded in a current browsing context.
        The default timeout 300,000 is imposed when a new session is created by WebDriver.
        If page load limits a given/default time frame, the script will be stopped by TimeoutException.
    */

    /* Ex. 4 */
    @Test
    public void efficiencyTechniquesTest() {
        driver.get(CalculatorPage.getPageUrl());
        CalculatorPage page = new CalculatorPage(driver);
        String sevenBtnDimensions = page.getElementDimensions(Constants.SEVEN_BUTTON_CSS_LOCATOR);
        System.out.println("Button '7' dimensions: " + sevenBtnDimensions);

        System.out.println("-----");

        boolean isSixDisplayed = page.isElementDisplayed(Constants.SIX_BUTTON_CSS_LOCATOR);
        System.out.println("Is button '6' displayed?" + "\n--> " + isSixDisplayed);

        int myResult = 68;
        ArrayList<String> calcClicks = new ArrayList<>();
        calcClicks.add(Constants.NINE_BUTTON_CSS_LOCATOR);
        calcClicks.add(Constants.MULTIPLY_BUTTON_CSS_LOCATOR);
        calcClicks.add(Constants.EIGHT_BUTTON_CSS_LOCATOR);
        calcClicks.add(Constants.MINUS_BUTTON_CSS_LOCATOR);
        calcClicks.add(Constants.FOUR_BUTTON_CSS_LOCATOR);
        int clicksResult = page.calculate(calcClicks);
        Assert.assertEquals(clicksResult, myResult);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
