package com.qaprosoft.carina.demo.gui.gsmarena;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.demo.gui.gsmarena.components.Video;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class VideoPage extends AbstractGsmArenaPage {

    @FindBy(id = "ytplayer")
    private List<Video> videos;

    public VideoPage(WebDriver driver) {
        super(driver);
        setPageURL("/videos.php3");
    }

    public boolean isVideosIsPresent() {
        return videos.isEmpty();
    }

    public List<Video> getVideos() {
        return videos;
    }
}
