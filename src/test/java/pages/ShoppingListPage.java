package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static api.ScenarioContext.ContextEnum.ADDED_PRODUCTS_TO_LIST;
import static api.ScenarioContext.getContext;
import static helpers.PropertiesFileReader.getSystemPropertyByName;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

@Getter
public class ShoppingListPage extends BasePage {

    @FindBy(xpath = "//input[@data-testid='desktop-add-item-autocomplete']")
    private WebElement addItemDrpoDown;

    @FindBy(xpath = "//span[@class='sc-ptSuy fnmilh']")
    private List<WebElement> prodcutsList;

    @FindBy(xpath = "//span[@data-testid='shopping-list-item-name']")
    private List<WebElement> addedProductsList;

    @FindBy(xpath = "//div[@data-testid='shopping-list-name']/h2")
    private WebElement shoppingListHeader;

    @FindBy(xpath = "//div[@class='sc-pTSbw gOmWnw']")
    private WebElement productsList;

    @FindBy(xpath = "//button[@data-testid='vertical-dots-shopping-list-button']")
    private WebElement shoppingListManu;

    @FindBy(xpath = "//h1[@class='sc-fzpdyU sc-psEpA sc-qYRsW htjyTI']")
    private WebElement emptyShoppingList;

    @FindBy(xpath = "//button[@data-testid='shopping-list-clear-list-menu-button']")
    private WebElement clearList;

    private final String EMPTY_SHOPPING_LIST = "empty_shoppingList";


    public ShoppingListPage(WebDriver driver) {
        super(driver);
    }

    public void openProductListDropDown() {
        clickOnElement(addItemDrpoDown);
    }

    public List<String> addProductsToList(int countOfProducts) {
        List<String> products = new ArrayList<>();
        wait = new WebDriverWait(driver, 5);
        wait.until(elementToBeClickable(productsList));

        for (int i = 0; i < countOfProducts; i++) {
            products.add(prodcutsList.get(i).getText());
            prodcutsList.get(i).click();
        }
        shoppingListHeader.click();
        return products;
    }

    public List<String> getAddedProductsFromList() {
        return addedProductsList.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void openShoppingListMenu() {
        clickOnElement(shoppingListManu);
    }

    public void clearShoppingList() {
        clickOnElement(clearList);
    }

    public void checkAddedProductToShoppingList(String productName) {
        boolean result = driver.findElement(By.xpath("//span[contains(@data-testid, 'shopping-list-item-name') and text() = '" +
                productName + "']")).isDisplayed();
        assertTrue(String.format("There is no product %s", productName), result);
    }

    public void checkEmptyShoppingList() {
        String expectedText = getSystemPropertyByName(EMPTY_SHOPPING_LIST);
        String actualText = emptyShoppingList.getText();
        assertEquals(String.format("Another text is displayed - %s", actualText), expectedText, actualText);
    }

    public void verifingAddedProductsInShoppingList() {
        List<String> addedProducts = getContext(ADDED_PRODUCTS_TO_LIST);
        List<String> productsFromList = getAddedProductsFromList();
        Collections.sort(addedProducts);
        Collections.sort(productsFromList);
        assertEquals(String.format("Different products are added %s", productsFromList), addedProducts, productsFromList);
    }
}
