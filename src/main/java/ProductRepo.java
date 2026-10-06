import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
public class ProductRepo {
    private final Map<String, Product> products;
    private final Map<String, Integer> quantities;

    public ProductRepo() {
        products = new HashMap<>();
        quantities = new HashMap<>();
    }

    public ProductRepo(Map<String, Product> products, Map<String, Integer> quantity) {
        this.products = products;

        this.quantities = quantity;
    }

    public Optional<Product> getProductById(String id) {
        return Optional.ofNullable(products.get(id));
    }

    public void addProduct(Product product, int quantity) {
        if (products.containsKey(product.id())) {
            this.quantities.put(product.id(), quantities.get(product.id()) + quantity);
        } else {
            this.quantities.put(product.id(), quantity);
        }
        this.products.put(product.id(), product);

    }

    public void removeProduct(Product product) {
        this.products.remove(product.id());
    }

    public void decreaseQuantity(Map<String, Integer> productsToOrder) {
        for (Map.Entry<String, Integer> productToOrder : productsToOrder.entrySet()) {
            Integer amountInStock = this.quantities.get(productToOrder.getKey());
            this.quantities.replace(productToOrder.getKey(), amountInStock - productToOrder.getValue());
        }
    }

    public void increaseQuantity(Map<String, Integer> productsToOrder) {
        for (Map.Entry<String, Integer> productToOrder : productsToOrder.entrySet()) {
            Integer amountInStock = this.quantities.get(productToOrder.getKey());
            this.quantities.replace(productToOrder.getKey(), amountInStock + productToOrder.getValue());
        }
    }

    public String printProducts() {
        int i = 1;
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Product> entry : products.entrySet()) {
            if (quantities.get(entry.getKey()) > 0) {
                sb.append(i).append(". ").append(entry.getValue()).append("\n");
                i++;
            }
        }
        return sb.toString();
    }
}
