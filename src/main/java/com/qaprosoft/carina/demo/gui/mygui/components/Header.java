package com.qaprosoft.carina.demo.gui.mygui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.qaprosoft.carina.demo.gui.mygui.pages.BasketPage;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Header extends AbstractUIObject {

    @FindBy(xpath = ".//a[@class=\"shopping_cart_link\"]")
    private ExtendedWebElement shopLink;

    public Header(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public BasketPage goToBasket(){
        shopLink.click();
        return new BasketPage(driver);
    }
}
