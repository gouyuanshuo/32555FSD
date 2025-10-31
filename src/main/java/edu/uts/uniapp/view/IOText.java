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
    public static String textInGreen(String str, IndentationLevel system) {
        return ANSI_GREEN+textWithIndentation(str,system)+ANSI_RESET;
    }
    public static String textInYellow(String str, IndentationLevel system) {
        return ANSI_YELLOW + textWithIndentation(str,system) + ANSI_RESET;
    }
    public static String textInBlue(String str, IndentationLevel system) {
        return ANSI_BLUE + textWithIndentation(str,system) + ANSI_RESET;
    }

    /**
     *  university system print
     */
    public static final String UNI_PROMPT_SELECT = "University System: (A)dmin, (S)tudent, or X: ";
    public static final String INVALID_OPTION = "Invalid option. Please try again.";
    public static final String THANK_YOU = "Thank You";
    public static final String INVALID_INPUT= "Invalid input";


    /**
     *  student system print
     */
    public static final String STU_PROMPT_SELECT = "Student System (l/r/x): ";

    public static final String IPT_EMAIL       = "Email: ";
    public static final String IPT_PASSWORD    = "Password: ";
    public static final String IPT_NAME        = "Name: ";
    public static final String INFO_INVALID    = "Incorrect email or password format";
    public static final String INFO_VALID      = "email and password formats acceptable";

    public static final String REGISTER_START  = "Student Sign Up";
    public static final String REGISTER_FAILED  = "Student %s already exists";
    public static final String REGISTER_SUCCESS = "Enrolling Student %s";

    public static final String LOGIN_START      = "Student Sign In";
    public static final String LOGIN_FAILED     = "Student dose not exist";

    /**
     *  admin system print
     */

    public static final String ADM_PROMPT_SELECT = "Admin System (c/g/p/r/s/x): ";

    public static final String ADM_SHOW_NONE     = "< Nothing to Display >";
    public static final String ADM_REMOVE_PROMPT = "Removing by ID: ";
    public static final String ADM_REMOVE_OK     = "Removing Student %s Account";
    public static final String ADM_REMOVE_NF     = "Student %s does not exist";
    public static final String ADM_REMOVE_BAD    = "Invalid id";
    public static final String ADM_CLEAR_CONFIRM = "Are you sure you want to clear the database (Y)es/(N)o: ";
    public static final String ADM_CLEAR_OK      = "Students data cleared";
    public static final String ADM_CLEAR_START   = "Clearing Students database";
    public static final String ADM_SHOW_STU      = "Student List";
    public static final String ADM_GROUP_GRD     = "Grade Grouping";
    public static final String ADM_GROUP_PF      = "PASS/FAIL Partition";


    /**
     * subject system print
     */
    public static final String SUB_PROMPT_SELECT = "Student Course Menu (c/e/r/s/x): ";

    public static final String ENR_LIMIT_REACHED = "Students are allowed to enrol in 4 subjects only";
    public static final String ENR_ENROLLED_FMT  = "Enrolling in Subject-%s";

    public static final String ENR_LIST_HEADER   = "Your are now enrolled in %d out of 4 subjects";
    public static final String ENR_SHOW_SUB      = "Showing %d subjects";
    public static final String ENR_REMOVE_PROMPT = "Remove subject by ID: ";
    public static final String ENR_REMOVE_OK     = "Dropping Subject-%d";
    public static final String ENR_REMOVE_NF     = "Subject not found.";
    public static final String ENR_REMOVE_BAD    = "Invalid subject id.";

    public static final String ENR_UPD_PWD       = "Updating Password";
    public static final String ENR_CONFIRM_PWD   = "Confirm Password: ";
    public static final String ENR_PWD_PROMPT    = "New Password: ";
    public static final String ENR_PWD_NOT_MATCH  = "Password does not match - try again";
    public static final String ENR_PWD_INVALID   ="Invalid password format";


    /**
     * GUI print
     */
    public static final String GUI_PWD_INCORRECT = "Invalid password format";
    public static final String GUI_LOGIN   ="Logged in: ";
    public static final String GUI_ENR_FAIL = "You cannot enrol in more than four (4) subjects.";
}

