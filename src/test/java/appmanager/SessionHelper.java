package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class SessionHelper {
    private final WebDriver wd;

    public SessionHelper(WebDriver wd) {
        this.wd = wd;
    }

    public boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void okCookie() {
        if (isElementPresent(By.cssSelector("div#cookiesBanner a.o-c-bar__button"))) {
            wd.findElement(By.cssSelector(("div#cookiesBanner a.o-c-bar__button"))).click();
        }
    }
}
