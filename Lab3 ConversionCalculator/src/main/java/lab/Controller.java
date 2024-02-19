package lab;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Name: Prakhar Verma
 * Username: vermp02
 */

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button calculate;

    @FXML
    private Button clear;

    @FXML
    private TextField cmT;

    @FXML
    private Label errorL;

    @FXML
    private Button exit;

    @FXML
    private TextField inT;

    @FXML
    private TextField mT;

    @FXML
    private TextField yT;

    @FXML
    void initialize() {
        errorL.setVisible(false);
    }

        // Method to determine which conversion to perform based on which input field is filled.
    public void convert() {
        if (!cmT.getText().isEmpty()) {
            cmConvert();
        } else if (!inT.getText().isEmpty()) {
            inConvert();
        } else if (!yT.getText().isEmpty()) {
            yConvert();
        } else if (!mT.getText().isEmpty()) {
            mConvert();
        }
    }

    // Method to check if the conversion can proceed based on the input fields' states.
    public boolean canConvert() {

        boolean status = false;

        if (!cmT.getText().isEmpty() && inT.getText().isEmpty() && yT.getText().isEmpty() && mT.getText().isEmpty())
            status = true;

        else if (cmT.getText().isEmpty() && !inT.getText().isEmpty() && yT.getText().isEmpty()
                && mT.getText().isEmpty())
            status = true;

        else if (cmT.getText().isEmpty() && inT.getText().isEmpty() && !yT.getText().isEmpty()
                && mT.getText().isEmpty())
            status = true;

        else if (cmT.getText().isEmpty() && inT.getText().isEmpty() && yT.getText().isEmpty()
                && !mT.getText().isEmpty())
            status = true;

        return status;
    }


    // Conversion methods from one unit to others. Each method follows a similar structure:
    // 1. Check if conversion can proceed with canConvert().
    // 2. Hide the error label if conversion is valid.
    // 3. Perform the conversion using the respective method.
    // 4. Set the converted values in the corresponding text fields, formatted to two decimal places.
    // 5. Show the error label if conversion is not valid due to multiple inputs or other reasons.

    public void cmConvert() {
        if (canConvert()) {

            errorL.setVisible(false);

            Double cmInput = Double.parseDouble(cmT.getText());

            Double mOut = cm2m(cmInput);
            Double inOut = cm2in(cmInput);
            Double yOut = cm2y(cmInput);

            mT.setText(String.format("%.2f", mOut));
            inT.setText(String.format("%.2f", inOut));
            yT.setText(String.format("%.2f", yOut));

        } else {
            errorL.setVisible(true);
        }
    }
    public static double cm2in(double d) {
        return(d / 2.54);
    }
    public static double cm2m(double d) {
        return(d / 100);
    }
    public static double cm2y(double d) {
        return(d / 91.44);
    }

    public void inConvert() {
        if (canConvert()) {

            errorL.setVisible(false);

            Double inInput = Double.parseDouble(inT.getText());

            Double mOut = in2m(inInput);
            Double cmOut = in2cm(inInput);
            Double yOut = in2y(inInput);

            mT.setText(String.format("%.2f", mOut));
            cmT.setText(String.format("%.2f", cmOut));
            yT.setText(String.format("%.2f", yOut));

        } else {
            errorL.setVisible(true);
        }
    }
    public static double in2cm(double d) {
        return(d * 2.54);
    }
    public static double in2y(double d) {
        return(d / 36);
    }
    public static double in2m(double d) {
        return(d / 39.37);
    }

    public void yConvert() {
        if (canConvert()) {

            errorL.setVisible(false);

            Double yInput = Double.parseDouble(yT.getText());

            Double mOut = y2m(yInput);
            Double cmOut = y2cm(yInput);
            Double inOut = y2in(yInput);

            mT.setText(String.format("%.2f", mOut));
            cmT.setText(String.format("%.2f", cmOut));
            inT.setText(String.format("%.2f", inOut));

        } else {
            errorL.setVisible(true);
        }
    }
    public static double y2m(double d) {
        return(d / 1.094);
    }
    public static double y2in(double d) {
        return(d * 36);
    }
    public static double y2cm(double d) {
        return(d * 91.44);
    }

    public void mConvert() {
        if (canConvert()) {

            errorL.setVisible(false);

            Double mInput = Double.parseDouble(mT.getText());

            Double yOut = m2y(mInput);
            Double cmOut = m2cm(mInput);
            Double inOut = m2in(mInput);

            cmT.setText(String.format("%.2f", cmOut));
            inT.setText(String.format("%.2f", inOut));
            yT.setText(String.format("%.2f", yOut));

        } else {
            errorL.setVisible(true);
        }
    }
    public static double m2y(double d) {
        return(d * 1.094);
    }
    public static double m2in(double d) {
        return(d * 39.37);
    }
    public static double m2cm(double d) {
        return(d * 100);
    }

    public void clearFields() {
        cmT.clear();
        inT.clear();
        mT.clear();
        yT.clear();
        errorL.setVisible(false);
    }
    public void exitApplication() {
        System.exit(0);
    }

}