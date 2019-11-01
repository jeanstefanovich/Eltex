package task3_University;

import java.io.*;
import java.util.Arrays;

public class UserInterface {
    public static void Start() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            System.out.println("Выберите вариант: \n" +
                    "1. Ввести новую запись.\n" +
                    "2. Просмотр записей.\n" +
                    "3. Выйти");

            switch (input()) {
                case "1":
                    User temp = createNewUser();
                    temp.saveToCSV();
                    continue;
                case "2":
                    showUsers();
                    continue;
                case "3":
                    break;
                default:
                    continue;
            }
            break;
        }
    }

    private static Student studentCreateInput() {
        System.out.println("Создание студента:");
        System.out.println("Введите id: ");
        String id = input();
        System.out.println("Введите имя студента:");
        String firstName = input();
        System.out.println("Введите отчество:");
        String middleName = input();
        System.out.println("Введите фамилию:");
        String lastName = input();
        System.out.println("Введите телефон:");
        String phone = input();
        System.out.println("Введите номер студенческого билета:");
        String studentNumber = input();
        System.out.println("Введите группу студента:");
        String group = input();
        return new Student(id, firstName, middleName, lastName, phone, studentNumber, group);
    }

    private static Teacher teacherCreateInput() {
        System.out.println("Создание преподавателя:");
        System.out.println("Введите id: ");
        String id = input();
        System.out.println("Введите имя:");
        String firstName = input();
        System.out.println("Введите отчество:");
        String middleName = input();
        System.out.println("Введите фамилию:");
        String lastName = input();
        System.out.println("Введите телефон:");
        String phone = input();
        System.out.println("Введите id кафедры:");
        String cathedraId = input();
        System.out.println("Введите название кафедры:");
        String cathedraTitle = input();
        System.out.println("Введите интересы:");
        String interests = input();
        return new Teacher(id, firstName, middleName, lastName, phone, cathedraId, cathedraTitle, interests);
    }

    private static User createNewUser() {
        while (true) {
            System.out.println("Выберите кого хотите ввести: \n" +
                    "1. Студента\n" +
                    "2. Преподавателя\n" +
                    "Введите(1, 2): ");
            if (input().equals("1")) {
                return studentCreateInput();
            }
            else if (input().equals("2")) {
                return teacherCreateInput();
            }
            else {
                System.out.println("Некорректный ввод, повторите ввод.");
                continue;
            }
        }
    }

    public static String input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return br.readLine();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return "";
    }

    private static void showUsers() {
        while (true) {
            System.out.println("Выберите файл который хотите просмотреть: \n" +
                    "1. Студенты\n" +
                    "2. Преподаватели\n" +
                    "Введите(1, 2): ");
            String temp = input();
            if (temp.equals("1")) {
                System.out.println("Выбрано 1.");
                showStudents();
                return;
            }
            else if (temp.equals("2")) {
                System.out.println("Выбрано 2.");
                showTeachers();
                return;
            }
            else {
                System.out.println("Некорректный ввод, повторите ввод.");
                continue;
            }
        }
    }

    private static void showStudents() {
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
            System.out.println();
            while (br.ready()) {
                System.out.println(br.readLine());
            }
            System.out.println();
            br.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void showTeachers() {
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
            System.out.println();
            while (br.ready()) {
                System.out.println(br.readLine());
            }
            System.out.println();
            br.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
