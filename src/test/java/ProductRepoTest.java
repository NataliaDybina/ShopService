import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {
    private ProductRepo productRepo;

    @BeforeEach
    void setUp() {
        productRepo = new ProductRepo();
    }

    @Test
    void getProductById_shouldReturnProduct() {
        Product product = new Product("m1", "Milk", BigDecimal.valueOf(1.29));
        productRepo.addProduct(product, 2);

        Optional<Product> foundProduct = productRepo.getProductById("m1");
        assertTrue(foundProduct.isPresent(), "Product not found");
        assertEquals("Milk", foundProduct.get().name());
        assertEquals(product, foundProduct.get());
    }

    @Test
    void getProductById_shouldReturnEmptyOptionalWhenNoProductExists() {
        Optional<Product> foundProduct = productRepo.getProductById("non-existent-id");
        assertTrue(foundProduct.isEmpty());
    }
}