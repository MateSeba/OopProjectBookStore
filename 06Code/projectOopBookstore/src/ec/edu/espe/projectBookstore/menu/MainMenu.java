package ec.edu.espe.projectBookstore.menu;

import ec.edu.espe.projectBookstore.view.AppContext;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class MainMenu extends Menu {

    public MainMenu(AppContext appContext) {
        super(appContext, "WELCOME TO BOOKSTORE SYSTEM", "Exit System", "Choose an option: ",
                "Goodbye! Thank you for using Bookstore System.");
        add("Admin Menu", new AdminMenu(appContext)::run);
        add("Customer Access", new CustomerAccessMenu(appContext)::run);
        add("Delivery Driver Menu", new DeliveryDriverMenu(appContext)::run);
        add("Reports (CSV)", new ReportsMenu(appContext)::run);
    }
}
