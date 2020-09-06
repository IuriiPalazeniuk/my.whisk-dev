package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class SignUpPage extends BasePage {

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public void fillInCredentials(String fieldName, String text) {
        wait = new WebDriverWait(driver, 5);
        WebElement elem = wait.until(elementToBeClickable(By.xpath("//input[@name='" + fieldName + "']")));
        elem.sendKeys(text);
    }

    public void clickOnButtonSignUpPage(String buttonName) {
        clickOnButton(buttonName, true);
    }


}
