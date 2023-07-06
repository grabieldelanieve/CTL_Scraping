package delanieve.pages;

import delanieve.base.TestBase;
import delanieve.utilities.Waits;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends TestBase {
    @FindBy(css = "input#nombre")
    WebElement nombre;

    @FindBy(xpath = "//input[@type='button' and @name='fh']")
    WebElement fecha;

    @FindBy(xpath = "//select[@name='Pais' and @id='Pais']")
    WebElement selectCountry;

    @FindBy(xpath = "//select[@name='n1' and @id='n1']")
    WebElement stateSelect;

    @FindBy(id = "select-ciudad")
    WebElement citySelect;

    @FindBy(xpath = "//a[contains(.,'Villa Altagracia')]")
    WebElement cityOption;

    @FindBy(xpath = "//input[@value='Aceptar']")
    WebElement aceptar;

    @FindBy(xpath = "//p[@class='text-right']")
    WebElement ancla;

    WebDriver driver;
    private Waits waits;

    public HomePage(WebDriver driver) {
        super();
        this.driver = driver;
        waits = new Waits(driver);
        PageFactory.initElements(driver, this);
    }

    public void typeName() {
        scroll(ancla);
        waits.forElement(nombre);
        nombre.sendKeys("Grabiel");
    }

    public void setSelectCountry() {
        waits.forElement(selectCountry);
        Select countryOption = new Select(selectCountry);
        countryOption.selectByVisibleText("República Dominicana");
    }

    public void selectStates() {
        waits.forElement(stateSelect);
        Select stateOption = new Select(stateSelect);
        stateOption.selectByVisibleText("San Cristóbal");
    }

    public void setDate() {
        waits.forElement(fecha);
        fecha.click();
    }

    public void selectCity() throws InterruptedException {
        Thread.sleep(2000);
//        waits.forElement(citySelect);
        citySelect.sendKeys("Villa Altagracia");
//        waits.forElement(cityOption);
        cityOption.click();
    }

    public CartaNatalPage submitForm() {
//        waits.forElement(aceptar);
        aceptar.click();
        return new CartaNatalPage(driver);
    }

    public void scroll(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        Coordinates coordinate =
                ((Locatable) element).getCoordinates();
        coordinate.onPage();
        coordinate.inViewPort();
    }

    public CartaNatalPage goToCartaNatalPage() {
        return new CartaNatalPage(driver);
    }

    public void goTo() {
        driver.get("https://carta-natal.es/carta.php");
    }

    public void waitForWebElementToAppear(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }
}
