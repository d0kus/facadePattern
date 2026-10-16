package onlineShop.implementation;
import onlineShop.model.Order;


public class ShippingService {
    private static int trackingCounter = 1001;

    public String dispatchShipment(Order order) {
        String trackingNumber = "TRACK-KZ-" + (trackingCounter++);
        System.out.println("[Logistics] Parcel dispatched to " + order.getDeliveryAddress() + ". Tracking number: " + trackingNumber);
        return trackingNumber;
    }

    public void cancelShipment(String trackingNumber) {
        System.out.println("[Logistics] Shipment " + trackingNumber + " marked for return to sender.");
    }

}
