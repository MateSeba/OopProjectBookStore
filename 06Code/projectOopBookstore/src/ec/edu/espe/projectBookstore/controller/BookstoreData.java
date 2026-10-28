package ec.edu.espe.projectBookstore.controller;

import ec.edu.espe.projectBookstore.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static ec.edu.espe.projectBookstore.controller.Lists.nextId;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class BookstoreData {

    public static final int REORDER_LEVEL = 3;
    private static final JsonStorage STORAGE = new JsonStorage("data");

    public final List<Author> authors = new ArrayList<>();
    public final List<Publisher> publishers = new ArrayList<>();
    public final List<Category> categories = new ArrayList<>();
    public final List<Book> books = new ArrayList<>();
    public final List<Customer> customers = new ArrayList<>();
    public final List<Order> orders = new ArrayList<>();
    public final List<Payment> payments = new ArrayList<>();
    public final List<DeliveryDriver> drivers = new ArrayList<>();
    public final List<DeliveryAssignment> deliveryAssignments = new ArrayList<>();
    public final List<Review> reviews = new ArrayList<>();
    public final List<Notification> notifications = new ArrayList<>();
    
    public static BookstoreData load() {
        try {
            if (STORAGE.hasData()) {
                BookstoreData loaded = STORAGE.load();
                System.out.println("Data loaded from the JSON files (folder 'data').");
                return loaded;
            }
            System.out.println("No JSON files found in the 'data' folder. Starting with an empty store.");
        } catch (Exception e) {
            System.out.println("Could not read the JSON files (" + e.getMessage() + "). Starting with an empty store.");
        }
        return new BookstoreData();
    }

    public void save() {
        try {
            STORAGE.save(this);
        } catch (IOException e) {
            System.out.println("Warning: the data could not be saved: " + e.getMessage());
        }
    }

    public List<Order> ordersOf(Customer customer) {
        return orders.stream()
                .filter(o -> o.getCustomer() != null && o.getCustomer().getId() == customer.getId())
                .toList();
    }

    public DeliveryAssignment deliveryOf(Order order) {
        return deliveryAssignments.stream()
                .filter(a -> a.getOrder() != null && a.getOrder().getId() == order.getId())
                .findFirst()
                .orElse(null);
    }
    
    public DeliveryAssignment assignDriver(Order order) {
        DeliveryDriver driver = drivers.stream()
                .min(Comparator.comparingLong(this::activeDeliveries))
                .orElse(null);
        if (driver == null) {
            return null;
        }
        DeliveryAssignment assignment = new DeliveryAssignment(nextId(deliveryAssignments, DeliveryAssignment::getId),
                order, driver, new Date(), "Pending");
        assignment.assignDriver(driver);
        deliveryAssignments.add(assignment);
        return assignment;
    }

    private long activeDeliveries(DeliveryDriver driver) {
        return deliveryAssignments.stream()
                .filter(a -> a.getDriver() == driver && !"Completed".equalsIgnoreCase(a.getStatus()))
                .count();
    }

    public void addNotification(Customer customer, String message) {
        if (customer != null) {
            notifications.add(new Notification(nextId(notifications, Notification::getId),
                    customer, message, new Date(), false));
        }
    }
}

