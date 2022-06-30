package com.qaprosoft.carina.demo.myWebGsmArenaTests;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.demo.gui.gsmarena.HomePage;
import com.qaprosoft.carina.demo.gui.gsmarena.OneVideoPage;
import com.qaprosoft.carina.demo.gui.gsmarena.VideoPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WebGsmArenaTests implements IAbstractTest {

    @Test()
    public void checkSpanIconTest(){
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page was not opened");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(homePage.isSpanIconCountTextIsVisible(), "Text is visible");
        softAssert.assertFalse(homePage.isLinesButtonClicked(), "Lines button is clicked");
        homePage.clickLinesButton();
        softAssert.assertTrue(homePage.isLinesButtonClicked(), "Lines button was not clicked");
        softAssert.assertTrue(homePage.isSpanIconCountTextIsVisible(), "Text is not visible");
        softAssert.assertAll();
    }

    @Test
    public void startVideoTest(){
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page was not opened");

        SoftAssert softAssert = new SoftAssert();
        homePage.clickLinesButton();
        softAssert.assertTrue(homePage.isLinesButtonClicked(), "Lines button was not clicked");
        VideoPage videoPage = homePage.clickVideoButton();
        softAssert.assertTrue(videoPage.isPageOpened(), "Video page was not opened");
        softAssert.assertFalse(videoPage.isVideosIsPresent(), "No videos on the page");
        OneVideoPage oneVideoPage = videoPage.getVideos().get(0).getPage();
        oneVideoPage.open();
        oneVideoPage.clickLargePlayButton();
        softAssert.assertTrue(oneVideoPage.isLargePlayerClicked(),
                "Large player doen`t clicked");
        oneVideoPage.clickSmallPlayButton();
        softAssert.assertTrue(oneVideoPage.isVideoPaused(), "Video is not paused");
        oneVideoPage.clickSmallPlayButton();
        softAssert.assertTrue(oneVideoPage.isVideoPlaying(), "Video is not playing");
        softAssert.assertAll();
    }
}
