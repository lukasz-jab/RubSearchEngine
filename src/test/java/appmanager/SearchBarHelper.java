package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchBarHelper {
    private final WebDriver wd;

    public SearchBarHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void searchByTyping(String item) {
        wd.findElement(By.cssSelector(("form#search_form input#search"))).sendKeys(item);
        wd.findElement(By.cssSelector(("form#search_form button#search_submit"))).click();
    }
}
