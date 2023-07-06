package delanieve.utilities;

import delanieve.base.TestBase;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Sequence;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class WebDriverListeners extends TestBase implements org.openqa.selenium.support.events.WebDriverListener {


    public void beforeAlertAccept(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public void afterAlertAccept(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public void afterAlertDismiss(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public void beforeAlertDismiss(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public void beforeNavigateTo(String url, WebDriver driver) {
        System.out.println("Navigating to url "+ url);
        // TODO Auto-generated method stub

    }

    public void afterNavigateTo(String url, WebDriver driver) {
        System.out.println("Navigated to url "+url);
        // TODO Auto-generated method stub

    }

    public void beforeNavigateBack(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public void afterNavigateBack(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public void beforeNavigateForward(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public void afterNavigateForward(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public void beforeNavigateRefresh(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public void afterNavigateRefresh(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public void beforeClickOn(WebElement element, WebDriver driver) {
        // TODO Auto-generated method stub
        System.out.println("Trying to click on "+element.toString());

    }

    public void afterClickOn(WebElement element, WebDriver driver) {
        // TODO Auto-generated method stub
        System.out.println("Clicked on "+element.toString());

    }

    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        // TODO Auto-generated method stub

    }

    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        // TODO Auto-generated method stub

    }

    public void beforeScript(String script, WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public void afterScript(String script, WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public void beforeSwitchToWindow(String windowName, WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public void afterSwitchToWindow(String windowName, WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public void onException(Throwable throwable, WebDriver driver) {
        // TODO Auto-generated method stub
        System.out.println("Exception is thrown " +throwable.getMessage());
//        TestUtils.takeScreenShot(driver);

    }

    public <X> void beforeGetScreenshotAs(OutputType<X> target) {
        // TODO Auto-generated method stub

    }

    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
        // TODO Auto-generated method stub

    }

    public void beforeGetText(WebElement element, WebDriver driver) {
        // TODO Auto-generated method stub
        System.out.println("Trying to get text form " + element.toString());

    }

    public void afterGetText(WebElement element, WebDriver driver, String text) {
        // TODO Auto-generated method stub
        System.out.println("text from element "+element.toString()+ " is " +text );

    }
}
