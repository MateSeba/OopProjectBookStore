package ec.edu.espe.projectBookstore.action;

import ec.edu.espe.projectBookstore.controller.BookstoreData;
import ec.edu.espe.projectBookstore.model.*;
import ec.edu.espe.projectBookstore.view.AppContext;
import ec.edu.espe.projectBookstore.view.ConsoleIO;
import ec.edu.espe.projectBookstore.view.Session;

import java.util.Date;

import static ec.edu.espe.projectBookstore.controller.Lists.nextId;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class CheckoutActions {

    private final BookstoreData data;
    private final ConsoleIO io;
    private final Session session;

    public CheckoutActions(AppContext appContext) {
        this.data = appContext.data;
        this.io = appContext.io;
        this.session = appContext.session;
    }

    public void checkout() {
        System.out.println("--- Checkout ---");
        ShoppingCart cart = session.getCart();
        if (cart.getItems().isEmpty()) {
            System.out.println("Your cart is empty. Add books first.");
            return;
        }
        System.out.println(cart.getContent());

        Address address = askShippingAddress();
        String method = askPaymentMethod();
        if (method == null) {
            System.out.println("Error: invalid payment method.");
            return;
        }

        try {
            cart.updateStock();   // checks the stock of the whole cart and subtracts it
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        Order order = createOrder(cart, address, method);
        data.orders.add(order);
        pay(order);
    }

    private Address askShippingAddress() {
        if (io.readLine("Ship to your registered address? (y/n): ").trim().equalsIgnoreCase("y")) {
            return session.getCustomer().getAddress();
        }
        System.out.println("\n--- Delivery Address Details ---");
        return io.readAddress();
    }

    private String askPaymentMethod() {
        return switch (io.readInt("Payment method (1=Tarjeta, 2=Efectivo, 3=Transferencia): ")) {
            case 1 -> "Tarjeta";
            case 2 -> "Efectivo";
            case 3 -> "Transferencia";
            default -> null;
        };
    }

    private Order createOrder(ShoppingCart cart, Address address, String method) {
        Order order = new Order(nextId(data.orders, Order::getId), new Date(), 0, address, "Pending", method);
        order.setCustomer(session.getCustomer());
        for (OrderDetail item : cart.getItems()) {
            order.addDetail(new OrderDetail(item.getId(), item.getQuantity(), item.getBook(), item.getUnitPrice()));
        }
        return order;
    }

    private void pay(Order order) {
        System.out.println("\n--- Processing Payment ---");
        Payment payment = new Payment(order.getId(), order.getTotal(), new Date(),
                order.getPaymentMethod(), "Pending", "REF-" + order.getId());
        payment.processPayment();
        data.payments.add(payment);

        if (!payment.validatePayment()) {
            payment.setStatus("Failed");
            order.cancelOrder();
            returnStock(order);
            System.out.println("Payment Failed. The order was cancelled.");
            return;
        }

        payment.setStatus("Completed");
        order.confirmOrder();
        payment.generateReceipt();
        System.out.println("Payment Successful! Payment Ref: " + payment.getReferenceNumber());
        System.out.println("Order Confirmed! Order ID: " + order.getId() + " | Total: $" + order.getTotal());

        assignDelivery(order);
        session.getCart().clearShoppingCart();
    }

    private void assignDelivery(Order order) {
        DeliveryAssignment assignment = data.assignDriver(order);
        if (assignment == null) {
            System.out.println("There are no delivery drivers registered yet.");
            return;
        }
        System.out.println("Delivery Driver Assigned: " + assignment.getDriver().getName()
                + " [Plate: " + assignment.getDriver().getVehiclePlate() + "]");
        System.out.println("Delivery Address: " + order.getShippingAddress().getFormattedAddress());
    }

    private void returnStock(Order order) {
        for (OrderDetail detail : order.getDetails()) {
            if (detail.getBook() != null) {
                detail.getBook().updateStock(detail.getQuantity());
            }
        }
    }
}
