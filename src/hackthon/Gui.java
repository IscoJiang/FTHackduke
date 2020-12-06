package hackthon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Gui implements ActionListener {

    public static String first, second;
    private static JTextField field1, field2;

    public static void main(String args[]) {

        // get the center location of the screen
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // initialize the Frame
        JFrame frame = new JFrame("Dice Drawing");
        frame.setBounds(screenWidth / 2 - 300, screenHeight / 2 - 200, 600,
            400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // create two labels
        JLabel Label1 = new JLabel("Select First Picture:");
        Label1.setBounds(40, 80, 180, 50);
        JLabel Label2 = new JLabel("Select Second Picture:");
        Label2.setBounds(40, 150, 200, 50);

        // create two textField
        field1 = new JTextField("");
        field1.setBounds(200, 80, 180, 50);
        field1.addFocusListener(new JTextFieldHintListener(field1,
            "select first picture"));
        field2 = new JTextField();
        field2.addFocusListener(new JTextFieldHintListener(field2,
            "select second picture"));
        field2.setBounds(200, 150, 180, 50);

        // create load button
        JButton load = new JButton("Output");
        load.setBounds(250, 300, 100, 50);
        load.addActionListener(new ActionListener() {
            @SuppressWarnings("unused")
            public void actionPerformed(ActionEvent e) {
                try {
                    if (field1.getText().length() == 0 || field1.getText()
                        .equals("select first picture") || (!field1.getText()
                            .endsWith(".jpg")) && !field1.getText().endsWith(
                                ".png")) {
                        first = null;
                    }
                    if (field2.getText().length() == 0 || field2.getText()
                        .equals("select second picture") || (!field2.getText()
                            .endsWith(".jpg")) && !field2.getText().endsWith(
                                ".png")) {
                        second = null;
                    }
                    // if not choose first file but input name in textField
                    if (first == null && (field1.getText().endsWith(".png")
                        || field1.getText().endsWith(".jpg"))) {
                        first = field1.getText();
                    }
                    // if first file's name differ from the name in textField
                    if (first != null && !first.contains(field1.getText())) {
                        first = field1.getText();
                    }
                    // if not choose second file but input name in textField
                    if (second == null && (field2.getText().endsWith(".png")
                        || field2.getText().endsWith(".jpg"))) {
                        second = field2.getText();
                    }
                    // if second file's name differ from the name in textField
                    if (second != null && !second.contains(field2.getText())) {
                        second = field2.getText();
                    }
                    if (first != null && second != null) {
                        Photo photo = new Photo(first, second);
                    }
                    else if (first != null) {
                        Photo photo = new Photo(first, null);
                    }
                    else if (second != null) {
                        Photo photo = new Photo(second, null);
                    }
                    else {
                        JOptionPane.showMessageDialog(frame,
                            "at least select one file");
                    }
                }
                catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        // create two buttons calls fileChoose
        JButton choose = new JButton("Select File");
        JButton choose2 = new JButton("Select File");
        choose.setBounds(400, 80, 100, 50);
        choose.addActionListener(new ChooseActionListener(field1, frame));
        choose2.setBounds(400, 150, 100, 50);
        choose2.addActionListener(new ChooseActionListener(field2, frame));

        // create a button to show the Dice Development
        JButton show = new JButton("Show Dice Development");
        show.setBounds(200, 220, 200, 50);
        show.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DiceDevelopment.main();
            }
        });

        // add components to the frame
        frame.add(Label1);
        frame.add(Label2);
        frame.add(field1);
        frame.add(field2);
        frame.add(choose);
        frame.add(choose2);
        frame.add(show);
        frame.add(load, BorderLayout.SOUTH);

        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }

    /**
     * ActionListener to choose the jpg or png file
     * 
     * @author Xianshun Jiang(xianshun@vt.edu)
     * @version 12/6/2020
     */
    private static class ChooseActionListener implements ActionListener {
        private JTextField textField;
        private JFrame frame;

        /**
         * add ActionListener
         * 
         * @param jTextField
         *            the field we want to show the file
         * @param frame
         *            to show dialog
         */
        public ChooseActionListener(JTextField jTextField, JFrame frame) {
            this.textField = jTextField;
            this.frame = frame;

        }


        /**
         * select jpg file
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser jfc = new JFileChooser();
            jfc.showSaveDialog(frame);
            try {
                File file = jfc.getSelectedFile();

                // check if the type of file is jpg or png
                if (!file.getName().endsWith(".jpg") && !file.getName()
                    .endsWith(".png")) {
                    JOptionPane.showMessageDialog(frame,
                        "Choose a jpg or png file");
                    first = file.getName();
                    return;
                }
                // store the address of the pic
                if (textField.equals(field1)) {
                    first = file.toString();
                }
                else {
                    second = file.toString();
                }
                textField.setForeground(Color.BLACK);
                textField.setText(file.getName());
            }
            catch (Exception e2) {
                JOptionPane.showMessageDialog(frame, "No file selected");
            }
        }

    }


    /**
     * Give Hint in the JTextField
     * 
     * @author Xianshun Jiang(xianshun@vt.edu)
     * @version 12/62020
     */
    private static class JTextFieldHintListener implements FocusListener {
        private String hintText;
        private JTextField textField;

        /**
         * show the hint when the focus was lost
         * 
         * @param jTextField
         *            the field shows the hint
         * @param hintText
         *            the text of the hit
         */
        public JTextFieldHintListener(JTextField jTextField, String hintText) {
            this.textField = jTextField;
            this.hintText = hintText;
            jTextField.setText(hintText);
            jTextField.setForeground(Color.GRAY);
        }


        /**
         * get the text
         */
        @Override
        public void focusGained(FocusEvent e) {
            String temp = textField.getText();
            if (temp.equals(hintText)) {
                textField.setText("");
                textField.setForeground(Color.BLACK);
            }
        }


        /**
         * show the hint when lost focus
         */
        @Override
        public void focusLost(FocusEvent e) {
            String temp = textField.getText();
            if (temp.equals("")) {
                textField.setForeground(Color.GRAY);
                textField.setText(hintText);
            }
        }
    }

}
