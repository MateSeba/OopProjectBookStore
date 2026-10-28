package ec.edu.espe.projectBookstore.controller;

import ec.edu.espe.projectBookstore.model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class ReportService {

    private final Path dir;

    public ReportService(String directory) {
        this.dir = Paths.get(directory);
    }

    public Path inventoryReport(List<Book> books, int reorderLevel) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add(row("Id", "Title", "ISBN", "Author", "Publisher", "Category", "Price", "Stock", "Needs reorder"));
        for (Book b : books) {
            lines.add(row(b.getId(), b.getTitle(), b.getIsbn(),
                    b.getAuthor() == null ? "N/A" : b.getAuthor().getName(),
                    b.getPublisher() == null ? "N/A" : b.getPublisher().getName(),
                    b.getCategory() == null ? "N/A" : b.getCategory().getName(),
                    money(b.getPrice()), b.getStock(),
                    b.getStock() <= reorderLevel ? "YES" : "NO"));
        }
        return write("inventory_report.csv", lines);
    }

    public Path lowStockReport(List<Book> books, int reorderLevel) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add(row("Id", "Title", "Publisher", "Current stock", "Reorder level"));
        for (Book b : books) {
            if (b.getStock() <= reorderLevel) {
                lines.add(row(b.getId(), b.getTitle(),
                        b.getPublisher() == null ? "N/A" : b.getPublisher().getName(),
                        b.getStock(), reorderLevel));
            }
        }
        return write("low_stock_report.csv", lines);
    }

    public Path salesReport(List<Order> orders) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add(row("Order id", "Date", "Customer", "Items", "Payment method", "Status", "Total"));
        for (Order o : orders) {
            int items = 0;
            for (OrderDetail d : o.getDetails()) {
                items += d.getQuantity();
            }
            lines.add(row(o.getId(), formatDate(o.getOrderDate()),
                    o.getCustomer() == null ? "N/A" : o.getCustomer().getName(),
                    items, o.getPaymentMethod(), o.getStatus(), money(o.getTotal())));
        }
        return write("sales_report.csv", lines);
    }

    public Path bookPerformanceReport(List<Book> books, List<Order> orders) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add(row("Id", "Title", "Units sold", "Revenue"));
        for (Book b : books) {
            int units = 0;
            float revenue = 0;
            for (Order o : orders) {
                if ("Cancelled".equalsIgnoreCase(o.getStatus())) {
                    continue;
                }
                for (OrderDetail d : o.getDetails()) {
                    if (d.getBook() != null && d.getBook().getId() == b.getId()) {
                        units += d.getQuantity();
                        revenue += d.getSubTotal();
                    }
                }
            }
            lines.add(row(b.getId(), b.getTitle(), units, money(revenue)));
        }
        return write("book_performance_report.csv", lines);
    }
    
    private Path write(String fileName, List<String> lines) throws IOException {
        Files.createDirectories(dir);
        Path path = dir.resolve(fileName);
        // The BOM at the start makes Excel show accents correctly
        lines.set(0, "\uFEFF" + lines.get(0));
        Files.write(path, lines, StandardCharsets.UTF_8);
        return path;
    }

    private static String row(Object... cells) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cells.length; i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(escape(cells[i]));
        }
        return sb.toString();
    }

    private static String escape(Object value) {
        String text = value == null ? "" : String.valueOf(value);
        if (text.contains(",") || text.contains("\"") || text.contains("\n") || text.contains("\r")) {
            return "\"" + text.replace("\"", "\"\"") + "\"";
        }
        return text;
    }

    private static String money(float value) {
        return String.format(Locale.US, "%.2f", value);
    }

    private static String formatDate(Date date) {
        return date == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
    }
}
