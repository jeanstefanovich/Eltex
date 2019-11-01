package task3_University;

import java.io.*;
import java.util.ArrayList;

public class Student extends User implements CSV{
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
    public void saveToCSV() {
        try {
            File file = new File("Students.csv");
            FileWriter fw;
            if (file.exists()) {
                fw = new FileWriter(file, true);
            } else {
                fw = new FileWriter(file, false);
                fw.write("Id;FirstName;MiddleName;LastName;Phone;StudentNumber;group \n");
            }
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.toCSV() + "\n");
            bw.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void readFromCSV() {}
    /*
    @Override
    public void readFromCSV() {
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
            while (br.ready()) {
                String tmp = br.readLine();
                this.fromCSV(tmp);
            }
            br.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

     */
}
