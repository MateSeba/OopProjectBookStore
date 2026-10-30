package ec.edu.espe.projectBookstore.view;

import ec.edu.espe.projectBookstore.controller.BookstoreData;
import ec.edu.espe.projectBookstore.model.Book;

import java.util.List;

import static ec.edu.espe.projectBookstore.controller.Lists.find;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class CatalogDisplay {

    private final BookstoreData data;
    private final ConsoleIO io;

    public CatalogDisplay(BookstoreData data, ConsoleIO io) {
        this.data = data;
        this.io = io;
    }

    public static String bookLine(Book b) {
        return "[" + b.getId() + "] " + b.getInfo();
    }

    public void listBooks() {
        System.out.println("--- Books ---");
        if (data.books.isEmpty()) {
            System.out.println("(no books)");
        }
        data.books.forEach(b -> System.out.println(bookLine(b)));
    }

    public void searchBooks() {
        System.out.println("--- Search books by title ---");
        String query = io.readLine("Title contains: ").trim().toLowerCase();
        List<Book> result = data.books.stream().filter(b -> b.getTitle().toLowerCase().contains(query)).toList();

        if (result.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println(result.size() + " book(s) found:");
            result.forEach(b -> System.out.println(bookLine(b)));
        }
    }


    public Book selectBook(String prompt) {
        if (data.books.isEmpty()) {
            System.out.println("There are no books yet.");
            return null;
        }
        listBooks();
        Book book = find(data.books, io.readInt(prompt), Book::getId);
        if (book == null) {
            System.out.println("Error: that book id does not exist.");
        }
        return book;
    }

    public void listAuthorsPublishersAndCategories() {
        System.out.println("--- Authors ---");
        printAuthors();
        System.out.println("\n--- Publishers ---");
        printPublishers();
        System.out.println("\n--- Categories ---");
        printCategories();
    }

    public void printAuthors() {
        if (data.authors.isEmpty()) {
            System.out.println("(no authors)");
        }
        data.authors.forEach(a -> System.out.println("[" + a.getId() + "] " + a.getName() + " (" + a.getNationality() + ")"));
    }

    public void printPublishers() {
        if (data.publishers.isEmpty()) {
            System.out.println("(no publishers)");
        }
        data.publishers.forEach(p -> System.out.println("[" + p.getId() + "] " + p.getName()));
    }

    public void printCategories() {
        if (data.categories.isEmpty()) {
            System.out.println("(no categories)");
        }
        data.categories.forEach(c -> System.out.println("[" + c.getId() + "] " + c.getName()));
    }
}
