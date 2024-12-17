package com.example.finalprojectbase;
/**
 * @author Rumesh Chathuska
 * CSC 225 - Project 4
 * BookDatabase Application
 *
 * This JavaFX application is designed to manage book records in a database.
 * The program allows users to:
 *
 * 1. Add new book records with details such as title, author, year published, price,
 *    and bestseller status.
 * 2. View all records in a JavaFX TableView, displaying stored data from the database.
 * 3. Filter the book records to display only bestsellers.
 * 4. Update existing records when a duplicate entry (based on title) is detected.
 * 5. Delete a specific record by entering its title.
 * 6. Delete all records from the database.
 *
 * Key Features:
 * - The application uses JavaFX for the user interface and Apache Derby for database operations.
 * - Records can be added, viewed, updated, filtered, or deleted via intuitive form inputs and buttons.
 * - A "Delete Record" feature allows the user to specify the title of a book to delete it directly.
 * - A "Delete All Records" button clears all book records from the database.
 * - Validation ensures all required fields are entered and formatted correctly.
 * - The application uses a case-insensitive check for duplicate titles in the database.
 * - Bestsellers can be toggled using radio buttons, and the TableView dynamically updates with any changes.
 * - Table columns are sortable: clicking on a column header allows sorting in ascending or descending order.
 *
 * Usage:
 * - To add a book record, enter details in the form and click "Submit Record."
 * - To view all records, click "View Table."
 * - To filter bestseller books, click "Filter Bestselling Books."
 * - To delete a specific record, enter the book title in the "Enter Title to Delete" field and click "Delete Record."
 * - To delete all records, click "Delete All Records."
 * - Duplicate entries based on the title will prompt the user to either update the existing record or keep the current record unchanged.
 * - To sort records by a specific column (e.g., title, author, year), click on the column header in the TableView.
 *
 * Designed and implemented for educational purposes, this application demonstrates the integration of
 * JavaFX and database management using Apache Derby.
 *
 */


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.*;

public class BookDatabase extends Application {

    private final TableView<Book> table = new TableView<>();
    private final ObservableList<Book> bookData = FXCollections.observableArrayList();

    @Override
    public void init() throws Exception {
        try (Connection connect = DriverManager.getConnection("jdbc:derby:BookDatabase;create=true")) {
            Statement state = connect.createStatement();
            DatabaseMetaData dbm = connect.getMetaData();
            ResultSet result = dbm.getTables(null, null, "BOOK_INFO", null);

            if (!result.next()) {
                state.execute("CREATE TABLE book_info (" +
                        "title VARCHAR(100), " +
                        "author VARCHAR(100), " +
                        "year_published INT, " +
                        "price DOUBLE, " +
                        "is_bestseller BOOLEAN)");
                System.out.println("Table BOOK_INFO created.");
            } else {
                System.out.println("Table BOOK_INFO already exists.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        // Title Label
        Label title = new Label("Book Information Form");
        title.setPrefWidth(850);
        title.setAlignment(Pos.CENTER);
        title.setPadding(new Insets(20, 0, 40, 0));
        title.setFont(Font.font("Verdana", 28));

        // Form components
        Label lbTitle = new Label("Book Title:");
        TextField tfTitle = new TextField();
        Label lbAuthor = new Label("Author:");
        TextField tfAuthor = new TextField();
        Label lbYear = new Label("Year Published:");
        TextField tfYear = new TextField();
        Label lbPrice = new Label("Price:");
        TextField tfPrice = new TextField();
        Label lbBestseller = new Label("Is Bestseller?");
        RadioButton rbYes = new RadioButton("Yes");
        RadioButton rbNo = new RadioButton("No");
        ToggleGroup tgBestseller = new ToggleGroup();
        rbYes.setToggleGroup(tgBestseller);
        rbNo.setToggleGroup(tgBestseller);
        //rbYes.setSelected(true);

        Label lbDelete = new Label("Enter Title to Delete:");
        TextField tfDelete = new TextField();
        Button deleteRecord = new Button("Delete Record");

        Button submit = new Button("Submit Record");
        Button view = new Button("View Table");
        Button filter = new Button("Filter Bestselling Books");
        Button deleteAll = new Button("Delete All Records");

        // Styling
        lbTitle.setFont(Font.font("Verdana", 20));
        tfTitle.setFont(Font.font("Verdana", 20));
        lbAuthor.setFont(Font.font("Verdana", 20));
        tfAuthor.setFont(Font.font("Verdana", 20));
        lbYear.setFont(Font.font("Verdana", 20));
        tfYear.setFont(Font.font("Verdana", 20));
        lbPrice.setFont(Font.font("Verdana", 20));
        tfPrice.setFont(Font.font("Verdana", 20));
        lbBestseller.setFont(Font.font("Verdana", 20));
        rbYes.setFont(Font.font("Verdana", 20));
        rbNo.setFont(Font.font("Verdana", 20));
        submit.setFont(Font.font("Verdana", 20));
        view.setFont(Font.font("Verdana", 20));
        filter.setFont(Font.font("Verdana", 20));
        deleteAll.setFont(Font.font("Verdana", 20));
        lbDelete.setFont(Font.font("Verdana", 20));
        tfDelete.setFont(Font.font("Verdana", 20));
        deleteRecord.setFont(Font.font("Verdana", 20));

        // Layout
        GridPane gp = new GridPane();
        gp.add(lbTitle, 0, 0);
        gp.add(tfTitle, 1, 0, 2, 1);
        gp.add(lbAuthor, 0, 1);
        gp.add(tfAuthor, 1, 1, 2, 1);
        gp.add(lbYear, 0, 2);
        gp.add(tfYear, 1, 2, 2, 1);
        gp.add(lbPrice, 0, 3);
        gp.add(tfPrice, 1, 3, 2, 1);
        gp.add(lbBestseller, 0, 4);
        gp.add(rbYes, 1, 4);
        gp.add(rbNo, 2, 4);
        gp.add(submit, 0, 5);
        gp.add(view, 1, 5);
        gp.add(filter, 2, 5);
        gp.setVgap(20);
        gp.setHgap(20);
        gp.add(lbDelete, 0, 6); // Add label for delete field
        gp.add(tfDelete, 1, 6, 2, 1); // Add TextField for delete
        gp.add(deleteRecord, 0, 7); // Add delete button
        gp.add(deleteAll, 1, 7);


        //Add the Delete All Button Layout to the Main Layout
        VBox mainLayout = new VBox(title, gp);
        mainLayout.setSpacing(10);
        mainLayout.setPadding(new Insets(20));



        // TableView configuration
        TableColumn<Book, String> titleCol = new TableColumn<>("Book's Title");
        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        TableColumn<Book, Integer> yearCol = new TableColumn<>("Year Published");
        TableColumn<Book, Double> priceCol = new TableColumn<>("Price");
        TableColumn<Book, Boolean> bestsellerCol = new TableColumn<>("Bestseller?");

        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("yearPublished"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        bestsellerCol.setCellValueFactory(new PropertyValueFactory<>("bestseller"));

        table.getColumns().add(titleCol);
        table.getColumns().add(authorCol);
        table.getColumns().add(yearCol);
        table.getColumns().add(priceCol);
        table.getColumns().add(bestsellerCol);

        table.setStyle("-fx-font-size: 30px; -fx-pref-width: 880px");
        titleCol.setStyle("-fx-font-size: 30px; -fx-pref-width: 220px");
        authorCol.setStyle("-fx-font-size: 30px; -fx-pref-width: 220px");
        yearCol.setStyle("-fx-font-size: 30px; -fx-pref-width: 220px");
        priceCol.setStyle("-fx-font-size: 30px; -fx-pref-width: 220px");
        bestsellerCol.setStyle("-fx-font-size: 30px; -fx-pref-width: 220px");

        Scene viewScene = new Scene(table);

        // Submit event handler
        submit.setOnAction(e -> handleSubmit(tfTitle, tfAuthor, tfYear, tfPrice, rbYes, rbNo));

        // View event handler
        view.setOnAction(e -> handleView(viewScene));

        // Filter event handler
        filter.setOnAction(e -> handleFilter());

        // Delete All event handler
        deleteAll.setOnAction(e -> handleDeleteAll());

        // Add action to the Delete Record button
        deleteRecord.setOnAction(e -> handleDeleteRecord(tfDelete));

        primaryStage.setTitle("Book Information Form");
        primaryStage.setScene(new Scene(mainLayout, 775, 630));
        primaryStage.show();
    }


        // Submit event handler
        private void handleSubmit(TextField tfTitle, TextField tfAuthor, TextField tfYear, TextField tfPrice, RadioButton rbYes, RadioButton rbNo) {
            try (Connection connect = DriverManager.getConnection("jdbc:derby:BookDatabase")) {


                // Validate Input Fields (from previous code)
                if (tfTitle.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Input Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Book Title is required.");
                    alert.show();
                    return;
                }

                if (tfAuthor.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Input Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Author is required.");
                    alert.show();
                    return;
                }

                int year;
                try {
                    year = Integer.parseInt(tfYear.getText());
                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Input Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Year Published must be a valid integer.");
                    alert.show();
                    return;
                }

                double price;
                try {
                    price = Double.parseDouble(tfPrice.getText());
                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Input Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Price must be a valid number.");
                    alert.show();
                    return;
                }

                if (!rbYes.isSelected() && !rbNo.isSelected()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Input Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select if the book is a bestseller.");
                    alert.show();
                    return;
                }

                // Check for duplicates in the database (case-insensitive)
                String checkSql = "SELECT * FROM book_info WHERE LOWER(title) = LOWER(?)";
                PreparedStatement checkStmt = connect.prepareStatement(checkSql);
                checkStmt.setString(1, tfTitle.getText());
                ResultSet result = checkStmt.executeQuery();


                if (result.next()) {
                    // Duplicate found: Prompt the user
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Duplicate Entry");
                    alert.setHeaderText("A record with this title already exists.");
                    alert.setContentText("Do you want to update the existing record with the new values?");

                    ButtonType updateButton = new ButtonType("Update");
                    ButtonType keepButton = new ButtonType("Keep Current");
                    ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                    alert.getButtonTypes().setAll(updateButton, keepButton, cancelButton);

                    // Handle user choice
                    alert.showAndWait().ifPresent(response -> {
                        table.getItems().clear();
                        if (response == updateButton) {
                            try {
                                // Update the existing record, including the title
                                String updateSql = "UPDATE book_info SET title = ?, author = ?, year_published = ?, price = ?, is_bestseller = ? WHERE LOWER(title) = LOWER(?)";
                                PreparedStatement updateStmt = connect.prepareStatement(updateSql);

                                // Bind the new values to the query
                                updateStmt.setString(1, tfTitle.getText()); // New title
                                updateStmt.setString(2, tfAuthor.getText()); // New author
                                updateStmt.setInt(3, year); // New year
                                updateStmt.setDouble(4, price); // New price
                                updateStmt.setBoolean(5, rbYes.isSelected()); // New bestseller status
                                updateStmt.setString(6, tfTitle.getText()); // Original title for WHERE clause

                                // Execute the update
                                int rowsUpdated = updateStmt.executeUpdate();

                                tfTitle.setText("");
                                tfAuthor.setText("");
                                tfYear.setText("");
                                tfPrice.setText("");
                                rbYes.setSelected(true);

                                // Show success message
                                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                                successAlert.setTitle("Success");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("Record updated successfully.");
                                successAlert.show();


                            } catch (SQLException ex) {

                                ex.printStackTrace();
                                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                                errorAlert.setTitle("Error");
                                errorAlert.setHeaderText(null);
                                errorAlert.setContentText("Failed to update the record: " + ex.getMessage());
                                errorAlert.show();
                            }
                        } else if (response == keepButton) {
                            // Keep the current record, do nothing
                            tfTitle.setText("");
                            tfAuthor.setText("");
                            tfYear.setText("");
                            tfPrice.setText("");
                            rbYes.setSelected(true);

                            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                            infoAlert.setTitle("Info");
                            infoAlert.setHeaderText(null);
                            infoAlert.setContentText("Current record kept unchanged.");
                            infoAlert.show();
                        }

                    });

                } else {
                    // No duplicate: Insert the new record
                    PreparedStatement addRecord = connect.prepareStatement("INSERT INTO book_info VALUES(?, ?, ?, ?, ?)");
                    addRecord.setString(1, tfTitle.getText());
                    addRecord.setString(2, tfAuthor.getText());
                    addRecord.setInt(3, year);
                    addRecord.setDouble(4, price);
                    addRecord.setBoolean(5, rbYes.isSelected());
                    addRecord.executeUpdate();

                    tfTitle.setText("");
                    tfAuthor.setText("");
                    tfYear.setText("");
                    tfPrice.setText("");
                    rbYes.setSelected(true);

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Success");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Record added successfully.");
                    successAlert.show();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        };



        // View event handler
        private void handleView(Scene viewScene) {
            table.getItems().clear();
            try (Connection connect = DriverManager.getConnection("jdbc:derby:BookDatabase")) {
                Statement state = connect.createStatement();
                ResultSet result = state.executeQuery("SELECT * FROM book_info");
                while (result.next()) {
                    String Title = result.getString("title");
                    String Author = result.getString("author");
                    int Year = result.getInt("year_published");
                    double price = result.getDouble("price");
                    boolean sellStatus = result.getBoolean("is_bestseller");

                    Book newBook = new Book(Title, Author, Year, price, sellStatus);
                    bookData.add(newBook);

                    table.setItems(bookData);
                }
                Stage smallStage = new Stage();
                smallStage.setScene(viewScene);
               // smallStage.setScene(new Scene(new VBox(table), 600, 600));
                smallStage.setTitle("All Records");
                smallStage.show();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        };

        // Filter Button Logic
        private void handleFilter() {
            try (Connection connect = DriverManager.getConnection("jdbc:derby:BookDatabase")) {
                // Prepare query to filter bestseller books
                String sql = "SELECT title, price FROM book_info WHERE is_bestseller = ?";
                PreparedStatement query = connect.prepareStatement(sql);
                query.setBoolean(1, true); // Filter for bestseller books
                ResultSet result = query.executeQuery();

                // Create a new ObservableList for filtered books
                ObservableList<Book> filteredBooks = FXCollections.observableArrayList();

                // Populate the list with filtered data
                while (result.next()) {
                    String filteredTitle = result.getString("title");
                    double filteredPrice = result.getDouble("price");

                    // Add filtered book objects (only title and price are relevant here)
                    filteredBooks.add(new Book(filteredTitle, null, 0, filteredPrice, true));
                }

                // Create a new TableView for the filtered results
                TableView<Book> filteredTable = new TableView<>(filteredBooks);

                // Configure columns for filtered table
                TableColumn<Book, String> filteredTitleCol = new TableColumn<>("Book Title");
                TableColumn<Book, Double> filteredPriceCol = new TableColumn<>("Price");

                // Bind columns to the corresponding properties
                filteredTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
                filteredPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

                // Add columns to the filtered table
                filteredTable.getColumns().add(filteredTitleCol);
                filteredTable.getColumns().add(filteredPriceCol);

                // Style the filtered table and columns
                filteredTable.setStyle("-fx-font-size: 18px; -fx-pref-width: 400px;");
                filteredTitleCol.setStyle("-fx-font-size: 18px; -fx-pref-width: 200px;");
                filteredPriceCol.setStyle("-fx-font-size: 18px; -fx-pref-width: 200px;");

                // Create a new stage to display the filtered table
                Stage filterStage = new Stage();
                filterStage.setScene(new Scene(new VBox(filteredTable), 400, 400));
                filterStage.setTitle("Bestseller Books");
                filterStage.show();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        };

        // Delete All Button Logic
        private void handleDeleteAll() {
            try (Connection connect = DriverManager.getConnection("jdbc:derby:BookDatabase")) {
                // Prepare the DELETE query to remove all rows
                String sql = "DELETE FROM book_info";
                Statement statement = connect.createStatement();
                int rowsDeleted = statement.executeUpdate(sql);

                // Optional: Refresh the TableView (clear all data)
                table.getItems().clear();

                // Display confirmation message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText(rowsDeleted + " record(s) deleted successfully.");
                alert.show();
            } catch (SQLException ex) {
                ex.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to delete records: " + ex.getMessage());
                alert.show();
            }
        };
    // Delete record event handler
    private void handleDeleteRecord(TextField tfDelete) {
        String titleToDelete = tfDelete.getText().trim(); // Get the input text

        // Validate input
        if (titleToDelete.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter the title of the book to delete.");
            alert.show();
            return;
        }

        try (Connection connect = DriverManager.getConnection("jdbc:derby:BookDatabase")) {
            // Prepare the DELETE query with a condition
            String sql = "DELETE FROM book_info WHERE LOWER(title) = LOWER(?)";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, titleToDelete);

            // Execute the query and check the result
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Record deleted successfully.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Not Found");
                alert.setHeaderText(null);
                alert.setContentText("No record found with the given title.");
                alert.show();
            }

            // Clear the input field after operation
            tfDelete.setText("");
        } catch (SQLException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to delete the record: " + ex.getMessage());
            alert.show();
        }
    }

    @Override
    public void stop() {
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException ex) {
            if (!"XJ015".equals(ex.getSQLState())) {
                ex.printStackTrace();
            }
        }
    }

        // Main Scene


    public static void main(String[] args) {
        launch(args);
    }
}
