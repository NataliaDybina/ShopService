import java.util.List;

public class ProductRepo {
    private List<Product> products;

    public ProductRepo(List<Product> products) {
        this.products = products;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product getProductById(String id) {
        for (Product product : products) {
            if (product.id().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }

}
