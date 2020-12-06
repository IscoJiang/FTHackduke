package hackthon;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Photo {

    public BufferedImage image, image2;
    private int width, height, rows, cols, size, rows2, col2;
    private int dark = 255;
    private int light = 0;
    public BufferedImage[][] imgs, imgs2;
    private boolean twoIn;

    public Photo(String s, String s2) throws IOException {
        this.readImage(s, s2);
        this.setUp();
        if (image2 == null) {
            this.out(image, imgs, "first");
            twoIn = false;
        }
        else {
            this.out(image, imgs, "first");
            this.out(image2, imgs2, "second");
            twoIn = true;
        }

    }


    private void setUp() {
        if (image != null && image2 != null) {
            image = this.zoom(image);
            image2 = this.zoom(image2);
            float ration = (float)image.getWidth() / image.getHeight();
            float ration2 = (float)image2.getWidth() / image2.getHeight();
            width = Math.min(image.getWidth(), image2.getWidth());
            height = (int)(width / Math.max(ration, ration2));

            imgs = this.createImgs(image);
            imgs2 = this.createImgs(image2);

            this.loadImage(image, imgs);
            this.loadImage(image2, imgs2);
        }
        else if (image != null) {
            width = image.getWidth();
            height = image.getHeight();
            imgs = this.createImgs(image);
            this.loadImage(image, imgs);
        }

    }


    /**
     * get the greatest common division of n1, n2
     * 
     * @param n1
     *            first int
     * @param n2
     *            second int
     * @return
     */
    private int getGcd(int n1, int n2) {
        int gcd = 0;
        if (n1 < n2) {
            n1 = n1 + n2;
            n2 = n1 - n2;
            n1 = n1 - n2;
        }
        if (n1 % n2 == 0) {
            gcd = n2;
        }
        while (n1 % n2 > 0) {
            n1 = n1 % n2;
            if (n1 < n2) {
                n1 = n1 + n2;
                n2 = n1 - n2;
                n1 = n1 - n2;
            }
            if (n1 % n2 == 0) {
                gcd = n2;
            }
        }
        return gcd;
    }


    private BufferedImage[][] createImgs(BufferedImage img) {
        if (twoIn) {

        }
        if (width != 0 && height != 0) {
            width = Math.min(width, width / 80 * 80);
            height = Math.min(height, height / 80 * 80);
        }
        else {
            width = width / 80 * 80;
            height = height / 80 * 80;
        }
        if (size != 0) {
            size = Math.min(size, this.getGcd(width, height));
        }
        else {
            size = this.getGcd(width, height);
        }
        // I'm totally get exhausted about the flowering commented code,
        // because I don't know how to solve it when a picture with larger width
        // and the other with larger height, it will throw exception
        // I know it's because The minimun scale of width or height is 80, the
        // other may larger

        // if (img.getWidth() > img.getHeight()) {
        rows = size;
        int side = img.getHeight() / rows;
        cols = img.getWidth() / side;
        // }
        // else {
        // cols = size;
        // int side = img.getWidth() / cols;
        // rows = img.getHeight() / side;
        // }

        return new BufferedImage[rows][cols];
    }


    /**
     * store image in 2-d array
     * 
     * @param img
     *            is the img we want to break apart
     * @param imgs
     *            is the array we store the pieces
     */
    private void loadImage(BufferedImage img, BufferedImage[][] imgs) {

        int chunkWidth = img.getWidth() / cols;
        int chunkHeight = img.getHeight() / rows;

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                imgs[x][y] = new BufferedImage(chunkWidth, chunkHeight, img
                    .getType());

                int rgb = Math.abs(imgs[x][y].getRGB(0, 0));
                if (rgb < dark) {
                    dark = rgb;
                }
                if (rgb > light) {
                    light = rgb;
                }
                Graphics2D gr = imgs[x][y].createGraphics();
                gr.drawImage(img, 0, 0, chunkWidth, chunkHeight, chunkWidth * y,
                    chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight
                        * x + chunkHeight, null);

                gr.dispose();
            }
        }
    }


    private BufferedImage zoom(BufferedImage img) {
        int ration, height, width;
        if (img.getHeight() > img.getWidth()) {
            ration = 500 / img.getHeight();
        }
        else {
            ration = 500 / img.getWidth();
        }
        height = img.getHeight() * ration;
        width = img.getWidth() * ration;

        BufferedImage re = new BufferedImage(width, height, img.getType());
        Graphics2D g = re.createGraphics();
        g.drawImage(img, 0, 0, width, height, null);
        g.dispose();

        return re;
    }


    public void out(BufferedImage img, BufferedImage[][] imgs, String s)
        throws IOException {

        int range = Math.abs(light - dark);
        int average = range / 6;
        BufferedImage finalImg = new BufferedImage(21 * cols, 21 * rows, img
            .getType());
        BufferedImage i1 = ImageIO.read(new File("Dice1.jpg"));
        BufferedImage i2 = ImageIO.read(new File("Dice2.jpg"));
        BufferedImage i3 = ImageIO.read(new File("Dice3.jpg"));
        BufferedImage i4 = ImageIO.read(new File("Dice4.jpg"));
        BufferedImage i5 = ImageIO.read(new File("Dice5.jpg"));
        BufferedImage i6 = ImageIO.read(new File("Dice6.jpg"));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                int rgb = Math.abs(imgs[i][j].getRGB(0, 0));
                if (rgb / average == 0 && rgb % average < average / 2) {
                    finalImg.createGraphics().drawImage(i6, 21 * j, 21 * i,
                        null);
                }
                else if ((rgb / average == 0 && rgb % average >= average / 2)
                    || (rgb / average == 1 && rgb % average < average / 2)) {
                    finalImg.createGraphics().drawImage(i5, 21 * j, 21 * i,
                        null);
                }
                else if ((rgb / average == 1 && rgb % average >= average / 2)
                    || (rgb / average == 2 && rgb % average < average / 2)) {
                    finalImg.createGraphics().drawImage(i4, 21 * j, 21 * i,
                        null);
                }
                else if ((rgb / average == 2 && rgb % average >= average / 2)
                    || (rgb / average == 3 && rgb % average < average / 2)) {
                    finalImg.createGraphics().drawImage(i3, 21 * j, 21 * i,
                        null);
                }
                else if ((rgb / average == 3 && rgb % average >= average / 2)
                    || (rgb / average == 4 && rgb % average < average / 2)) {
                    finalImg.createGraphics().drawImage(i2, 21 * j, 21 * i,
                        null);
                }
                else {
                    finalImg.createGraphics().drawImage(i1, 21 * j, 21 * i,
                        null);
                }
            }
        }
        ImageIO.write(finalImg, "jpg", new File(s + ".jpg"));
    }


    /**
     * read image based on the String input
     * 
     * @param s
     *            String of first image
     * @param s2
     *            String of second image
     * @throws IOException
     */
    private void readImage(String s, String s2) throws IOException {
        if (s != null) {
            File f = new File(s);
            image = ImageIO.read(f);
        }
        if (s2 != null) {
            File f = new File(s2);
            image2 = ImageIO.read(f);
        }
    }
}
