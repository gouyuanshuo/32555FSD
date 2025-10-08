package edu.uts.uniapp.controller;



import edu.uts.uniapp.view.CLIView;

/**
 * University top menu：
 * (A) Admin, (S) Student, (X) Exit
 */
public class UniversityController {
    private final CLIView view = new CLIView();
    private final StudentController studentController = new StudentController();
    private final AdminController adminController = new AdminController();

    public void start() {
        printBanner();

        boolean running = true;
        while (running) {
            view.println("");
            view.println("===== University System =====");
            view.println("(A) Admin");
            view.println("(S) Student");
            view.println("(X) Exit");
            char op = view.readOption("Select an option: ");

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
        view.println("Goodbye!");
    }

    private void printBanner() {
        view.println("=======================================");
        view.println("   CLIUniApp - University Application   ");
        view.println("=======================================");
    }
}
