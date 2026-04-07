import com.puntawat.metsofteng2.week2.model.PurchaseItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseItemTest {

    private Random random;

    @BeforeEach
    void beforeEach() {
        random = new Random();
    }


    @Test
    void getTotalCost() {
        double price = random.nextDouble() * 100;
        int quantity = random.nextInt(100);
        PurchaseItem purchaseItem = new PurchaseItem("", price, quantity);
        assertEquals(price * quantity, purchaseItem.getTotalCost(), 0.01);
    }
}