package com.example.uni_assignment4;

/*
 *
 * @author shinemettd (David O.)
 *
 */

public class Calculator {
    private double operand1; //first operand
    private double operand2; //second operand
    private char operator; //expression operator
    private boolean operatorSet; //flag for operator existence
    private double result; //final result of expression

    private String mainExpression; //instance that contains all expression
    private int operatorIndex; //operator index for future speculations with main expression
    private boolean dotSet; // flag for dot existence to avoid unnecessary bugs

    CalculatorController controller; //controller object that will send all user input symbols

    Calculator(CalculatorController controller) {
        //initialize controller object
        this.controller = controller;
        //setting default values
        operand1 = 0.0;
        operand2 = 0.0;
        result = 0.0;
        mainExpression = "";
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
        //sets operator and activates operatorSet flag
        operatorSet = true;
        //also it sets the operator index
        operatorIndex = mainExpression.length();
    }

    public double getResult() {
        //idk why the assignment says to write this method
        return result;
    }

    public String getMainExpression() {
        //main method that returns expression to the controller
        return mainExpression;
    }

    public void getSymbol(char controllerMessage) {
        //major case when user click on equal sign -> calculates all expression and sets mainExpression value
        if (controllerMessage == '=') {
            if (operatorSet) {
                //automatically adds zero to the operand if user clicked dot and didn't fill in the value
                addZero();
                //cutting second operand after operator by using operatorIndex instance
                setOperand2(Double.parseDouble(mainExpression.substring(operatorIndex + 1)));
                //reset main expression
                mainExpression = "";
                //calling calculate method
                calculate();
            }
        } else if (controllerMessage == 'С' && !isOperator(controllerMessage) || mainExpression.equals("NaN")) {
            //clear all values after user choice "Clear" or after division by zero result
            clearAll();
        } else if (controllerMessage == 'E' && !isOperator(controllerMessage)) {
            //button CE clears only part after operator
            mainExpression = mainExpression.substring(0, operatorIndex + 1);
            //resets second operand value
            setOperand2(0.0);
            //resets dot flag
            dotSet = false;
        } else if (controllerMessage == 'N') {
            //adding to the first value negativity and vice versa
            if (mainExpression.charAt(0) == '-' && !operatorSet) {
                mainExpression = mainExpression.substring(1);
            } else if (!operatorSet) {
                mainExpression = '-' + mainExpression;
            }
        } else if (controllerMessage == '.') {
            //activating dot flag when user clicks at dot
            if (!dotSet) {
                dotSet = true;
                mainExpression += controllerMessage;
            }
        } else if (!isOperator(controllerMessage)) {
            //it's adding digits to the main expression
            mainExpression += controllerMessage;//operator case
        } else {
            //operator case

            //if operator wasn't set before
            if (!operatorSet && isOperator(controllerMessage)) {
                //automatically adds a zero after the period
                addZero();
                //sets operator
                setOperator(controllerMessage);
                //sets first operand
                setOperand1(Double.parseDouble(mainExpression));
                //add character to the main expression
                mainExpression += controllerMessage;
            //if operator was set before
            } else {
                //automatically adds a zero after the period
                addZero();
                //resets operator
                setOperator(controllerMessage);
                //deletes last operator and adding new one
                mainExpression = mainExpression.substring(0, mainExpression.length() - 1);
                //add character to the main expression
                mainExpression += controllerMessage;
            }
        }
    }

    private void calculate() {
        //switch case with math expressions
        switch (operator) {
            case '+' -> result = operand1 + operand2;
            case '-' -> result = operand1 - operand2;
            case '*' -> result = operand1 * operand2;
            case '/' -> {
                //only allows to divide by non-zero operand
                if (operand2 != 0) {
                    result = operand1 / operand2;
                }
            }
        }
        //resets operator flag
        operatorSet = false;
        //final mains expression refresh by cutting zero remainder from double value
        //and setting "NaN" value if division by zero occurred
        mainExpression = (operand2 == 0 && operator == '/') ? "NaN" : cutExcessZero(result);
    }
    //resets all values to the default
    private void clearAll() {
        operand1 = 0.0;
        operand2 = 0.0;
        result = 0.0;
        operator = ' ';
        operatorSet = false;
        operatorIndex = -1;
        mainExpression = "";
        dotSet = false;
    }
    //method that itself adds a zero if user didn't enter any other value after using the dot
    private void addZero() {
        //checks if there was a dot
        if (mainExpression.charAt(mainExpression.length() - 1) == '.') {
            mainExpression += '0';
        }
        //resets dot flag
        dotSet = false;
    }
    //method that cuts zero remainder from double value
    private String cutExcessZero(double answer) {
        return (int) answer == answer ? String.valueOf((int) answer) : String.valueOf(answer);
    }
    //method that return true if symbol is operator and false if not
    private boolean isOperator(char symbol) {
        return symbol == '+' || symbol == '-' || symbol == '*' || symbol == '/';
    }
}


