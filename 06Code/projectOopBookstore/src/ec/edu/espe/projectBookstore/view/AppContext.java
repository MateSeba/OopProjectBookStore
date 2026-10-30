package ec.edu.espe.projectBookstore.view;

import ec.edu.espe.projectBookstore.controller.BookstoreData;
import ec.edu.espe.projectBookstore.controller.ReportService;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public final class AppContext {

    public final BookstoreData data;
    public final ConsoleIO io;
    public final Session session = new Session();
    public final CatalogDisplay catalog;
    public final ReportService reports = new ReportService("reports");

    public AppContext(BookstoreData data) {
        this.data = data;
        this.io = new ConsoleIO(data::save);
        this.catalog = new CatalogDisplay(data, io);
    }
}
