package edu.uts.uniapp.controller;


import edu.uts.uniapp.model.Database;
import edu.uts.uniapp.model.Student;
import edu.uts.uniapp.model.Subject;
import edu.uts.uniapp.view.CLIView;
import edu.uts.uniapp.view.IOText;

import java.util.*;

/**
 * admin system
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
                    view.println(IOText.textInRed(IOText.INVALID_OPTION,IOText.IndentationLevel.AdminSystem));
            }
        }
    }
    private void showStudents() {
        List<Student> all = Database.readAll();
        view.println(IOText.textInYellow(IOText.ADM_SHOW_STU, IOText.IndentationLevel.AdminSystem));
        if(all.isEmpty()){
            view.println(IOText.textWithIndentation(IOText.ADM_SHOW_NONE,IOText.IndentationLevel.SubjectSystem));
            return;
        }
        for (Student s : all) {
            view.println(IOText.textWithIndentation(s.info(), IOText.IndentationLevel.AdminSystem));
        }
    }

    /** */
    private void groupByGrade() {
        view.println(IOText.textInYellow(IOText.ADM_GROUP_GRD, IOText.IndentationLevel.AdminSystem));
        List<Student> all = Database.readAll();
        if(all.isEmpty()){
            view.println(IOText.textWithIndentation(IOText.ADM_SHOW_NONE,IOText.IndentationLevel.SubjectSystem));
            return;
        }

        Map<String, List<Student>> groups = new LinkedHashMap<>();
        groups.put("HD", new ArrayList<>());
        groups.put("D", new ArrayList<>());
        groups.put("C", new ArrayList<>());
        groups.put("P", new ArrayList<>());
        groups.put("F", new ArrayList<>());
        groups.put("N/A", new ArrayList<>()); // doesn't have courses

        for (Student s : all) {
            groups.getOrDefault(s.grade(), groups.get("N/A")).add(s);
        }
        groups.forEach((grade, list) -> {
            view.println(IOText.textWithIndentation(String.format("%s --> %s", grade,list.toString()), IOText.IndentationLevel.AdminSystem));
        });
    }



    private void partitionPassFail() {
        view.println(IOText.textInYellow(IOText.ADM_GROUP_PF, IOText.IndentationLevel.AdminSystem));

        List<Student> all = Database.readAll();

        List<Student> pass = all.stream().filter(Student::isPass).toList();
        List<Student> fail = all.stream().filter(s -> !s.isPass()).toList();

        view.println(IOText.textWithIndentation(String.format("FAIL -> %s",fail), IOText.IndentationLevel.AdminSystem));
        view.println(IOText.textWithIndentation(String.format("PASS -> %s",pass), IOText.IndentationLevel.AdminSystem));


    }

    private void removeById() {
        String idStr = view.readLine(IOText.textWithIndentation(IOText.ADM_REMOVE_PROMPT, IOText.IndentationLevel.AdminSystem));
        try {
            final int id = Integer.parseInt(idStr);
            List<Student> all = Database.readAll();
            final boolean removed = all.removeIf(s -> s.getId() == id);
            if (removed) {
                Database.writeAll(all);
                view.println(IOText.textInYellow(String.format(IOText.ADM_REMOVE_OK,idStr), IOText.IndentationLevel.AdminSystem));
            }else {
                view.println(IOText.textInRed(String.format(IOText.ADM_REMOVE_NF,idStr), IOText.IndentationLevel.AdminSystem));
            }
        } catch (NumberFormatException e) {
            view.println(IOText.textInRed(IOText.ADM_REMOVE_BAD, IOText.IndentationLevel.AdminSystem));
        }
    }

    private void clearAll() {
        view.println(IOText.textInYellow(IOText.ADM_CLEAR_START, IOText.IndentationLevel.AdminSystem));
        String sure = view.readLine(IOText.textInRed(IOText.ADM_CLEAR_CONFIRM,IOText.IndentationLevel.AdminSystem));
        if ("Y".equalsIgnoreCase(sure)) {
            Database.clear();
            view.println(IOText.textInYellow(IOText.ADM_CLEAR_OK, IOText.IndentationLevel.AdminSystem));
        } else if(!"N".equalsIgnoreCase(sure)) {
            view.println(IOText.textInRed(IOText.INVALID_INPUT, IOText.IndentationLevel.AdminSystem));
        }
    }
}
