package ec.edu.espe.projectBookstore.action;

import ec.edu.espe.projectBookstore.controller.BookstoreData;
import ec.edu.espe.projectBookstore.model.*;
import ec.edu.espe.projectBookstore.view.AppContext;
import ec.edu.espe.projectBookstore.view.CatalogDisplay;
import ec.edu.espe.projectBookstore.view.ConsoleIO;

import static ec.edu.espe.projectBookstore.controller.Lists.find;
import static ec.edu.espe.projectBookstore.controller.Lists.nextId;
import static ec.edu.espe.projectBookstore.view.TextUtil.clean;
import static ec.edu.espe.projectBookstore.view.TextUtil.requireText;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class CatalogActions {

    private final BookstoreData data;
    private final ConsoleIO io;
    private final CatalogDisplay catalog;

    public CatalogActions(AppContext appContext) {
        this.data = appContext.data;
        this.io = appContext.io;
        this.catalog = appContext.catalog;
    }

    public void addAuthor() {
        System.out.println("--- Add author ---");
        String name = io.readLine("Name: ");
        try {
            requireText(name, "The author name");
            Author author = new Author(nextId(data.authors, Author::getId), name.trim(),
                    clean(io.readLine("Nationality: ")), clean(io.readLine("Biography: ")));
            data.authors.add(author);
            System.out.println("Author added: " + author);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void addPublisher() {
        System.out.println("--- Add publisher ---");
        String name = io.readLine("Name: ");
        try {
            requireText(name, "The publisher name");
            Publisher publisher = new Publisher(nextId(data.publishers, Publisher::getId), name.trim(),
                    clean(io.readLine("Description: ")));
            data.publishers.add(publisher);
            System.out.println("Publisher added: " + publisher);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void addCategory() {
        System.out.println("--- Add category ---");
        String name = io.readLine("Name: ");
        try {
            requireText(name, "The category name");
            Category category = new Category(nextId(data.categories, Category::getId), name.trim(),
                    clean(io.readLine("Description: ")));
            data.categories.add(category);
            System.out.println("Category added: " + category);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void addBook() {
        System.out.println("--- Add book ---");
        if (data.authors.isEmpty() || data.publishers.isEmpty() || data.categories.isEmpty()) {
            System.out.println("You need at least one author, one publisher and one category first.");
            return;
        }

        String title = io.readLine("Title: ");
        String isbn = io.readLine("ISBN: ");
        float price = io.readFloat("Price: ");
        int stock = io.readInt("Stock: ");
        String description = io.readLine("Description: ");

        catalog.printAuthors();
        Author author = find(data.authors, io.readInt("Author id: "), Author::getId);
        catalog.printPublishers();
        Publisher publisher = find(data.publishers, io.readInt("Publisher id: "), Publisher::getId);
        catalog.printCategories();
        Category category = find(data.categories, io.readInt("Category id: "), Category::getId);

        if (author == null || publisher == null || category == null) {
            System.out.println("Error: the author, publisher or category id does not exist.");
            return;
        }

        try {
            requireText(title, "The title");
            requireText(isbn, "The ISBN");
            if (price < 0 || stock < 0) {
                throw new IllegalArgumentException("Price/Stock cannot be negative");
            }
            if (data.books.stream().anyMatch(b -> b.getIsbn().equalsIgnoreCase(isbn.trim()))) {
                throw new IllegalArgumentException("A book with ISBN " + isbn.trim() + " already exists");
            }
            Book book = new Book(nextId(data.books, Book::getId), title.trim(), isbn.trim(), price, stock,
                    clean(description), author, publisher, category);
            data.books.add(book);
            System.out.println("Book added: " + CatalogDisplay.bookLine(book));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
