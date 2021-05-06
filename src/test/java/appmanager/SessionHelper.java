package appmanager;

import org.openqa.selenium.WebDriver;

public class SessionHelper {
    private final WebDriver wd;

    public SessionHelper(WebDriver wd) {
        this.wd = wd;
    }
}
