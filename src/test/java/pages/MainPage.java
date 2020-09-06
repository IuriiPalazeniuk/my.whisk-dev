package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnTab(String tabName) {
        driver.findElement(By.xpath("//div[@class='sc-fzqMAW fqlIbq sc-fzoydu ctZjfV']//a[contains(text(),'"
                + tabName + "')]")).click();
    }

    public void clickButton(String buttonName) {
        clickOnButton(buttonName, false);
    }
}
