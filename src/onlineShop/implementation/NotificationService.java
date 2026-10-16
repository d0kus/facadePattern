package onlineShop.implementation;
import onlineShop.model.Order;

public class NotificationService {
    public void sendOrderConfirmation(Order order, String trackingNumber) {
        System.out.println("[Notification] Email to " + order.getCustomerEmail() +
                ": Hello " + order.getCustomerName() +
                "! Your order for " + order.getProduct().getName() +
                " is on the way (Track: " + trackingNumber + ").");
    }
    public void sendReturnReceipt(String email, String orderId, double amount) {
        System.out.println("[Notification] Email to " + email +
                ": Return for order #" + orderId + " accepted. $" + amount + " will be refunded.");
    }
}
