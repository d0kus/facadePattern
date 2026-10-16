package onlineShop.facade;
import onlineShop.model.Order;
import onlineShop.model.OrderSummary;
import onlineShop.implementation.*;


public class OrderCheckoutFacade {
    private final InventoryWarehouse warehouse;
    private final PaymentGateway payment;
    private final ShippingService shipping;
    private final NotificationService notification;


    public OrderCheckoutFacade() {
        this(new InventoryWarehouse(), new PaymentGateway(), new ShippingService(), new NotificationService());
    }

    public OrderCheckoutFacade(InventoryWarehouse warehouse,
                               PaymentGateway payment,
                               ShippingService shipping,
                               NotificationService notification) {
        this.warehouse = warehouse;
        this.payment = payment;
        this.shipping = shipping;
        this.notification = notification;
    }

    public OrderSummary placeOrder(Order order, String paymentMethod){
        System.out.println("\n>>> [OrderCheckoutFacade] Coordinating order #" + order.getOrderId() + " <<<");

        if (!warehouse.isAvailable(order.getProduct(), order.getQuantity())) {
            return new OrderSummary(false, order.getOrderId(), null, 0, "Product is out of stock!");
        }

        warehouse.reserve(order.getProduct(), order.getQuantity());

        double total = order.calculateTotal();
        boolean paymentSuccess = payment.processPayment(order.getCustomerName(), total, paymentMethod);

        if (!paymentSuccess){
            System.out.println("[OrderCheckoutFacade] Rolling back inventory reservation due to payment failure...");
            warehouse.restock(order.getProduct(), order.getQuantity());
            return new OrderSummary(false, order.getOrderId(), null, total, "Payment rejected by bank.");
        }

        String trackingNumber = shipping.dispatchShipment(order);

        notification.sendOrderConfirmation(order, trackingNumber);

        System.out.println(">>> [OrderCheckoutFacade] Order processed successfully! <<<");
        return new OrderSummary(true, order.getOrderId(), trackingNumber, total, "Completed");

    }
}
