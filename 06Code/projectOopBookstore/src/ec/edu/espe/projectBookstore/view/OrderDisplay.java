package ec.edu.espe.projectBookstore.view;

import ec.edu.espe.projectBookstore.model.Order;
import ec.edu.espe.projectBookstore.model.OrderDetail;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public final class OrderDisplay {

    private OrderDisplay() {
    }

    public static void print(Order o) {
        System.out.println("Order #" + o.getId() + " | " + TextUtil.formatDate(o.getOrderDate()) + " | Status: " + o.getStatus()
                + " | Payment: " + o.getPaymentMethod() + " | Total: $" + o.getTotal());
        for (OrderDetail d : o.getDetails()) {
            System.out.println("    - " + d);
        }
    }

    public static void printSummary(Order o) {
        System.out.println("Order #" + o.getId() + " | " + o.getStatus() + " | $" + o.getTotal());
    }
}
