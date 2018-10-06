package com.srg;

import com.srg.cube.Shape;
import com.srg.cube.impl.*;
import com.srg.cube.impl.Rectangle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JPanel {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 950;

    public static BufferedImage blue;

    private static int score;
    private static Shape shape;

    static {
        try {
            blue = ImageIO.read(new File("src/resource/blue.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        JFrame jFrame = new JFrame("TERIS");
        Main main = new Main();
        jFrame.add(main);
        jFrame.setSize(Main.WIDTH, Main.HEIGHT);
        jFrame.setLocationRelativeTo(null);//set the position of the windows
        jFrame.setResizable(false);//set can't be changed
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);//show the windows
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.setFont(new Font("楷体", 25, 20));
        g.drawString("Score:" + score, 15, 30);

        int flag = (int) (Math.random() * 5);
        paintType(g, flag);
    }

    private void paintType(Graphics g, int flag) {

        switch (flag) {
            case 0:
                shape = new LForm(WIDTH / 2 - blue.getWidth(), 0);
                break;
            case 1:
                shape = new ZForm((WIDTH - blue.getWidth() * 3) / 2, 0);
                break;
            case 2:
                shape = new Rectangle((WIDTH - blue.getWidth()) / 2, 0);
                break;
            case 3:
                shape = new TForm((WIDTH - blue.getWidth() * 3) / 2, 0);
                break;
            case 4:
                shape = new Square((WIDTH - blue.getWidth() * 2) / 2, 0);
                break;
            default:
                break;
        }
        for (int i = 0; i < 4; i++)
            g.drawImage(shape.image, shape.cell[i].x, shape.cell[i].y, null);
    }
}
