package ec.edu.espe.projectBookstore.action;

import ec.edu.espe.projectBookstore.controller.BookstoreData;
import ec.edu.espe.projectBookstore.model.DeliveryAssignment;
import ec.edu.espe.projectBookstore.model.Order;
import ec.edu.espe.projectBookstore.view.AppContext;
import ec.edu.espe.projectBookstore.view.ConsoleIO;
import ec.edu.espe.projectBookstore.view.OrderDisplay;
import ec.edu.espe.projectBookstore.view.Session;

import java.util.List;

import static ec.edu.espe.projectBookstore.controller.Lists.find;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class CustomerOrderActions {

    private final BookstoreData data;
    private final ConsoleIO io;
    private final Session session;

    public CustomerOrderActions(AppContext appContext) {
        this.data = appContext.data;
        this.io = appContext.io;
        this.session = appContext.session;
    }

    public void trackOrder() {
        System.out.println("--- Order & Delivery Tracking ---");
        Order order = askOwnOrder();
        if (order == null) {
            return;
        }
        OrderDisplay.print(order);
        DeliveryAssignment assignment = data.deliveryOf(order);
        if (assignment == null) {
            System.out.println("Delivery Status: No delivery assignment created yet.");
            return;
        }
        System.out.println("Delivery Status: " + assignment.getStatus());
        if (assignment.getDriver() != null) {
            System.out.println("Driver: " + assignment.getDriver().getName() + " (" + assignment.getDriver().getPhone() + ")");
        }
    }

    public void listMyOrders() {
        System.out.println("--- My orders ---");
        List<Order> mine = data.ordersOf(session.getCustomer());
        if (mine.isEmpty()) {
            System.out.println("You have no orders yet.");
        }
        mine.forEach(OrderDisplay::print);
    }

    private Order askOwnOrder() {
        List<Order> mine = data.ordersOf(session.getCustomer());
        if (mine.isEmpty()) {
            System.out.println("You have no orders yet.");
            return null;
        }
        mine.forEach(OrderDisplay::printSummary);
        Order order = find(mine, io.readInt("Enter Order ID: "), Order::getId);
        if (order == null) {
            System.out.println("Error: that order is not in your history.");
        }
        return order;
    }
}
