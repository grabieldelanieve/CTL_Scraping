package delanieve.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

public class CartaNatal {


    ArrayList<String> pla = new ArrayList<String>();
    ArrayList<String> coordenadas = new ArrayList<String>();
    ArrayList<String> casas = new ArrayList<String>();
    ArrayList<String> signos = new ArrayList<String>();

    int counter = 0;

    @Test()
    public void test1() throws InterruptedException {

        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://carta-natal.es/carta.php");

        Thread.sleep(3000);

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,680)", "");

        driver.findElement(By.id("nombre")).sendKeys("Grabiel");

        driver.findElement(By.xpath("//input[@type='button' and @name='fh']")).click();

        WebElement select;

        select = driver.findElement(By.xpath("//select[@name='Pais' and @id='Pais']"));
        Select countryOption = new Select(select);
        countryOption.selectByVisibleText("República Dominicana");

        Thread.sleep(2000);

        select = driver.findElement(By.xpath("//select[@name='n1' and @id='n1']"));
        Select stateOption = new Select(select);
        stateOption.selectByVisibleText("San Cristóbal");

        Thread.sleep(2000);

        jse.executeScript("window.scrollBy(0,200)", "");

        driver.findElement(By.id("select-ciudad")).sendKeys("Villa Altagracia");
        driver.findElement(By.xpath("//a[contains(.,'Villa Altagracia')]")).click();


        driver.findElement(By.xpath("//input[@value='Aceptar']")).click();

        jse.executeScript("window.scrollBy(0,2000)", "");

        tabla1Value(driver);

        getData();

        driver.close();
    }

    public void tabla1Value(WebDriver driver){

        List<String> tablas;
        List<String> imgDomElements;
        List<String> planetas;

        List<WebElement> planetsElements = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
        List<WebElement> rows = driver.findElements(By.xpath("//tr/td/following-sibling::td"));
        List<WebElement> signs = driver.findElements(By.xpath("//tr/td/following-sibling::td/div/img"));

        planetas = planetsElements.stream().map(s -> s.getText()).collect(Collectors.toList());
        tablas = rows.stream().map(s -> s.getText()).collect(Collectors.toList());
        imgDomElements = signs.stream().map(s -> s.getDomProperty("title")).collect(Collectors.toList());


        // Planetas
        for (int i = 11; i <= 20; i++) {
            pla.add(planetas.get(i));
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

        for (int i=73; i<84; i++){

        }
    }

    public void getData() {
        Map<String, List<String>> table1 = new HashMap<String, List<String>>();
        ArrayList<List<String>> g = new ArrayList<>();

        // Displaying data from comand line
        while (coordenadas.size() > counter) {
            g.add(Arrays.asList(signos.get(counter), coordenadas.get(counter), casas.get(counter)));
            table1.put(pla.get(counter), g.get(counter));
            counter++;
        }
        for (Map.Entry<String, List<String>> entry : table1.entrySet()){
            System.out.println(entry.getKey() + " ::: " + entry.getValue());
        }
    }
}
