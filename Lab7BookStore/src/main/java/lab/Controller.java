package lab;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * Name: 		Sudarshan Krishnan
 * Username:	kriss03
 */
public class Controller {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private ListView<String> cartListView;

    @FXML
    private Button addBtn;

    @FXML
    private ListView<String> avbleBooksListView;

    @FXML
    private ImageView logoImg;

    @FXML
    private Button okButton;

    @FXML
    private Button removeBtn;

    @FXML
    private TextField subTotalTxt;

    @FXML
    private TextField taxTxt;

    @FXML
    private TextField totalTxt;

    private ArrayList<Book> books;
    
    @FXML
    public void initialize() {
        avbleBooksListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        cartListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        books = new ArrayList<>();
        // Set default theme (optional)
        switchToLightTheme();
    }

    public void switchToLightTheme() {
        rootPane.getStyleClass().removeAll("light-theme", "dark-theme", "retro-theme");
        rootPane.getStyleClass().add("light-theme");
    }

    public void switchToDarkTheme() {
        rootPane.getStyleClass().removeAll("light-theme", "dark-theme", "retro-theme");
        rootPane.getStyleClass().add("dark-theme");
    }

    public void switchToRetroTheme() {
        rootPane.getStyleClass().removeAll("light-theme", "dark-theme", "retro-theme");
        rootPane.getStyleClass().add("retro-theme");
    }

    @FXML
    void checkOut(ActionEvent event) {
        double subtotal = calculateSubTotal(), tax;
        tax = 0.07 * subtotal;
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Checkout");
        alert.setHeaderText("Checkout Details");
        alert.setContentText(String.format(
            "%-15s$%-8.2f\n%-15s$%-8.2f\n%-15s$%-8.2f",
            "Subtotal:", subtotal,
            "Tax:", tax,
            "Total:", subtotal + tax));
        alert.showAndWait();
        clearCart(event); // Optionally clear the cart after checkout
    }

    @FXML
    void clearCart(ActionEvent event) {
        cartListView.getItems().clear();
    }

    @FXML
    void exitStore(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void loadBooks(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File projectDirectory = new File(System.getProperty("user.dir"));
        fileChooser.setInitialDirectory(projectDirectory);
        File selectedFile = fileChooser.showOpenDialog(null);
        try (RandomAccessFile reader = new RandomAccessFile(selectedFile, "r")) {
            while (true) {
                try {
                    String name = reader.readUTF();
                    double price = reader.readDouble();
                    Book book = new Book(name, price);
                    avbleBooksListView.getItems().add(book.getName());
                    books.add(book);
                } catch (EOFException e) {
                    break; // End of file reached
                }
            }
        }
    }

    @FXML
    void addToCart(ActionEvent event) {
        ObservableList<String> names = avbleBooksListView.getSelectionModel().getSelectedItems();
        cartListView.getItems().addAll(names);
    }

    @FXML
    void removeFromCart(ActionEvent event) {
        ObservableList<String> names = cartListView.getSelectionModel().getSelectedItems();
        cartListView.getItems().removeAll(names);
    }

    double calculateSubTotal() {
        double subtotal = 0;
        for (String name : cartListView.getItems()) {
            for (Book book : books) {
                if (book.getName().equalsIgnoreCase(name)) {
                    subtotal += book.getPrice();
                    break;
                }
            }
        }
        return subtotal;
    }
}
