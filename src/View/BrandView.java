package View;

import Entity.Brand;
import Entity.Product;
import Model.BrandModel;

import java.util.ArrayList;

public class BrandView extends View{
    private BrandModel brandModel;

    public BrandView(String[] options, BrandModel brandModel) {
        super(options);
        this.brandModel = brandModel;
    }

    @Override
    public String menu() {
        String result = "Brand Menu:";
        result += "\n  back) Back to main menu";
        for (int i = 0; i < options.length; i++) {
            result += "\n  " + i + ") " + options[i];
        }
        return result;
    }

    public String brandListMenu() {
        ArrayList<Brand> products = brandModel.getBrands();
        String result = "List of Brands:" +
                "\n  back) Back to brand menu";
        result += brandListToString();
        return result;
    }

    public String brandListToString(){
        int index = 0;
        String result = "";
        for (Brand brand: brandModel.getBrands()) {
            result += "\n  " + index + ") " + brand.getName();
            index++;
        }
        return result;
    }

    public String brandMenu(int index){
        Brand brand =  brandModel.getBrand(index);
        return brandMenu(brand);
    }

    public String brandMenu(Brand brand){
        String result = "The brand '" + brand.getName() + "' has the following products:";
        result += "\n  back) Back to previous action";
        int i = 0;
        for (Product product: brand.getProducts()) {
            result +=  "\n  " + i + ") " + product.getName();
            i++;
        }
        return result + "\n";
    }
}
