package ec.edu.espe.projectBookstore.menu;

import ec.edu.espe.projectBookstore.controller.BookstoreData;
import ec.edu.espe.projectBookstore.controller.ReportService;
import ec.edu.espe.projectBookstore.view.AppContext;

import java.io.IOException;
import java.nio.file.Path;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class ReportsMenu extends Menu {

    private interface ReportJob {
        Path generate() throws IOException;
    }

    private final BookstoreData data;
    private final ReportService reports;

    public ReportsMenu(AppContext appContext) {
        super(appContext, "CSV REPORTS (folder 'reports')", "Back", "Choose a report: ", "Returning to Main Menu...");
        this.data = appContext.data;
        this.reports = appContext.reports;

        ReportJob inventory = () -> reports.inventoryReport(data.books, BookstoreData.REORDER_LEVEL);
        ReportJob lowStock = () -> reports.lowStockReport(data.books, BookstoreData.REORDER_LEVEL);
        ReportJob sales = () -> reports.salesReport(data.orders);
        ReportJob performance = () -> reports.bookPerformanceReport(data.books, data.orders);

        add("Inventory report", () -> generate(inventory));
        add("Low-stock report", () -> generate(lowStock));
        add("Sales report (orders)", () -> generate(sales));
        add("Book performance (units sold, revenue)", () -> generate(performance));
        add("Generate all reports", () -> generate(inventory, lowStock, sales, performance));
    }

    private void generate(ReportJob... jobs) {
        try {
            for (ReportJob job : jobs) {
                System.out.println("Report saved: " + job.generate().toAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("Error writing the report: " + e.getMessage());
        }
    }
}
