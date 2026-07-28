package com.example.student;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.*;
import java.util.*;

public class HelloController {
    @FXML private TextField Name, ID, Department, CGPA;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> colName, colID, colDept;
    @FXML private TableColumn<Student, Double> colCGPA;

    private final String FILE = "students.txt";

    @FXML
    public void initialize() {
        // Link Table Columns
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDept.setCellValueFactory(new PropertyValueFactory<>("department"));
        colCGPA.setCellValueFactory(new PropertyValueFactory<>("cgpa"));

        Name.setPrefWidth(200);
        Name.setPrefHeight(20);
        ID.setPrefWidth(200);
        ID.setPrefHeight(20);
        Department.setPrefWidth(200);
        Department.setPrefHeight(20);
        CGPA.setPrefWidth(200);
        CGPA.setPrefHeight(20);


        // Click a row to fill the text fields
        studentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                Name.setText(newV.getName());
                ID.setText(newV.getId());
                Department.setText(newV.getDepartment());
                CGPA.setText(String.valueOf(newV.getCgpa()));
            }
        });

        showAllData();
    }

    @FXML
    protected void processData() { // SAVE BUTTON
        if (Name.getText().isEmpty() || ID.getText().isEmpty()) return;
        try {
            FileWriter fw = new FileWriter(FILE, true);
            PrintWriter pw = new PrintWriter(fw);

            pw.println(Name.getText());
            pw.println(ID.getText());
            pw.println(Department.getText());
            pw.println(CGPA.getText());
            pw.println("----");

            pw.close(); // Saves the file immediately

            showAllData();
            clearFields();
        } catch (Exception e) {
            System.out.println("Error saving");
        }
    }

    private void showAllData() {
        ArrayList<Student> list = new ArrayList<>();
        File file = new File(FILE);
        if (!file.exists()) return;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String n = sc.nextLine();
                String i = sc.nextLine();
                String d = sc.nextLine();
                String cStr = sc.nextLine();
                if (sc.hasNextLine())
                    sc.nextLine(); // skip ----

                list.add(new Student(n, i, d, Double.parseDouble(cStr)));
            }
            studentTable.getItems().setAll(list);
        } catch (Exception e) {
        }
    }

    @FXML
    void searchStudent() {
        ArrayList<Student> results = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(FILE))) {
            while (sc.hasNextLine()) {
                String n = sc.nextLine(); String i = sc.nextLine();
                String d = sc.nextLine(); double c = Double.parseDouble(sc.nextLine());
                sc.nextLine();

                if (n.toLowerCase().contains(Name.getText().toLowerCase()) || i.contains(ID.getText())) {
                    results.add(new Student(n, i, d, c));
                }
            }
            studentTable.getItems().setAll(results);
        } catch (Exception e) { }
    }

    @FXML
    void updateStudent() {
        ArrayList<Student> list = getListFromFile();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(ID.getText())) {
                list.set(i, new Student(Name.getText(), ID.getText(), Department.getText(), Double.parseDouble(CGPA.getText())));
                break;
            }
        }
        saveToFile(list);
    }

    @FXML
    void deleteStudent() {
        ArrayList<Student> list = getListFromFile();
        list.removeIf(s -> s.getId().equals(ID.getText()));
        saveToFile(list);
    }

    private ArrayList<Student> getListFromFile() {
        ArrayList<Student> list = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(FILE))) {
            while (sc.hasNextLine()) {
                String n = sc.nextLine();
                String i = sc.nextLine();
                String d = sc.nextLine();
                double c = Double.parseDouble(sc.nextLine());
                sc.nextLine();
                list.add(new Student(n, i, d, c));
            }
        } catch (Exception e) {

        }
        return list;
    }

    private void saveToFile(ArrayList<Student> list) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Student s : list) {
                pw.println(s.getName()); pw.println(s.getId());
                pw.println(s.getDepartment()); pw.println(s.getCgpa());
                pw.println("----");
            }
            pw.close();
            showAllData();
            clearFields();
        } catch (Exception e) {

        }
    }

    private void clearFields() {
        Name.clear(); ID.clear(); Department.clear(); CGPA.clear();
    }
}