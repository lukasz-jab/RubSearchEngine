package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {
    private final WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void homeButton() {

        wd.findElement(By.cssSelector("div#nav_main_rx a[title=Home]")).click();
    }

    public void openChart() {
        wd.findElement(By.cssSelector("div.rx-m-header__content button#minicart_data")).click();
    }
}
