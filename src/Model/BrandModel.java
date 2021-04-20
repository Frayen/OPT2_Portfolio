package Model;

import Entity.Brand;

import java.util.ArrayList;
import java.util.Arrays;

public class BrandModel {
    private ArrayList<Brand> brands;

    public BrandModel() {
        this.brands = new ArrayList<Brand>();
    }

    public ArrayList<Brand> getBrands() {
        return new ArrayList<Brand>(Arrays.asList(brands.toArray(new Brand[0])));
    }

    public Brand getBrand(int index) {
        return brands.get(index);
    }

    public boolean addUniqueBrand(Brand newBrand) {
        if (!isBrandNameUnique(newBrand.getName())) {
            return false;
        }
        return brands.add(newBrand);
    }

    public boolean isBrandNameUnique(String name) {
        for (Brand brand: brands) {
            if (brand.getName().equals(name)){
                return false;
            }
        }
        return true;
    }

    public int getBrandIndex(Brand brand) {
        return brands.indexOf(brand);
    }
}
