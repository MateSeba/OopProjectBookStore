package ec.edu.espe.projectBookstore.view;

import ec.edu.espe.projectBookstore.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static final int REORDER_LEVEL = 3;
    private static final Scanner scanner = new Scanner(System.in);

    private static final List<Author> authors = new ArrayList<>();
    private static final List<Publisher> publishers = new ArrayList<>();
    private static final List<Category> categories = new ArrayList<>();
    private static final List<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        int option;
        do {
            printMenu();
            option = readInt("Choose an option: ");
            System.out.println();

            switch (option) {
                case 1 -> addAuthor();
                case 2 -> addPublisher();
                case 3 -> addCategory();
                case 4 -> addBook();
                case 5 -> listAuthorsPublishersAndCategories();
                case 6 -> listBooks();
                case 7 -> searchBooks();
                case 8 -> updateStock();
                case 9 -> showLowStockBooks();
                case 0 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid option. Please choose a number from the menu.");
            }
        } while (option != 0);
    }

    private static void printMenu() {
        System.out.println("\n========== BOOKSTORE MENU ==========");
        System.out.println(" 1. Add author | 2. Add publisher | 3. Add category | 4. Add book");
        System.out.println(" 5. List authors, publishers, categories | 6. List books");
        System.out.println(" 7. Search books | 8. Update stock | 9. Show low-stock | 0. Exit");
        System.out.println("====================================");
    }

    private static void addAuthor() {
        System.out.println("--- Add author ---");
        String name = readLine("Name: ");
        try {
            requireText(name, "The author name");
            Author author = new Author(nextId(authors), name.trim(), clean(readLine("Nationality: ")), clean(readLine("Biography: ")));
            authors.add(author);
            System.out.println("Author added: " + author);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addPublisher() {
        System.out.println("--- Add publisher ---");
        String name = readLine("Name: ");
        try {
            requireText(name, "The publisher name");
            Publisher publisher = new Publisher(nextId(publishers), name.trim(), clean(readLine("Description: ")));
            publishers.add(publisher);
            System.out.println("Publisher added: " + publisher);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addCategory() {
        System.out.println("--- Add category ---");
        String name = readLine("Name: ");
        try {
            requireText(name, "The category name");
            Category category = new Category(nextId(categories), name.trim(), clean(readLine("Description: ")));
            categories.add(category);
            System.out.println("Category added: " + category);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addBook() {
        System.out.println("--- Add book ---");
        if (authors.isEmpty() || publishers.isEmpty() || categories.isEmpty()) {
            System.out.println("You need at least one author, one publisher and one category first.");
            return;
        }

        String title = readLine("Title: ");
        String isbn = readLine("ISBN: ");
        float price = readFloat("Price: ");
        int stock = readInt("Stock: ");
        String description = readLine("Description: ");

        printAuthors();
        Author author = findById(authors, readInt("Author id: "));
        printPublishers();
        Publisher publisher = findById(publishers, readInt("Publisher id: "));
        printCategories();
        Category category = findById(categories, readInt("Category id: "));

        if (author == null || publisher == null || category == null) {
            System.out.println("Error: the author, publisher or category id does not exist.");
            return;
        }

        try {
            requireText(title, "The title");
            requireText(isbn, "The ISBN");
            if (price < 0 || stock < 0) throw new IllegalArgumentException("Price/Stock cannot be negative");
            if (findBookByIsbn(isbn) != null) throw new IllegalArgumentException("A book with ISBN " + isbn.trim() + " already exists");

            Book book = new Book(nextId(books), title.trim(), isbn.trim(), price, stock, clean(description), author, publisher, category);
            books.add(book);
            System.out.println("Book added: [" + book.getId() + "] " + book.getInfo());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listAuthorsPublishersAndCategories() {
        System.out.println("--- Authors ---"); printAuthors();
        System.out.println("\n--- Publishers ---"); printPublishers();
        System.out.println("\n--- Categories ---"); printCategories();
    }

    private static void listBooks() {
        System.out.println("--- Books ---");
        if (books.isEmpty()) System.out.println("(no books)");
        else books.forEach(b -> System.out.println("[" + b.getId() + "] " + b.getInfo()));
    }

    private static void searchBooks() {
        System.out.println("--- Search books by title ---");
        String query = readLine("Title contains: ").trim().toLowerCase();
        List<Book> result = books.stream().filter(b -> b.getTitle().toLowerCase().contains(query)).toList();

        if (result.isEmpty()) System.out.println("No books found.");
        else {
            System.out.println(result.size() + " book(s) found:");
            result.forEach(b -> System.out.println("[" + b.getId() + "] " + b.getInfo()));
        }
    }

    private static void showLowStockBooks() {
        System.out.println("--- Low-stock books (stock <= " + REORDER_LEVEL + ") ---");
        List<Book> result = books.stream()
                .filter(b -> new Inventory(new Date(), REORDER_LEVEL, b).checkReorderStatus())
                .toList();

        if (result.isEmpty()) System.out.println("No books need to be reordered.");
        else result.forEach(b -> System.out.println("[" + b.getId() + "] " + b.getInfo()));
    }

    private static void updateStock() {
        System.out.println("--- Update book stock ---");
        if (books.isEmpty()) { System.out.println("There are no books yet."); return; }
        listBooks();
        Book book = findById(books, readInt("Book id: "));
        if (book == null) { System.out.println("Error: that book id does not exist."); return; }
        
        int quantity = readInt("Quantity (positive to add, negative to subtract): ");
        int before = book.getStock();
        book.updateStock(quantity);

        if (book.getStock() != before || quantity == 0) {
            System.out.println("Stock updated: [" + book.getId() + "] " + book.getInfo());
        } else {
            System.out.println("Error: stock cannot go below 0 (current: " + book.getStock() + ").");
        }
    }

    // Helper genérico para buscar por ID
    private static <T> T findById(List<T> list, int id) {
        for (T item : list) {
            if (item instanceof Author a && a.getId() == id) return item;
            if (item instanceof Publisher p && p.getId() == id) return item;
            if (item instanceof Category c && c.getId() == id) return item;
            if (item instanceof Book b && b.getId() == id) return item;
        }
        return null;
    }

    private static Book findBookByIsbn(String isbn) {
        return books.stream().filter(b -> b.getIsbn().equalsIgnoreCase(isbn.trim())).findFirst().orElse(null);
    }

    // Generador de ID unificado para cualquier lista
    private static int nextId(List<?> list) {
        return list.size() + 1;
    }

    private static void requireText(String value, String label) {
        if (value == null || value.trim().isEmpty()) throw new IllegalArgumentException(label + " cannot be empty");
    }

    private static String clean(String value) { return value == null ? "" : value.trim(); }

    private static void printAuthors() {
        if (authors.isEmpty()) System.out.println("(no authors)");
        authors.forEach(a -> System.out.println("[" + a.getId() + "] " + a.getName() + " (" + a.getNationality() + ")"));
    }

    private static void printPublishers() {
        if (publishers.isEmpty()) System.out.println("(no publishers)");
        publishers.forEach(p -> System.out.println("[" + p.getId() + "] " + p.getName()));
    }

    private static void printCategories() {
        if (categories.isEmpty()) System.out.println("(no categories)");
        categories.forEach(c -> System.out.println("[" + c.getId() + "] " + c.getName()));
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            System.exit(0);
            return "";
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            try { return Integer.parseInt(readLine(prompt).trim()); }
            catch (NumberFormatException e) { System.out.println("Please type a whole number."); }
        }
    }

    private static float readFloat(String prompt) {
        while (true) {
            try { return Float.parseFloat(readLine(prompt).trim().replace(',', '.')); }
            catch (NumberFormatException e) { System.out.println("Please type a number."); }
        }
    }
}