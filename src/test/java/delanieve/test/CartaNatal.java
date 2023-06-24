package delanieve.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CartaNatal {

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

        cartaNatalData(driver);

        driver.close();
    }

    @Test
    public void test2() {
        System.out.println("Hola Soy el Test #2");
    }

    public void cartaNatalData(WebDriver driver) {

        List<String> tablas;
        List<String> imgDomElements;
        List<String> planetas;

        List<WebElement> planetsElements = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
        List<WebElement> rows = driver.findElements(By.xpath("//tr/td/following-sibling::td"));
        List<WebElement> signs = driver.findElements(By.xpath("//tr/td/following-sibling::td/div/img"));

        ArrayList<String> pla = new ArrayList<String>();
        ArrayList<String> coordenadas = new ArrayList<String>();
        ArrayList<String> casas = new ArrayList<String>();
        ArrayList<String> signos = new ArrayList<String>();

        int counter = 0;

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
        // Displaying data from comand line
        while (coordenadas.size() > counter) {
            System.out.println(pla.get(counter) + " | " + signos.get(counter) + " | " + coordenadas.get(counter) + " | " + casas.get(counter));
            counter++;
        }
    }
}
