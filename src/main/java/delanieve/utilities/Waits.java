package delanieve.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class Waits {

    private WebDriver driver;

    public Waits(WebDriver driver) {
        super();
        this.driver = driver;
    }

    public void forElement(WebElement element) {
        Duration waitTime = Duration.ofSeconds(10);
        final WebDriverWait wait = new WebDriverWait(driver, waitTime);
        try{
            wait.until(visibilityOf(element));
        } catch (Exception e){
            System.out.println("Wait time was superior of " + waitTime);
        }
    }
}
