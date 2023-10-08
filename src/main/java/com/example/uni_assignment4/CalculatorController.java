package com.example.uni_assignment4;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CalculatorController {
    @FXML
    private TextField textField;
    private Calculator calculator;

    public CalculatorController() {
        calculator = new Calculator(this);
    }
    @FXML
    public void handle(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        String symbolValue = btn.getText();
        if (symbolValue.equals("CE")) {
            System.out.println("caught CE");
            calculator.getSymbol('E');
        } else if (symbolValue.equals("±")) {
            calculator.getSymbol('N');
        } else {
            calculator.getSymbol(symbolValue.charAt(0));
            System.out.println("caught --- " + symbolValue.charAt(0));
        }
        textField.setText(calculator.getSymbolCollector());
    }
}