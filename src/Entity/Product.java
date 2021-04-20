package Entity;

public class Product {
    private String name;
    private double price;
    private Integer inStock;
    private Integer sale;
    private Brand brand;

    public Product(String name, double price, Integer inStock, Brand brand, Integer sale) {
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.sale = sale;
        this.brand = brand;
        brand.addProduct(this);
    }

    public String getName() {
        return name;
    }

    public double getOriginalPrice() {
        return price;
    }

    public double getPrice() {
        double calc = (price / 100) * (100 - sale);
        return Math.round(calc* 100.0) /100.0;
    }

    public Integer getInStock() {
        return inStock;
    }

    public Integer getSale() {
        return sale;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public Brand getBrand() {
        return brand;
    }
}
