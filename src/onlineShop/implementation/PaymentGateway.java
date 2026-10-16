package onlineShop.implementation;

public class PaymentGateway {
    public boolean processPayment(String customerName, double amount, String paymentMethod){
        System.out.println("[Payment] Authorizing $" + amount + " for " + customerName + " via " + paymentMethod + "...");

        if ("BLOCKED_CARD".equalsIgnoreCase(paymentMethod)) {
            System.out.println("[Payment] Error: Card declined by bank!");
            return false;
        }

        System.out.println("[Payment] Payment approved.");
        return true;
    }

    public void processRefund(String customerName, double amount) {
        System.out.println("[Payment] Refund of $" + amount + " successfully sent back to " + customerName);
    }
}
