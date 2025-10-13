package edu.uts.uniapp.model;

import java.io.Serializable;
import java.util.Random;

public class Subject implements Serializable {
    private int id;

    //TODO: mark should be int
    private double mark;
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
    public void setId(int id) {
        this.id = id;
    }
    public double getMark() {
        return mark;
    }
    public void setMark(double mark) {
        this.mark = mark;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String toString(){
        return String.format("[ Subject::%03d -- mark = %.1f -- grade =  %s ]", id, mark, grade);
    }
}
