

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class CalculatorGUI{

    int boardWidth = 360;
    int boardHeight = 540;

    Color lightGrey = new Color(212, 212, 210);
    Color darkGrey = new Color(80, 80, 80);
    Color black = new Color(28, 28, 28);
    Color orange = new Color(255, 149, 0);
    
    String[] buttonValues = {
        "AC", "+/-", "%", "÷", 
        "7", "8", "9", "×", 
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };

    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};

    JFrame frame = new JFrame("Calculator");
    JLabel displaylabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();


    String A = "0";
    String op = null;
    String B = null;

    public CalculatorGUI() {
        //Frame
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        //Label
        displaylabel.setBackground(black);
        displaylabel.setForeground(Color.white);
        displaylabel.setFont(new Font("Arial", Font.PLAIN, 90));
        displaylabel.setHorizontalAlignment(JLabel.RIGHT);
        displaylabel.setText("0");
        displaylabel.setOpaque(true);
        
        //Panel
        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displaylabel);
        frame.add(displayPanel, BorderLayout.NORTH);
        
        buttonsPanel.setLayout(new GridLayout(5, 4));
        buttonsPanel.setBackground(black);
        frame.add(buttonsPanel);
        
        for (int i = 0; i < buttonValues.length; i++) {
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(black));
            if(Arrays.asList(topSymbols).contains(buttonValue)){
                button.setBackground(lightGrey);
                button.setForeground(black);
            }else if(Arrays.asList(rightSymbols).contains(buttonValue)){
                button.setBackground(orange);
                button.setForeground(Color.white);
            }else{
                button.setBackground(darkGrey);
                button.setForeground(Color.white);
            }
            
            buttonsPanel.add(button);
            
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    JButton button = (JButton) e.getSource();
                    String buttonValue = button.getText();
                    
                    if(Arrays.asList(rightSymbols).contains(buttonValue)){
                        if(buttonValue.equals("=")){
                            if(A != null){
                                B = displaylabel.getText();
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);

                                if(op == "+"){
                                    displaylabel.setText(removeZeroDec(numA + numB));
                                }else if(op == "-"){
                                    displaylabel.setText(removeZeroDec(numA - numB));
                                }else if(op == "×"){
                                    displaylabel.setText(removeZeroDec(numA * numB));
                                }else if(op == "÷"){
                                    displaylabel.setText(removeZeroDec(numA / numB));
                                }
                                clearAll();

                            }
                        }
                        else if ("+-×÷".contains(buttonValue)) {
                            if(op == null){
                                
                                A = displaylabel.getText();
                                displaylabel.setText("0");
                                B = "0";
                            }
                            op = buttonValue;
                        }
                    }
                    else if (Arrays.asList(topSymbols).contains(buttonValue)) {
                        if(buttonValue == "AC"){
                            clearAll();
                            displaylabel.setText("0");
                        }else if (buttonValue == "+/-") {
                            double numDisplay = Double.parseDouble(displaylabel.getText());
                            numDisplay *= -1;
                            displaylabel.setText(removeZeroDec(numDisplay));
                        }else if (buttonValue == "%") {
                            double numDisplay = Double.parseDouble(displaylabel.getText());
                            numDisplay /= 100;
                            displaylabel.setText(removeZeroDec(numDisplay));
                        }
                    }else if (buttonValue.equals("√")) {
                        double num = Double.parseDouble(displaylabel.getText());
                        if (num < 0) {
                            displaylabel.setText("Error");
                        } else {
                            displaylabel.setText(removeZeroDec(Math.sqrt(num)));
                        }
                    }else{
                        if(buttonValue == "."){
                            if(!displaylabel.getText().contains(buttonValue)){
                                displaylabel.setText(displaylabel.getText() + buttonValue);
                            }
                        }else if("0123456789".contains(buttonValue)){
                            if(displaylabel.getText() == "0"){
                                displaylabel.setText(buttonValue);
                            }else{
                                displaylabel.setText(displaylabel.getText() + buttonValue);
                            }
                        }
                    }
                }
            });
        }

        frame.setVisible(true);
    }
    
    void clearAll(){
        A = "0";
        op = null;
        B = null;
    }
    
    String removeZeroDec(double numDisplay){
        if(numDisplay % 1 == 0){
            return Integer.toString((int) numDisplay);
        }else{
            return Double.toString(numDisplay);
        }
    }
}
