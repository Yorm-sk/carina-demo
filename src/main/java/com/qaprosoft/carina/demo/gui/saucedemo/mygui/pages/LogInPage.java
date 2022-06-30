package com.qaprosoft.carina.demo.gui.saucedemo.mygui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends SauceDemoAbstract {
    private final String ERROR_BOX_TEXT_BLOCKED_USER = "Epic sadface: Sorry, this user has been locked out.";
    private final String ERROR_BOX_TEXT_NO_SUCH_USER =
            "Epic sadface: Username and password do not match any user in this service";


    @FindBy(xpath = "//h3")
    private ExtendedWebElement errorBox;

    @FindBy(xpath = "//div[@class=\"form_group\"]/input[@name=\"user-name\"]")
    private ExtendedWebElement usernameForm;

    @FindBy(xpath = "//div[@class=\"form_group\"]/input[@name=\"password\"]")
    private ExtendedWebElement passwordForm;

    @FindBy(xpath = "//input[@type=\"submit\"]")
    private ExtendedWebElement loginButton;

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    public InventoryPage logInAsStandardUser() {
        usernameForm.type("standard_user");
        passwordForm.type("secret_sauce");
        clickLoginButton();
        return new InventoryPage(driver);
    }

    public boolean checkPosition() {
        return usernameForm.getLocation().y < passwordForm.getLocation().y &&
                passwordForm.getLocation().y < loginButton.getLocation().y;
    }

    public InventoryPage enterWrightDataAndClick(String username, String password){
        this.usernameForm.type(username);
        this.passwordForm.type(password);
        clickLoginButton();
        return new InventoryPage(driver);
    }

    public void enterWrongDataAndClick(String username, String password){
        this.usernameForm.type(username);
        this.passwordForm.type(password);
        clickLoginButton();
    }

    private void clickLoginButton(){
        loginButton.click();
    }

    public boolean isLockedErrorMessageAppeared(){
        return StringUtils.equalsIgnoreCase(errorBox.getText(), ERROR_BOX_TEXT_BLOCKED_USER);
    }

    public boolean isNoUserErrorMessageAppeared(){
        return StringUtils.equalsIgnoreCase(errorBox.getText(), ERROR_BOX_TEXT_NO_SUCH_USER);
    }
}
