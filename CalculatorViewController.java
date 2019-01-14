/**
 * File Name : CalculatorViewController.java Author: Tanishq Bansal, #040883753 Course: CST8221 -
 * JAP, Lab Section: 304 Assignment: Assignment 1, Part 1 Date: 18th October, 2018 Professor: Daniel
 * Cormier Purpose: this file contains the CalculatorViewControlller class for all the details of
 * the gui
 */
package calculator;



import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;


/**
 * The Class CalculatorViewController.The class CalculatorViewController is responsible for building
 * and operating the calculator GUI. It extends JPanel. All the buttons, and their details are in
 * this class
 * 
 * @author Tanishq Bansal
 * @version 1.0
 * @see package calculator
 * @since java version "1.8.0_181"
 */
public class CalculatorViewController extends JPanel {


  /** The display 1. */
  private JTextField display1;

  /** The display 2. */
  private JTextField display2;

  /** The error. */
  private JLabel error;

  /** The back space. */
  private JButton backSpace;

  /** The dot button. */
  private JButton dotButton;

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The single decimal value. */
  private final String SINGLE_DECIMAL_VALUE = ".0";

  /** The double decimal value. */
  private final String DOUBLE_DECIMAL_VALUE = ".00";

  /** The matte border size. */
  private final int MATTE_BORDER_SIZE = 5;

  /** The backspace button. */
  private final String BACKSPACE_BUTTON = "\u21DA";

  /** The dimension width constant. */
  private final int DIMENSION_WIDTH = 46;

  /** The dimension height constant. */
  private final int DIMENSION_HEIGHT = 55;

  /** The column display size. */
  private final int COLUMN_DISPLAY_SIZE = 16;

  /** count is used for counting the backspace. */
  private int count = 0;

  /** The button name. */
  private String[] buttonName =
      {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "\u00B1", "+"};
  
  /** The button keys. */
  // Button Keys on the keyboard
  int[] buttonKeys = {KeyEvent.VK_7, KeyEvent.VK_8, KeyEvent.VK_9, KeyEvent.VK_SLASH, KeyEvent.VK_4,
      KeyEvent.VK_5, KeyEvent.VK_6, KeyEvent.VK_ASTERISK, KeyEvent.VK_1, KeyEvent.VK_2,
      KeyEvent.VK_3, KeyEvent.VK_MINUS, KeyEvent.VK_0, KeyEvent.VK_DECIMAL, -1, KeyEvent.VK_PLUS};
  
  /** The numeric keys. */
  // Numeric Keys on the Keyboard
  int[] numericKeys = {KeyEvent.VK_NUMPAD7, KeyEvent.VK_NUMPAD8, KeyEvent.VK_NUMPAD9,
      KeyEvent.VK_DIVIDE, KeyEvent.VK_NUMPAD4, KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD6,
      KeyEvent.VK_MULTIPLY, KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD2, KeyEvent.VK_NUMPAD3,
      KeyEvent.VK_SUBTRACT, KeyEvent.VK_NUMPAD0, KeyEvent.VK_DECIMAL, -1, KeyEvent.VK_ADD};

  /** The model. */
  private CalculatorModel model = new CalculatorModel();

  /** The double precision. */
  JRadioButton doublePrecision = new JRadioButton(DOUBLE_DECIMAL_VALUE);

  /** The integer checkbox. */
  private final String INTEGER_CHECKBOX = "Int";
  
  /** The character i. */
  private final String CHARACTER_I = "I";

  /**
   * Instantiates a new calculator view controller.
   */
  public CalculatorViewController() {
    // setting up the top part of the calulator
    super.setBorder(new MatteBorder(MATTE_BORDER_SIZE, MATTE_BORDER_SIZE, MATTE_BORDER_SIZE,
        MATTE_BORDER_SIZE, Color.BLACK));
    super.setLayout(new BorderLayout());
    JPanel north = new JPanel(new BorderLayout());
    JPanel innerNorth = new JPanel(new BorderLayout());
    JPanel center = new JPanel(new BorderLayout());
    JPanel innercenter = new JPanel(new GridLayout(4, 4, 2, 2));

    error = new JLabel("F", SwingConstants.CENTER);
    error.setFont(new Font(getName(), Font.BOLD, 20));
    error.setPreferredSize(new Dimension(DIMENSION_WIDTH, DIMENSION_HEIGHT));
    error.setBackground(Color.YELLOW);
    error.setBorder(new MatteBorder(0, 1, 0, 1, Color.BLACK));

    backSpace = new JButton("\u21DA");
    backSpace.setPreferredSize(new Dimension(DIMENSION_WIDTH, DIMENSION_HEIGHT));
    backSpace.setFont(new Font(getName(), Font.BOLD, 30));
    backSpace.setBackground(Color.YELLOW);
    backSpace.setBorder(new MatteBorder(0, 1, 0, 1, Color.BLACK));
    backSpace.setToolTipText("Alt-B (Backspace)");
    backSpace.setMnemonic('B');
    backSpace.setOpaque(false);
    backSpace.setContentAreaFilled(false);
    backSpace.setBorderPainted(true);
    backSpace.addActionListener(new Controller());
    backSpace.setActionCommand(BACKSPACE_BUTTON);

    display1 = new JTextField();
    display1.setPreferredSize(new Dimension(0, 30));
    display1.setColumns(COLUMN_DISPLAY_SIZE);
    display1.setEditable(false);
    display1.setHorizontalAlignment(JTextField.RIGHT);
    display1.setBackground(Color.WHITE);
    display1.setBorder(new EmptyBorder(0, 0, 0, 0));

    display2 = new JTextField();
    display2.setPreferredSize(new Dimension(0, 30));
    display2.setColumns(COLUMN_DISPLAY_SIZE);
    display2.setEditable(false);
    display2.setBackground(Color.WHITE);
    display2.setText("0.0");
    display2.setHorizontalAlignment(JTextField.RIGHT);
    display2.setBorder(new EmptyBorder(0, 0, 0, 0));


    // Center part
    for (int i = 0; i < buttonName.length; i++) {
      // used for keyboard action setting the keyboard keys with the controller
      AbstractAction keyboardAction = new AbstractAction() {

        private static final long serialVersionUID = -4707393028982930674L;

        @Override
        public void actionPerformed(ActionEvent e) {

          new Controller().actionPerformed(e);

        }
      };
      JButton button = new JButton();
      if (buttonName[i] == "/" || buttonName[i] == "*" || buttonName[i] == "-"
          || buttonName[i] == "+") {
        button =
            createButton(buttonName[i], buttonName[i], Color.BLACK, Color.CYAN, new Controller());

      } else if (buttonName[i] == "\u00B1") {
        button =
            createButton(buttonName[i], buttonName[i], Color.BLACK, Color.PINK, new Controller());

      } else {

        button =
            createButton(buttonName[i], buttonName[i], Color.BLACK, Color.BLUE, new Controller());
      }
      button.setFont(new Font(getName(), Font.BOLD, 20));
      innercenter.add(button);

      // function to make keyboard number work
      if (buttonKeys[i] != -1) {

        button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke(buttonKeys[i], 0), buttonName[i]);
        button.getActionMap().put(buttonName[i], keyboardAction);

      }

      // numeric key to work
      if (numericKeys[i] != -1) {

        button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke(numericKeys[i], 0), buttonName[i]);

        button.getActionMap().put(buttonName[i], keyboardAction);

      }

    }

    // BUTTONS
    JPanel checkBoxAndButton = new JPanel(new BorderLayout());
    checkBoxAndButton.setPreferredSize(new Dimension(520, 30));
    checkBoxAndButton.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, Color.BLACK));
    checkBoxAndButton.setBackground(Color.BLACK);
    Box box = Box.createHorizontalBox();
    JCheckBox checkBox = new JCheckBox(INTEGER_CHECKBOX);
    checkBox.setBackground(Color.GREEN);
    checkBox.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
    checkBox.setPreferredSize(new Dimension(45, 0));
    checkBox.addActionListener(new Controller());

    JRadioButton singleDecimal = new JRadioButton(SINGLE_DECIMAL_VALUE);
    singleDecimal.addActionListener(new Controller());
    JRadioButton doubleDecimal = new JRadioButton(DOUBLE_DECIMAL_VALUE);
    doubleDecimal.addActionListener(new Controller());
    doubleDecimal.setSelected(true);
    JRadioButton sci = new JRadioButton("Sci");
    sci.addActionListener(new Controller());
    // changing color for button background
    singleDecimal.setBackground(Color.YELLOW);
    doubleDecimal.setBackground(Color.YELLOW);
    sci.setBackground(Color.YELLOW);
    // box.add(checkBox,new BoxLayout(box, BoxLayout.LINE_AXIS));
    box.add(singleDecimal, new BoxLayout(box, BoxLayout.LINE_AXIS));
    box.add(doubleDecimal, new BoxLayout(box, BoxLayout.LINE_AXIS));
    box.add(sci, new BoxLayout(box, BoxLayout.LINE_AXIS));
    ButtonGroup group = new ButtonGroup();
    group.add(singleDecimal);
    group.add(doubleDecimal);
    group.add(sci);
    group.add(checkBox);
    box.setBackground(Color.GREEN);
    JButton equalsl = new JButton();
    JButton equalsr = new JButton();
    JButton cButtonup = new JButton();
    JButton cButtondown = new JButton();

    equalsl = createButton("=", "=", Color.BLACK, Color.YELLOW, new Controller());
    equalsl.setFont(new Font(getName(), Font.BOLD, 20));
    equalsl.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 5, Color.BLACK));
    equalsl.setPreferredSize(new Dimension(50, 0));
    equalsr = createButton("=", "=", Color.BLACK, Color.YELLOW, new Controller());
    equalsr.setFont(new Font(getName(), Font.BOLD, 20));
    equalsr.setPreferredSize(new Dimension(50, 0));
    equalsr.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 0, Color.BLACK));
    cButtonup = createButton("C", "C", Color.BLACK, Color.RED, new Controller());
    cButtonup.setFont(new Font(getName(), Font.BOLD, 20));
    cButtonup.setPreferredSize(new Dimension(0, 50));
    cButtonup.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, Color.BLACK));
    cButtondown = createButton("C", "C", Color.BLACK, Color.RED, new Controller());
    cButtondown.setFont(new Font(getName(), Font.BOLD, 20));
    cButtondown.setPreferredSize(new Dimension(0, 50));
    cButtondown.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));

    checkBoxAndButton.add(checkBox, BorderLayout.WEST);
    checkBoxAndButton.add(box, BorderLayout.EAST);

    // adding panels and button to other panels
    north.add(error, BorderLayout.WEST);
    innerNorth.add(display1, BorderLayout.NORTH);
    innerNorth.add(display2, BorderLayout.SOUTH);
    north.add(innerNorth, BorderLayout.CENTER);
    north.add(backSpace, BorderLayout.EAST);
    north.add(checkBoxAndButton, BorderLayout.SOUTH);
    north.setBackground(Color.YELLOW);

    center.add(innercenter, BorderLayout.CENTER);
    center.add(cButtonup, BorderLayout.NORTH);
    center.add(cButtondown, BorderLayout.SOUTH);
    innercenter.setBorder(new MatteBorder(2, 2, 2, 1, Color.WHITE));

    super.add(north, BorderLayout.NORTH);
    super.add(center, BorderLayout.CENTER);
    super.add(equalsl, BorderLayout.WEST);
    super.add(equalsr, BorderLayout.EAST);


  }

  /**
   * Creates the button.
   *
   * @param text the text
   * @param ac the ac
   * @param fg the fg
   * @param bg the bg
   * @param handler the handler
   * @return the j button
   */
  private JButton createButton(String text, String ac, Color fg, Color bg, ActionListener handler) {

    JButton button = new JButton(text);

    if (ac != null) {
      button.setActionCommand(ac);

    }

    button.setBackground(bg);
    button.setForeground(fg);
    button.setFont(new Font(ac, Font.PLAIN, 20));
    button.addActionListener(handler);
    if (text.equals(".")) {
      dotButton = button;
    }
    return button;

  }



  /**
   * The Class Controller.
   * 
   * @author Tanishq Bansal
   * @version 1.0
   * @see package calculator
   * @since java version "1.8.0_181"
   */
  private class Controller implements ActionListener {
    
    /** The center. */
    JPanel center;
    
    /** The east. */
    JPanel east;

    /**
     * Instantiates a new controller.
     */
    public Controller() {

    }

    /**
     * Instantiates a new controller.
     *
     * @param center the center
     * @param east the east
     */
    public Controller(JPanel center, JPanel east) {

      this.center = center;
      this.east = east;

    }

    /**
     * Action performed.
     *
     * @param e the e
     * @return returns the JButton
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      boolean dotButtonChecker = false;
      // check for the dot command
      if (e.getActionCommand().equals(".")) {
        for (int i = 0; i < display2.getText().length(); i++) {
          if (display2.getText().charAt(i) == '.') {// if we get the dot button then make the
                                                    // boolean to true
            dotButtonChecker = true;
          }
        }
        if (!dotButtonChecker) {
          display2.setText(display2.getText() + e.getActionCommand());
        }
      } else if (e.getActionCommand().equals("\u00B1")) { // check for the plus minus sign

        count++;
        if (count == 1) {
          display2.setText("-" + display2.getText());
        } else if (count % 2 == 0) {
          display2.setText(display2.getText().substring(1, display2.getText().length()));
          count = 0;
        }

      } else if (e.getActionCommand().equals(".0") || e.getActionCommand().equals(".00")
          || e.getActionCommand().equals("Sci")) {

        // set the dotbutton to its function when we get any float operational mode

        dotButton.setEnabled(true);
        dotButton.setBackground(Color.BLUE);
        dotButton.setVisible(true);
        dotButton.setOpaque(true);
        error.setText("F");
        display2.setText("0.0");
        display1.setText("");
        error.setBackground(Color.YELLOW);
        model.setFloatingPointPrecision(e.getActionCommand());

        // loop through the components to enable it


        if (error.getText().equals("E"))
          doublePrecision.setSelected(true);
        dotButton.setBackground(Color.BLUE);


      } else if (e.getActionCommand().equals("\u21DA")) { // check for the backspace
        if (display2.isEditable()) {
          if (display2.getText().length() == 2 && display2.getText().startsWith("-")) {

            if (error.getText().equals("F")) {
              display2.setText("0.0");
            } else {
              display2.setText("0");
            }

          } else {
            display2.setText(display2.getText().substring(0, display2.getText().length() - 1));
          }
        }
        // function for the Key C
      } else if (e.getActionCommand().equals("C")) {
        // clearCalculator();
        if (error.getText().equals("F")) {
          display1.setText("");
          display2.setText("0.0");
          display2.setEditable(false);
          error.setText("F");
          error.setBackground(Color.YELLOW);
          error.setOpaque(true);
          // do this if the error text is E
          for (int i = 0; i < center.getComponentCount(); i++) {

            center.getComponent(i).setEnabled(true);

          }

        } else if (error.getText().equals("E")) {

          display1.setText("");
          display2.setText("0.0");
          error.setText("F");
          error.setBackground(Color.YELLOW);
          error.setOpaque(true);
          for (int i = 0; i < center.getComponentCount(); i++) {

            center.getComponent(i).setEnabled(true);

          }
          doublePrecision.setSelected(true);
          dotButton.setBackground(Color.BLUE);
          east.getComponent(1).setEnabled(true);
        } else {
          display1.setText("");
          display2.setText("0");
          dotButton.setEnabled(false);
          dotButton.setBackground(new Color(178, 156, 250));
          dotButton.setOpaque(true);
          // dotButton.setVisible(false);

          error.setText(CHARACTER_I);
          error.setBackground(Color.GREEN);
          error.setOpaque(true);

        }

        display2.setEditable(false);

      } else if (e.getActionCommand().equals(INTEGER_CHECKBOX)) { // if the action command is Int
                                                                  // then set the
                                                                  // error to 'I'nd
        // display the text as '0'
        /*
         * error.setText("I"); error.setBackground(Color.GREEN); error.setOpaque(true);
         * error.setVisible(true); dotButton.setBackground(new Color(178, 156, 250));
         * dotButton.setEnabled(false); dotButton.setOpaque(true); dotButton.setVisible(false);
         * display2.setText("0");
         * 
         * for (int i = 0; i < center.getComponentCount(); i++) {
         * 
         * center.getComponent(i).setEnabled(true);
         * 
         * }
         */
        if (error.getText().equals(CHARACTER_I)) {

          doublePrecision.setSelected(true);

          error.setText("F");
          error.setBackground(Color.YELLOW);
          model.setFloatingPointPrecision(".00");
          model.setOperationalMode("Float");
          dotButton.setEnabled(true);
          dotButton.setBackground(Color.BLUE);

          display1.setText("");

        } else {
          error.setText(CHARACTER_I);
          error.setPreferredSize(new Dimension(35, 55));
          error.setBackground(Color.GREEN);
          display1.setText("");
          display2.setText("0");
          error.setBorder(new MatteBorder(0, 1, 0, 1, Color.BLACK));
          error.setFont(new Font(getName(), Font.BOLD, 19));
          error.setOpaque(true);
          error.setVisible(true);

          dotButton.setEnabled(false);
          dotButton.setBackground(new Color(178, 156, 250));
          dotButton.setOpaque(true);

          model.setFloatingPointPrecision(e.getActionCommand());
          model.setOperationalMode("Integer");

        }

      } else if (e.getActionCommand().equals("=")) { // check for the equals sign

        model.setOperand2(display2.getText().replaceAll("[^0-9.-]", ""));
        model.setOperationalMode(error.getText());
        String ans = model.getResult();
        if (ans.equals("nan") || ans.equals("results not defined")) {

          display2.setText("Results not defined");
          error.setText("E");
          error.setBackground(Color.RED);
          for (int i = 0; i < center.getComponentCount(); i++) {

            center.getComponent(i).setEnabled(false);

          }
          east.getComponent(1).setEnabled(false);
        } else if (ans.equals("infinity") || ans.contains("cannot divide by zero")) {


          display2.setText("Cannot divide by zero");

          error.setText("E");
          error.setBackground(Color.RED);
          error.setOpaque(true);

          for (int i = 0; i < center.getComponentCount(); i++) {

            center.getComponent(i).setEnabled(false);

          }
          east.getComponent(1).setEnabled(false);

        } else {
          display1.setText("");
          display2.setText("");
          display2.setText(ans);
        }

        display2.setEditable(false);

      } else if (e.getActionCommand().equals("+") || e.getActionCommand().equals("-")
          || e.getActionCommand().equals("/") || e.getActionCommand().equals("*")) {
        model.setOperand1(display2.getText());
        if ((display1.getText().isEmpty() || display1.getText().length() == 1)
            && e.getActionCommand().equals("-") && !display2.isEditable()) {
          display2.setText(e.getActionCommand());
          display2.setEditable(true);
        } else {
          display1.setText(display2.getText() + e.getActionCommand());
          model.setArithmeticOperation(e.getActionCommand());
          display2.setEditable(false);
        }
      } else {
        if (display2.getHeight() > display2.getText().length())
          if (display2.getText().equals("0.0") || display2.getText().equals("0")) {

            display2.setEditable(true);
            display2.setText(e.getActionCommand());

          } else if (!display2.isEditable()) {

            display2.setText(e.getActionCommand());
            display2.setEditable(true);
          } else if (!display1.getText().isEmpty() && !display2.isEditable()) {

            display2.setEditable(true);
            display2.setText(e.getActionCommand());

          } else {

            display2.setEditable(true);
            display2.setText(display2.getText() + e.getActionCommand());

          }

      }
    }

  }
}
