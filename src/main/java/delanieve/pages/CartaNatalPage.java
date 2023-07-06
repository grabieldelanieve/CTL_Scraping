package delanieve.pages;

import delanieve.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class CartaNatalPage extends TestBase {
    @FindBy(xpath = "//*[@value='Sinastría']")
    WebElement ancla;

    WebDriver driver;
    int counter = 0;
    // Tabla 1
    ArrayList<String> plan = new ArrayList<String>();
    ArrayList<String> coordenadas = new ArrayList<String>();
    ArrayList<String> casas = new ArrayList<String>();
    ArrayList<String> signos = new ArrayList<String>();
    // Tabla 2
    ArrayList<String> tablaPlacidusCasas = new ArrayList<String>();
    ArrayList<String> tablaPlacidusSignos = new ArrayList<String>();
    ArrayList<String> tablaPlacidusCoordenadas = new ArrayList<String>();
    // Tabla 3
    ArrayList<String> tabla3Planetas = new ArrayList<String>();
    ArrayList<String> tabla3Aspectos = new ArrayList<String>();
    ArrayList<String> tabla3Planetas2 = new ArrayList<String>();
    ArrayList<String> tabla3Orbes = new ArrayList<String>();

    public CartaNatalPage(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void tabla1Value(WebDriver driver) {

        List<WebElement> planetsElements = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
        List<WebElement> rows = driver.findElements(By.xpath("//tr/td/following-sibling::td"));
        List<WebElement> signs = driver.findElements(By.xpath("//tr/td/following-sibling::td/div/img"));

        List<String> tablas = rows.stream().map(s -> s.getText()).collect(Collectors.toList());
        List<String> planetas = planetsElements.stream().map(s -> s.getText()).collect(Collectors.toList());
        List<String> imgDomElements = signs.stream().map(s -> s.getDomProperty("title")).collect(Collectors.toList());

        // Planetas
        for (int i = 11; i <= 20; i++) {
            plan.add(planetas.get(i));
        }
        // Signos
        for (int i = 0; i < 10; i++) {
            signos.add(imgDomElements.get(i));
        }
        // Coordenadas
        for (int i = 53; i <= 72; i++) {
            coordenadas.add(tablas.get(i));
            i++;
            // Casas
            if (i >= 54) {
                casas.add(tablas.get(i));
            }
        }
    }

    public void tabla2Value(WebDriver driver) {

        List<WebElement> coordenadasObjects = driver.findElements(By.xpath("//tr/td/following-sibling::td"));
        List<String> coordenadasText = coordenadasObjects.stream().map(s -> s.getText()).collect(Collectors.toList());
        List<WebElement> casasObjects = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
        List<String> casasText = casasObjects.stream().map(s -> s.getText()).collect(Collectors.toList());
        List<WebElement> signosObjects = driver.findElements(By.xpath("//tr/td/following-sibling::td/div/img"));
        List<String> signosText = signosObjects.stream().map(s -> s.getDomProperty("title")).collect(Collectors.toList());

        for (int i = 21; i <= 32; i++) {
            tablaPlacidusCasas.add(casasText.get(i));
        }
        for (int i = 10; i <= 21; i++) {
            tablaPlacidusSignos.add(signosText.get(i));
        }
        for (int i = 73; i <= 84; i++) {
            tablaPlacidusCoordenadas.add(coordenadasText.get(i));
        }
    }

    public void tabla3Value(WebDriver driver) {

        List<WebElement> planetasObjects = driver.findElements(By.xpath("//tr/td[1]"));
        List<String> planetasText = planetasObjects.stream().map(s -> s.getText()).collect(Collectors.toList());
        List<WebElement> aspectosObjects = driver.findElements(By.xpath("//tr/td/following-sibling::td/div/img"));
        List<String> aspectosText = aspectosObjects.stream().map(s -> s.getDomProperty("title")).collect(Collectors.toList());
        List<WebElement> planetas2Objects = driver.findElements(By.xpath("//tr/td/following-sibling::td/div/img"));
        List<String> planetas2Text = planetas2Objects.stream().map(s -> s.getDomProperty("title")).collect(Collectors.toList());
        List<WebElement> orbesObjects = driver.findElements(By.cssSelector(".orbe"));
        List<String> orbesTexts = orbesObjects.stream().map(s -> s.getText()).collect(Collectors.toList());

        for (int i = 34; i <= planetasText.size() - 14; i++) {
            tabla3Planetas.add(planetasText.get(i));
        }
        for (int i = 22; i <= aspectosText.size() - 1; i++) {
            tabla3Aspectos.add(aspectosText.get(i));
            i++;
        }
        for (int i = 23; i <= planetas2Text.size() - 1; i++) {
            tabla3Planetas2.add(planetas2Text.get(i));
            i++;
        }
        for (int i = 0; i <= orbesTexts.size() - 1; i++) {
            tabla3Orbes.add(orbesTexts.get(i));
        }
    }

    public void getData() {
        scroll(ancla);

        tabla1Value(driver);
        tabla2Value(driver);
        tabla3Value(driver);

        Map<String, List<String>> table1 = new HashMap<>();
        Map<String, List<String>> table2 = new HashMap<>();
        Map<String, List<String>> table3 = new HashMap<>();

        ArrayList<List<String>> tabla1HashMap = new ArrayList<>();
        ArrayList<List<String>> tabla2HashMap = new ArrayList<>();
        ArrayList<List<String>> tabla3HashMap = new ArrayList<>();

        // Displaying data from command line
        while (counter <= tabla3Orbes.size()) {
            if (counter <= 9) {
                tabla1HashMap.add(Arrays.asList(signos.get(counter), coordenadas.get(counter), casas.get(counter)));
                table1.put(plan.get(counter), tabla1HashMap.get(counter));
            }
            if (counter <= 11) {
                tabla2HashMap.add(Arrays.asList(tablaPlacidusSignos.get(counter), tablaPlacidusCoordenadas.get(counter)));
                table2.put(tablaPlacidusCasas.get(counter), tabla2HashMap.get(counter));
            }
            if (counter <= tabla3Orbes.size() -1) {
                // HashMap table #3 - Pending to code...
                tabla3HashMap.add(Arrays.asList(tabla3Planetas.get(counter),tabla3Aspectos.get(counter), tabla3Planetas2.get(counter), tabla3Orbes.get(counter)));
                table3.put(Integer.toString(counter), tabla3HashMap.get(counter));
            }
            counter++;
        }
        for (Map.Entry<String, List<String>> entry : table1.entrySet()) {
            System.out.println(entry.getKey() + " ::: " + entry.getValue());
            // Setting data of table #1 - Pending to code...
        }
        for (Map.Entry<String, List<String>> entry2 : table2.entrySet()) {
            System.out.println(entry2.getKey() + " ::: " + entry2.getValue());
            // Setting data of table #2 - Pending to code...
        }
        for (Map.Entry<String, List<String>> entry3 : table3.entrySet()) {
            System.out.println(entry3.getKey() + " ::: " + entry3.getValue());
            // Setting data of table #3 - Pending to code...
        }
    }

    public void scroll(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        Coordinates coordinate =
                ((Locatable) element).getCoordinates();
        coordinate.onPage();
        coordinate.inViewPort();
    }

}
