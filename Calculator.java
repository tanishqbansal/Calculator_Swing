/**
 * File Name : Calculator.java Author: Tanishq Bansal, #040883753 Course: CST8221 - JAP, Lab
 * Section: 304 Assignment: Assignment 1, Part 1 Date: 18th October, 2018 Professor: Daniel Cormier
 * Purpose: this file contains the Calulator class for the main method and launching the gui
 */
package calculator;


import java.awt.EventQueue;
import javax.swing.JFrame;


/**
 * The Class Calculator. This class is responsible for launching the application. The class contains
 * a main method only. Inside the main method you must first create a CaclculatorSplashScreen object
 * and call its showSplashWindow() method and display the splash screen for 5 s. Then the main
 * method must call EventQueue.invokeLater() method with a Runnable instance the run() method of
 * which creates a JFrame object; sets the frame title and minimum size; sets the default close
 * operation for the frame; sets the content pane of the frame to a CalculatorViewController object;
 * sets the application location at launch; and finally, makes the frame visible.
 * 
 * @author Tanishq Bansal
 * @version 1.0
 * @see package calculator
 * @since java version "1.8.0_181"
 */
public class Calculator {

  /** {@value} Sets the height of the GUI with 380. */
  private final static int HEIGHT = 380;

  /** {@value} Sets the width of the GUI with 520. */
  private final static int WIDTH = 520;

  /** {@value} Sets the title of the GUI with CALCULATOR. */
  private final static String TITLE = "Calculator";

  /** {@value} Sets the Splash time of the GUI with 5000 ms. */
  private final static int DURATION = 20;

  /** {@value} Sets whether the GUI is visible or not. */
  private final static boolean ISTRUE = true;

  /**
   * The main method here runs the whole GUI Frame, sets the title of the Frame and size of the
   * frame.
   *
   * @param args the arguments
   * @return returns nothing
   */
  public static void main(String args[]) {

    /*
     * Create an Object of CalculatorSplashScreen so that we can create a Splash Screen when we
     * start the GUI
     */
    // CalculatorSplashScreen splash = new CalculatorSplashScreen(DURATION);
    // splash.showSplashWindow();
    /*
     * Making an Object of the class where we have main code of the GUI
     */
    CalculatorViewController view = new CalculatorViewController();
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        /* Create an Object of JFrame */
        JFrame frame = new JFrame();
        /*
         * add an object to the frame Object
         */
        frame.add(view);
        /* Set the title of the GUI Frame */
        frame.setTitle(TITLE);
        /* Set the default closing operations */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /* Set whether the Frame location is set using the Platform */
        frame.setLocationByPlatform(ISTRUE);
        /* Set the height and width of the GUI */
        frame.setSize(HEIGHT, WIDTH);
        /* Make the GUI visible */
        frame.setVisible(ISTRUE);

      }
    });
  }
}
