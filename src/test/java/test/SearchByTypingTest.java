package test;

import com.google.common.collect.Ordering;
import model.Product;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchByTypingTest extends TestBase {

    @Test
    public void testGrossChartValue() {
        app.searchBar().searchByTyping("Thor Hammer");
        Set<Product> productsSBT = new HashSet<>(app.product().getProducts());
        Product productFromSearchPage = productsSBT.iterator().next();
        app.session().okCookie();
        app.product().addToChart(productFromSearchPage);
        app.goTo().openChart();
        BigDecimal grossValue = app.product().getGrossValue();

//test sprawdza czy cena wybranego produktu podana na stronie z wynikami zwiększona o VAT jest równa wartości TOTAL w koszyku.
        assertThat(grossValue, equalTo(((productFromSearchPage.getPriceToCalc().multiply(new BigDecimal("1.2"))).setScale(2, RoundingMode.HALF_UP))));
    }

    @Test
    public void testSearchByBrand() {
        app.goTo().homeButton();
        app.searchBar().searchByTyping("Makita");
        List<Product> productsBar = app.product().getProducts();
        List<String> productsName = productsBar.stream().map((o) -> o.getName()).collect(Collectors.toList());

//test sprawdza czy każdy wyszukany element ma w nazwie wyszukiwaną markę:
        assertThat(productsName, everyItem(containsString("MAKITA")));

        app.session().okCookie();
        app.searchBar().revelanceHighestFirst();
        List<Product> productsOrderedByPrice = app.product().getProducts();
        List<Double> productsPriceList = productsOrderedByPrice.stream().map((o) -> Double.parseDouble(o.getPrice())).collect(Collectors.toList());

//test sprawdza czy każdy wyszukany element został posortowany według kwoty malejąco:
        Assert.assertTrue(Ordering.natural().reverse().isOrdered(productsPriceList));

    }

    @Test
    public void testCompareProducts() {
        app.goTo().homeButton();
        app.searchBar().searchByTyping("Dewalt");
        app.session().okCookie();
        List<Product> productsFromSearch = app.product().getProducts();

        app.searchBar().addToCompare(productsFromSearch.get(0));
        app.searchBar().addToCompare(productsFromSearch.get(1));
        app.searchBar().compareProducts(productsFromSearch.get(0));
        List<Product> productsFromCompare = app.product().getProductsFromCompare();

        List<String> productsFromSearchNames = productsFromSearch.stream().map((o)->o.getName().toLowerCase()).collect(Collectors.toList());
        List<String> productsFromSearchAddedToCompare = new ArrayList<>();
        productsFromSearchAddedToCompare.add(productsFromSearchNames.get(0));
        productsFromSearchAddedToCompare.add(productsFromSearchNames.get(1));
        List<String> productsFromCompareNames = productsFromCompare.stream().map((o)->o.getName().toLowerCase()).collect(Collectors.toList());

//test sprawdza czy wybrane produktu po zaznaczeniu opcji "compare" pojawią się na stronie: compare/display
        assertThat(productsFromSearchAddedToCompare, equalTo(productsFromCompareNames));

    }
}
