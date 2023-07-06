package delanieve.test;

import delanieve.TestComponents.BaseTest;
import delanieve.abstractComponents.BaseComponent;
import delanieve.pageobjects.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class mainPage extends BaseTest {
    @Test
    public void Test1() throws InterruptedException {
        homePage.typeName();
        homePage.setDate();
        homePage.setSelectCountry();
        Thread.sleep(2000);
        homePage.selectStates();
        Thread.sleep(2000);
        homePage.selectCity();
        homePage.submitForm();

        // Darlin estuvo por aqui :)
    }
}
