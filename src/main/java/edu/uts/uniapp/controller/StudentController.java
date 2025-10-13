package edu.uts.uniapp.controller;

import edu.uts.uniapp.model.Database;
import edu.uts.uniapp.model.Student;
import edu.uts.uniapp.util.RegexConstants;
import edu.uts.uniapp.view.CLIView;
import edu.uts.uniapp.view.IOText;

import java.util.ArrayList;
import java.util.List;

/***
 * students system，next:register/login/）
 **/
public class StudentController {
    private final CLIView view = new CLIView();
    SubjectController subjectController = new SubjectController();

    public void showMenu() {
        boolean back = false;
        while (!back) {
            char op = view.readOption(IOText.textInBlue(IOText.STU_PROMPT_SELECT,IOText.IndentationLevel.StudentSystem));

            switch (op) {
                case 'L':
                    Student currentStudent=login();
                    if (currentStudent != null) {
                        // 进入选课子系统
                        List<Student> all = Database.readAll();
                        // 重新从列表里拿同一个引用（避免序列化拷贝差异）
                        Student ref = all.stream()
                                .filter(s -> s.getEmail().equalsIgnoreCase(currentStudent.getEmail()))
                                .findFirst().orElse(currentStudent);
                        subjectController.run(ref, all);
                    }
                    break;
                case 'R':
                    register();
                    break;
                case 'X':
                    back = true;
                    break;
                default:
                    view.println("Invalid option. Please try again.");
            }
        }
    }
    private void register() {

        view.println("student sign up");
        String email = view.readLine("Email: ");
        String pw = view.readLine("Password: ");

        // RegEx 验证
        if (!pw.matches(RegexConstants.PASSWORD)||!email.matches(RegexConstants.EMAIL)) {
            view.println("Incorrect email or password format");
            return;
        }else{
            view.println("Email and password formats acceptable");
        }

        // 唯一性（按 email）
        List<Student> all = Database.readAll();
        boolean exists = all.stream().anyMatch(s -> s.getEmail().equalsIgnoreCase(email));
        if (exists) {
            view.println("Student already exists.");
            return;
        }

        String name = view.readLine("Name: ");
        view.println("Student already exists.");

        Student s = new Student(name, email, pw);
        all.add(s);
        Database.writeAll(all);
        view.println("Registration successful! Your ID: " + s.getIdStr());
    }


    private Student login() {
        List<Student> all = Database.readAll();
        if (all == null){
            all = new ArrayList<>();
        }

        String email = view.readLine("Email: ");
        String pw = view.readLine("Password: ");

        // 登录前也做格式验证（与样例 I/O 一致）
        if (!pw.matches(RegexConstants.PASSWORD)||!email.matches(RegexConstants.EMAIL)) {
            view.println("Incorrect email or password format");
            return null;
        }else{
            view.println("email and password formats acceptable");
        }

        for (Student s : all) {
            if (s.getEmail().equalsIgnoreCase(email) && s.getPassword().equals(pw)) {
                view.println("Login successful.");
                return s;
            }
        }
        view.println("Invalid credentials.");
        return null;
    }
}

