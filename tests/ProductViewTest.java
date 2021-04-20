import Entity.Brand;
import Entity.Product;
import Model.ProductModel;
import View.ProductView;
import org.junit.Assert;
import org.junit.Test;

public class ProductViewTest {
    @Test
    public void displayProduct(){

        ProductModel productModel = new ProductModel();
        ProductView productView = new ProductView(new String[]{"nothing"},productModel);
        Brand brand = new Brand("brand");
        Product product = new Product("prod", 10 ,10, brand, 0);
        brand.addProduct(product);
        productModel.addUniqueProduct(product);

        String display1 = "Name: " + product.getName() + "\n";
        display1 += "Original price: " + product.getOriginalPrice() + "\n";
        display1 += "Sale: " + product.getSale() + "\n";
        display1 += "Price: " + product.getPrice() + "\n";
        display1 += "In Stock: " + product.getInStock() + "\n";

        String display2 = "Name: " + product.getName() + "\n";
        display2 += "Price: " + product.getPrice() + "\n";
        display2 += "In Stock: " + product.getInStock() + "\n";

        Assert.assertEquals(display2, productView.productToString(1));
        Assert.assertNotEquals(display1, productView.productToString(1));
    }
}
