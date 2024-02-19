package lab;

/**
 * Name: Sudarshan Krishnan
 * Username: kriss03
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Controller {
	
	//FXML controls for Scene Builder

	@FXML
	private RadioButton cyanRadio;

	@FXML
	private Button divergeButton;

	@FXML
	private CheckBox eastCheck;

	@FXML
	private Label eastLB;

	@FXML
	private RadioButton greenRadio;

	@FXML
	private CheckBox northCheck;

	@FXML
	private Label northLB;

	@FXML
	private RadioButton orangeRadio;

	@FXML
	private ToggleGroup randomAssSomething;

	@FXML
	private RadioButton salmonRadio;

	@FXML
	private CheckBox southCheck;

	@FXML
	private Label southLB;

	@FXML
	private CheckBox westCheck;

	@FXML
	private Label westLB;

	@FXML
	private TextField wordBox;

	@FXML
	void initialize() {

	}

	// Changing the color of each border area depending on selected CheckBoxes and RadioButtons. 
	//// Handler for changing color based on selection
	
	public void colorChange() {
		
		// Apply color change based on selected RadioButton

		if (salmonRadio.isSelected()) {
			
			// Change color of labels based on CheckBox selection

			if (northCheck.isSelected()) {

				northLB.setStyle("-fx-background-color: salmon;");
			}

			if (southCheck.isSelected()) {

				southLB.setStyle("-fx-background-color: salmon;");
			}

			if (eastCheck.isSelected()) {

				eastLB.setStyle("-fx-background-color: salmon;");
			}

			if (westCheck.isSelected()) {

				westLB.setStyle("-fx-background-color: salmon;");

			}
		}

		if (orangeRadio.isSelected()) {

			if (northCheck.isSelected()) {

				northLB.setStyle("-fx-background-color: orange;");
			}

			if (southCheck.isSelected()) {

				southLB.setStyle("-fx-background-color: orange;");
			}

			if (eastCheck.isSelected()) {

				eastLB.setStyle("-fx-background-color: orange;");
			}

			if (westCheck.isSelected()) {

				westLB.setStyle("-fx-background-color: orange;");

			}
		}

		if (greenRadio.isSelected()) {

			if (northCheck.isSelected()) {

				northLB.setStyle("-fx-background-color: springgreen;");
			}

			if (southCheck.isSelected()) {

				southLB.setStyle("-fx-background-color: springgreen;");
			}

			if (eastCheck.isSelected()) {

				eastLB.setStyle("-fx-background-color: springgreen;");
			}

			if (westCheck.isSelected()) {

				westLB.setStyle("-fx-background-color: springgreen;");

			}
		}

		if (cyanRadio.isSelected()) {

			if (northCheck.isSelected()) {

				northLB.setStyle("-fx-background-color: cyan;");
			}

			if (southCheck.isSelected()) {

				southLB.setStyle("-fx-background-color: cyan;");
			}

			if (eastCheck.isSelected()) {

				eastLB.setStyle("-fx-background-color: cyan;");
			}

			if (westCheck.isSelected()) {

				westLB.setStyle("-fx-background-color: cyan;");

			}
		}

	}

	// Display each entered word clockwise around the border
	// Handler for distributing words to labels
	
	public void diverge() {
		
		// Split input text and distribute words to labels

		String input = wordBox.getText().trim();

		String[] words = input.split("\\s+");

		// Check for exactly four words
		
		if (words.length == 4) {
			
			// Set text for labels in clockwise order

			northLB.setText(words[0]);
			eastLB.setText(words[1]);
			southLB.setText(words[2]);
			westLB.setText(words[3]);
		}

		else {
			
			// Reset text fields and labels if not exactly four words
			
			wordBox.setText("");
			northLB.setText("");
			eastLB.setText("");
			southLB.setText("");
			westLB.setText("");
			wordBox.setPromptText("Please enter exacctly 4 words");
			
			
		}

	}

}
