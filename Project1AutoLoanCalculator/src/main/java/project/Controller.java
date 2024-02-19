package project;

/**
 * Name: Sudarshan Krishnan
 * Username: kriss03
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Controller {
	
	//------------------------- FXML Controllers -----------------------------------\\

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label baseLB;

    @FXML
    private TextField basePriceTB;

    @FXML
    private Button calculateButton;

    @FXML
    private Label downPaymentLB;

    @FXML
    private TextField downPaymentTB;

    @FXML
    private Label extraLB;

    @FXML
    private Label financingLB;

    @FXML
    private Label loanLB;

    @FXML
    private Label loanOutpuLB;

    @FXML
    private ToggleGroup loanTermToggleGroup;

    @FXML
    private Label monthlyLB;

    @FXML
    private Label monthlyOutputLB;

    @FXML
    private Label paymentLable;

    @FXML
    private CheckBox rearCameraCB;

    @FXML
    private Button resetButton;

    @FXML
    private Label salesTaxLB;

    @FXML
    private TextField salesTaxTB;

    @FXML
    private CheckBox sunRoofCB;

    @FXML
    private RadioButton term1Radio;

    @FXML
    private RadioButton term2Radio;

    @FXML
    private RadioButton term3Radio;

    @FXML
    private RadioButton term4Radio;

    @FXML
    private Label termLabel;

    @FXML
    private Label totalLB;

    @FXML
    private Label totalOutputLB;

    @FXML
    private CheckBox touchScreenLCB;

	@FXML
	void initialize() {

		salesTaxTB.setText("7.0");
		term1Radio.setSelected(true);
		rearCameraCB.setSelected(true);

	}
	
	public void calculate() {
		
		//------------------------- Total Loan AmountCalculation -----------------------------------\\
		
		double optionCosts = 0.0;
		
				if (sunRoofCB.isSelected()) {
					optionCosts += 1510;
				}
				
				if (touchScreenLCB.isSelected()) {
					optionCosts += 470;
				}
				
				if (rearCameraCB.isSelected()) {
					optionCosts += 340;
				}
				
		double subtotal = 0.0;
		subtotal = Double.parseDouble(basePriceTB.getText()) + optionCosts;
		
		
		double tax;
		double taxRate = Double.parseDouble(salesTaxTB.getText()) / 100;
		tax = subtotal * taxRate;
		
		double totalLoan = 0.0;
		totalLoan = subtotal + tax -  Double.parseDouble(downPaymentTB.getText());
	
		
		//------------------------- Monthly Payment Calculation -----------------------------------\\
		
		double annualInterestRate = 0.0;
		int months = 0;
		
				if (term1Radio.isSelected()) {
					annualInterestRate = 7.0/100;
					months = 24;
				}
				
				if (term2Radio.isSelected()) {
					annualInterestRate = 6.0/100;
					months = 36;
				}
				
				if (term3Radio.isSelected()) {
					annualInterestRate = 5.5/100;
					months = 48;
				}
				
				if (term4Radio.isSelected()) {
					annualInterestRate = 5.0/100;
					months = 60;
				}
				
		double montlyInterest = 0.0;
		montlyInterest = (annualInterestRate/12);
		
		double monthlyScale = 0.0;
		monthlyScale = Math.pow((1+montlyInterest),months);
		
		double montlyPayment = 0.0;
		montlyPayment = totalLoan * ((montlyInterest * monthlyScale) / (monthlyScale-1));
		
		
		//------------------------------- Total Payment Calculation -----------------------------------\\
		
		
		double totalPayment = 0.0;
		totalPayment = montlyPayment * months;
	
	
		//--------------------------- Printing Final Payment Calculations -----------------------------\\
		
		loanOutpuLB.setText(String.format("%.2f", totalLoan));
		monthlyOutputLB.setText(String.format("%.2f", montlyPayment));
		totalOutputLB.setText(String.format("%.2f", totalPayment));
		
	}
		
	public void reset() {
		
		//--------------------------- Reset Payment Information -----------------------------\\
		
		loanOutpuLB.setText("0.0");
		monthlyOutputLB.setText("0.0");
		totalOutputLB.setText("0.0");
		
		
		//--------------------------- Reset Financing Information -----------------------------\\
		
		
		basePriceTB.setText("0.0");
		downPaymentTB.setText("0.0");
		salesTaxTB.setText("7.0");
		
		
		//--------------------------- Reset Loan Term -----------------------------\\
		
		
		term1Radio.setSelected(true);
		term2Radio.setSelected(false);
		term3Radio.setSelected(false);
		term4Radio.setSelected(false);
		
		
		//--------------------------- Reset Extra Options -----------------------------\\
		
		
		rearCameraCB.setSelected(true);
		touchScreenLCB.setSelected(false);
		sunRoofCB.setSelected(false);
		
	}
}
