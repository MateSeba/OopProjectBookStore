package ec.edu.espe.projectBookstore.view;

import ec.edu.espe.projectBookstore.controller.BookstoreData;
import ec.edu.espe.projectBookstore.controller.ReportService;
import ec.edu.espe.projectBookstore.model.*;

import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static ec.edu.espe.projectBookstore.controller.Lists.find;
import static ec.edu.espe.projectBookstore.controller.Lists.nextId;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BookstoreData data = BookstoreData.load();   // loads the JSON files
    private static final ReportService reports = new ReportService("reports");

    // Session of the customer that is logged in
    private static Customer current;
    private static ShoppingCart cart;

    public static void main(String[] args) {
        int option;
        do {
            showMenu("WELCOME TO BOOKSTORE SYSTEM", "Exit System",
                    "Admin Menu", "Customer Access", "Delivery Driver Menu", "Reports (CSV)");
            option = readInt("Choose an option: ");
            System.out.println();

            switch (option) {
                case 1 -> adminMenu();
                case 2 -> customerAccessMenu();
                case 3 -> deliveryDriverMenu();
                case 4 -> reportsMenu();
                case 0 -> System.out.println("Goodbye! Thank you for using Bookstore System.");
                default -> System.out.println("Invalid option. Please choose a valid number.");
            }
            data.save();   // automatic save in the JSON files
        } while (option != 0);
    }

    // ============================== MENUS ==============================

    private static void adminMenu() {
        int option;
        do {
            showMenu("ADMIN MENU", "Back",
                    "Add author", "Add publisher", "Add category", "Add book",
                    "List authors, publishers, categories", "List books", "Search books",
                    "Update stock", "Show low-stock books", "Edit book price",
                    "List all orders", "Complete a delivery", "List customers");
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
                case 10 -> editBookPrice();
                case 11 -> listAllOrders();
                case 12 -> completeDelivery();
                case 13 -> listCustomers();
                case 0 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid option. Please choose a valid number.");
            }
            data.save();
        } while (option != 0);
    }

    private static void customerAccessMenu() {
        int option;
        do {
            showMenu("CUSTOMER ACCESS", "Back", "Register (new customer)", "Log in");
            option = readInt("Choose an option: ");
            System.out.println();

            switch (option) {
                case 1 -> registerCustomer();
                case 2 -> login();
                case 0 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid option. Please choose a valid number.");
            }
            data.save();
        } while (option != 0 && current == null);
    }

    private static void customerMenu() {
        int option;
        do {
            showMenu("CUSTOMER MENU", "Log out",
                    "View catalog (list books)", "Search book by title", "Add book to cart", "View cart",
                    "Remove book from cart", "Checkout (create order & pay)", "Track order & delivery status",
                    "My orders");
            option = readInt("Choose a customer option: ");
            System.out.println();

            switch (option) {
                case 1 -> listBooks();
                case 2 -> searchBooks();
                case 3 -> addToCart();
                case 4 -> System.out.println("--- Shopping cart ---\n" + cart.getContent());
                case 5 -> removeFromCart();
                case 6 -> checkout();
                case 7 -> trackOrder();
                case 8 -> listMyOrders();
                case 0 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid option. Please choose a valid number.");
            }
            data.save();
        } while (option != 0);
    }

    private static void deliveryDriverMenu() {
        int option;
        do {
            showMenu("DELIVERY DRIVER MENU", "Back",
                    "View assigned deliveries", "View delivery address", "Update delivery status");
            option = readInt("Choose a driver option: ");
            System.out.println();

            switch (option) {
                case 1 -> viewAssignedDeliveries();
                case 2 -> viewDeliveryAddress();
                case 3 -> updateDeliveryStatus();
                case 0 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid option. Please choose a valid number.");
            }
            data.save();
        } while (option != 0);
    }

    private static void reportsMenu() {
        int option;
        do {
            showMenu("CSV REPORTS (folder 'reports')", "Back",
                    "Inventory report", "Low-stock report", "Sales report (orders)",
                    "Book performance (units sold, revenue)", "Generate all reports");
            option = readInt("Choose a report: ");
            System.out.println();

            try {
                switch (option) {
                    case 1 -> printSaved(reports.inventoryReport(data.books, BookstoreData.REORDER_LEVEL));
                    case 2 -> printSaved(reports.lowStockReport(data.books, BookstoreData.REORDER_LEVEL));
                    case 3 -> printSaved(reports.salesReport(data.orders));
                    case 4 -> printSaved(reports.bookPerformanceReport(data.books, data.orders));
                    case 5 -> {
                        printSaved(reports.inventoryReport(data.books, BookstoreData.REORDER_LEVEL));
                        printSaved(reports.lowStockReport(data.books, BookstoreData.REORDER_LEVEL));
                        printSaved(reports.salesReport(data.orders));
                        printSaved(reports.bookPerformanceReport(data.books, data.orders));
                    }
                    case 0 -> System.out.println("Returning to Main Menu...");
                    default -> System.out.println("Invalid option. Please choose a valid number.");
                }
            } catch (IOException e) {
                System.out.println("Error writing the report: " + e.getMessage());
            }
        } while (option != 0);
    }

    private static void showMenu(String title, String exitLabel, String... options) {
        System.out.println("\n========== " + title + " ==========");
        for (int i = 0; i < options.length; i++) {
            System.out.println(String.format("%3d. %s", i + 1, options[i]));
        }
        System.out.println(String.format("%3d. %s", 0, exitLabel));
    }

    private static void printSaved(Path path) {
        System.out.println("Report saved: " + path.toAbsolutePath());
    }

    // ============================== DELIVERY DRIVER OPERATIONS ==============================

    private static void viewAssignedDeliveries() {
        System.out.println("--- Assigned Deliveries ---");
        if (data.deliveryAssignments.isEmpty()) {
            System.out.println("No deliveries assigned at the moment.");
            return;
        }

        data.deliveryAssignments.forEach(da -> {
            String driverName = da.getDriver() != null ? da.getDriver().getName() : "Unassigned";
            int orderId = da.getOrder() != null ? da.getOrder().getId() : -1;
            System.out.println("Assignment ID: [" + da.getId() + "] | Order ID: " + orderId 
                    + " | Driver: " + driverName + " | Status: " + da.getStatus());
        });
    }

    private static void viewDeliveryAddress() {
        System.out.println("--- View Delivery Address ---");
        if (data.deliveryAssignments.isEmpty()) {
            System.out.println("No delivery assignments available.");
            return;
        }

        int assignmentId = readInt("Enter Assignment ID: ");
        DeliveryAssignment assignment = findAssignmentById(assignmentId);

        if (assignment == null) {
            System.out.println("Error: Delivery Assignment ID not found.");
            return;
        }

        if (assignment.getOrder() != null && assignment.getOrder().getShippingAddress() != null) {
            Address addr = assignment.getOrder().getShippingAddress();
            System.out.println("Delivery Address for Order #" + assignment.getOrder().getId() + ":");
            System.out.println(" - " + addr.getFormattedAddress());
        } else {
            System.out.println("No address associated with this order.");
        }
    }

    private static void updateDeliveryStatus() {
        System.out.println("--- Update Delivery Status ---");
        if (data.deliveryAssignments.isEmpty()) {
            System.out.println("No delivery assignments available.");
            return;
        }

        int assignmentId = readInt("Enter Assignment ID to update: ");
        DeliveryAssignment assignment = findAssignmentById(assignmentId);

        if (assignment == null) {
            System.out.println("Error: Delivery Assignment ID not found.");
            return;
        }

        System.out.println("Current status: " + assignment.getStatus());
        System.out.println("Select new status:");
        System.out.println("1. In Transit");
        System.out.println("2. Delivered");
        System.out.println("3. Failed / Cancelled");

        int choice = readInt("Choice: ");
        String newStatus = switch (choice) {
            case 1 -> "In Transit";
            case 2 -> "Delivered";
            case 3 -> "Failed";
            default -> null;
        };

        if (newStatus != null) {
            assignment.setStatus(newStatus);
            if (assignment.getOrder() != null) {
                assignment.getOrder().setStatus(newStatus);
            }
            System.out.println("Delivery status updated successfully to: " + newStatus);
        } else {
            System.out.println("Invalid selection. Status unchanged.");
        }
    }

    private static DeliveryAssignment findAssignmentById(int id) {
        return data.deliveryAssignments.stream()
                .filter(da -> da.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // ============================== ADMIN: CATALOG DATA ==============================

    private static void addAuthor() {
        System.out.println("--- Add author ---");
        String name = readLine("Name: ");
        try {
            requireText(name, "The author name");
            Author author = new Author(nextId(data.authors, Author::getId), name.trim(),
                    clean(readLine("Nationality: ")), clean(readLine("Biography: ")));
            data.authors.add(author);
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
            Publisher publisher = new Publisher(nextId(data.publishers, Publisher::getId), name.trim(),
                    clean(readLine("Description: ")));
            data.publishers.add(publisher);
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
            Category category = new Category(nextId(data.categories, Category::getId), name.trim(),
                    clean(readLine("Description: ")));
            data.categories.add(category);
            System.out.println("Category added: " + category);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addBook() {
        System.out.println("--- Add book ---");
        if (data.authors.isEmpty() || data.publishers.isEmpty() || data.categories.isEmpty()) {
            System.out.println("You need at least one author, one publisher and one category first.");
            return;
        }

        String title = readLine("Title: ");
        String isbn = readLine("ISBN: ");
        float price = readFloat("Price: ");
        int stock = readInt("Stock: ");
        String description = readLine("Description: ");

        printAuthors();
        Author author = find(data.authors, readInt("Author id: "), Author::getId);
        printPublishers();
        Publisher publisher = find(data.publishers, readInt("Publisher id: "), Publisher::getId);
        printCategories();
        Category category = find(data.categories, readInt("Category id: "), Category::getId);

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
            System.out.println("Book added: [" + book.getId() + "] " + book.getInfo());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ============================== ADMIN: STOCK, PRICES, ORDERS ==============================

    private static void updateStock() {
        System.out.println("--- Update book stock ---");
        Book book = selectBook("Book id: ");
        if (book == null) {
            return;
        }
        int quantity = readInt("Quantity (positive to add, negative to subtract): ");
        int before = book.getStock();
        book.updateStock(quantity);

        if (book.getStock() != before || quantity == 0) {
            System.out.println("Stock updated: [" + book.getId() + "] " + book.getInfo());
        } else {
            System.out.println("Error: stock cannot go below 0 (current: " + book.getStock() + ").");
        }
    }

    private static void showLowStockBooks() {
        System.out.println("--- Low-stock books (stock <= " + BookstoreData.REORDER_LEVEL + ") ---");
        List<Book> result = data.books.stream()
                .filter(b -> new Inventory(new Date(), BookstoreData.REORDER_LEVEL, b).checkReorderStatus())
                .toList();

        if (result.isEmpty()) {
            System.out.println("No books need to be reordered.");
        }
        result.forEach(b -> System.out.println("[" + b.getId() + "] " + b.getInfo()));
    }

    private static void editBookPrice() {
        System.out.println("--- Edit book price ---");
        Book book = selectBook("Book id: ");
        if (book == null) {
            return;
        }
        float price = readFloat("New price: ");
        if (price < 0) {
            System.out.println("Error: the price cannot be negative.");
            return;
        }
        book.setPrice(price);
        System.out.println("Price updated: [" + book.getId() + "] " + book.getInfo());
    }

    private static void listAllOrders() {
        System.out.println("--- All orders ---");
        if (data.orders.isEmpty()) {
            System.out.println("There are no orders yet.");
        }
        for (Order o : data.orders) {
            System.out.println("Customer: " + (o.getCustomer() == null ? "N/A" : o.getCustomer().getName()));
            printOrder(o);
        }
    }

    private static void completeDelivery() {
        System.out.println("--- Complete a delivery ---");
        List<DeliveryAssignment> active = data.deliveryAssignments.stream()
                .filter(a -> !"Completed".equalsIgnoreCase(a.getStatus()))
                .toList();
        if (active.isEmpty()) {
            System.out.println("There are no pending deliveries.");
            return;
        }
        for (DeliveryAssignment a : active) {
            System.out.println("Delivery #" + a.getId()
                    + " | Order " + (a.getOrder() == null ? "N/A" : "#" + a.getOrder().getId())
                    + " | Driver: " + (a.getDriver() == null ? "N/A" : a.getDriver().getName())
                    + " | Status: " + a.getStatus());
        }

        DeliveryAssignment selected = find(active, readInt("Delivery id to complete: "), DeliveryAssignment::getId);
        if (selected == null) {
            System.out.println("Error: that delivery id is not in the pending list.");
            return;
        }
        selected.completeDelivery();
        Order order = selected.getOrder();
        if (order != null) {
            order.updateStatus("Delivered");
        }
        System.out.println("Delivery #" + selected.getId() + " completed.");
    }

    private static void listCustomers() {
        System.out.println("--- Customers ---");
        if (data.customers.isEmpty()) {
            System.out.println("(no customers registered)");
        }
        data.customers.forEach(c -> System.out.println("[" + c.getId() + "] " + c.getName() + " | " + c.getEmail() + " | " + c.getPhone()));
    }

    // ============================== CUSTOMER: ACCESS ==============================

    private static void registerCustomer() {
        System.out.println("--- Customer registration ---");
        String name = readLine("Full name: ");
        String email = readLine("Email: ");
        String phone = readLine("Phone: ");
        int age = readInt("Age (18-100): ");
        String password = readLine("Password (min. 8 characters): ");
        System.out.println("Address details:");
        Address address = readAddress();

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

    private static void login() {
        System.out.println("--- Log in ---");
        Customer customer = Customer.login(data.customers, readLine("Email: "), readLine("Password: "));
        if (customer == null) {
            System.out.println("Error: wrong email or password.");
            return;
        }
        current = customer;
        cart = new ShoppingCart(customer.getId(), new Date(), 0);
        System.out.println("Welcome, " + customer.getName() + "!");
        customerMenu();
        current = null;
        cart = null;
    }

    // ============================== CUSTOMER: CART AND CHECKOUT ==============================

    private static void addToCart() {
        System.out.println("--- Add book to cart ---");
        Book book = selectBook("Enter Book ID: ");
        if (book == null) {
            return;
        }
        if (!book.checkAvailability()) {
            System.out.println("Sorry, this book is currently out of stock.");
            return;
        }
        int qty = readInt("Quantity: ");
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

    private static void removeFromCart() {
        System.out.println("--- Remove book from cart ---");
        if (cart.getItems().isEmpty()) {
            System.out.println("The shopping cart is empty.");
            return;
        }
        System.out.println(cart.getContent());
        Book book = find(data.books, readInt("Book ID to remove: "), Book::getId);
        if (book != null && cart.removeBook(book)) {
            System.out.println("Book removed. Cart subtotal: $" + cart.calculateSubtotal());
        } else {
            System.out.println("Error: that book is not in your cart.");
        }
    }

    private static void checkout() {
        System.out.println("--- Checkout ---");
        if (cart.getItems().isEmpty()) {
            System.out.println("Your cart is empty. Add books first.");
            return;
        }
        System.out.println(cart.getContent());

        Address address;
        if (readLine("Ship to your registered address? (y/n): ").trim().equalsIgnoreCase("y")) {
            address = current.getAddress();
        } else {
            System.out.println("\n--- Delivery Address Details ---");
            address = readAddress();
        }

        String method = switch (readInt("Payment method (1=Tarjeta, 2=Efectivo, 3=Transferencia): ")) {
            case 1 -> "Tarjeta";
            case 2 -> "Efectivo";
            case 3 -> "Transferencia";
            default -> null;
        };
        if (method == null) {
            System.out.println("Error: invalid payment method.");
            return;
        }

        try {
            cart.updateStock();   // checks the stock of the whole cart and subtracts it
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        Order order = new Order(nextId(data.orders, Order::getId), new Date(), 0, address, "Pending", method);
        order.setCustomer(current);
        for (OrderDetail item : cart.getItems()) {
            order.addDetail(new OrderDetail(item.getId(), item.getQuantity(), item.getBook(), item.getUnitPrice()));
        }
        data.orders.add(order);
        pay(order);
    }

    private static void pay(Order order) {
        System.out.println("\n--- Processing Payment ---");
        Payment payment = new Payment(order.getId(), order.getTotal(), new Date(),
                order.getPaymentMethod(), "Pending", "REF-" + order.getId());
        payment.processPayment();
        data.payments.add(payment);

        if (!payment.validatePayment()) {
            payment.setStatus("Failed");
            order.cancelOrder();
            returnStock(order);
            System.out.println("Payment Failed. The order was cancelled.");
            return;
        }

        payment.setStatus("Completed");
        order.confirmOrder();
        payment.generateReceipt();
        System.out.println("Payment Successful! Payment Ref: " + payment.getReferenceNumber());
        System.out.println("Order Confirmed! Order ID: " + order.getId() + " | Total: $" + order.getTotal());

        DeliveryAssignment assignment = data.assignDriver(order);
        if (assignment == null) {
            System.out.println("There are no delivery drivers registered yet.");
        } else {
            System.out.println("Delivery Driver Assigned: " + assignment.getDriver().getName()
                    + " [Plate: " + assignment.getDriver().getVehiclePlate() + "]");
            System.out.println("Delivery Address: " + order.getShippingAddress().getFormattedAddress());
        }
        cart.clearShoppingCart();
    }

    private static void returnStock(Order order) {
        for (OrderDetail detail : order.getDetails()) {
            if (detail.getBook() != null) {
                detail.getBook().updateStock(detail.getQuantity());
            }
        }
    }

    // ============================== CUSTOMER: ORDERS, REVIEWS, NOTIFICATIONS ==============================

    private static Order askOwnOrder() {
        List<Order> mine = data.ordersOf(current);
        if (mine.isEmpty()) {
            System.out.println("You have no orders yet.");
            return null;
        }
        mine.forEach(o -> System.out.println("Order #" + o.getId() + " | " + o.getStatus() + " | $" + o.getTotal()));
        Order order = find(mine, readInt("Enter Order ID: "), Order::getId);
        if (order == null) {
            System.out.println("Error: that order is not in your history.");
        }
        return order;
    }

    private static void trackOrder() {
        System.out.println("--- Order & Delivery Tracking ---");
        Order order = askOwnOrder();
        if (order == null) {
            return;
        }
        printOrder(order);
        DeliveryAssignment assignment = data.deliveryOf(order);
        if (assignment == null) {
            System.out.println("Delivery Status: No delivery assignment created yet.");
            return;
        }
        System.out.println("Delivery Status: " + assignment.getStatus());
        if (assignment.getDriver() != null) {
            System.out.println("Driver: " + assignment.getDriver().getName() + " (" + assignment.getDriver().getPhone() + ")");
        }
    }

    private static void listMyOrders() {
        System.out.println("--- My orders ---");
        List<Order> mine = data.ordersOf(current);
        if (mine.isEmpty()) {
            System.out.println("You have no orders yet.");
        }
        mine.forEach(Main::printOrder);
    }

    // ============================== SHARED LISTINGS ==============================

    private static void listBooks() {
        System.out.println("--- Books ---");
        if (data.books.isEmpty()) {
            System.out.println("(no books)");
        }
        data.books.forEach(b -> System.out.println("[" + b.getId() + "] " + b.getInfo()));
    }

    private static void searchBooks() {
        System.out.println("--- Search books by title ---");
        String query = readLine("Title contains: ").trim().toLowerCase();
        List<Book> result = data.books.stream().filter(b -> b.getTitle().toLowerCase().contains(query)).toList();

        if (result.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println(result.size() + " book(s) found:");
            result.forEach(b -> System.out.println("[" + b.getId() + "] " + b.getInfo()));
        }
    }

    /** Shows the books, asks for an id and returns the book (or null after printing the error). */
    private static Book selectBook(String prompt) {
        if (data.books.isEmpty()) {
            System.out.println("There are no books yet.");
            return null;
        }
        listBooks();
        Book book = find(data.books, readInt(prompt), Book::getId);
        if (book == null) {
            System.out.println("Error: that book id does not exist.");
        }
        return book;
    }

    private static void listAuthorsPublishersAndCategories() {
        System.out.println("--- Authors ---");
        printAuthors();
        System.out.println("\n--- Publishers ---");
        printPublishers();
        System.out.println("\n--- Categories ---");
        printCategories();
    }

    private static void printAuthors() {
        if (data.authors.isEmpty()) {
            System.out.println("(no authors)");
        }
        data.authors.forEach(a -> System.out.println("[" + a.getId() + "] " + a.getName() + " (" + a.getNationality() + ")"));
    }

    private static void printPublishers() {
        if (data.publishers.isEmpty()) {
            System.out.println("(no publishers)");
        }
        data.publishers.forEach(p -> System.out.println("[" + p.getId() + "] " + p.getName()));
    }

    private static void printCategories() {
        if (data.categories.isEmpty()) {
            System.out.println("(no categories)");
        }
        data.categories.forEach(c -> System.out.println("[" + c.getId() + "] " + c.getName()));
    }

    private static void printOrder(Order o) {
        System.out.println("Order #" + o.getId() + " | " + formatDate(o.getOrderDate()) + " | Status: " + o.getStatus()
                + " | Payment: " + o.getPaymentMethod() + " | Total: $" + o.getTotal());
        for (OrderDetail d : o.getDetails()) {
            System.out.println("    - " + d);
        }
    }

    private static Address readAddress() {
        String mainStreet = readLine("Main Street: ");
        String secondaryStreet = readLine("Secondary Street: ");
        String sector = readLine("Sector (e.g. Norte/Sur): ");
        String reference = readLine("Reference: ");
        return new Address(mainStreet, secondaryStreet, sector, reference);
    }

    private static void requireText(String value, String label) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(label + " cannot be empty");
        }
    }

    private static String clean(String value) {
        return value == null ? "" : value.trim();
    }

    private static String formatDate(Date date) {
        return date == null ? "-" : new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            data.save();
            System.exit(0);
            return "";
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readLine(prompt).trim());
            } catch (NumberFormatException e) {
                System.out.println("Please type a whole number.");
            }
        }
    }

    private static float readFloat(String prompt) {
        while (true) {
            try {
                return Float.parseFloat(readLine(prompt).trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Please type a number.");
            }
        }
    }
}