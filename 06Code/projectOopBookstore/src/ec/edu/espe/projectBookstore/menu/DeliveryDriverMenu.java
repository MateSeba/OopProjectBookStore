package ec.edu.espe.projectBookstore.menu;

import ec.edu.espe.projectBookstore.action.DeliveryDriverActions;
import ec.edu.espe.projectBookstore.view.AppContext;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class DeliveryDriverMenu extends Menu {

    public DeliveryDriverMenu(AppContext appContext) {
        super(appContext, "DELIVERY DRIVER MENU", "Back", "Choose a driver option: ", "Returning to Main Menu...");
        DeliveryDriverActions driverActions = new DeliveryDriverActions(appContext);

        add("View assigned deliveries", driverActions::viewAssignedDeliveries);
        add("View delivery address", driverActions::viewDeliveryAddress);
        add("Update delivery status", driverActions::updateDeliveryStatus);
    }
}
