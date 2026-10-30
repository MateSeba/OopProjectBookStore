package ec.edu.espe.projectBookstore.menu;

import ec.edu.espe.projectBookstore.action.CartActions;
import ec.edu.espe.projectBookstore.action.CheckoutActions;
import ec.edu.espe.projectBookstore.action.CustomerOrderActions;
import ec.edu.espe.projectBookstore.view.AppContext;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class CustomerMenu extends Menu {

    public CustomerMenu(AppContext appContext) {
        super(appContext, "CUSTOMER MENU", "Log out", "Choose a customer option: ", "Logging out...");
        CartActions cartActions = new CartActions(appContext);
        CheckoutActions checkoutActions = new CheckoutActions(appContext);
        CustomerOrderActions orderActions = new CustomerOrderActions(appContext);

        add("View catalog (list books)", appContext.catalog::listBooks);
        add("Search book by title", appContext.catalog::searchBooks);
        add("Add book to cart", cartActions::addToCart);
        add("View cart", cartActions::viewCart);
        add("Remove book from cart", cartActions::removeFromCart);
        add("Checkout (create order & pay)", checkoutActions::checkout);
        add("Track order & delivery status", orderActions::trackOrder);
        add("My orders", orderActions::listMyOrders);
    }
}
