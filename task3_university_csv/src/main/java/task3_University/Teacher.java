package task3_University;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Teacher extends User implements CSV {
    private Cathedra cathedra;
    private String interests;

    public Cathedra getCathedra() {
        return cathedra;
    }

    public void setCathedra(Cathedra cathedra) {
        this.cathedra = cathedra;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public Teacher(){}

    public Teacher(String id, String firstName, String middleName, String lastName, String phone, String cathedraId,
                   String cathedraTitle, String interests) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setMiddleName(middleName);
        this.setLastName(lastName);
        this.setPhone(phone);
        this.setInterests(interests);
        this.setCathedra(new Cathedra(cathedraId, cathedraTitle));
    }

    @Override
    public String toCSV() {
        return this.getId() + ";" + this.getFirstName() + ";" + this.getMiddleName() + ";"
                + this.getLastName() + ";" + this.getPhone() + ";" + this.getCathedra().getId() + ";"
                + this.getCathedra().getTitle() + ";" + this.getInterests();
    }

    @Override
    public void fromCSV(String str) {
        String arr[] = str.split(";");
        this.setId(arr[0]);
        this.setFirstName(arr[1]);
        this.setMiddleName(arr[2]);
        this.setLastName(arr[3]);
        this.setPhone(arr[4]);
        this.setCathedra(new Cathedra(arr[5], arr[6]));
        this.setInterests(arr[7]);
    }

    @Override
    public void saveToCSV() {
        try {
            File file = new File("Teachers.csv");
            FileWriter fw;
            if (file.exists()) {
                 fw = new FileWriter(file, true);
            }
            else {
                fw = new FileWriter(file, false);
                fw.write("Id;FirstName;MiddleName;LastName;Phone;CathedraId;CathedraTitle;Interests\n");
            }
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.toCSV());
            bw.close();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void readFromCSV() {}
/*
    @Override
    public void readFromCSV() {
        try {
            File file = new File("Teachers.csv");
            FileReader fr;
            if (file.exists()) {
                fr = new FileReader("Teachers.csv");
            }
            else {
                System.out.println("Файл Teachers.csv не существует.");
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


