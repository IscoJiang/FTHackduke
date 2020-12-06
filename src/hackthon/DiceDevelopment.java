package hackthon;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * create Dice Development
 * 
 * @author Xianshun Jiang(xianshun@vt.edu)
 * @version 12/6/2020
 */
@SuppressWarnings("serial")
public class DiceDevelopment extends JFrame {
    private static JFrame frame;
    private static JPanel panel;

    public static void main() {

        frame = new JFrame("Development");
        frame.setVisible(true);
        frame.setBounds(0, 0, 1300, 900);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                DiceDevelopment.dice("1", g, 600, 300);
                DiceDevelopment.dice("2", g, 400, 300);
                DiceDevelopment.dice("3", g, 600, 100);
                DiceDevelopment.dice("4", g, 600, 500);
                DiceDevelopment.dice("5", g, 800, 300);
                DiceDevelopment.dice("6", g, 200, 300);
            }
        };
        frame.add(panel);
        JOptionPane.showMessageDialog(frame,
            "Development of Dice, and Ignore rotation for 2, 3, 6" + "\n"
                + "The dice we use to draw is 1/7 of this dice");

    }


    /**
     * create dice depends on the String
     * 
     * @param number
     *            the number of dice
     * @param g
     *            Graphics
     * @param x
     *            x-location
     * @param y
     *            y-location
     */
    public static void dice(String number, Graphics g, int x, int y) {
        g.setColor(new Color(60, 60, 60));
        g.fillRect(x, y, 145, 145);
        g.setColor(Color.black);
        g.fillOval(x, y, 145, 145);
        g.setColor(Color.white);
        switch (number) {
            case "1":
                g.fillOval(x + 55, y + 55, 35, 35);
                break;
            case "2":
                g.fillOval(x + 87, y + 23, 35, 35);
                g.fillOval(x + 23, y + 87, 35, 35);
                break;
            case "3":
                g.fillOval(x + 55, y + 55, 35, 35);
                g.fillOval(x + 87, y + 23, 35, 35);
                g.fillOval(x + 23, y + 87, 35, 35);
                break;
            case "4":
                g.fillOval(x + 23, y + 23, 35, 35);
                g.fillOval(x + 87, y + 87, 35, 35);
                g.fillOval(x + 87, y + 23, 35, 35);
                g.fillOval(x + 23, y + 87, 35, 35);
                break;
            case "5":
                g.fillOval(x + 55, y + 55, 35, 35);
                g.fillOval(x + 23, y + 23, 35, 35);
                g.fillOval(x + 87, y + 87, 35, 35);
                g.fillOval(x + 87, y + 23, 35, 35);
                g.fillOval(x + 23, y + 87, 35, 35);
                break;
            case "6":
                g.fillOval(x + 23, y + 55, 35, 35);
                g.fillOval(x + 87, y + 55, 35, 35);
                g.fillOval(x + 23, y + 95, 35, 35);
                g.fillOval(x + 87, y + 95, 35, 35);
                g.fillOval(x + 87, y + 15, 35, 35);
                g.fillOval(x + 23, y + 15, 35, 35);
                break;
        }
    }
}
