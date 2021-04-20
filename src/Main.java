import Entity.Brand;
import Model.BrandModel;
import Model.ProductModel;
import View.MainView;
import View.ProductView;
import View.BrandView;
import Entity.Product;
import View.View;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String[] PRODUCT_OPTIONS = new String[]{"View products", "Add product"};
    private static final String[] BRAND_OPTIONS = new String[]{"View brands", "Add brand"};
    private static final String[] MAIN_OPTIONS = new String[]{"Product menu", "Brand menu"};
    private static final String EXIT_CODE = "exit";
    private static final String ERROR_CODE = "Error";
    private static final String BACK_CODE = "back";

    private static ProductModel productModel = new ProductModel();
    private static BrandModel brandModel = new BrandModel();
    private static ProductView productView = new ProductView(PRODUCT_OPTIONS, productModel);
    private static BrandView brandView = new BrandView(BRAND_OPTIONS, brandModel);
    private static MainView mainView = new MainView(MAIN_OPTIONS);

    public static void main(String[] args) {
        //dummy data
        Brand brand1 = new Brand("samsung");
        brandModel.addUniqueBrand(brand1);

        Product product1 = new Product("Samasung Galaxy S10", 10.55, 20, brand1,0);
        Product product2 = new Product("Samsung A70", 10.55, 20, brand1,5);
        brand1.addProduct(product1);
        brand1.addProduct(product2);

        productModel.addUniqueProduct(product1);
        productModel.addUniqueProduct(product2);

        String option = "Start";

        while (!option.equals(EXIT_CODE)) {
            option = validateOptionsView(mainView, mainView.menu());

            switch (option){
                case ERROR_CODE:
                    System.out.println("ERROR: unexpected input try again");
                    break;
                case EXIT_CODE:
                    break;
                case "0":
                    productMenu();
                    break;
                case "1":
                    brandMenu();
                    break;
            }
        }
    }

    public static void productMenu() {
        String option = "Start";

        while (!option.equals(BACK_CODE)) {
            option = validateOptionsView(productView, productView.menu());
            if (option.equals(ERROR_CODE)) {
                System.out.println("ERROR: unexpected input try again");
                continue;
            }

            switch (option){
                case BACK_CODE:
                    break;
                case "0":
                    viewProducts();
                    break;
                case "1":
                    addProduct();
                    break;
            }
        }
    }

    public static void brandMenu() {
        String option = "Start";

        while (!option.equals(BACK_CODE)) {
            option = validateOptionsView(brandView, brandView.menu());
            if (option.equals(ERROR_CODE)) {
                System.out.println("ERROR: unexpected input try again");
                continue;
            }

            switch (option){
                case BACK_CODE:
                    break;
                case "0":
                    viewBrands();
                    break;
                case "1":
                    addBrand();
            }
        }
    }

    public static void viewProducts() {
        String option = "start";

        while (!option.equals(BACK_CODE)){
            System.out.println(productView.productsToString());
            ArrayList<Product> products = productModel.getProducts();
            option = validateOptions(products.size()-1, SCANNER.nextLine());
            if (option.equals(ERROR_CODE)) {
                System.out.println("ERROR: unexpected input try again");
                continue;
            }
            if (!option.equals(BACK_CODE)) {
                int index = Integer.parseInt(option);
                viewProduct(index);
            }
        }
    }

    public static void viewBrands(){
        String option = "start";

        while (!option.equals(BACK_CODE)){
            ArrayList<Brand> brands = brandModel.getBrands();
            if (brands.size() == 0) {
                System.out.println("Sorry brand is currently empty!\n");
                option = BACK_CODE;
                continue;
            }

            System.out.println(brandView.brandListMenu());
            option = validateOptions(brands.size()-1, SCANNER.nextLine());
            if (option.equals(ERROR_CODE)) {
                System.out.println("ERROR: unexpected input try again\n");
                continue;
            }
            if (!option.equals(BACK_CODE)) {
                int index = Integer.parseInt(option);
                viewBrand(index);
            }
        }
    }

    public static void viewProduct(int index) {
        String option = "start";
        while (!option.equals(BACK_CODE)) {
            System.out.println(productView.productToString(index));
            System.out.println("Select one of the following options:");
            System.out.println(BACK_CODE + ") go back to view products");
            System.out.println("0) View Brand");
            System.out.println("1) Edit product");
            option = validateOptions(brandModel.getBrands().size() - 1, SCANNER.nextLine());
            switch (option) {
                case BACK_CODE:
                    break;
                case "0":
                    Product product = productModel.getProduct(Integer.parseInt(option));
                    if (product == null) {
                        System.out.println("Error: coudln't find product! Try again.");
                        break;
                    }
                    viewBrand(brandModel.getBrandIndex(product.getBrand()));
                    break;
                case "1":
                    editProduct(index);
                    break;
                default:
                    System.out.println("ERROR: unexpected input try again\n");
            }
        }
    }

    public static void viewBrand(int index) {
        String option = "start";
        while (!option.equals(BACK_CODE)) {
            if (brandModel.getBrand(index).getProducts().size() == 0) {
                System.out.println("Sorry brand currently doesn't have any products!\n");
                option = BACK_CODE;
                continue;
            }
            System.out.println(brandView.brandMenu(index));
            option = validateOptions(brandModel.getBrands().size() - 1, SCANNER.nextLine());
            if (option.equals(ERROR_CODE)) {
                System.out.println("ERROR: unexpected input try again\n");
                continue;
            }
            if (!option.equals(BACK_CODE)) {
                int productIndex = Integer.parseInt(option);
                viewProduct(productIndex);
            }
        }
    }

    public static void addBrand(){
        boolean error = true;
        while (error) {
            System.out.println("Enter brand name:");
            String brandName = SCANNER.nextLine();
            if (!brandModel.isBrandNameUnique(brandName)) {
                System.out.println("Error: Brand name is not unique! Try again.\n");
                continue;
            }
            Brand brand = new Brand(brandName);
            if (!brandModel.addUniqueBrand(brand)){
                System.out.println("Error: something went wrong! Try again.\n");
                continue;
            }
            System.out.println("Brand has been successfully added.\n");
            error = false;
        }
    }

    public static void addProduct(){
        boolean error = true;
        while (error) {
            Brand brand = findBrand("Select brand to add a Product:");
            if(brand == null) {
                System.out.println("Error: couldn't find the brand\n");
                continue;
            }
            Product product = createNewProduct(brand);
            if (!productModel.addUniqueProduct(product)) {
                System.out.println("Error couldn't add product. Try making an product again.\n");
                continue;
            }
            System.out.println("Product has been successfully added.\n");
            error = false;
        }
    }

    public static void editProduct(int index){
        String option = "Start";
        while (option.equals(ERROR_CODE) || option.equals("Start")) {
            System.out.println("Select one of the options to edit the value:");
            System.out.println("  0) Edit in stock");
            System.out.println("  1) Edit in price");
            System.out.println("  2) Edit in sale");
            option = validateOptions(3, SCANNER.nextLine());
            if(option.equals(ERROR_CODE)) {
                System.out.println("Error: unexpected input! Try again\n");
                continue;
            }
            Product product = productModel.getProduct(index);
            if (product == null) {
                System.out.println("Error: coudln't find product! Try again.");
                return;
            }
            switch (option) {
                case BACK_CODE:
                    break;
                case "0":
                    System.out.println("Current in stock: " + product.getInStock());
                    product.setInStock(getInStock());
                    break;
                case "1":
                    System.out.println("Current price: " + product.getPrice());
                    product.setPrice(getPrice());
                    break;
                case "2":
                    System.out.println("Current sale: " + product.getSale());
                    product.setSale(getSale());
                    break;
                default:
                    System.out.println("ERROR: unexpected input try again\n");
                    break;
            }
        }

    }

    public static Brand findBrand(String printQuestion) {
        String option = "Start";
        Brand brand = null;
        while (option.equals(ERROR_CODE) || option.equals("Start")) {
            System.out.print(printQuestion);
            System.out.print(brandView.brandListToString() + "\n");

            option = validateOptions(brandModel.getBrands().size() - 1, SCANNER.nextLine());
            if(option.equals(ERROR_CODE)) {
                System.out.println("Error: unexpected input! Try again\n");
                continue;
            }
            brand =  brandModel.getBrand(Integer.parseInt(option));
        }
        return brand;
    }

    public static Product createNewProduct(Brand brand) {
        String name = getProductName();
        double price = getPrice();
        Integer inStock = getInStock();
        Integer sale = getSale();
        return new Product(name,price,inStock,brand,sale);
    }

    public static double getPrice() {
        String price = "";
        boolean isValid = false;
        while (!isValid) {
            System.out.println("Enter price (10.55):");
            price = SCANNER.nextLine();
            if (!Pattern.matches("^[0-9]{1,13}(\\.[0-9]{1,2})?$", price)) {
                System.out.println("Error: Try again");
                continue;
            }
            isValid = true;
        }
        return Double.parseDouble(price);
    }

    public static Integer getInStock() {
        String inStock = "";
        boolean isValid = false;
        while (!isValid) {
            System.out.println("Enter in stock:");
            inStock = SCANNER.nextLine();
            if (!Pattern.matches("^[1-9][0-9]*$", inStock)) {
                System.out.println("Error: try again");
                continue;
            }
            isValid = true;
        }
        return Integer.valueOf(inStock);
    }

    public static Integer getSale() {
        String sale = "";
        boolean isValid = false;
        while (!isValid) {
            System.out.println("Enter in sale:");
            sale = SCANNER.nextLine();
            if (!Pattern.matches("^[1-9][0-9]{0,2}|0$", sale)) {
                System.out.println("Error: try again");
                continue;
            }
            if (Integer.parseInt(sale) > 100 || Integer.parseInt(sale) < 0) {
                System.out.println("Error: try again");
                continue;
            }
            isValid = true;

        }
        return Integer.parseInt(sale);
    }

    public static String getProductName() {
        String productName = "";
        boolean isUnique = false;
        while (!isUnique) {
            System.out.println("Enter product name:");
            productName = SCANNER.nextLine();
            if (productModel.isProductNameUnique(productName)) {
                isUnique = true;
                continue;
            }
            System.out.println("Error: Product name is not unique");
        }
        return productName;
    }

    public static String validateOptionsView(View view, String print) {
        System.out.println(print);
        return validateOptions(view.getOptions().length, SCANNER.nextLine());
    }

    public static String validateOptions(int maxOptions, String rawInput) {
        if (rawInput.isEmpty()) {
            return ERROR_CODE;
        }
        if(rawInput.equals(BACK_CODE)) {
            return BACK_CODE;
        } else if(rawInput.equals(EXIT_CODE)) {
            return EXIT_CODE;
        } else if (Pattern.matches("^(?=\\d*$).+$", rawInput)) {
            if (Integer.parseInt(rawInput) <= maxOptions) {
                return rawInput;
            }
        }
        return ERROR_CODE;
    }

}
