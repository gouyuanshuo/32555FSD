package edu.uts.uniapp.controller;


import edu.uts.uniapp.model.Database;
import edu.uts.uniapp.model.Student;
import edu.uts.uniapp.model.Subject;
import edu.uts.uniapp.view.CLIView;
import edu.uts.uniapp.view.IOText;

import java.util.*;

/**
 * admin system menu（next step: “展示、分组、划分、删除、清空”等功能）
 */
public class AdminController {
    private final CLIView view = new CLIView();

    public void showMenu() {
        boolean back = false;
        while (!back) {
            char op = view.readOption(IOText.textInBlue(IOText.ADM_PROMPT_SELECT,IOText.IndentationLevel.AdminSystem));

            switch (op) {
                case 'S':
                    showStudents();
                    break;
                case 'G':
                    groupByGrade();
                    break;
                case 'P':
                    partitionPassFail();
                    break;
                case 'R':
                    removeById();
                    break;
                case 'C':
                    clearAll();
                    break;
                case 'X':
                    back = true;
                    break;
                default:
                    view.println("Invalid option. Please try again.");
            }
        }
    }
    private void showStudents() {
        List<Student> all = Database.readAll();
        if (all.isEmpty()) {
            view.println("(no students)");
            return;
        }
        for (Student s : all) {
            view.println(s.toString());
        }
    }
    /** 将学生按“最高科目等级”聚类展示（示例做法） */
    private void groupByGrade() {
        List<Student> all = Database.readAll();
        if (all.isEmpty()) {
            view.println("(no students)");
            return;
        }
        Map<String, List<Student>> groups = new LinkedHashMap<>();
        groups.put("HD", new ArrayList<>());
        groups.put("D", new ArrayList<>());
        groups.put("C", new ArrayList<>());
        groups.put("P", new ArrayList<>());
        groups.put("F", new ArrayList<>());
        groups.put("N/A", new ArrayList<>()); // 无课程

        for (Student s : all) {
            String key = highestGradeOf(s);
            groups.getOrDefault(key, groups.get("N/A")).add(s);
        }

        groups.forEach((grade, list) -> {
            view.println("[" + grade + "]");
            if (list.isEmpty()) view.println("  (none)");
            else list.forEach(st -> view.println(st.toString()));
        });
    }

    private String highestGradeOf(Student s) {
        return s.getSubjects().stream()
                .map(Subject::getGrade)
                .sorted(Comparator.comparingInt(AdminController::gradeRank)) // HD>D>C>P>F
                .findFirst()
                .orElse("N/A");
    }

    private static int gradeRank(String g) {
        switch (g) {
            case "HD": return 0;
            case "D":  return 1;
            case "C":  return 2;
            case "P":  return 3;
            case "F":  return 4;
            default:   return 5;
        }
    }

    private void partitionPassFail() {
        List<Student> all = Database.readAll();
        List<Student> pass = all.stream().filter(Student::isPass).toList();
        List<Student> fail = all.stream().filter(s -> !s.isPass()).toList();

        view.println("[PASS]");
        if (pass.isEmpty()) {
            view.println("  (none)");
        } else {
            pass.forEach(s -> view.println("  " + s.getIdStr() + " " + s.getEmail() + " avg=" + String.format("%.1f", s.averageMark())));
        }

        view.println("[FAIL]");
        if (fail.isEmpty()) {
            view.println("  (none)");
        } else {
            fail.forEach(s -> view.println("  " + s.getIdStr() + " " + s.getEmail() + " avg=" + String.format("%.1f", s.averageMark())));
        }
    }

    private void removeById() {
        String idStr = view.readLine("Enter student id (6 digits): ");
        try {
            int id = Integer.parseInt(idStr);
            List<Student> all = Database.readAll();
            boolean removed = all.removeIf(s -> s.getId() == id);
            Database.writeAll(all);
            view.println(removed ? "Student removed." : "Student not found.");
        } catch (NumberFormatException e) {
            view.println("Invalid id.");
        }
    }

    private void clearAll() {
        String sure = view.readLine("Type 'YES' to confirm clearing all students: ");
        if ("YES".equalsIgnoreCase(sure)) {
            Database.clear();
            view.println("Database cleared.");
        } else {
            view.println("Cancelled.");
        }
    }
}
