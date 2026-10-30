package ec.edu.espe.projectBookstore.view;

import ec.edu.espe.projectBookstore.model.Customer;
import ec.edu.espe.projectBookstore.model.ShoppingCart;

import java.util.Date;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class Session {

    private Customer customer;
    private ShoppingCart cart;

    public void start(Customer customer) {
        this.customer = customer;
        this.cart = new ShoppingCart(customer.getId(), new Date(), 0);
    }

    public void end() {
        customer = null;
        cart = null;
    }

    public boolean isLoggedIn() {
        return customer != null;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ShoppingCart getCart() {
        return cart;
    }
}
