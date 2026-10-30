package ec.edu.espe.projectBookstore.action;

import ec.edu.espe.projectBookstore.controller.BookstoreData;
import ec.edu.espe.projectBookstore.model.Address;
import ec.edu.espe.projectBookstore.model.Customer;
import ec.edu.espe.projectBookstore.view.AppContext;
import ec.edu.espe.projectBookstore.view.ConsoleIO;
import ec.edu.espe.projectBookstore.view.Session;

import static ec.edu.espe.projectBookstore.controller.Lists.nextId;
import static ec.edu.espe.projectBookstore.view.TextUtil.clean;
import static ec.edu.espe.projectBookstore.view.TextUtil.requireText;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class CustomerAccountActions {

    private final BookstoreData data;
    private final ConsoleIO io;
    private final Session session;

    public CustomerAccountActions(AppContext appContext) {
        this.data = appContext.data;
        this.io = appContext.io;
        this.session = appContext.session;
    }

    public void register() {
        System.out.println("--- Customer registration ---");
        String name = io.readLine("Full name: ");
        String email = io.readLine("Email: ");
        String phone = io.readLine("Phone: ");
        int age = io.readInt("Age (18-100): ");
        String password = io.readLine("Password (min. 8 characters): ");
        System.out.println("Address details:");
        Address address = io.readAddress();

        try {
            requireText(name, "The name");
            requireText(email, "The email");
            if (!email.contains("@")) {
                throw new IllegalArgumentException("The email is not valid");
            }
            Customer customer = new Customer(nextId(data.customers, Customer::getId),
                    name.trim(), email.trim(), clean(phone), age, password, address);
            System.out.println(customer.register(data.customers)
                    ? "Registration successful! You can log in now."
                    : "Error: that email is already registered.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** Asks for the credentials and starts the session. Returns true when the login worked. */
    public boolean login() {
        System.out.println("--- Log in ---");
        Customer customer = Customer.login(data.customers, io.readLine("Email: "), io.readLine("Password: "));
        if (customer == null) {
            System.out.println("Error: wrong email or password.");
            return false;
        }
        session.start(customer);
        System.out.println("Welcome, " + customer.getName() + "!");
        return true;
    }
}
