package com.qaprosoft.carina.demo.gui.mygui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends SauceDemoAbstract {

    @FindBy(xpath = "//h3")
    private ExtendedWebElement errorBox;

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

    public InventoryPage enterWrightDataAndClick(String username, String password){
        this.username.type(username);
        this.password.type(password);
        loginButton.click();
        return new InventoryPage(driver);
    }

    public boolean enterWrongData(String username, String password){
        String lockedUser = "Epic sadface: Sorry, this user has been locked out.";
        String wrongUser = "Epic sadface: Username and password do not match any user in this service";
        this.username.type(username);
        this.password.type(password);
        loginButton.click();
        return StringUtils.equalsIgnoreCase(errorBox.getText(), lockedUser)
                || StringUtils.equalsIgnoreCase(errorBox.getText(), wrongUser);
    }
}
