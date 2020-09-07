package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnButton(String buttonName, boolean buttonType) {
        wait = new WebDriverWait(driver, 5);
        String type = buttonType ? "submit" : "button";
        WebElement button = wait.until(elementToBeClickable(driver.findElement(
                By.xpath("//button[@type='" + type + "']//div[contains(text(),'" + buttonName + "')]"))));
        button.click();
    }

    public void clickOnElement(WebElement element) {
        wait = new WebDriverWait(driver, 5);
        WebElement elem = wait.until(elementToBeClickable(element));
        elem.click();
    }

    public void clickOnPopUpButtonByText(String text) {
        wait = new WebDriverWait(driver, 5);
        WebElement button = wait.until(elementToBeClickable(driver.findElement(
                By.xpath("//div[contains(@class, 'sc-fznWOq fNofKk') and text() = '" + text + "']"))));
        button.click();
    }


}
