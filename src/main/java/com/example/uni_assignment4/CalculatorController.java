package com.example.uni_assignment4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/*
 *
 * @author shinemettd (David O.)
 *
 */

public class CalculatorController {
    @FXML
    private TextField textField; //textField that keeps all expressions
    private final Calculator calculator; //calculator object to handle it user actions

    public CalculatorController() {
        //creating calculator object with current controller class argument
        calculator = new Calculator(this);
    }
    @FXML
    public void handle(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        String symbolValue = btn.getText();
        if (symbolValue.equals("CE")) {
            //some speculations for correct char input functionality
            calculator.getSymbol('E');
        } else if (symbolValue.equals("±")) {
            //some speculations for correct char input functionality
            calculator.getSymbol('N');
        } else {
            //sending char value to the calculator object by using its method
            calculator.getSymbol(symbolValue.charAt(0));
        }
        //updating textField with calculator instance "mainExpression"
        textField.setText(calculator.getMainExpression());
    }
}