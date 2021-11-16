import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CalculatorPage {
    private WebDriver driver;

    public CalculatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public static String getPageUrl() {
        return "https://dgotlieb.github.io/WebCalculator/";
    }

    public String getElementDimensions(String cssSelector) {
        Rectangle elementDimensions =  driver.findElement(By.cssSelector(cssSelector)).getRect();
        return "\nHeight - " + elementDimensions.getHeight() + "px\n" + "Width - " + elementDimensions.getWidth() + "px";
    }

    public boolean isElementDisplayed(String cssSelector) {
        WebElement element = driver.findElement(By.cssSelector(cssSelector));
        return element.isDisplayed();
    }

    public int calculate(ArrayList<String> cssSelectors) {
        for (String selector: cssSelectors) {
            driver.findElement(By.cssSelector(selector)).click();
        }
        driver.findElement(By.cssSelector(Constants.EQUAL_BUTTON_CSS_LOCATOR)).click();
        String result = driver.findElement(By.cssSelector(Constants.RESULT_SCREEN_CSS_LOCATOR)).getText();
        return Integer.parseInt(result);
    }
}
