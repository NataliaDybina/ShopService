import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {
    private ProductRepo productRepo;
    private ShopService shopService;

    @BeforeEach
    void setUp() {
        productRepo = new ProductRepo();
        shopService = new ShopService(productRepo);
    }

    @Test
    void getProductById_shouldReturnProduct() {
        Product product = new Product("m1", "Milk", BigDecimal.valueOf(1.29));
        productRepo.addProduct(product);

        Optional<Product> foundProduct = productRepo.getProductById("m1");
        assertTrue(foundProduct.isPresent(), "Product not found");
        assertEquals("Milk", foundProduct.get().name());
        assertEquals(product, foundProduct.get());
    }

    @Test
    void getProductById_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> shopService.getProductById("non-existent-id"));
    }

    @Test
    void getOrdersByOrderStatus() {
    }
}