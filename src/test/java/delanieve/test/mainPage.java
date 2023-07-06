package delanieve.test;

import delanieve.pages.CartaNatalPage;
import delanieve.pages.HomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import delanieve.base.TestBase;

public class mainPage extends TestBase {
    HomePage homePage;
    CartaNatalPage cartaNatal;
    public mainPage(){
        super();
    }
    @BeforeMethod()
    public void setUp() {
        initializaton();
        homePage = new HomePage(driver);
    }
    @Test(description = "Filling the form inputs from Home Page")
    public void TC01_FillingFormInputs() throws InterruptedException {
        homePage.typeName();
        homePage.setDate();
        homePage.setSelectCountry();
        homePage.selectStates();
        homePage.selectCity();
        homePage.submitForm();
    }

//    @Test(description = "Scrapping tables data from CTN Page")
    public void TC02_ScrappingTablesData() throws InterruptedException {
        logIn();
        cartaNatal = homePage.submitForm();
        cartaNatal.getData();
    }

    public void logIn() throws InterruptedException {
        homePage.typeName();
        homePage.setDate();
        homePage.setSelectCountry();
        homePage.selectStates();
        homePage.selectCity();
        homePage.submitForm();
    }

//    @AfterMethod()
    public void tearDown(){
        tearDownMain();
    }
}
