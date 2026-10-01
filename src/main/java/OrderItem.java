public record OrderItem(Product product, int quantity) {
    public String toString() {
        return product.toString() + ", Quantity: " + quantity + "\n";
    }
}
