package appmanager;

import model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductHelper {
    private WebDriver wd;
    private WebDriverWait wait;

    public ProductHelper(WebDriver wd, WebDriverWait wait) {
        this.wd = wd;
        this.wait = wait;
    }

    public Set<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        List<WebElement> webProducts = new ArrayList<>();
        webProducts = wd.findElements(By.cssSelector(("div.product-list-container div.block-product-list")));
        for (WebElement e : webProducts) {
            String name = e.findElement(By.cssSelector(("div.product-list-container div.block-product-list h2 span"))).getText();
            String description = e.findElement(By.cssSelector(("div.product-list-container div.block-product-list div.product-description--content p"))).getText();
            String price = e.findElement(By.cssSelector(("div.product-list-container div.block-product-list div.price.block-price strong"))).getText();
            String id = e.findElement(By.cssSelector("div.product-list-container div.block-product-list div.cart.clearfix input[name=productCodePost]")).getAttribute("value");
            products.add(new Product().withId(id).withName(name).withDescription(description).withPrice(cleaned(price)));
        }
        return new HashSet<Product>(products);
    }

    public void addToChart(Product product) {
        wd.findElement(By.xpath(("//div[@class='block-product-list']//button[@data-productcode='"+product.getId()+"']"))).click();
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector("span.rx-c-btn__count"))));
    }

    public BigDecimal getGrossValue() {
        String grossV = wd.findElement(By.cssSelector("div.cart-recap-box ul li:nth-of-type(3)")).getAttribute("innerText");
        return new BigDecimal(cleaned(grossV));
    }



    public static String cleaned(String price) {
        return price.replaceAll(" ", "").replaceAll("£", "")
                .replaceAll("ex.VAT", "").replaceAll("Total", "").replaceAll("\\n", "");
    }
}
