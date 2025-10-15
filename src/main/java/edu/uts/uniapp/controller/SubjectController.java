package edu.uts.uniapp.controller;

import edu.uts.uniapp.model.Database;
import edu.uts.uniapp.model.Student;
import edu.uts.uniapp.model.Subject;
import edu.uts.uniapp.util.RegexConstants;
import edu.uts.uniapp.view.CLIView;
import edu.uts.uniapp.view.IOText;

import java.util.List;

public class SubjectController {
    private final CLIView view = new CLIView();

    public void run(Student current, List<Student> all) {
        boolean back = false;
        while (!back) {
            char op = view.readOption(IOText.textInBlue(IOText.SUB_PROMPT_SELECT,IOText.IndentationLevel.SubjectSystem));

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
                    view.println(IOText.textInRed(IOText.INVALID_OPTION,IOText.IndentationLevel.SubjectSystem));
            }
        }
    }

    private void changePassword(Student current, List<Student> all) {

        view.println(IOText.textInYellow(IOText.ENR_UPD_PWD, IOText.IndentationLevel.SubjectSystem));

        String newPw = view.readLine(IOText.textWithIndentation(IOText.ENR_PWD_PROMPT,IOText.IndentationLevel.SubjectSystem));
        boolean passwordNowMatch=true;
        while(passwordNowMatch){
            String confirmPw = view.readLine(IOText.textWithIndentation(IOText.ENR_CONFIRM_PWD,IOText.IndentationLevel.SubjectSystem));
            if (!newPw.equals(confirmPw)) {
                view.println(IOText.textInRed(IOText.ENR_PWD_NOT_MATCH,IOText.IndentationLevel.SubjectSystem));
            }else{
                passwordNowMatch=false;
            }
        }

        if(newPw.matches(RegexConstants.PASSWORD)) {
            current.setPassword(newPw);
            Database.writeAll(all);
        }else{
            view.println(IOText.textInRed(IOText.ENR_PWD_INVALID,IOText.IndentationLevel.SubjectSystem));
        }

    }

    private void enrol(Student current, List<Student> all) {
        if (!current.canEnrollMore()) {
            view.println(IOText.textInRed(IOText.ENR_LIMIT_REACHED,IOText.IndentationLevel.SubjectSystem));
            return;
        }
        Subject s = new Subject();
        current.enrollSubject(s);
        Database.writeAll(all);
        view.println(IOText.textInYellow(String.format(IOText.ENR_ENROLLED_FMT, s.getId()), IOText.IndentationLevel.SubjectSystem));
        view.println(IOText.textInYellow(String.format(IOText.ENR_LIST_HEADER, current.getSubjects().size()),IOText.IndentationLevel.SubjectSystem));
    }

    private void remove(Student current, List<Student> all) {
        String idStr = view.readLine(IOText.textWithIndentation(IOText.ENR_REMOVE_PROMPT, IOText.IndentationLevel.SubjectSystem));
        try {
            int sid = Integer.parseInt(idStr);
            boolean ok = current.removeSubjectById(sid);
            if (ok) {
                Database.writeAll(all);
                view.println(IOText.textInYellow(String.format(IOText.ENR_REMOVE_OK, sid), IOText.IndentationLevel.SubjectSystem));
                view.println(IOText.textInYellow(String.format(IOText.ENR_LIST_HEADER, current.getSubjects().size()), IOText.IndentationLevel.SubjectSystem));
            } else {
                view.println(IOText.textInRed(IOText.ENR_REMOVE_NF,IOText.IndentationLevel.SubjectSystem));
            }
        } catch (NumberFormatException e) {
            view.println(IOText.textInRed(IOText.ENR_REMOVE_BAD,IOText.IndentationLevel.SubjectSystem));
        }
    }

    private void show(Student current) {
        view.println(IOText.textInYellow(String.format(IOText.ENR_SHOW_SUB, current.getSubjects().size()), IOText.IndentationLevel.SubjectSystem));
        current.getSubjects().forEach(s -> view.println(IOText.textWithIndentation(s.toString(), IOText.IndentationLevel.SubjectSystem)));
    }
}

