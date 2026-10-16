package onlineShop.facade;
import onlineShop.model.Order;
import onlineShop.implementation.*;

public class OrderReturnFacade {
    private final InventoryWarehouse warehouse;
    private final PaymentGateway payment;
    private final ShippingService shipping;
    private final NotificationService notification;

    public OrderReturnFacade() {
        this(new InventoryWarehouse(), new PaymentGateway(), new ShippingService(), new NotificationService());
    }

    public OrderReturnFacade(InventoryWarehouse warehouse,
                               PaymentGateway payment,
                               ShippingService shipping,
                               NotificationService notification) {
        this.warehouse = warehouse;
        this.payment = payment;
        this.shipping = shipping;
        this.notification = notification;
    }


    public void processReturn(Order order, String trackingNumber) {
        System.out.println("\n>>> [OrderReturnFacade] Coordinating return for order #" + order.getOrderId() + " <<<");

        shipping.cancelShipment(trackingNumber);

        warehouse.restock(order.getProduct(), order.getQuantity());

        double refundAmount = order.calculateTotal();
        payment.processRefund(order.getCustomerName(), refundAmount);

        notification.sendReturnReceipt(order.getCustomerEmail(), order.getOrderId(), refundAmount);
        System.out.println(">>> [OrderReturnFacade] Return finished successfully! <<<");
    }
}
