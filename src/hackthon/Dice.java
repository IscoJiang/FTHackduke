package hackthon;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Dice {

    public static void createImage() {
        for (int i = 1; i <= 6; i++) {
            BufferedImage img = new BufferedImage(21, 21,
                BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = img.createGraphics();
            g2d.setColor(new Color(60, 60, 60));
            g2d.fillRect(0, 0, 21, 21);
            g2d.setColor(Color.black);
            g2d.fillOval(0, 0, 21, 21);
            Dice.dice(String.valueOf(i), g2d);
            try {
                ImageIO.write(img, "jpg", new File("Dice" + String.valueOf(i)
                    + ".jpg"));
            }
            catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            g2d.dispose();
        }
    }


    public static void dice(String number, Graphics2D g) {
        g.setColor(Color.white);
        switch (number) {
            case "1":
                g.fillOval(8, 8, 5, 5);
                break;
            case "2":
                g.fillOval(12, 3, 5, 5);
                g.fillOval(3, 12, 5, 5);
                break;
            case "3":
                g.fillOval(8, 8, 5, 5);
                g.fillOval(12, 3, 5, 5);
                g.fillOval(3, 12, 5, 5);
                break;
            case "4":
                g.fillOval(3, 3, 5, 5);
                g.fillOval(12, 12, 5, 5);
                g.fillOval(12, 3, 5, 5);
                g.fillOval(3, 12, 5, 5);
                break;
            case "5":
                g.fillOval(8, 8, 5, 5);
                g.fillOval(3, 3, 5, 5);
                g.fillOval(12, 12, 5, 5);
                g.fillOval(12, 3, 5, 5);
                g.fillOval(3, 12, 5, 5);
                break;
            case "6":
                g.fillOval(3, 8, 5, 5);
                g.fillOval(12, 8, 5, 5);
                g.fillOval(3, 3, 5, 5);
                g.fillOval(12, 13, 5, 5);
                g.fillOval(12, 3, 5, 5);
                g.fillOval(3, 13, 5, 5);
                break;
        }
    }

}
