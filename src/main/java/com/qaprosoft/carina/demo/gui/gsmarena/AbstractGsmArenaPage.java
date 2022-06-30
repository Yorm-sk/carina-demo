package com.qaprosoft.carina.demo.gui.gsmarena;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public abstract class AbstractGsmArenaPage extends AbstractPage {

    @FindBy(id = "header")
    private ExtendedWebElement header;

    @FindBy(xpath = "//button[@aria-label]")
    private ExtendedWebElement linesButton;

    @FindBy(xpath = "//a[@href=\"tipus.php3\"]//span[@class=\"icon-count\"]")
    private ExtendedWebElement spanIconText;

    @FindBy(xpath = "//ul[@id=\"menu\"]//li[a=\"Videos\"]")
    private ExtendedWebElement videosButton;

    public AbstractGsmArenaPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSpanIconCountTextIsVisible(){
        return spanIconText.isVisible();
    }

    public void clickLinesButton(){
        linesButton.click();
    }

    public boolean isLinesButtonClicked(){
        return StringUtils.equalsIgnoreCase(linesButton.getAttribute("class"), "lines-button minus focused open");
    }

    public VideoPage clickVideoButton(){
        videosButton.click();
        return new VideoPage(driver);
    }
}
