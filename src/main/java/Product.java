import java.math.BigDecimal;
import java.util.UUID;

public record Product(String id, String name, BigDecimal price, boolean onStock) {
}
