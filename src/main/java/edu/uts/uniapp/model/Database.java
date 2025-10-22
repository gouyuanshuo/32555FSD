package edu.uts.uniapp.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String FILE = "students.data";

    public static List<Student> readAll() {
        File f = new File(FILE);
        if (!f.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<Student>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void writeAll(List<Student> students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clear() {
        try (FileOutputStream fos = new FileOutputStream(FILE)) {
            fos.write(new byte[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

