package com.qaprosoft.carina.demo.gui.mygui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CardItem extends AbstractUIObject {

    @FindBy(xpath = ".//div[@class=\"inventory_item_name\"]")
    private ExtendedWebElement name;

    public CardItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getNameText(){
        String text = name.getText();
        return text;
    }
}
