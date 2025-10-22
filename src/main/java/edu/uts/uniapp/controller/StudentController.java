package edu.uts.uniapp.controller;

import edu.uts.uniapp.model.Database;
import edu.uts.uniapp.model.Student;
import edu.uts.uniapp.util.RegexConstants;
import edu.uts.uniapp.view.CLIView;
import edu.uts.uniapp.view.IOText;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                        List<Student> all = Database.readAll();
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
                    view.println(IOText.textInRed(IOText.INVALID_OPTION,IOText.IndentationLevel.StudentSystem));
            }
        }
    }
    private record EmailAndPwd(String email, String pwd) {}

    private EmailAndPwd get11(){
        String email = "";
        String pw = "";
        boolean invaildInfo = true;
        while (invaildInfo) {
            email = view.readLine(IOText.textWithIndentation(IOText.IPT_EMAIL,IOText.IndentationLevel.StudentSystem));
            pw = view.readLine(IOText.textWithIndentation(IOText.IPT_PASSWORD,IOText.IndentationLevel.StudentSystem));

            // RegEx check
            if (!pw.matches(RegexConstants.PASSWORD)||!email.matches(RegexConstants.EMAIL)) {
                view.println(IOText.textInRed(IOText.INFO_INVALID, IOText.IndentationLevel.StudentSystem));
            }else{
                invaildInfo=false;
                view.println(IOText.textInYellow(IOText.INFO_VALID, IOText.IndentationLevel.StudentSystem));
            }
        }
        return new EmailAndPwd(email, pw);
    }

    private void register() {

        view.println(IOText.textInGreen(IOText.REGISTER_START,IOText.IndentationLevel.StudentSystem));
        final EmailAndPwd emailAndPwd=get11();

        // 唯一性（按 email）
        List<Student> all = Database.readAll();
        Optional<Student> exists = all.stream().filter(s -> s.getEmail().equalsIgnoreCase(emailAndPwd.email)).findFirst();

        if (exists.isPresent()) {
            view.println(IOText.textInRed(String.format(IOText.REGISTER_FAILED, exists.get().getName()),IOText.IndentationLevel.StudentSystem));
        }else {
            String name = view.readLine(IOText.textWithIndentation(IOText.IPT_NAME,IOText.IndentationLevel.StudentSystem));

            Student s = new Student(name, emailAndPwd.email, emailAndPwd.pwd);
            boolean inValidID=true;
            while(inValidID){
                final Student fs=s;
                if(all.stream().anyMatch((student )->{return student.getId()==fs.getId();})){
                    s = new Student(name, emailAndPwd.email, emailAndPwd.pwd);
                }else{
                    inValidID=false;
                }
            }
            all.add(s);
            Database.writeAll(all);
            view.println(IOText.textInYellow(String.format(IOText.REGISTER_SUCCESS,name),IOText.IndentationLevel.StudentSystem));
        }
    }


    private Student login() {
        List<Student> all = Database.readAll();
        if (all == null){
            all = new ArrayList<>();
        }

        view.println(IOText.textInGreen(IOText.LOGIN_START,IOText.IndentationLevel.StudentSystem));
        final EmailAndPwd emailAndPwd=get11();

        //Optional<Student> exists = all.stream().filter(s -> s.getEmail().equalsIgnoreCase(emailAndPwd.email)&& s.getPassword().equals(emailAndPwd.pwd)).findFirst();

        for (Student s : all) {
            if (s.getEmail().equalsIgnoreCase(emailAndPwd.email) && s.getPassword().equals(emailAndPwd.pwd)) {
                return s;
            }
        }
        view.println(IOText.textInRed(IOText.LOGIN_FAILED, IOText.IndentationLevel.StudentSystem));
        return null;
    }
}

