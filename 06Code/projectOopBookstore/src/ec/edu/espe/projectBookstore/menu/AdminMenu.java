package ec.edu.espe.projectBookstore.menu;

import ec.edu.espe.projectBookstore.action.AdminActions;
import ec.edu.espe.projectBookstore.action.CatalogActions;
import ec.edu.espe.projectBookstore.action.StockActions;
import ec.edu.espe.projectBookstore.view.AppContext;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class AdminMenu extends Menu {

    public AdminMenu(AppContext appContext) {
        super(appContext, "ADMIN MENU", "Back", "Choose an admin option: ", "Returning to Main Menu...");
        CatalogActions catalogActions = new CatalogActions(appContext);
        StockActions stockActions = new StockActions(appContext);
        AdminActions adminActions = new AdminActions(appContext);

        add("Add author", catalogActions::addAuthor);
        add("Add publisher", catalogActions::addPublisher);
        add("Add category", catalogActions::addCategory);
        add("Add book", catalogActions::addBook);
        add("List authors, publishers, categories", appContext.catalog::listAuthorsPublishersAndCategories);
        add("List books", appContext.catalog::listBooks);
        add("Search books", appContext.catalog::searchBooks);
        add("Update stock", stockActions::updateStock);
        add("Show low-stock books", stockActions::showLowStockBooks);
        add("Edit book price", stockActions::editBookPrice);
        add("List all orders", adminActions::listAllOrders);
        add("Complete a delivery", adminActions::completeDelivery);
        add("List customers", adminActions::listCustomers);
    }
}
