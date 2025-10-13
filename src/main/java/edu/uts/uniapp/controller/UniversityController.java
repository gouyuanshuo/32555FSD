package edu.uts.uniapp.controller;



import edu.uts.uniapp.view.CLIView;
import edu.uts.uniapp.view.IOText;

/**
 * University top menu：
 * (A) Admin, (S) Student, (X) Exit
 */
public class UniversityController {
    private final CLIView view = new CLIView();
    private final StudentController studentController = new StudentController();
    private final AdminController adminController = new AdminController();

    public void start() {

        boolean running = true;
        while (running) {
            char op = view.readOption(IOText.textInRed("University System: (A)dmin, (S)tudent, or X: "));

            switch (op) {
                case 'A':
                    adminController.showMenu();
                    break;
                case 'S':
                    studentController.showMenu();
                    break;
                case 'X':
                    running = false;
                    break;
                default:
                    view.println("Invalid option. Please try again.");
            }
        }
        view.println("Thank you");
    }

    private void printBanner() {
        view.println("=======================================");
        view.println("   CLIUniApp - University Application   ");
        view.println("=======================================");
    }
}
