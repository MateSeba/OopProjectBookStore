package ec.edu.espe.projectBookstore.action;

import ec.edu.espe.projectBookstore.controller.BookstoreData;
import ec.edu.espe.projectBookstore.model.DeliveryAssignment;
import ec.edu.espe.projectBookstore.model.Order;
import ec.edu.espe.projectBookstore.view.AppContext;
import ec.edu.espe.projectBookstore.view.ConsoleIO;
import ec.edu.espe.projectBookstore.view.OrderDisplay;

import java.util.List;

import static ec.edu.espe.projectBookstore.controller.Lists.find;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class AdminActions {

    private final BookstoreData data;
    private final ConsoleIO io;

    public AdminActions(AppContext appContext) {
        this.data = appContext.data;
        this.io = appContext.io;
    }

    public void listAllOrders() {
        System.out.println("--- All orders ---");
        if (data.orders.isEmpty()) {
            System.out.println("There are no orders yet.");
        }
        for (Order o : data.orders) {
            System.out.println("Customer: " + (o.getCustomer() == null ? "N/A" : o.getCustomer().getName()));
            OrderDisplay.print(o);
        }
    }

    public void completeDelivery() {
        System.out.println("--- Complete a delivery ---");
        List<DeliveryAssignment> active = data.deliveryAssignments.stream()
                .filter(a -> !"Completed".equalsIgnoreCase(a.getStatus()))
                .toList();
        if (active.isEmpty()) {
            System.out.println("There are no pending deliveries.");
            return;
        }
        for (DeliveryAssignment a : active) {
            System.out.println("Delivery #" + a.getId()
                    + " | Order " + (a.getOrder() == null ? "N/A" : "#" + a.getOrder().getId())
                    + " | Driver: " + (a.getDriver() == null ? "N/A" : a.getDriver().getName())
                    + " | Status: " + a.getStatus());
        }

        DeliveryAssignment selected = find(active, io.readInt("Delivery id to complete: "), DeliveryAssignment::getId);
        if (selected == null) {
            System.out.println("Error: that delivery id is not in the pending list.");
            return;
        }
        selected.completeDelivery();
        Order order = selected.getOrder();
        if (order != null) {
            order.updateStatus("Delivered");
        }
        System.out.println("Delivery #" + selected.getId() + " completed.");
    }

    public void listCustomers() {
        System.out.println("--- Customers ---");
        if (data.customers.isEmpty()) {
            System.out.println("(no customers registered)");
        }
        data.customers.forEach(c -> System.out.println("[" + c.getId() + "] " + c.getName() + " | " + c.getEmail() + " | " + c.getPhone()));
    }
}
