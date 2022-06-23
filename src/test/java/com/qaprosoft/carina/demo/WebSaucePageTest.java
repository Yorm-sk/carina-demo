package com.qaprosoft.carina.demo;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.demo.gui.mygui.pages.InventoryPage;
import com.qaprosoft.carina.demo.gui.mygui.pages.LogInPage;
import com.qaprosoft.carina.demo.gui.mygui.pages.ProductPageWithId4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class WebSaucePageTest implements IAbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test()
    public void alphabeticSortedProductsTest(){
        InventoryPage inventoryPage = loginAsStandardUser();
        SoftAssert softAssert  = new SoftAssert();
        softAssert.assertTrue(inventoryPage.getFilterMenu().isSelected("name (a to z)"),
                "A-z is not selected");
        softAssert.assertTrue(inventoryPage.isSorted());
        softAssert.assertAll();
    }

    @Test()
    public void verifyDropDownMenuTest(){
        InventoryPage inventoryPage = loginAsStandardUser();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(inventoryPage.getFilterMenu().isSelected("name (a to z)"),
                "A-z is not selected");
        softAssert.assertTrue(inventoryPage.getFilterMenu().select(1), "Z to a in not selected");
        softAssert.assertTrue(inventoryPage.getFilterMenu().isSelected("name (z to a)"),
                "Z to a in not selected");
        softAssert.assertAll();
    }

    @Test()
    public void verifyFilterMenuTest(){
        InventoryPage inventoryPage = loginAsStandardUser();
        SoftAssert softAssert = new SoftAssert();
        List<ExtendedWebElement> elementList =  inventoryPage.getFilterMenu().getOptions();
        for (ExtendedWebElement element:elementList) softAssert.assertTrue(element.isElementPresent());
        softAssert.assertAll();
    }

    @Test()
    public void verifyProductItemCard(){
        InventoryPage inventoryPage = loginAsStandardUser();
        List<ExtendedWebElement> elementList = inventoryPage.getFirstCardElement();
        SoftAssert softAssert = new SoftAssert();
        for (ExtendedWebElement element : elementList){
            softAssert.assertTrue(element.isElementPresent(), element.getName() + " not present");
        }
        softAssert.assertAll();
    }

    @Test()
    public void openFirstCardTest(){
        InventoryPage inventoryPage = loginAsStandardUser();
        ProductPageWithId4 page = inventoryPage.openFirstInventoryPage();
        Assert.assertTrue(page.isPageOpened(), "Product page was no open");
    }

    @Test()
    public void checkPositions(){
        LogInPage loginPage = openPage();
        Assert.assertTrue(loginPage.checkPosition(), "Positions isn`t write");
    }

    private InventoryPage loginAsStandardUser(){
        LogInPage loginPage = openPage();

        InventoryPage inventoryPage = loginPage.logInAsStandardUser();
        Assert.assertTrue(inventoryPage.isPageOpened(), "Inventory page not opened");
        LOGGER.info("User login success, inventory page is opened");
        return inventoryPage;
    }

    private LogInPage openPage(){
        LogInPage loginPage = new LogInPage(getDriver());
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page not opened");
        return loginPage;
    }
}
