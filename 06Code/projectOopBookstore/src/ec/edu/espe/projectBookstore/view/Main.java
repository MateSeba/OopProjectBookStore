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

    private static final List<Order> orders = new ArrayList<>();
    private static final List<Payment> payments = new ArrayList<>();
    private static final List<DeliveryAssignment> deliveryAssignments = new ArrayList<>();
    private static final List<DeliveryDriver> drivers = new ArrayList<>();

    public static void main(String[] args) {
        preloadInitialData();

        int roleOption;
        do {
            printRoleMenu();
            roleOption = readInt("Choose your role / select option: ");
            System.out.println();

            switch (roleOption) {
                case 1 -> adminMenu();
                case 2 -> customerMenu();
                case 0 -> System.out.println("Goodbye! Thank you for using Bookstore System.");
                default -> System.out.println("Invalid option. Please choose 1, 2 or 0.");
            }
        } while (roleOption != 0);
    }

    private static void preloadInitialData() {
        drivers.add(new DeliveryDriver("D-01", "Carlos Perez", "PBA-1234", "0991234567", "Available", null));
        drivers.add(new DeliveryDriver("D-02", "Juan Lopez", "PBB-5678", "0997654321", "Available", null));

        Author a1 = new Author(1, "Gabriel Garcia Marquez", "Colombiana", "Premio Nobel de Literatura");
        Author a2 = new Author(2, "Mario Vargas Llosa", "Peruana", "Escritor y ensayista");
        authors.add(a1);
        authors.add(a2);

        Publisher p1 = new Publisher(1, "Editorial Sudamericana", "Editorial de literatura hispanoamericana");
        Publisher p2 = new Publisher(2, "Penguin Random House", "Grupo editorial internacional");
        publishers.add(p1);
        publishers.add(p2);

        Category c1 = new Category(1, "Ficcion", "Novelas y obras narrativas");
        Category c2 = new Category(2, "Realismo Magico", "Obras con elementos fantasticos");
        categories.add(c1);
        categories.add(c2);

        Book b1 = new Book(1, "Cien Anos de Soledad", "978-0307474728", 25.50f, 10, "Obra maestra hispanoamericana", a1, p1, c2);
        Book b2 = new Book(2, "La Ciudad y los Perros", "978-8420471839", 18.00f, 8, "Novela del Colegio Militar", a2, p2, c1);
        Book b3 = new Book(3, "El Amor en los Tiempos del Colera", "978-0307389732", 22.00f, 5, "Historia de amor entre Fermina y Florentino", a1, p1, c2);

        books.add(b1);
        books.add(b2);
        books.add(b3);
    }

    private static void printRoleMenu() {
        System.out.println("\n====================================");
        System.out.println("      WELCOME TO BOOKSTORE SYSTEM   ");
        System.out.println("====================================");
        System.out.println(" 1. Administrator Menu");
        System.out.println(" 2. Customer Menu");
        System.out.println(" 0. Exit System");
        System.out.println("====================================");
    }

    private static void adminMenu() {
        int option;
        do {
            printAdminMenu();
            option = readInt("Choose an admin option: ");
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
                case 0 -> System.out.println("Returning to Main Role Menu...");
                default -> System.out.println("Invalid option. Please choose a valid number.");
            }
        } while (option != 0);
    }

    private static void printAdminMenu() {
        System.out.println("\n========== ADMIN MENU ==========");
        System.out.println(" 1. Add author | 2. Add publisher | 3. Add category | 4. Add book");
        System.out.println(" 5. List authors, publishers, categories | 6. List books");
        System.out.println(" 7. Search books | 8. Update stock | 9. Show low-stock | 0. Back");
        System.out.println("=================================");
    }

    private static void customerMenu() {
        int option;
        do {
            printCustomerMenu();
            option = readInt("Choose a customer option: ");
            System.out.println();

            switch (option) {
                case 1 -> listBooks();
                case 2 -> searchBooks();
                case 3 -> createOrderAndProcessPayment();
                case 4 -> trackOrderAndDelivery();
                case 0 -> System.out.println("Returning to Main Role Menu...");
                default -> System.out.println("Invalid option. Please choose a valid number.");
            }
        } while (option != 0);
    }

    private static void printCustomerMenu() {
        System.out.println("\n========== CUSTOMER MENU ==========");
        System.out.println(" 1. View Catalog (List Books)");
        System.out.println(" 2. Search Book by Title");
        System.out.println(" 3. Buy Book (Create Order & Pay)");
        System.out.println(" 4. Track Order & Delivery Status");
        System.out.println(" 0. Back");
        System.out.println("====================================");
    }

    private static void createOrderAndProcessPayment() {
        System.out.println("--- Purchase Book (Create Order) ---");
        if (books.isEmpty()) {
            System.out.println("No books available in the store right now.");
            return;
        }

        listBooks();
        Book selectedBook = findById(books, readInt("Enter Book ID to buy: "));
        if (selectedBook == null) {
            System.out.println("Error: Book ID does not exist.");
            return;
        }

        if (selectedBook.getStock() <= 0) {
            System.out.println("Sorry, this book is currently out of stock.");
            return;
        }

        int qty = readInt("Enter quantity to buy: ");
        if (qty <= 0 || qty > selectedBook.getStock()) {
            System.out.println("Error: Invalid quantity or insufficient stock.");
            return;
        }

        float totalAmount = selectedBook.getPrice() * qty;
        System.out.println("Total Amount: $" + totalAmount);

        System.out.println("\n--- Delivery Address Details ---");
        String mainStreet = readLine("Main Street: ");
        String secondaryStreet = readLine("Secondary Street: ");
        String sector = readLine("Sector (e.g. Norte/Sur): ");
        String reference = readLine("Reference: ");
        Address deliveryAddress = new Address(mainStreet, secondaryStreet, sector, reference);

        String paymentMethod = readLine("Payment Method (Tarjeta/Efectivo/Transferencia): ");
        Order order = new Order(nextId(orders), new Date(), totalAmount, deliveryAddress, "Pending", paymentMethod);
        orders.add(order);
        selectedBook.updateStock(-qty);

        System.out.println("\n--- Processing Payment ---");
        String refNumber = "REF-" + (100 + payments.size());
        Payment payment = new Payment(nextId(payments), totalAmount, new Date(), paymentMethod, "Completed", refNumber);
        payments.add(payment);

        if (payment.validatePayment()) {
            order.confirmOrder();
            System.out.println("Payment Successful! Payment Ref: " + payment.getReferenceNumber());
            System.out.println("Order Confirmed! Order ID: " + order.getId());

            if (!drivers.isEmpty()) {
                DeliveryDriver assignedDriver = drivers.get(0);
                DeliveryAssignment assignment = new DeliveryAssignment(nextId(deliveryAssignments), order, assignedDriver, new Date(), "Pending");
                assignment.assignDriver(assignedDriver);
                deliveryAssignments.add(assignment);

                System.out.println("Delivery Driver Assigned: " + assignedDriver.getName() + " [Plate: " + assignedDriver.getVehiclePlate() + "]");
                System.out.println("Delivery Address: " + deliveryAddress.getFormattedAddress());
            }
        } else {
            System.out.println("Payment Failed. Order remained Pending.");
        }
    }

    private static void trackOrderAndDelivery() {
        System.out.println("--- Order & Delivery Tracking ---");
        if (orders.isEmpty()) {
            System.out.println("No orders found in the system.");
            return;
        }

        int orderId = readInt("Enter Order ID to track: ");
        Order order = findById(orders, orderId);

        if (order == null) {
            System.out.println("Error: Order ID not found.");
            return;
        }

        System.out.println("Order ID: " + order.getId() + " | Status: " + order.getStatus() + " | Total: $" + order.getTotal());

        DeliveryAssignment assignment = deliveryAssignments.stream()
                .filter(a -> a.getOrder() != null && a.getOrder().getId() == orderId)
                .findFirst()
                .orElse(null);

        if (assignment != null) {
            System.out.println("Delivery Status: " + assignment.getStatus());
            if (assignment.getDriver() != null) {
                System.out.println("Driver: " + assignment.getDriver().getName() + " (" + assignment.getDriver().getPhone() + ")");
            }
        } else {
            System.out.println("Delivery Status: No delivery assignment created yet.");
        }
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
        else books.forEach(b -> System.out.println("[" + b.getId() + "] " + b.getInfo() + " | Stock: " + b.getStock() + " | Price: $" + b.getPrice()));
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

   // @SuppressWarnings("unchecked")
    private static <T> T findById(List<T> list, int id) {
        for (T item : list) {
            if (item instanceof Author a && a.getId() == id) return (T) a;
            if (item instanceof Publisher p && p.getId() == id) return (T) p;
            if (item instanceof Category c && c.getId() == id) return (T) c;
            if (item instanceof Book b && b.getId() == id) return (T) b;
            if (item instanceof Order o && o.getId() == id) return (T) o;
        }
        return null;
    }

    private static Book findBookByIsbn(String isbn) {
        return books.stream().filter(b -> b.getIsbn().equalsIgnoreCase(isbn.trim())).findFirst().orElse(null);
    }

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