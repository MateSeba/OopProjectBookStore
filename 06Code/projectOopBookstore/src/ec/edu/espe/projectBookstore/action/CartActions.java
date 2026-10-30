package ec.edu.espe.projectBookstore.action;

import ec.edu.espe.projectBookstore.controller.BookstoreData;
import ec.edu.espe.projectBookstore.model.Book;
import ec.edu.espe.projectBookstore.model.OrderDetail;
import ec.edu.espe.projectBookstore.model.ShoppingCart;
import ec.edu.espe.projectBookstore.view.AppContext;
import ec.edu.espe.projectBookstore.view.CatalogDisplay;
import ec.edu.espe.projectBookstore.view.ConsoleIO;
import ec.edu.espe.projectBookstore.view.Session;

import static ec.edu.espe.projectBookstore.controller.Lists.find;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class CartActions {

    private final BookstoreData data;
    private final ConsoleIO io;
    private final CatalogDisplay catalog;
    private final Session session;

    public CartActions(AppContext appContext) {
        this.data = appContext.data;
        this.io = appContext.io;
        this.catalog = appContext.catalog;
        this.session = appContext.session;
    }

    public void viewCart() {
        System.out.println("--- Shopping cart ---\n" + session.getCart().getContent());
    }

    public void addToCart() {
        System.out.println("--- Add book to cart ---");
        ShoppingCart cart = session.getCart();
        Book book = catalog.selectBook("Enter Book ID: ");
        if (book == null) {
            return;
        }
        if (!book.checkAvailability()) {
            System.out.println("Sorry, this book is currently out of stock.");
            return;
        }
        int qty = io.readInt("Quantity: ");
        int inCart = cart.getItems().stream()
                .filter(item -> item.getBook().getId() == book.getId())
                .mapToInt(OrderDetail::getQuantity)
                .sum();
        if (qty <= 0 || qty + inCart > book.getStock()) {
            System.out.println("Error: invalid quantity or insufficient stock (available: " + book.getStock() + ").");
            return;
        }
        try {
            cart.addBook(book, qty);
            System.out.println("Added to cart. Cart subtotal: $" + cart.calculateSubtotal());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void removeFromCart() {
        System.out.println("--- Remove book from cart ---");
        ShoppingCart cart = session.getCart();
        if (cart.getItems().isEmpty()) {
            System.out.println("The shopping cart is empty.");
            return;
        }
        System.out.println(cart.getContent());
        Book book = find(data.books, io.readInt("Book ID to remove: "), Book::getId);
        if (book != null && cart.removeBook(book)) {
            System.out.println("Book removed. Cart subtotal: $" + cart.calculateSubtotal());
        } else {
            System.out.println("Error: that book is not in your cart.");
        }
    }
}
