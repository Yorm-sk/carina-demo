package com.qaprosoft.carina.demo.gui.mygui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.demo.gui.mygui.components.CardItem;
import com.qaprosoft.carina.demo.gui.mygui.components.FilterMenu;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends AfterLoginPage{

    @FindBy(xpath = "//div[@class=\"inventory_item\"]")
    private List<CardItem> cardItems;

    @FindBy(xpath = "//div[@class=\"inventory_item\"]//img")
    private List<ExtendedWebElement> productImages;

    @FindBy(xpath = "//div[@class=\"inventory_item\"]//a/div")
    private List<ExtendedWebElement> itemNames;

    @FindBy(xpath = "//div[@class=\"inventory_item\"]//div[@class=\"inventory_item_desc\"]")
    private List<ExtendedWebElement> productDescriptions;

    @FindBy(xpath = "//div[@class=\"inventory_item\"]//div[@class=\"inventory_item_price\"]")
    private List<ExtendedWebElement> productPrices;

    @FindBy(xpath = "//div[@class=\"inventory_item\"]//button")
    private List<ExtendedWebElement> addToCardButtons;

    @FindBy(xpath = "//select")
    private FilterMenu filterMenu;

    public InventoryPage(WebDriver driver) {
        super(driver);
        setPageURL("/inventory.html");
    }

    public List<CardItem> getCardItems() {
        return cardItems;
    }

    public FilterMenu getFilterMenu() {
        return filterMenu;
    }

    public List<ExtendedWebElement> getFirstCardElement(){
        return List.of(productImages.get(0), itemNames.get(0), productDescriptions.get(0),
                productPrices.get(0), addToCardButtons.get(0));
    }

    public ProductPageWithId4 openFirstInventoryPage(){
        productImages.get(0).click();
        return new ProductPageWithId4(driver);
    }

    public boolean isSorted(){
        List<String> names = itemNames.stream().map(ExtendedWebElement::getText).collect(Collectors.toList());
        if (names.isEmpty() || names.size() ==1) return true;

        Iterator<String> iter = names.iterator();
        String current, previous = iter.next();
        while (iter.hasNext()){
            current = iter.next();
            if (previous.compareTo(current) > 0) return false;
            previous = current;
        }
        return true;
    }

    public boolean clickCartButton(int index){
        addToCardButtons.get(index).click();
        return StringUtils.equalsIgnoreCase(addToCardButtons.get(index).getText(), "remove");
    }
}
