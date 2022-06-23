package com.qaprosoft.carina.demo.gui.mygui.pages;

import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class LogInPage extends SauceDemoAbstract {

    @FindBy(xpath = "//div[@class=\"form_group\"]/input[@name=\"user-name\"]")
    private ExtendedWebElement username;

    @FindBy(xpath = "//div[@class=\"form_group\"]/input[@name=\"password\"]")
    private ExtendedWebElement password;

    @FindBy(xpath = "//input[@type=\"submit\"]")
    private ExtendedWebElement loginButton;

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    public InventoryPage logInAsStandardUser() {
        username.type("standard_user");
        password.type("secret_sauce");
        loginButton.click();
        return new InventoryPage(driver);
    }

    public boolean checkPosition() {
        boolean everythingOk = username.getLocation().y < password.getLocation().y &&
                username.getLocation().y < loginButton.getLocation().y;
        if (!(password.getLocation().y < loginButton.getLocation().y)) everythingOk = false;
        return everythingOk;
    }
}
