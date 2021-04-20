import Entity.Brand;
import Entity.Product;
import Model.ProductModel;
import org.junit.Assert;
import org.junit.Test;

public class ProductModelTest {
    @Test
    public void addUniqueProduct(){
        String productName = "Samsung Galaxy s10";
        Product product = new Product(productName, 10, 10 ,new Brand("Samsung"), 0);
        ProductModel productModel = new ProductModel();
        Assert.assertTrue(productModel.addUniqueProduct(product));
        Assert.assertEquals(1, productModel.getProducts().size());
        Assert.assertFalse(productModel.getProducts().size() > 1);
    }
    @Test
    public void isProductNameUniqueTest() {
        String productName = "Samsung Galaxy s10";
        Product product = new Product(productName, 10, 10 ,new Brand("Samsung"), 0);
        ProductModel productModel = new ProductModel();
        Assert.assertTrue(productModel.isProductNameUnique(productName));
        Assert.assertTrue(productModel.addUniqueProduct(product));
        Assert.assertFalse(productModel.isProductNameUnique(productName));
    }
}
