package Model;

import Entity.Product;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductModel {
    private ArrayList<Product> products;

    public ProductModel() {
        this.products = new ArrayList<Product>();
    }

    public ArrayList<Product> getProducts() {
        return new ArrayList<Product>(Arrays.asList(products.toArray(new Product[0])));
    }

    public Product getProduct(int index) {
        return products.get(index);
    }

    public boolean addUniqueProduct(Product newProduct) {
        if (!isProductNameUnique(newProduct.getName())) {
            return false;
        }
        return products.add(newProduct);
    }

    public boolean isProductNameUnique(String name) {
        for (Product product: products) {
            if (product.getName().equals(name)){
                return false;
            }
        }
        return true;
    }
}
