import com.puntawat.metsofteng2.week2.model.PurchaseItem;
import com.puntawat.metsofteng2.week2.model.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    private Random random;

    @BeforeEach
    void beforeEach() {
        random = new Random();
    }

    @Test
    void getTotalCost() {
        ShoppingCart shoppingCart = ShoppingCart.getInstance();
        double costs = 0;
        for (int i = 0; i < 10; i++) {
            double price = random.nextDouble() * 100;
            int quantity = random.nextInt(100);
            PurchaseItem purchaseItem = new PurchaseItem("", price, quantity);
            shoppingCart.addItem(purchaseItem);
            costs += price * quantity;
        }
        assertEquals(costs, shoppingCart.getTotalCost(), 0.01);
    }
}