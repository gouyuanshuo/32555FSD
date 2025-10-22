package edu.uts.uniapp.model;

import java.io.Serializable;
import java.util.Random;

public class Subject implements Serializable {
    private int id;

    private int mark;
    private String grade;

    public Subject() {
        this.id = new Random().nextInt(999) + 1;
        this.mark = new Random().nextInt(76) + 25;
        this.grade = calculateGrade(mark);
    }

    private String calculateGrade(double mark) {
        if (mark >= 85) return "HD";
        else if (mark >= 75) return "D";
        else if (mark >= 65) return "C";
        else if (mark >= 50) return "P";
        else return "F";
    }

    public int getId() {
        return id;
    }

    public int getMark() {
        return mark;
    }

    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String toString(){
        return String.format("[ Subject::%03d -- mark = %d -- grade =  %s ]", id, mark, grade);
    }
}
