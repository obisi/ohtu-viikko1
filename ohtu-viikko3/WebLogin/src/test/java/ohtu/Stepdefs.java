package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Stepdefs {

    WebDriver driver = new ChromeDriver();
    String baseUrl = "http://localhost:4567";

    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }

    @Given("^command new user is selected$")
    public void new_user_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
    
    @Given("^user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created$")
    public void user_with_username_with_password_is_successfully_created(String username, String password) throws Throwable {
        new_user_selected();
        registerNewUser(username, password, password);
        logInPageToMainPage();
    }
    
    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is tried to be created$")
    public void user_with_username_and_password_is_tried_to_be_created(String username, String password) throws Throwable {
        new_user_selected();
        registerNewUser(username, password, password);
        registerPageToMainPage();
    }

    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }

    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation \"([^\"]*)\" are given$")
    public void username_correct_and_password_and_matching_password_confirmation_are_given(String username, String password, String passwordConfirmation) throws Throwable {
        registerNewUser(username, password, passwordConfirmation);
    }

    @When("^incorrect username \"([^\"]*)\" and correct password \"([^\"]*)\" and matching password confirmation \"([^\"]*)\" are given$")
    public void username_incorrect_and_password_and_matching_password_confirmation_are_given(String username, String password, String passwordConfirmation) throws Throwable {
        registerNewUser(username, password, passwordConfirmation);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" and matching password confirmation \"([^\"]*)\" are given$")
    public void username_correct_and_incorrect_password_and_matching_password_confirmation_are_given(String username, String password, String passwordConfirmation) throws Throwable {
        registerNewUser(username, password, passwordConfirmation);
    }

    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" and mismatching password confirmation \"([^\"]*)\" are entered$")
    public void username_correct_and_password_and_mismatching_password_confirmation_are_given(String username, String password, String passwordConfirmation) throws Throwable {
        registerNewUser(username, password, passwordConfirmation);
    }

    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }

    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @Then("^a new user is created$")
    public void a_new_user_is_created() throws Throwable {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @Then("^user is not created and error \"username should have at least 3 characters\" is reported$")
    public void a_new_user_is_not_created_incorrect_username() throws Throwable {
        pageHasContent("Create username and give password");
    }

    @Then("^user is not created and error \"password should have at least 8 characters\" is reported$")
    public void a_new_user_is_not_created_incorrect_password() throws Throwable {
        pageHasContent("Create username and give password");
    }

    @Then("^user is not created and error \"password and password confirmation do not match\" is reported in and error message is given$")
    public void a_new_user_is_not_created_mismathing_password_confirmation() throws Throwable {
        pageHasContent("Create username and give password");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    /* helper methods */
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    private void registerNewUser(String username, String password, String passwordConfirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConfirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }

    private void registerPageToMainPage() {
        WebElement element = driver.findElement(By.linkText("back to home"));
        element.click();
        
    }

    private void logInPageToMainPage() {
        WebElement element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        
        element = driver.findElement(By.linkText("logout"));
        element.click();
    }

}
