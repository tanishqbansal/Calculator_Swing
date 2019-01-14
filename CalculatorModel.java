/*
 * 
 */
package calculator;

/**
 * File Name : Calculator.java Author: Tanishq Bansal, #040883753 Course: CST8221 - JAP, Lab
 * Section: 304 Assignment: Assignment 1, Part 1 Date: 18th October, 2018 Professor: Daniel Cormier
 * Purpose: this file contains the Calulator class for the main method and launching the gui
 */
/*
 * @author Tanishq Bansal
 * 
 * @version 1.0
 * 
 * @since 1.8.0_181
 */
public class CalculatorModel {
  
  /** The operand 1. */
  private String operand1 = "0"; // to set the first operand in an object
  
  /** The operand 2. */
  private String operand2 = "0"; // to set a second operand in an object
  
  /** The floating point precision. */
  private String floatingPointPrecision = ".00"; // to set the floating precisions it can be .00 or
                                                 
                                                 /** The error state. */
                                                 // .0
  private boolean errorState; // to return an error state whether there is any error or not
  
  /** The arithmetic operation. */
  private String arithmeticOperation = "+"; // arithmetic operations +,-,*,/
  
  /** The operational mode. */
  private String operationalMode = "F"; // whether its an Integer or float mode
  
  /** The result. */
  private String result; // final result to return

  /**
   * Gets the result.
   *
   * @return Returns the calculated value from the object (works like a toString)
   */
  public String getResult() {
    calculate();// called so that it can set the global variable result to some string
    /**
     * if the operational mode is F then it means there are three types of precisions which can be
     * set Sci, .0 or, .00
     */
    if (operationalMode.equals("F")) {
      // System.out.println(floatingPointPrecision);
      if (floatingPointPrecision.equals("Sci")) {
        result = String.format("%e", Double.parseDouble(result));
      } else if (floatingPointPrecision.equals(".0")) {
        result = String.format("%.1f", Double.parseDouble(result));

      } else if (floatingPointPrecision.equals(".00")) {
        result = String.format("%.2f", Double.parseDouble(result));
      }
    } else if (operationalMode.equals("I")) {

      if (result.equalsIgnoreCase("Cannot divide by zero")
          || result.equalsIgnoreCase("Results not defined")) {
        result = String.format("%s", result);
        return this.result;
      }
      if (result.contains("."))
        result = result.substring(0, result.indexOf("."));
      result = String.format("%d", Integer.parseInt(result));
    }
    return this.result;
  }

  /**
   * This method sets the airthmetic operation to an object.
   *
   * @param arithmetic - to get the arithmetic +, -, *, /
   * @return void
   */

  public void setArithmeticOperation(String arithmetic) {
    this.arithmeticOperation = arithmetic;

  }

  /**
   * This method sets the operational mode to an object.
   *
   * @param operation - to set an operation like F or int
   * @return void
   */
  public void setOperationalMode(String operation) {
    this.operationalMode = operation;

  }

  /**
   * The following method sets the floating point precision to an object.
   *
   * @param floating - which sets the precision like .0 or .00
   * @return void
   */

  public void setFloatingPointPrecision(String floating) {
    this.floatingPointPrecision = floating;

  }

  /**
   * Sets the first operand to an object.
   *
   * @param operand - to set an object between 0 - 9
   * @return void
   */
  public void setOperand1(String operand) {
    this.operand1 = operand;

  }

  /**
   * Sets the second operand to an object.
   *
   * @param operand - to set an object between 0 - 9
   * @return void
   */

  public void setOperand2(String operand) {
    this.operand2 = operand;

  }

  /**
   * The following function calculates the result using operands, operator and operational mode.
   *
   * @return none
   */

  public void calculate() {
    int finalValue = 0;
    try {
      /*
       * if the arithmetic operation is I i.e., Integer, then we get inside and switch the
       * arithmetic operation and calculate and set the result
       */
      switch (operationalMode) {
        case "I": {
          switch (arithmeticOperation) {

            case "+":
              finalValue = Integer.parseInt(operand1) + Integer.parseInt(operand2);
              break;
            case "-":
              finalValue = Integer.parseInt(operand1) - Integer.parseInt(operand2);
              break;
            case "*":
              finalValue = Integer.parseInt(operand1) * Integer.parseInt(operand2);
              break;
            case "/":
              finalValue = Integer.parseInt(operand1) / Integer.parseInt(operand2);
              break;

          }

          result = Integer.toString(finalValue);

        }
        case "F": {
          /*
           * if it is not Integer it means its a float, so here we are calculating the operands
           * using float
           */
          double doubleValue = 0.0;
          switch (arithmeticOperation) {

            case "+":
              doubleValue = Double.parseDouble(operand1) + Double.parseDouble(operand2);
              break;
            case "-":
              doubleValue = Double.parseDouble(operand1) - Double.parseDouble(operand2);
              break;
            case "*":
              doubleValue = Double.parseDouble(operand1) * Double.parseDouble(operand2);
              break;
            case "/":
              doubleValue = Double.parseDouble(operand1) / Double.parseDouble(operand2);
              break;

          }
          result = Double.toString(doubleValue);

        }
      }
    } catch (ArithmeticException e) {
      result = "Cannot divide by zero";
    } catch (Exception e1) {
      result = "Results not defined";
    }
  }

}
