package test;

import model.Product;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchByTypingTest extends TestBase {

    @Test
    public void testSearchByTyping() {
        app.searchBar().searchByTyping("Thor Hammer");
        Set<Product> productsSBT = app.product().getProducts();
        Product productFromSearchPage = productsSBT.iterator().next();
        app.session().okCookie();
        app.product().addToChart(productFromSearchPage);
        app.goTo().openChart();
        BigDecimal grossValue = app.product().getGrossValue();

//test sprawdza czy cena wybranego produktu podana na stronie z wynikami zwiększona o VAT jest równa wartości TOTAL w koszyku.
        assertThat(grossValue, equalTo(((productFromSearchPage.getPriceToCalc().multiply(new BigDecimal("1.2"))).setScale(2, RoundingMode.HALF_UP))));


    }
}
