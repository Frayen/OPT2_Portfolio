package Entity;

import java.util.ArrayList;
import java.util.Arrays;

public class Brand {
    private String name;
    private ArrayList<Product> products;

    public Brand(String name) {
        this.name = name;
        this.products = new ArrayList<Product>();
    }

    public String getName() {
        return name;
    }

    public boolean addProduct(Product product) {
        return products.add(product);
    }

    public ArrayList<Product> getProducts() {
        return new ArrayList<Product>(Arrays.asList(products.toArray(new Product[0])));
    }
}
