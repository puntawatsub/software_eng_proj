import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<PurchaseItem> purchaseItems;

    public ShoppingCart() {
        this.purchaseItems = new ArrayList<>();
    }

    public void addItem(PurchaseItem purchaseItem) {
        purchaseItems.add(purchaseItem);
    }

    public void removeItem(PurchaseItem purchaseItem) {
        purchaseItems.remove(purchaseItem);
    }

    public List<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }

    public double getTotalCost() {
        double total = 0;
        for (var items : purchaseItems) {
            total += items.getTotalCost();
        }
        return total;
    }
}
