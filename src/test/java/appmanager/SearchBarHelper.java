package appmanager;

import model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SearchBarHelper {
    private final WebDriver wd;

    public SearchBarHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void searchByTyping(String item) {
        wd.findElement(By.cssSelector(("form#search_form input#search"))).sendKeys(item);
        wd.findElement(By.cssSelector(("form#search_form button#search_submit"))).click();
    }

    public void revelanceHighestFirst() {
        Select revelance = new Select(wd.findElement(By.cssSelector("div.container select#sortOptions1")));
        revelance.selectByValue("price-desc");
    }

    public void addToCompare(Product product) {
        wd.findElement(By.cssSelector("form#addToCompareForm_"+product.getId()+"")).click();
    }

    public void compareProducts(Product product) {
        wd.findElement(By.cssSelector("span.buttonTemplate_"+product.getId()+" a[href='/en/compare/display']")).click();
    }
}
