package ec.edu.espe.projectBookstore.menu;

import ec.edu.espe.projectBookstore.view.AppContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public abstract class Menu {

    private record Option(String label, Runnable action) {
    }

    protected final AppContext appContext;
    private final String title;
    private final String exitLabel;
    private final String prompt;
    private final String exitMessage;
    private final List<Option> options = new ArrayList<>();

    protected Menu(AppContext appContext, String title, String exitLabel, String prompt, String exitMessage) {
        this.appContext = appContext;
        this.title = title;
        this.exitLabel = exitLabel;
        this.prompt = prompt;
        this.exitMessage = exitMessage;
    }

    protected void add(String label, Runnable action) {
        options.add(new Option(label, action));
    }

    public void run() {
        int option;
        do {
            appContext.io.showMenu(title, exitLabel, options.stream().map(Option::label).toList());
            option = appContext.io.readInt(prompt);
            System.out.println();
            execute(option);
            appContext.data.save();   
        } while (shouldContinue(option));
    }

    protected boolean shouldContinue(int option) {
        return option != 0;
    }

    private void execute(int option) {
        if (option == 0) {
            System.out.println(exitMessage);
        } else if (option > 0 && option <= options.size()) {
            options.get(option - 1).action().run();
        } else {
            System.out.println("Invalid option. Please choose a valid number.");
        }
    }
}
