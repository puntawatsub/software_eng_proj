import java.text.NumberFormat;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.printf("Select a language (1-3):%n1. English%n2. Finnish%n3. Swedish%n4. Japanese%n");
        Locale locale = Locale.of("en", "US");
        while (true) {
            System.out.print("Select (1-4): ");
            try {
                locale = switch (Integer.parseInt(sc.next())) {
                    case 1 -> Locale.of("en", "US");
                    case 2 -> Locale.of("fi", "FI");
                    case 3 -> Locale.of("sv", "SE");
                    case 4 -> Locale.of("ja", "JP");
                    default -> throw new Exception();
                };
                break;
            } catch (Exception ignored) {
                System.out.println(ResourceBundle.getBundle("MessagesBundle", locale).getString("invalidNumberMessage"));
            }
        }

        ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle", locale);

        NumberFormat nf = NumberFormat.getInstance(locale);

        int itemCount;
        while (true) {
            try {
                System.out.printf("%s: ", rb.getString("promptItemCount"));
                Number number = nf.parse(sc.next());
                if (number.doubleValue() % 1 != 0) {
                    throw new NumberFormatException();
                }
                itemCount = number.intValue();
                if (itemCount > 0) {
                    break;
                }
            } catch (ParseException | NumberFormatException ignored) {
                System.out.println(rb.getString("invalidNumberMessage"));
            }
        }

        ShoppingCart shoppingCart = new ShoppingCart();

        for (int i = 0; i < itemCount; i++) {
            double price;
            int quantity;
            while (true) {
                try {
                    System.out.printf("%s: ", rb.getString("promptItemPrice"));
                    Number number = nf.parse(sc.next());
                    price = number.doubleValue();
                    if (price >= 0) {
                        break;
                    }
                } catch (NumberFormatException | ParseException ignored) {
                    System.out.println(rb.getString("invalidNumberMessage"));
                }
            }
            while (true) {
                try {
                    System.out.printf("%s: ", rb.getString("promptItemQuantity"));
                    Number number = nf.parse(sc.next());
                    if (number.doubleValue() % 1 != 0) {
                        throw new NumberFormatException();
                    }
                    quantity = number.intValue();
                    if (quantity > 0) {
                        break;
                    }
                } catch (NumberFormatException | ParseException ignored) {
                    System.out.println(rb.getString("invalidNumberMessage"));
                }
            }
            shoppingCart.addItem(new PurchaseItem(price, quantity));
        }
        var totalCost = shoppingCart.getTotalCost();
        String currencyText = totalCost != 1 ? rb.getString("resultCurrencyPlural") : rb.getString("resultCurrencySingular");
        System.out.printf(locale, "%s %.2f %s.%n", rb.getString("resultResponse"), totalCost, currencyText);
    }
}
