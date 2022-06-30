package com.qaprosoft.carina.demo.gui.gsmarena;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class OneVideoPage extends AbstractPage {

    @FindBy(xpath = "//div[@class=\"ytp-cued-thumbnail-overlay\"]")
    private ExtendedWebElement LargeButtonStatus;

    @FindBy(xpath = "//div[@class=\"ytp-pause-overlay ytp-scroll-min\"]")
    private ExtendedWebElement videoStatus;
    @FindBy(xpath = "//button[@class=\"ytp-large-play-button ytp-button\"]")
    private ExtendedWebElement largePlayButton;

    @FindBy(xpath = "//button[@class=\"ytp-play-button ytp-button\"]")
    private ExtendedWebElement smallPlayButton;

    public OneVideoPage(WebDriver driver, String url) {
        super(driver);
        setPageAbsoluteURL(url);
    }

    public void clickLargePlayButton(){
        largePlayButton.click();
    }

    public void clickSmallPlayButton(){
        smallPlayButton.click();
    }

    public boolean isLargePlayerClicked(){
        return StringUtils.equalsIgnoreCase(LargeButtonStatus.getAttribute("style"), "display: none;");
    }

    public boolean isVideoPaused(){
        return StringUtils.equalsIgnoreCase(videoStatus.getAttribute("style"), "");
    }

    public boolean isVideoPlaying(){
        return StringUtils.equalsIgnoreCase(videoStatus.getAttribute("style"), "display: none;");
    }
}
