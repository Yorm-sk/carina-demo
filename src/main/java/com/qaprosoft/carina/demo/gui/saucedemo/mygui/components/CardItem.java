package com.qaprosoft.carina.demo.gui.saucedemo.mygui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CardItem extends AbstractUIObject {

    @FindBy(xpath = ".//div[@class=\"inventory_item_name\"]")
    private ExtendedWebElement name;

    @FindBy(xpath = ".//div[@class=\"inventory_item_img\"]")
    private ExtendedWebElement productImage;

    @FindBy(xpath = ".//div[@class=\"inventory_item_desc\"]")
    private ExtendedWebElement productDescription;

    @FindBy(xpath = ".//div[@class=\"\"]")
    private ExtendedWebElement productPrice;

    @FindBy(xpath = ".//button")
    private ExtendedWebElement addToCardButton;

    public CardItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getNameText() {
        return name.getText();
    }

    public boolean isAllElementsPresents() {
        return (name.isElementPresent() && productImage.isElementPresent() && productDescription.isElementPresent()
                && productDescription.isElementPresent() && addToCardButton.isElementPresent());
    }
}
