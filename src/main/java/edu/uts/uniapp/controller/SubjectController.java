package edu.uts.uniapp.controller;

import edu.uts.uniapp.model.Database;
import edu.uts.uniapp.model.Student;
import edu.uts.uniapp.model.Subject;
import edu.uts.uniapp.util.RegexConstants;
import edu.uts.uniapp.view.CLIView;

import java.util.List;

public class SubjectController {
    private final CLIView view = new CLIView();

    public void run(Student current, List<Student> all) {
        boolean back = false;
        while (!back) {
            view.println("");
            view.println("----- Subject Enrolment System -----");
            view.println("(C) change password");
            view.println("(E) enrol");
            view.println("(R) remove");
            view.println("(S) show");
            view.println("(X) exit");
            char op = view.readOption("Select an option: ");

            switch (op) {
                case 'C':
                    changePassword(current, all);
                    break;
                case 'E':
                    enrol(current, all);
                    break;
                case 'R':
                    remove(current, all);
                    break;
                case 'S':
                    show(current);
                    break;
                case 'X':
                    back = true;
                    break;
                default:
                    view.println("Invalid option. Please try again.");
            }
        }
    }

    private void changePassword(Student current, List<Student> all) {
        String newPw = view.readLine("Enter new password: ");
        // 不在这里做正则限制，沿用注册规则也可以；如需强制，可在此加入 Regex 校验
        if(newPw.matches(RegexConstants.PASSWORD)) {
            current.setPassword(newPw);
            Database.writeAll(all);
            view.println("Password changed successfully.");
        }
        view.println("Invalid email format. Email must end with @university.com");
    }

    private void enrol(Student current, List<Student> all) {
        if (!current.canEnrollMore()) {
            view.println("Cannot enrol in more than four (4) subjects.");
            return;
        }
        Subject s = new Subject();
        current.enrollSubject(s);
        Database.writeAll(all);
        view.println("Enrolled: " + s);
        view.println(String.format("New average: %.1f", current.averageMark()));
    }

    private void remove(Student current, List<Student> all) {
        String idStr = view.readLine("Enter subject id to remove: ");
        try {
            int sid = Integer.parseInt(idStr);
            boolean ok = current.removeSubjectById(sid);
            if (ok) {
                Database.writeAll(all);
                view.println("Subject removed.");
            } else {
                view.println("Subject not found.");
            }
        } catch (NumberFormatException e) {
            view.println("Invalid subject id.");
        }
    }

    private void show(Student current) {
        view.println("");
        view.println("Your enrolled subjects:");
        if (current.getSubjects().isEmpty()) {
            view.println("(none)");
        } else {
            current.getSubjects().forEach(s -> view.println(s.toString())
            );
        }
        view.println(String.format("Average=%.1f, PASS=%s", current.averageMark(), current.isPass() ? "YES" : "NO"));
    }
}

