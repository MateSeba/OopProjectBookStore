package ec.edu.espe.projectBookstore.view;

import ec.edu.espe.projectBookstore.model.Address;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class ConsoleIO {

    private final Scanner scanner = new Scanner(System.in);
    private final Runnable onEndOfInput;

    public ConsoleIO(Runnable onEndOfInput) {
        this.onEndOfInput = onEndOfInput;
    }

    public String readLine(String prompt) {
        System.out.print(prompt);
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            onEndOfInput.run();
            System.exit(0);
            return "";
        }
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readLine(prompt).trim());
            } catch (NumberFormatException e) {
                System.out.println("Please type a whole number.");
            }
        }
    }

    public float readFloat(String prompt) {
        while (true) {
            try {
                return Float.parseFloat(readLine(prompt).trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Please type a number.");
            }
        }
    }

    public Address readAddress() {
        String mainStreet = readLine("Main Street: ");
        String secondaryStreet = readLine("Secondary Street: ");
        String sector = readLine("Sector (e.g. Norte/Sur): ");
        String reference = readLine("Reference: ");
        return new Address(mainStreet, secondaryStreet, sector, reference);
    }

    public void showMenu(String title, String exitLabel, List<String> options) {
        System.out.println("\n========== " + title + " ==========");
        for (int i = 0; i < options.size(); i++) {
            System.out.println(String.format("%3d. %s", i + 1, options.get(i)));
        }
        System.out.println(String.format("%3d. %s", 0, exitLabel));
    }
}
