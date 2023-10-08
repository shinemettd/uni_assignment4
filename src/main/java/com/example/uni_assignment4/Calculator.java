package com.example.uni_assignment4;

import javafx.scene.control.TextField;

public class Calculator {
    private double operand1;
    private double operand2;
    private char operator;
    private boolean operatorSet;
    private double result;

    private String symbolCollector;
    private int operatorIndex;
    private boolean dotSet;

    CalculatorController controller;

    Calculator(CalculatorController controller) {
        this.controller = controller;
        operand1 = 0.0;
        operand2 = 0.0;
        result = 0.0;
        symbolCollector = "";
        operatorIndex = -1;
        operatorSet = false;
        operator = ' ';
        dotSet = false;
    }

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

    public void setOperator(char operator) {
        this.operator = operator;
        operatorSet = true;
        operatorIndex = symbolCollector.length();
    }

    public double getResult() {
        return result;
    }

    public String getSymbolCollector() {
        return symbolCollector;
    }

    public void getSymbol(char controllerMessage) {
        System.out.println("caught " + controllerMessage + " in model");
        if (controllerMessage == '=') {
            if (operatorSet) {
                addZero();
                setOperand2(Double.parseDouble(symbolCollector.substring(operatorIndex + 1, symbolCollector.length()))); //
                System.out.println("Set 2nd op " + operand2);
                symbolCollector = "";
                calculateAndUpdate();
                operatorSet = false;
            }
        } else if (controllerMessage == 'С' && !isOperator(controllerMessage) || symbolCollector.equals("NaN")) {
            clearAll();
            System.out.println("Cleared all");
        } else if (controllerMessage == 'E' && !isOperator(controllerMessage)) {
            symbolCollector = symbolCollector.substring(0, operatorIndex + 1);
            setOperand2(0.0);
            dotSet = false;
            System.out.println("set 0.0 to 2nd op");
        } else if (controllerMessage == 'N') {
            if (symbolCollector.charAt(0) == '-' && !operatorSet) {
                symbolCollector = symbolCollector.substring(1, symbolCollector.length());
            } else if (!operatorSet) {
                symbolCollector = '-' + symbolCollector;
            }
        } else if (controllerMessage == '.') {
            if (!dotSet) {
                dotSet = true;
                symbolCollector += controllerMessage;
            }
        } else if (!isOperator(controllerMessage)) {
            symbolCollector += controllerMessage;
        } else {
            if (!operatorSet && isOperator(controllerMessage)) {
                addZero();
                setOperator(controllerMessage);
                setOperand1(Double.parseDouble(symbolCollector));
                symbolCollector += controllerMessage;
                System.out.println("zerd symbolColl and set op with op1" + operator + " with " + operand1);
            } else {
                addZero();
                setOperator(controllerMessage);
                symbolCollector = symbolCollector.substring(0, symbolCollector.length() - 1);
                symbolCollector += controllerMessage;
                System.out.println("refreshd operator w " + controllerMessage);
            }
        }
    }

    private void calculateAndUpdate() {
        System.out.println("OP1 = " + operand1 + "\nOP2 = " + operand2 + "\nOPS = " + operator);
        switch(operator) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                if (operand2 != 0) {
                    result = operand1 / operand2;
                }
                break;
        }
        String answer = (operand2 == 0 && operator == '/') ? "NaN" : cutExcessZero(result);
        System.out.println("got ans=" + answer);
        symbolCollector = answer;
    }

    private void clearAll() {
        operand1 = 0.0;
        operand2 = 0.0;
        result = 0.0;
        operator = ' ';
        operatorSet = false;
        operatorIndex = -1;
        symbolCollector = "";
        dotSet = false;
    }

    private void addZero() {
        if (symbolCollector.charAt(symbolCollector.length() - 1) == '.') {
            symbolCollector += '0';
        }
        dotSet = false;
    }

    private String cutExcessZero(double answer) {
        String stringAnswer = (int) answer == answer ? String.valueOf((int) answer) : String.valueOf(answer);
        return stringAnswer;
    }

    private boolean isOperator(char symbol) {
        return symbol == '+' || symbol == '-' || symbol == '*' || symbol == '/';
    }
}


