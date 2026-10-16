package onlineShop.model;

public class Order {
    private final String orderId;
    private final String customerName;
    private final String customerEmail;
    private final Product product;
    private final int quantity;
    private final String deliveryAddress;

    public Order(String orderId, String customerName, String customerEmail, Product product, int quantity, String deliveryAddress) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.product = product;
        this.quantity = quantity;
        this.deliveryAddress = deliveryAddress;
    }

    public double calculateTotal() {
        return product.getPrice() * quantity;
    }

    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public String getDeliveryAddress() { return deliveryAddress; }
}
