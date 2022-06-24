package com.qaprosoft.carina.demo.gui.mygui.pages;

import org.openqa.selenium.WebDriver;

public class ProductPageWithId4 extends AfterLoginPage{
    public ProductPageWithId4(WebDriver driver) {
        super(driver);
        setPageURL("/inventory-item.html?id=4");
    }
}
