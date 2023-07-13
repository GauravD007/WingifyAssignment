package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {
    public static WebDriver driver;

    public LogInPage (WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//img[@src=\"img/logo-big.png\"]")
    public WebElement logo;

    @FindBy(xpath = "//div[text()='Both Username and Password must be present ']")
    public WebElement blankCredentials;

    @FindBy(xpath = "//div[text()='Password must be present']")
    public WebElement withoutPass;

    @FindBy(xpath = "//div[text()='Username must be present']")
    public WebElement withoutUsername;

    @FindBy(id = "username")
    public WebElement username;

    @FindBy(id = "password")
    public WebElement password;

    @FindBy(id = "log-in")
    public WebElement loginbtn;

    @FindBy(xpath = "//input[@class='form-check-input']")
    public WebElement checkbox;

    @FindBy(xpath = "//a[@style='display: inline-block; margin-bottom:4px;']")
    public WebElement twitterLogo;

    @FindBy(xpath = "//img[@src=\"img/facebook.png\"]")
    public WebElement facebookLogo;

    @FindBy(xpath = "//img[@src=\"img/linkedin.png\"]")
    public WebElement linkedinLogo;

    @FindBy(xpath = "//span[text()='Sign in to Twitter']")
    public WebElement twitterLoginPage;

    @FindBy(xpath = "//div[text()='Log in to Facebook']")
    public WebElement facebookLoginPage;

    @FindBy(xpath = "//h1[text()='Sign in']")
    public WebElement linkedLoginPage;
}
