package delanieve.pageobjects;

import delanieve.abstractComponents.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.*;
import java.util.stream.Collectors;

public class CartaNatalPage extends BaseComponent {
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

    public CartaNatalPage(WebDriver driver) {
        super(driver);
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
//
    }

    public void getData() {
        Map<String, List<String>> table1 = new HashMap<String, List<String>>();
        Map<String, List<String>> table2 = new HashMap<String, List<String>>();

        ArrayList<List<String>> tabla1HashMap = new ArrayList<>();
        ArrayList<List<String>> tabla2HashMap = new ArrayList<>();

        // Displaying data from command line
        while (counter <= 12) {
            if (counter <= 9) {
                tabla1HashMap.add(Arrays.asList(signos.get(counter), coordenadas.get(counter), casas.get(counter)));
                table1.put(plan.get(counter), tabla1HashMap.get(counter));
            }
            if (counter <= 11) {
                tabla2HashMap.add(Arrays.asList(tablaPlacidusSignos.get(counter), tablaPlacidusCoordenadas.get(counter)));
                table2.put(tablaPlacidusCasas.get(counter), tabla2HashMap.get(counter));
            }
            // HashMap table #3 - Pending to code...
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
    }
}
