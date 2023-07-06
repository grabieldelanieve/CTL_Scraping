package delanieve.base;

import delanieve.pages.HomePage;
import delanieve.utilities.SeleniumActions;
import delanieve.utilities.WebDriverListeners;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.log4testng.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {
    protected static WebDriver driver;
    protected static Properties properties;
    protected static SeleniumActions sele_Actions;
    protected static WebDriverListener eventListeners;
    protected static ChromeOptions chromeOptions;
    protected static Logger log;

    HomePage homePage;

    public TestBase() {

        try {
            properties = new Properties();
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir")
                            + "\\src\\main\\java\\delanieve\\config\\GlobalData.properties");
            properties.load(ip);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("io exception");
        }

    }

    protected static void initializaton() {
        String browserName = properties.getProperty("browser");
        driver = getDriver(browserName);
//        log.info(browserName + " is configured");

        eventListeners = new WebDriverListeners();
        driver = new EventFiringDecorator<WebDriver>(eventListeners).decorate(driver);

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));

        driver.get(properties.getProperty("url"));

        sele_Actions = new SeleniumActions();

    }

    private static WebDriver getDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            return new ChromeDriver(chromeOptions);
        } else if (browserName.equalsIgnoreCase("FF")) {
            System.setProperty("webdriver.gecko.driver", "");
            return new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("IE")) {
            System.setProperty("webdriver.edge.driver", "edge.exe");
            return new EdgeDriver();
        }
        return null;
    }


    public void tearDownMain() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
