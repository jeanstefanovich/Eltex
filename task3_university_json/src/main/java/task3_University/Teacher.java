package task3_University;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Teacher extends User implements CSV, JSON {
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
    public void saveToCsvFile() {
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
            bw.write(this.toCSV() + "\n");

            bw.close();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<Teacher> readFromCsvFile() {
        try {
            File file = new File("Teachers.csv");
            FileReader fr;
            if (file.exists()) {
                fr = new FileReader("Teachers.csv");
            }
            else {
                System.out.println("Файл Teachers.csv не существует.");
                return null;
            }
            BufferedReader br = new BufferedReader(fr);
            ArrayList<Teacher> arrayList = new ArrayList<>();
            while (br.ready()) {
                String tmp = br.readLine();
                Teacher temp = new Teacher();
                temp.fromCSV(tmp);
                arrayList.add(temp);
            }
            br.close();
            return arrayList;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void saveToJsonFile() {
        try {
            File file = new File("Teachers.json");
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
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void fromJson(String str) {
        ObjectMapper om = new ObjectMapper();
        try {
            Teacher temp = om.readValue(str, Teacher.class);
            this.setId(temp.getId());
            this.setFirstName(temp.getFirstName());
            this.setMiddleName(temp.getMiddleName());
            this.setLastName(temp.getLastName());
            this.setPhone(temp.getPhone());
            this.setCathedra(temp.getCathedra());
            this.setInterests(temp.getInterests());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void ConvertFromCsvToJson() {
        try {
            File file = new File("Teachers.csv");
            FileReader fr;
            if (file.exists()) {
                fr = new FileReader("Teachers.csv");
            }
            else {
                System.out.println("Файл Teachers.csv не существует.");
                return;
            }
            BufferedReader br = new BufferedReader(fr);
            //pass through colomns titles
            if (br.ready()) br.readLine();
            while (br.ready()) {
                String tmp = br.readLine();
                Teacher temp = new Teacher();
                temp.fromCSV(tmp);
                temp.saveToJsonFile();
            }
            br.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<Teacher> readFromJsonFile() {
        try {
            File file = new File("Teachers.json");
            FileReader fr;
            if (file.exists()) {
                fr = new FileReader("Teachers.json");
            }
            else {
                System.out.println("Файл Teachers.json не существует.");
                return null;
            }
            ObjectMapper om = new ObjectMapper();
            ArrayList<Teacher> arrayList = om.readValue(file, new TypeReference<ArrayList<Teacher>>() {
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
                + this.getLastName() + ";" + this.getPhone() + ";" + this.getCathedra().getId() + ";"
                + this.getCathedra().getTitle() + ";" + this.getInterests();
    }

    public static void ConvertFromJsonToCsv() {
        ArrayList<Teacher> list = readFromJsonFile();
        for (Teacher temp : list) {
            System.out.println("Конвертация записи: " + temp.toString());
            temp.saveToCsvFile();
        }
    }
}


