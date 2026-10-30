package ec.edu.espe.projectBookstore.menu;

import ec.edu.espe.projectBookstore.action.CustomerAccountActions;
import ec.edu.espe.projectBookstore.view.AppContext;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class CustomerAccessMenu extends Menu {

    private final CustomerAccountActions account;
    private final CustomerMenu customerMenu;

    public CustomerAccessMenu(AppContext appContext) {
        super(appContext, "CUSTOMER ACCESS", "Back", "Choose an option: ", "Returning to Main Menu...");
        this.account = new CustomerAccountActions(appContext);
        this.customerMenu = new CustomerMenu(appContext);

        add("Register (new customer)", account::register);
        add("Log in", this::login);
    }

    private void login() {
        if (account.login()) {
            customerMenu.run();
            appContext.session.end();
        }
    }

    @Override
    protected boolean shouldContinue(int option) {
        return option != 0 && !appContext.session.isLoggedIn();
    }
}
