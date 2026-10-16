package onlineShop;
import onlineShop.facade.*;
import onlineShop.model.*;
import onlineShop.implementation.*;

public class Main {
    public static void main(String[] args) {

        OrderCheckoutFacade checkoutFacade = new OrderCheckoutFacade();
        OrderReturnFacade returnFacade = new OrderReturnFacade();

        Product laptop = new Product("PROD-01", "MacBook Air M2", 1199.0);
        Product smartphone = new Product("PROD-02", "iPhone 18 Pro", 899.0);


        System.out.println("Successful checkout: ");
        Order order1 = new Order("ORD-101", "Diyar", "someemail@gmail.com", laptop, 1, "Mangilik el 40, Astana");
        OrderSummary result1 = checkoutFacade.placeOrder(order1, "VISA_CARD");
        System.out.println("\nResult: " + result1);

        System.out.println();


        System.out.println("Payment error: ");
        Order order2 = new Order("ORD-102", "Aibek", "gg@gmail.com", smartphone, 1, "Sygnak 50, Astana");
        OrderSummary result2 = checkoutFacade.placeOrder(order2, "BLOCKED_CARD");
        System.out.println("\nResult: " + result2);

        System.out.println();


        if (result1.isSuccess()) {
            System.out.println("Refund facade: ");
            returnFacade.processReturn(order1, result1.getTrackingNumber());
        }

        System.out.println();


        System.out.println("Advanced client (Warehouse Auditor) directly checking stock:");
        InventoryWarehouse auditorWarehouse = new InventoryWarehouse();
        int remainingLaptops = auditorWarehouse.getStockCount(laptop.getId());
        System.out.println("Remaining laptops in warehouse: " + remainingLaptops);
    }
}