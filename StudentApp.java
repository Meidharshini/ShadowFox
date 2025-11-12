import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StudentApp extends Application {

    ObservableList<Student> studentList = FXCollections.observableArrayList();
    TableView<Student> table = new TableView<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        // Input Fields for adding new students
        TextField idField = new TextField();
        idField.setPromptText("ID (Numbers only)");
        TextField nameField = new TextField();
        nameField.setPromptText("Name (Letters only)");
        TextField gradeField = new TextField();
        gradeField.setPromptText("Grade (String)");

        // Buttons
        Button addBtn = new Button("Add");
        Button deleteBtn = new Button("Delete");

        // Table columns
        TableColumn<Student, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        idCol.setCellFactory(TextFieldTableCell.forTableColumn());
        idCol.setOnEditCommit(event -> {
            String newId = event.getNewValue().trim();
            if (!newId.matches("\\d+")) {
                showAlert("ID must be numeric.");
                table.refresh();
            } else {
                event.getRowValue().setId(newId);
            }
        });

        TableColumn<Student, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(event -> {
            String newName = event.getNewValue().trim();
            if (!newName.matches("[a-zA-Z ]+")) {
                showAlert("Name must contain letters only.");
                table.refresh();
            } else {
                event.getRowValue().setName(newName);
            }
        });

        TableColumn<Student, String> gradeCol = new TableColumn<>("Grade");
        gradeCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGrade()));
        gradeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        gradeCol.setOnEditCommit(event -> {
            String newGrade = event.getNewValue().trim();
            if (newGrade.isEmpty()) {
                showAlert("Grade cannot be empty.");
                table.refresh();
            } else {
                event.getRowValue().setGrade(newGrade);
            }
        });

        table.getColumns().addAll(idCol, nameCol, gradeCol);
        table.setItems(studentList);
        table.setEditable(true);  // Enable editing

        // Add Button
        addBtn.setOnAction(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String grade = gradeField.getText().trim();

            if (!validateInput(id, name, grade)) return;

            studentList.add(new Student(id, name, grade));
            clearFields(idField, nameField, gradeField);
        });

        // Delete Button
        deleteBtn.setOnAction(e -> {
            Student selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                studentList.remove(selected);
            } else {
                showAlert("Please select a student to delete.");
            }
        });

        // Layout
        HBox inputBox = new HBox(10, idField, nameField, gradeField, addBtn, deleteBtn);
        VBox root = new VBox(10, inputBox, table);
        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.setTitle("Student Information System");
        stage.show();
    }

    // Validation method
    private boolean validateInput(String id, String name, String grade) {
        if(id.isEmpty() || name.isEmpty() || grade.isEmpty()) {
            showAlert("Please fill all fields.");
            return false;
        }

        if (!id.matches("\\d+")) {
            showAlert("ID must be numeric.");
            return false;
        }

        if (!name.matches("[a-zA-Z ]+")) {
            showAlert("Name must contain letters only.");
            return false;
        }

        return true;
    }

    // Clear input fields
    private void clearFields(TextField id, TextField name, TextField grade) {
        id.clear();
        name.clear();
        grade.clear();
    }

    // Alert
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

// Student class with setters for updates
class Student {
    private String id;
    private String name;
    private String grade;

    public Student(String id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}
