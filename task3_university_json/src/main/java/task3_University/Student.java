package task3_University;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.RandomAccessFile;
import java.io.*;
import java.util.ArrayList;

public class Student extends User implements CSV, JSON {
    private String studentNumber;
    private String group;

    public Student(){}

    public Student(String id, String firstName, String middleName, String lastName, String phone, String studentNumber,
                   String group) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setMiddleName(middleName);
        this.setLastName(lastName);
        this.setPhone(phone);
        this.setStudentNumber(studentNumber);
        this.setGroup(group);
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toCSV() {
        return this.getId() + ";" + this.getFirstName() + ";" + this.getMiddleName() + ";"
                + this.getLastName() + ";" + this.getPhone() + ";" + this.getStudentNumber() + ";"
                + this.getGroup();
    }

    @Override
    public void fromCSV(String str) {
        String arr[] = str.split(";");
        this.setId(arr[0]);
        this.setFirstName(arr[1]);
        this.setMiddleName(arr[2]);
        this.setLastName(arr[3]);
        this.setPhone(arr[4]);
        this.setStudentNumber(arr[5]);
        this.setGroup(arr[6]);
    }

    @Override
    public void saveToCsvFile() {
        try {
            File file = new File("Students.json");
            FileWriter fw;
            if (file.exists()) {
                fw = new FileWriter(file, true);
            } else {
                fw = new FileWriter(file, false);
                fw.write("Id;FirstName;MiddleName;LastName;Phone;StudentNumber;group\n");
            }
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.toCSV() + "\n");
            bw.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<Student> readFromCsvFile() {
        try {
            File file = new File("Students.csv");
            FileReader fr;
            if (file.exists()) {
                fr = new FileReader("Students.csv");
            }
            else {
                System.out.println("Файл Students.csv не существует.");
                throw new IOException();
            }
            BufferedReader br = new BufferedReader(fr);
            ArrayList<Student> arrayList = new ArrayList<>();
            while (br.ready()) {
                String tmp = br.readLine();
                Student temp = new Student();
                temp.fromCSV(tmp);
            }
            br.close();
            return arrayList;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static void ConvertFromCsvToJson() {
        try {
            File file = new File("Students.csv");
            FileReader fr;
            if (file.exists()) {
                fr = new FileReader("Students.csv");
            }
            else {
                System.out.println("Файл Students.csv не существует.");
                return;
            }
            BufferedReader br = new BufferedReader(fr);
            //pass through colomns titles
            if (br.ready()) br.readLine();
            while (br.ready()) {
                String tmp = br.readLine();
                Student temp = new Student();
                temp.fromCSV(tmp);
                temp.saveToJsonFile();
            }
            br.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void fromJson(String str) {
        ObjectMapper om = new ObjectMapper();
        try {
            Student temp = om.readValue(str, Student.class);
            this.setId(temp.getId());
            this.setFirstName(temp.getFirstName());
            this.setMiddleName(temp.getMiddleName());
            this.setLastName(temp.getLastName());
            this.setPhone(temp.getPhone());
            this.setStudentNumber(temp.getStudentNumber());
            this.setGroup(temp.getGroup());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveToJsonFile() {
        try {
            File file = new File("Students.json");
            RandomAccessFile raf;
            FileWriter fw;
            String delimeter;
            if (file.exists()) {
                raf = new RandomAccessFile(file, "rw");
                raf.seek(raf.length() - 1);
                fw = new FileWriter(raf.getFD());
                delimeter = ", ";
            } else {
                raf = new RandomAccessFile(file, "rw");
                fw = new FileWriter(raf.getFD());
                fw.write("[");
                delimeter = "";
            }
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(delimeter + this.toJSON() + "]");
            bw.close();
            /*
            FileWriter fw;

            if (file.exists()) {
                fw = new FileWriter(file, true);
                fw.write("\b");
            }
            else {
                fw = new FileWriter(file, false);
                fw.write("[");
            }
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.toJSON());
            bw.write("]");
            bw.close();
            */
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<Student> readFromJsonFile() {
        try {
            File file = new File("Students.json");
            FileReader fr;
            if (file.exists()) {
                fr = new FileReader("Students.json");
            }
            else {
                System.out.println("Файл Students.json не существует.");
                return null;
            }
            ObjectMapper om = new ObjectMapper();
            ArrayList<Student> arrayList = om.readValue(file, new TypeReference<ArrayList<Student>>() {
            });
            return  arrayList;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public String toString() {
        return this.getId() + ";" + this.getFirstName() + ";" + this.getMiddleName() + ";"
                + this.getLastName() + ";" + this.getPhone() + ";" + this.getStudentNumber() + ";"
                + this.getGroup();
    }

    public static void ConvertFromJsonToCsv() {
        ArrayList<Student> list = readFromJsonFile();
        for (Student temp : list) {
            System.out.println("Конвертация записи: " + temp.toString());
            temp.saveToCsvFile();
        }
    }
}
