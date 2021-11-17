import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSingleton {
    private static ChromeDriver driver;

    private DriverSingleton() {}

    public static ChromeDriver getInstance() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "/Users/batman/Downloads/chromedriver");
            driver = new ChromeDriver();
        }
        return driver;
    }
}
