package View;

import Model.ProductModel;
import Entity.Product;

import java.util.ArrayList;

public class ProductView extends View{
    private ProductModel productModel;

    public ProductView(String[] options,ProductModel productModel) {
        super(options);
        this.productModel = productModel;
    }

    @Override
    public String menu() {
        String result = "Product Menu:";
        result += "\n  back) Back to main Menu";
        for (int i = 0; i < options.length; i++) {
            result += "\n  " + i + ") " + options[i];
        }
        return result;
    }

    public String productsToString() {
        ArrayList<Product> products = productModel.getProducts();
        String result = "List of products:";
        result += "\n  back) Back to previous action";
        int index = 0;
        for (Product product: products) {
            result += "\n  " + index + ") " + product.getName();
            index++;
        }
        return result;
    }

    public String productToString(int index) {
        Product product = productModel.getProduct(index);
        String display = "Name: " + product.getName() + "\n";

        if (product.getSale() != 0) {
            display += "Original price: " + product.getOriginalPrice() + "\n";
            display += "Sale: " + product.getSale() + "\n";
            display += "Price: " + product.getPrice() + "\n";
        } else {
            display += "Price: " + product.getOriginalPrice() + "\n";
        }


        display += "In Stock: " + product.getInStock() + "\n";
        return display;
    }
}
