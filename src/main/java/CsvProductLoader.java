import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class CsvProductLoader {
    public static ProductRepo loadProductsFromCsv(String fileName) {
        Map<String, Product> products = new HashMap<>();
        Map<String, Integer> productQuantity = new HashMap<>();

        InputStream inputStream = CsvProductLoader.class
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("Resource file not found: " + fileName);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                if (line.isEmpty()) continue;

                String[] fields = line.split(",");

                String productId = fields[0].trim();
                String productName = fields[1].trim();
                BigDecimal price = BigDecimal.valueOf(Double.parseDouble(fields[2].trim()));
                int quantity = Integer.parseInt(fields[3].trim());
                Product product = new Product(productId, productName, price);
                products.put(productId, product);
                productQuantity.put(productId, quantity);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ProductRepo(products, productQuantity);
    }
}
