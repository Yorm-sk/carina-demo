package com.qaprosoft.carina.demo.gui.mygui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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

    public List<ExtendedWebElement> getOptions() {
        return options;
    }

    public boolean isSelected(String textOption){
        return StringUtils.containsIgnoreCase(activeOption.getText(), textOption);
    }

    public boolean select(int index){
        return select.select(index);
    }
}
