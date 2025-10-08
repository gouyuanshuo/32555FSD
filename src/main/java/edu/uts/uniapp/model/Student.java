package edu.uts.uniapp.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Student implements Serializable {
    private int id;
    private String name;
    private String email;
    private String password;
    private List<Subject> subjects = new ArrayList<>();

    public Student(String name, String email, String password) {
        this.id = new Random().nextInt(900000) + 100000;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public boolean canEnrollMore() { return subjects.size() < 4; }

    public void enrollSubject(Subject subject) {
        if (canEnrollMore()) subjects.add(subject);
        else System.out.println("Cannot enrol more than 4 subjects.");
    }

    public boolean removeSubjectById(int subjectId) {
        return subjects.removeIf(s -> s.getId() == subjectId);
    }

    public double averageMark() {
        return subjects.stream().mapToDouble(Subject::getMark).average().orElse(0.0);
    }

    public boolean isPass() { return averageMark() >= 50; }

    // getters/setters/toString
    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdStr(){
        return String.valueOf(id);
    }

    public String toString() {
        return String.format(
                "Student{id=%s, name='%s', email='%s', avg=%.1f, pass=%s, subjects=%d}",
                getIdStr(), name, email, averageMark(), isPass() ? "Y" : "N", subjects.size()
        );
    }
}

