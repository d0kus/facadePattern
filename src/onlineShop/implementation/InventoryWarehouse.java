package onlineShop.implementation;
import onlineShop.model.Product;
import java.util.Map;
import java.util.HashMap;

public class InventoryWarehouse {
    private static final Map<String, Integer> stock = new HashMap<>();

    static {
        stock.put("PROD-01", 10);
        stock.put("PROD-02", 4);
    }

    public boolean isAvailable(Product product, int quantity){
        int currentStock = stock.getOrDefault(product.getId(), 0);
        return currentStock >= quantity;
    }

    public void reserve(Product product, int quantity){
        int currentStock = stock.getOrDefault(product.getId(), 0);
        stock.put(product.getId(), currentStock - quantity);
        System.out.println("[Warehouse] Reserved " + quantity + "x " + product.getName() + ". Remaining: " + stock.get(product.getId()));
    }

    public void restock(Product product, int quantity) {
        int currentStock = stock.getOrDefault(product.getId(), 0);
        stock.put(product.getId(), currentStock + quantity);
        System.out.println("[Warehouse] Restocked " + quantity + "x " + product.getName() + ". Current: " + stock.get(product.getId()));
    }

    public int getStockCount(String productId){
        return stock.getOrDefault(productId, 0);
    }

}
