package ec.edu.espe.projectBookstore.action;

import ec.edu.espe.projectBookstore.controller.BookstoreData;
import ec.edu.espe.projectBookstore.model.Book;
import ec.edu.espe.projectBookstore.model.Inventory;
import ec.edu.espe.projectBookstore.view.AppContext;
import ec.edu.espe.projectBookstore.view.CatalogDisplay;
import ec.edu.espe.projectBookstore.view.ConsoleIO;

import java.util.Date;
import java.util.List;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class StockActions {

    private final BookstoreData data;
    private final ConsoleIO io;
    private final CatalogDisplay catalog;

    public StockActions(AppContext appContext) {
        this.data = appContext.data;
        this.io = appContext.io;
        this.catalog = appContext.catalog;
    }

    public void updateStock() {
        System.out.println("--- Update book stock ---");
        Book book = catalog.selectBook("Book id: ");
        if (book == null) {
            return;
        }
        int quantity = io.readInt("Quantity (positive to add, negative to subtract): ");
        int before = book.getStock();
        book.updateStock(quantity);

        if (book.getStock() != before || quantity == 0) {
            System.out.println("Stock updated: " + CatalogDisplay.bookLine(book));
        } else {
            System.out.println("Error: stock cannot go below 0 (current: " + book.getStock() + ").");
        }
    }

    public void showLowStockBooks() {
        System.out.println("--- Low-stock books (stock <= " + BookstoreData.REORDER_LEVEL + ") ---");
        List<Book> result = data.books.stream()
                .filter(b -> new Inventory(new Date(), BookstoreData.REORDER_LEVEL, b).checkReorderStatus())
                .toList();

        if (result.isEmpty()) {
            System.out.println("No books need to be reordered.");
        }
        result.forEach(b -> System.out.println(CatalogDisplay.bookLine(b)));
    }

    public void editBookPrice() {
        System.out.println("--- Edit book price ---");
        Book book = catalog.selectBook("Book id: ");
        if (book == null) {
            return;
        }
        float price = io.readFloat("New price: ");
        if (price < 0) {
            System.out.println("Error: the price cannot be negative.");
            return;
        }
        book.setPrice(price);
        System.out.println("Price updated: " + CatalogDisplay.bookLine(book));
    }
}
