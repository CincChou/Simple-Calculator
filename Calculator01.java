package com.xinyingHCI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Calculator01 extends JFrame{
    public static String strOne = "";
    public static String strTwo = "";
    public static double result;
    public static String show_res = "";
    public static String mode = "";

    public static String operator = "";
    public static double numOne;
    public static double numTwo;
    public static int switch_count = 0;

    private JTextField tf = new JTextField("");
    private JButton[] btn0 = new JButton[3];
    private JButton[] btn = new JButton[16];


    public Calculator01() {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6,2,3,3));

        //input field configure
        tf.setEditable(false);
        frame.add(tf);
        tf.setPreferredSize(new Dimension(300,50));                

        //3 Functional Buttons
        JPanel pan0 = new JPanel(new GridLayout(1,3,3,3));
        String str0[] = {"clear", "m-switch", "enter"};

        for(int i=0; i<3; i++){
            btn0[i] = new JButton(""+str0[i]);
            pan0.add(btn0[i]);
        }
        //Add listener
        //"clear" button
        btn0[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result = 0;
                show_res = "";
                tf.setText(show_res);
                strOne = "";
                strTwo = "";
                numOne = 0;
                numTwo = 0;
            }
        });

        //"switch mode" button
        btn0[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch_count += 1;
                if(switch_count % 2 == 1){
                    mode = "mode";
                }else{
                    mode = "";
                }
                result = 0;
                show_res = "";
                tf.setText(show_res);
                strOne = "";
                strTwo = "";
                numOne = 0;
                numTwo = 0;
            }
        });

        //"enter" button
        btn0[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strOne = tf.getText();
                tf.setText("");
            }
        });
        //add panel to frame
        pan0.setPreferredSize(new Dimension(300,50));
        frame.add(pan0);

        //16 calculation related buttons
        String[] str = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+"};

        //Using GridLayOut to organize buttons
        JPanel pan1 = new JPanel(new GridLayout(1,4,3,3));
        JPanel pan2 = new JPanel(new GridLayout(1,4,3,3));
        JPanel pan3 = new JPanel(new GridLayout(1,4,3,3));
        JPanel pan4 = new JPanel(new GridLayout(1,4,3,3));

        for(int i = 0; i<4; i++) {
            btn[i] = new JButton("" + str[i]);
            pan1.add(btn[i]);
        }
        frame.add(pan1);

        for(int i = 4; i<8; i++) {
            btn[i] = new JButton("" + str[i]);
            pan2.add(btn[i]);
        }
        frame.add(pan2);

        for(int i = 8; i<12; i++) {
            btn[i] = new JButton("" + str[i]);
            pan3.add(btn[i]);
        }
        frame.add(pan3);

        for(int i = 12; i<16; i++) {
            btn[i] = new JButton("" + str[i]);
            pan4.add(btn[i]);
        }
        frame.add(pan4);

        //Add listener to each button
        for(int i=0; i<16; i++){
            btn[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String current_str = e.getActionCommand();
                    System.out.println(current_str);
                    if(mode.equals("")) {
                        System.out.println("now in mode default");
                        if (".0123456789".indexOf(current_str) != -1) {
                            System.out.println(current_str);
                            tf.setText(tf.getText() + current_str);
                            tf.setHorizontalAlignment(JTextField.RIGHT);
                        } else if (current_str.matches("[\\+\\-*/]{1}")) {
                            if (operator.length() == 0) {
                                //first input
                                strOne = tf.getText();
                                tf.setText("");
                                operator = current_str;
                            } else {
                                //not first 12+1 /
                                strTwo = tf.getText();
                                numOne = Double.valueOf(strOne);
                                numTwo = Double.valueOf(strTwo);

                                switch (operator) {
                                    case "+":
                                        result = numOne + numTwo;
                                        break;
                                    case "-":
                                        result = numOne - numTwo;
                                        break;
                                    case "*":
                                        result = numOne * numTwo;
                                        break;

                                    case "/":
                                        if (numTwo != 0) {
                                            result = numOne / numTwo;
                                        }
                                        break;
                                }
                                show_res = String.valueOf(result);
                                tf.setText(show_res);

                                strOne = tf.getText();
                                strTwo = "";
                                tf.setText("");
                                operator = current_str;


                            }

                        } else if (current_str.equals("=")) {
                            strTwo = tf.getText();
                            numOne = Double.valueOf(strOne);
                            numTwo = Double.valueOf(strTwo);

                            switch (operator) {
                                case "+":
                                    result = numOne + numTwo;
                                    break;
                                case "-":
                                    result = numOne - numTwo;
                                    break;
                                case "*":
                                    result = numOne * numTwo;
                                    break;

                                case "/":
                                    if (numTwo != 0) {
                                        result = numOne / numTwo;
                                    }
                                    break;
                            }
                            show_res = String.valueOf(result);
                            tf.setText(show_res);

                            strOne = "";
                            strTwo = "";
                            operator = "";
                        }
                    }else if(mode.equals("mode")){
                        System.out.println("now in mode RPN");
                        if (".0123456789".indexOf(current_str) != -1) {
                            System.out.println(current_str);
                            tf.setText(tf.getText() + current_str);
                            tf.setHorizontalAlignment(JTextField.RIGHT);
                        }else if (current_str.equals("enter")) {
                            strOne = tf.getText();
                            tf.setText("");

                        }else if(current_str.matches("[\\+\\-*/]{1}")){
                            strTwo = tf.getText();
                            numOne = Double.valueOf(strOne);
                            numTwo = Double.valueOf(strTwo);

                            switch (current_str){
                                case "+":
                                    result = numOne + numTwo;
                                    break;
                                case "-":
                                    result = numOne - numTwo;
                                    break;
                                case "*":
                                    result = numOne * numTwo;
                                    break;

                                case "/":
                                    if (numTwo != 0) {
                                        result = numOne / numTwo;
                                    }
                                    break;

                            }
                            show_res = ""+result;
                            tf.setText(show_res);

                        }

                    }

                }
            });
        }

        frame.setSize(300,400);
        frame.setResizable(false);

        frame.setVisible(true);

    }

    public static void main(String[] args) {
        Calculator01 calculator = new Calculator01();
        }
    }




