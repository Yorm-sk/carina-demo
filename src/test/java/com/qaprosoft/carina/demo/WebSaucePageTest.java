package com.qaprosoft.carina.demo;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.demo.exeptions.ListIsEmptyException;
import com.qaprosoft.carina.demo.gui.saucedemo.mygui.components.CardItem;
import com.qaprosoft.carina.demo.gui.saucedemo.mygui.pages.BasketPage;
import com.qaprosoft.carina.demo.gui.saucedemo.mygui.pages.InventoryPage;
import com.qaprosoft.carina.demo.gui.saucedemo.mygui.pages.LogInPage;
import com.qaprosoft.carina.demo.gui.saucedemo.mygui.pages.ProductPageWithId4;
import org.apache.commons.lang3.StringUtils;
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
    public void testCardNamesTest() {
        InventoryPage inventoryPage = loginAsStandardUser();
        SoftAssert softAssert = new SoftAssert();
        List<CardItem> cards = inventoryPage.getCardItems();
        List<String> expectedNames = List.of("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)");
        int i = 0;
        for(CardItem card: cards) softAssert.assertTrue(StringUtils.equalsIgnoreCase(card.getNameText(),
                expectedNames.get(i++)));
        softAssert.assertAll();
    }

    @Test()
    public void basketQuantityTest() {
        InventoryPage inventoryPage = loginAsStandardUser();
        SoftAssert softAssert = new SoftAssert();
        inventoryPage.clickCartButton(0);
        softAssert.assertTrue(inventoryPage.isButtonTextChanged(0));
        inventoryPage.clickCartButton(2);
        softAssert.assertTrue(inventoryPage.isButtonTextChanged(2));
        softAssert.assertAll();

        BasketPage basketPage = inventoryPage.getHeader().goToBasket();
        Assert.assertTrue(basketPage.isPageOpened(), "Basket page didn`t opened");
        Assert.assertEquals(basketPage.quantityOfCardItems(), 2);
    }

    @Test()
    public void loginWrongUserTest() {
        LogInPage logInPage = openPage();
        SoftAssert softAssert = new SoftAssert();
        logInPage.enterWrongDataAndClick("locked_out_user", "secret_sauce");
        softAssert.assertTrue(logInPage.isLockedErrorMessageAppeared());
        logInPage.enterWrongDataAndClick("Dima", "secret_sauce");
        softAssert.assertTrue(logInPage.isNoUserErrorMessageAppeared());
        logInPage.enterWrongDataAndClick("standard_user", "Drond");
        softAssert.assertTrue(logInPage.isNoUserErrorMessageAppeared());
        logInPage.enterWrongDataAndClick("Roma", "12345");
        softAssert.assertTrue(logInPage.isNoUserErrorMessageAppeared());
        softAssert.assertAll();
    }

    @Test()
    public void loginWrightUserTest() {
        LogInPage log = openPage();
        SoftAssert softAssert = new SoftAssert();
        InventoryPage inventoryPage = log.enterWrightDataAndClick("standard_user", "secret_sauce");
        softAssert.assertTrue(inventoryPage.isPageOpened());
        inventoryPage.openURL(log.getPageURL());
        inventoryPage = log.enterWrightDataAndClick("problem_user", "secret_sauce");
        softAssert.assertTrue(inventoryPage.isPageOpened());
        inventoryPage.openURL(log.getPageURL());
        inventoryPage = log.enterWrightDataAndClick("performance_glitch_user", "secret_sauce");
        softAssert.assertTrue(inventoryPage.isPageOpened());
        inventoryPage.openURL(log.getPageURL());
        softAssert.assertAll();
    }

    @Test()
    public void alphabeticSortedProductsTest() {
        InventoryPage inventoryPage = loginAsStandardUser();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(inventoryPage.getFilterMenu().isSelected("name (a to z)"),
                "A-z is not selected");
        softAssert.assertTrue(inventoryPage.isSorted());
        softAssert.assertAll();
    }

    @Test()
    public void verifyDropDownMenuTest() {
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
    public void verifyFilterMenuTest() {
        InventoryPage inventoryPage = loginAsStandardUser();
        Assert.assertTrue(inventoryPage.getFilterMenu().isOptionsValid(), "Options not valid");
    }

    @Test()
    public void verifyProductItemCard() {
        InventoryPage inventoryPage = loginAsStandardUser();
        Assert.assertTrue(inventoryPage.getCardItems().get(0).isAllElementsPresents(),
                "Some elements are not present");
    }

    @Test()
    public void openFirstCardTest() {
        InventoryPage inventoryPage = loginAsStandardUser();
        ProductPageWithId4 page = null;
        try {
            page = inventoryPage.openFirstInventoryPage();
        } catch (ListIsEmptyException e) {
            LOGGER.error(e.getMessage());
        }
        Assert.assertNotNull(page, "There is no products on the page");
        Assert.assertTrue(page.isPageOpened(), "Product page was not open");
    }

    @Test()
    public void checkPositions() {
        LogInPage loginPage = openPage();
        Assert.assertTrue(loginPage.checkPosition(), "Positions isn`t write");
    }

    private InventoryPage loginAsStandardUser() {
        LogInPage loginPage = openPage();

        InventoryPage inventoryPage = loginPage.logInAsStandardUser();
        Assert.assertTrue(inventoryPage.isPageOpened(), "Inventory page not opened");
        LOGGER.info("User login success, inventory page is opened");
        return inventoryPage;
    }

    private LogInPage openPage() {
        LogInPage loginPage = new LogInPage(getDriver());
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page not opened");
        return loginPage;
    }
}
