package com.qaprosoft.carina.demo.gui.gsmarena.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.qaprosoft.carina.demo.gui.gsmarena.OneVideoPage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Video extends AbstractUIObject {

    @FindBy(xpath = ".")
    private ExtendedWebElement thisElement;

    public Video(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public OneVideoPage getPage(){
        return new OneVideoPage(driver, thisElement.getAttribute("src"));
    }
}
