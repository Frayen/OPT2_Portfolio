import Entity.Brand;
import Model.BrandModel;
import org.junit.Assert;
import org.junit.Test;

public class BrandModelTest {
    @Test
    public void addUniqueProductTest() {
        String brandName = "samsung";
        BrandModel brandModel = new BrandModel();
        Assert.assertTrue(brandModel.addUniqueBrand(new Brand(brandName)));
        Assert.assertFalse(brandModel.addUniqueBrand(new Brand(brandName)));
        Assert.assertFalse(brandModel.getBrands().size() > 1 );
        Assert.assertEquals(1, brandModel.getBrands().size());

    }
    @Test
    public void isProductNameUniqueTest(){
        String brandName = "samsung";
        BrandModel brandModel = new BrandModel();
        Assert.assertTrue(brandModel.isBrandNameUnique(brandName));
        Assert.assertTrue(brandModel.addUniqueBrand(new Brand(brandName)));
        Assert.assertFalse(brandModel.isBrandNameUnique(brandName));
    }
}
