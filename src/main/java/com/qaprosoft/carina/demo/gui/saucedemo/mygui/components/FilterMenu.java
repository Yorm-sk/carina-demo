package com.qaprosoft.carina.demo.gui.saucedemo.mygui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterMenu extends AbstractUIObject {

    @FindBy(xpath = "//span[@class=\"active_option\"]")
    private ExtendedWebElement activeOption;

    @FindBy(xpath = "//select")
    private ExtendedWebElement select;
    @FindBy(xpath = "//select/option")
    private List<ExtendedWebElement> options;

    public FilterMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean isSelected(String textOption){
        return StringUtils.containsIgnoreCase(activeOption.getText(), textOption);
    }

    public boolean select(int index){
        return select.select(index);
    }

    public boolean isOptionsValid(){
        List<String> neededElements = Stream.of("Name (A to Z)", "Name (Z to A)", "Price (low to high)",
                "Price (high to low)").map(String::toLowerCase).collect(Collectors.toList());
        List<String> elementOnPage = options.stream()
                .map(ExtendedWebElement::getText)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        return  neededElements.equals(elementOnPage);
    }
}
