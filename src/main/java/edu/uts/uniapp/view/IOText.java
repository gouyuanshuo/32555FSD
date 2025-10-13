package edu.uts.uniapp.view;

public class IOText {


    //color
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    //print color function
    public static String textInRed(String str) {
        return ANSI_RED+str+ANSI_RESET;
    }
    public static String textInGreen(String str) {
        return ANSI_GREEN+str+ANSI_RESET;
    }
    public static String textInYellow(String str) {
        return ANSI_YELLOW+str+ANSI_RESET;
    }
    public static String textInBlue(String str) {
        return ANSI_BLUE+str+ANSI_RESET;
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
    public final String unitext="aaaaa";


    /**
     *  student system print
     */
    public final String stu="";


    /**
     *  admin system print
     */

    public final String admin="";

    /**
     * subject system print
     */

    public final String subject="";

}

