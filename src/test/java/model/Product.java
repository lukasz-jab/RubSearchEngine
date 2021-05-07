package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private BigDecimal priceToCalc;
    private String price;
    private String description;
    private String name;
    private String id;

    public Product(String id, String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.id = id;
    }

    public Product() {

    }

    public Product withPriceToCalc(BigDecimal priceToCalc) {
        this.priceToCalc = priceToCalc;
        return this;
    }

    public BigDecimal getPriceToCalc() {
        return priceToCalc = new BigDecimal(price);
    }

    public String getPrice() {
        return price;
    }

    public Product withPrice(String price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product withName(String name) {
        this.name = name;
        return this;
    }

    public Product withId(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(price, product.price) && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, id);
    }
}
