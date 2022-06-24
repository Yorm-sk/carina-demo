package com.qaprosoft.carina.demo.gui.mygui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BasketPage extends AfterLoginPage{

    @FindBy(xpath = "//div[@class=\"cart_item\"]")
    private List<ExtendedWebElement> cartItems;

    public BasketPage(WebDriver driver) {
        super(driver);
        setPageURL("/cart.html");
    }

    public int quantityOfCardItems(){
        return cartItems.size();
    }
}
