package edu.uts.uniapp.view;

import java.util.Scanner;

/**
 * 简单的命令行输入输出封装。
 */
public class CLIView {
    private final Scanner scanner = new Scanner(System.in);

    public void println(String s) {
        System.out.println(s);
    }

    public void print(String s) {
        System.out.print(s);
    }

    /**
     * 读取一行（去掉两端空白），允许空值时返回空字符串。
     */
    public String readLine(String prompt) {
        print(prompt);
        String line = scanner.nextLine();
        return line == null ? "" : line.trim();
    }

    /**
     * 读取用户选项（只取第一个非空字符，转为大写）。
     */
    public char readOption(String prompt) {
        String s = readLine(prompt);
        return (s.length() != 1) ? '\0' : Character.toUpperCase(s.charAt(0));
    }
}