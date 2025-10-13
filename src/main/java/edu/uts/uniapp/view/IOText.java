package edu.uts.uniapp.view;

public class IOText {


    public enum IndentationLevel {
        UniversitySystem,
        StudentSystem,
        AdminSystem,
        SubjectSystem,
    }

    public static String textWithIndentation(String text, IndentationLevel system){
        return switch (system) {
            case StudentSystem, AdminSystem -> "\t\t" + text;
            case SubjectSystem -> "\t\t\t\t" + text;
            default -> text;
        };
    }

    //color
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    //print color function
    public static String textInRed(String str, IndentationLevel system) {
        return ANSI_RED + textWithIndentation(str,system) + ANSI_RESET;
    }
    public static String textInGreen(String str) {
        return ANSI_GREEN+str+ANSI_RESET;
    }
    public static String textInYellow(String str, IndentationLevel system) {
        return ANSI_YELLOW + textWithIndentation(str,system) + ANSI_RESET;
    }
    public static String textInBlue(String str, IndentationLevel system) {
        return ANSI_BLUE + textWithIndentation(str,system) + ANSI_RESET;
    }
    public static String textInPurple(String str) {
        return ANSI_PURPLE+str+ANSI_RESET;
    }
    public static String textInCyan(String str) {
        return ANSI_CYAN+str+ANSI_RESET;
    }

    /**
     *  university system print
     */
    public static final String UNI_PROMPT_SELECT = "University System: (A)dmin, (S)tudent, or X: ";
    public static final String INVALID_OPTION = "Invalid option. Please try again.";
    public static final String THANK_YOU = "Thank You";


    /**
     *  student system print
     */
    public static final String STU_PROMPT_SELECT = "Student System (l/r/x): ";


    /**
     *  admin system print
     */

    public static final String ADMIN_PROMPT_SELECT = "Admin System (c/g/p/r/s/x): ";

    /**
     * subject system print
     */



}

