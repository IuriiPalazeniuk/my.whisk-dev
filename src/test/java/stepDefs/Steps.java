package stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.MainPage;
import pages.ShoppingListPage;
import pages.SignUpPage;

import java.util.List;

import static api.ScenarioContext.ContextEnum.ADDED_PRODUCTS_TO_LIST;
import static api.ScenarioContext.setContext;
import static driverFactory.LocalDriverFactory.closeDriver;
import static driverFactory.LocalDriverFactory.getDriver;
import static helpers.PropertiesFileReader.getSystemPropertyByName;

public class Steps {

    private SignUpPage signUpPage = new SignUpPage(getDriver());
    private MainPage mainPage = new MainPage(getDriver());
    private ShoppingListPage shoppingListPage = new ShoppingListPage(getDriver());

    private final String URL = "url";


    @After("@UI")
    public void tearDown() {
        closeDriver();
    }

    @Given("user navigate to my Whisk")
    public void userNavigateToMyWhisk() {
        String webUrl = getSystemPropertyByName(URL);
        getDriver().get(webUrl);
    }

    @When("click {string} button")
    public void clickSignInButton(String buttonName) {
        mainPage.clickButton(buttonName);
    }

    @And("navigate to {string} tab")
    public void navigateToTab(String tabName) {
        mainPage.clickOnTab(tabName);
    }

    @When("fill in {string}")
    public void fillInCredentials(String fieldName) {
        String usernamePassword = getSystemPropertyByName(fieldName);
        signUpPage.fillInCredentials(fieldName, usernamePassword);
    }

    @And("click {string}")
    public void clickContinue(String buttonName) {
        signUpPage.clickOnButtonSignUpPage(buttonName);
    }

    @And("add {int} products to the list")
    public void addProductsToTheList(int countOfProducts) {
        shoppingListPage.getDefaultShoppingList().get(0).click();
        if (shoppingListPage.getAddedProductsList().isEmpty()) {
            shoppingListPage.openProductListDropDown();
            List<String> addedProductsList = shoppingListPage.addProductsToList(countOfProducts);
            setContext(ADDED_PRODUCTS_TO_LIST, addedProductsList);
        }
    }

    @Then("check the result")
    public void checkTheResult() {
        shoppingListPage.verifingAddedProductsInShoppingList();
    }

    @And("open Shopping list menu")
    public void openShoppingListMenu() {
        shoppingListPage.getDefaultShoppingList().get(0).click();
        shoppingListPage.openShoppingListMenu();
    }

    @Then("check that user doesn't have Shopping lists")
    public void checkThatUserDoesnTHaveShoppingLists() {
        shoppingListPage.checkEmptyShoppingList();
    }

    @And("delete Shopping list and click {string}")
    public void deleteShoppingList(String button) {
        shoppingListPage.deleteShoppingList();
        shoppingListPage.clickOnPopUpButtonByText(button);
    }

    @Then("check added products {string}")
    public void checkAddedProductsName(String productName) {
        shoppingListPage.getDefaultShoppingList().get(0).click();
        shoppingListPage.checkAddedProductToShoppingList(productName);
    }

    @And("create Shopping List with default name and click {string}")
    public void createShoppingListWithDefaultName(String buttonName) {
        if (shoppingListPage.getDefaultShoppingList().isEmpty()) {
            shoppingListPage.createShoppingList();
            shoppingListPage.clickOnPopUpButtonByText(buttonName);
        }
    }

}
