import java.util.HashMap;
import java.util.Map;

public class ProductRepo {
    private Map<String, Product> products;
    private Map<String, Integer> quantity;

    public ProductRepo(Map<String, Product> products, Map<String, Integer> quantity) {
        this.products = products;
        this.quantity = quantity;
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Product> products) {
        this.products = products;
    }

    public Map<String, Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(Map<String, Integer> quantity) {
        this.quantity = quantity;
    }

    public Product getProductById(String id) {
        return products.get(id);
    }

    public void addProduct(Product product) {
        this.products.put(product.id(), product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product.id());
    }

}
