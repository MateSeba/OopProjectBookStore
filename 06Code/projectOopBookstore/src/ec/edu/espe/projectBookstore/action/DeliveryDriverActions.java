package ec.edu.espe.projectBookstore.action;

import ec.edu.espe.projectBookstore.controller.BookstoreData;
import ec.edu.espe.projectBookstore.model.Address;
import ec.edu.espe.projectBookstore.model.DeliveryAssignment;
import ec.edu.espe.projectBookstore.view.AppContext;
import ec.edu.espe.projectBookstore.view.ConsoleIO;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class DeliveryDriverActions {

    private final BookstoreData data;
    private final ConsoleIO io;

    public DeliveryDriverActions(AppContext appContext) {
        this.data = appContext.data;
        this.io = appContext.io;
    }

    public void viewAssignedDeliveries() {
        System.out.println("--- Assigned Deliveries ---");
        if (data.deliveryAssignments.isEmpty()) {
            System.out.println("No deliveries assigned at the moment.");
            return;
        }

        data.deliveryAssignments.forEach(da -> {
            String driverName = da.getDriver() != null ? da.getDriver().getName() : "Unassigned";
            int orderId = da.getOrder() != null ? da.getOrder().getId() : -1;
            System.out.println("Assignment ID: [" + da.getId() + "] | Order ID: " + orderId
                    + " | Driver: " + driverName + " | Status: " + da.getStatus());
        });
    }

    public void viewDeliveryAddress() {
        System.out.println("--- View Delivery Address ---");
        DeliveryAssignment assignment = askAssignment("Enter Assignment ID: ");
        if (assignment == null) {
            return;
        }

        if (assignment.getOrder() != null && assignment.getOrder().getShippingAddress() != null) {
            Address addr = assignment.getOrder().getShippingAddress();
            System.out.println("Delivery Address for Order #" + assignment.getOrder().getId() + ":");
            System.out.println(" - " + addr.getFormattedAddress());
        } else {
            System.out.println("No address associated with this order.");
        }
    }

    public void updateDeliveryStatus() {
        System.out.println("--- Update Delivery Status ---");
        DeliveryAssignment assignment = askAssignment("Enter Assignment ID to update: ");
        if (assignment == null) {
            return;
        }

        System.out.println("Current status: " + assignment.getStatus());
        System.out.println("Select new status:");
        System.out.println("1. In Transit");
        System.out.println("2. Delivered");
        System.out.println("3. Failed / Cancelled");

        String newStatus = switch (io.readInt("Choice: ")) {
            case 1 -> "In Transit";
            case 2 -> "Delivered";
            case 3 -> "Failed";
            default -> null;
        };

        if (newStatus == null) {
            System.out.println("Invalid selection. Status unchanged.");
            return;
        }
        assignment.setStatus(newStatus);
        if (assignment.getOrder() != null) {
            assignment.getOrder().setStatus(newStatus);
        }
        System.out.println("Delivery status updated successfully to: " + newStatus);
    }

    /** Asks for an assignment id; returns null (after printing the reason) when there is nothing to select. */
    private DeliveryAssignment askAssignment(String prompt) {
        if (data.deliveryAssignments.isEmpty()) {
            System.out.println("No delivery assignments available.");
            return null;
        }
        int id = io.readInt(prompt);
        DeliveryAssignment assignment = data.deliveryAssignments.stream()
                .filter(da -> da.getId() == id)
                .findFirst()
                .orElse(null);
        if (assignment == null) {
            System.out.println("Error: Delivery Assignment ID not found.");
        }
        return assignment;
    }
}
