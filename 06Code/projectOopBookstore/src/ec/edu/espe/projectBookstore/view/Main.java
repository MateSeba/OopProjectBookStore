package ec.edu.espe.projectBookstore.view;

import ec.edu.espe.projectBookstore.controller.BookstoreData;
import ec.edu.espe.projectBookstore.menu.MainMenu;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class Main {

    public static void main(String[] args) {
        AppContext appContext = new AppContext(BookstoreData.load());   // loads the JSON files
        new MainMenu(appContext).run();
    }
}
