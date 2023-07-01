package delanieve.pageobjects;

import delanieve.abstractComponents.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends BaseComponent {
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

    WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void typeName() {
        scroll(nombre);
        nombre.sendKeys("Grabiel");
    }

    public void setSelectCountry() {
        Select countryOption = new Select(selectCountry);
        countryOption.selectByVisibleText("República Dominicana");
    }

    public void selectStates() {
        Select stateOption = new Select(stateSelect);
        stateOption.selectByVisibleText("San Cristóbal");
    }

    public void setDate() {
        fecha.click();
    }

    public void selectCity() {
        citySelect.sendKeys("Villa Altagracia");
        cityOption.click();
    }

    public void submitForm() {
        aceptar.click();
    }

    public void scroll(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        Coordinates coordinate =
                ((Locatable)element).getCoordinates();
        coordinate.onPage();
        coordinate.inViewPort();
    }

    public CartaNatalPage goToCartaNatalPage() {
        return new CartaNatalPage(driver);
    }

    public void goTo() {
        driver.get("https://carta-natal.es/carta.php");
    }
}
