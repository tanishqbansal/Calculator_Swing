/**
 * File Name : CalculatorSplashScreen.java Author: Tanishq Bansal, #040883753 Course: CST8221 - JAP,
 * Lab Section: 304 Assignment: Assignment 1, Part 1 Date: 18th October, 2018 Professor: Daniel
 * Cormier Purpose: Contains the Splash Screen class for generating splash screen
 */
package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;



/**
 * The Class CalculatorSplashScreen. This class is responsible for displaying a splash screen before
 * the launch of the application. The splash screen displays an image, and contains my name and
 * student number at the bottom of the screen.
 * 
 * @author Tanishq Bansal
 * @version 1.0
 * @see package calculator
 * @since java version "1.8.0_181"
 */
public class CalculatorSplashScreen extends JWindow {

  /** Swing components are serializable and require serialVersionUID. */
  private static final long serialVersionUID = 6248477390124803341L;

  /** Splash screen duration. */
  private final int duration;


  /** The Constant PB_MINIMUM. */
  public static final int PB_MINIMUM = 0;
  
  /** The Constant PB_MAXIMUM. */
  public static final int PB_MAXIMUM = 100;
  
  /** The p bar. */
  JProgressBar pBar;

  /**
   * Default constructor. Sets the show time of the splash screen.
   *
   * @param duration the duration
   */
  public CalculatorSplashScreen(int duration) {
    this.duration = duration;
  }

  /**
   * Responsible for Showing a splash screen in the center of the desktop with a image, name and
   * student number for the amount of time given in the constructor.
   */
  public void showSplashWindow() {
    // create content pane
    JPanel content = new JPanel(new BorderLayout());
    // or use the window content pane
    // JPanel content = (JPanel)getContentPane();
    content.setBackground(Color.GRAY);

    // Set the window's bounds, position the window in the center of the screen
    int width = 400 + 10;
    int height = 400 + 10;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screen.width - width) / 2;
    int y = (screen.height - height) / 2;
    // set the location and the size of the window
    setBounds(x, y, width, height);
    JLabel label = new JLabel(new ImageIcon(getClass().getResource("image.gif")));
    pBar = new JProgressBar();
    pBar.setMinimum(PB_MINIMUM);
    pBar.setMaximum(PB_MAXIMUM);
    pBar.setPreferredSize(new Dimension(80, 20));
    JLabel demo = new JLabel("Tanishq Bansal 040883753", JLabel.WHEN_IN_FOCUSED_WINDOW);


    demo.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
    demo.setHorizontalAlignment(SwingConstants.CENTER);
    content.add(label, BorderLayout.CENTER);
    content.add(demo, BorderLayout.SOUTH);
    content.add(pBar, BorderLayout.NORTH);
    Color customColor = new Color(44, 197, 211);
    content.setBorder(BorderFactory.createLineBorder(customColor, 10));

    setContentPane(content);

    setVisible(true);
    setProgressBar();

    try {

      Thread.sleep(duration);
    } catch (InterruptedException e) {
      /* log an error here? *//* e.printStackTrace(); */}
    dispose();
  }

  /**
   * Reset progress bar.
   *
   * @param valueToUpdate the value to update
   */
  public void resetProgressBar(int valueToUpdate) {
    pBar.setStringPainted(true);
    if (pBar.getValue() <= 20) {
      pBar.setString("Loading Calculator. Please wait…");
      pBar.setValue(valueToUpdate);
      pBar.setForeground(Color.BLUE);
      pBar.setBackground(Color.WHITE);
      return;
    }
    pBar.setString(null);
    pBar.setValue(valueToUpdate);

  }

  /**
   * Sets the progress bar.
   */
  public void setProgressBar() {

    for (int i = 0; i < 100; i++) {
      final int percent = i;

      try {
        SwingUtilities.invokeLater(new Runnable() {
          public void run() {
            resetProgressBar(percent);
          }
        });
        Thread.sleep(50);
      } catch (InterruptedException e) {
        ;
      }
    }
  }



}
