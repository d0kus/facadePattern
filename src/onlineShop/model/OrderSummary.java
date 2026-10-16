package onlineShop.model;

public class OrderSummary {
    private final boolean success;
    private final String orderId;
    private final String trackingNumber;
    private final double totalAmount;
    private final String statusMessage;

    public OrderSummary(boolean success, String orderId, String trackingNumber, double totalAmount, String statusMessage) {
        this.success = success;
        this.orderId = orderId;
        this.trackingNumber = trackingNumber;
        this.totalAmount = totalAmount;
        this.statusMessage = statusMessage;
    }

    public boolean isSuccess() { return success; }
    public String getTrackingNumber() { return trackingNumber; }

    @Override
    public String toString() {
        if (success) {
            return "[ORDER SUCCESS] Order ID: " + orderId + " | Paid: $" + totalAmount + " | Tracking: " + trackingNumber;
        } else {
            return "[ORDER FAILED] Reason: " + statusMessage;
        }
    }
}
