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
        this.id = new Random().nextInt(1000000) ;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public boolean canEnrollMore() { return subjects.size() < 4; }

    public String grade() {
        if (averageMark() >= 85) return "HD";
        else if (averageMark() >= 75) return "D";
        else if (averageMark() >= 65) return "C";
        else if (averageMark() >= 50) return "P";
        else if (averageMark() >= 25) return "F";
        else return "N/A";
    }

    public void enrollSubject(Subject subject) {
        subjects.add(subject);
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
        return String.format("%06d", id);
    }

    public String toString() {
        return String.format("%s :: %s --> GRADE: %s - MARK: %.2f", getName(), getIdStr(), grade(), averageMark());
    }
    public String info(){
        return String.format("%s :: %s --> Email: %s", getName(), getIdStr(), getEmail());
    }
}

