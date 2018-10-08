package com.srg;

import com.srg.cube.Cell;
import com.srg.cube.Shape;
import com.srg.cube.impl.*;
import com.srg.cube.impl.Rectangle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends JPanel {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 950;

    public static BufferedImage blue;

    private static int score;
    private static Shape[] shapes = {};
    private int index = 0;

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
        main.start();
    }

    private void start() {
        int interval = 5;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                enterAction();
                moveAction();
                checkAction();
                repaint();
            }
        }, interval, interval);
    }

    private void checkAction() {

    }

    private void moveAction() {
        for (Shape shape : shapes) {
            int maxY = 0;
            for (Cell cell : shape.cell)
                if (maxY < cell.y) maxY = cell.y;
            if (maxY != HEIGHT - 70)
                shape.autoDown();
        }
    }

    private void enterAction() {
        if (isArrivedBorder()) {
            Shape shape = nextOne();
            shapes = Arrays.copyOf(shapes, shapes.length + 1);
            shapes[shapes.length - 1] = shape;
        }
    }

    private boolean isArrivedBorder() {
        for (Shape shape : shapes) {
            int maxY = 0;
            for (Cell cell : shape.cell)
                if (maxY < cell.y) maxY = cell.y;
            if (maxY != HEIGHT - 70) return false;
        }
        return true;
    }

    private Shape nextOne() {
        int flag = (int) (Math.random() * 5);
        switch (flag) {
            case 0:
                return new LForm(WIDTH / 2 - blue.getWidth(), 0);
            case 1:
                return new Rectangle((WIDTH - blue.getWidth()) / 2, 0);
            case 2:
                return new Square(WIDTH / 2 - blue.getWidth(), 0);
            case 3:
                return new TForm((WIDTH - blue.getWidth() * 3) / 2, 0);
        }
        return new ZForm((WIDTH - blue.getWidth() * 3) / 2, 0);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.setFont(new Font("楷体", 25, 20));
        g.drawString("Score:" + score, 15, 30);
        paintType(g);
    }

    private void paintType(Graphics g) {
        for (Shape shape : shapes) {
            if (shape instanceof LForm) {
                LForm lForm = (LForm) shape;
                for (int i = 0; i < 4; i++)
                    g.drawImage(lForm.image, lForm.cell[i].x, lForm.cell[i].y, null);
            } else if (shape instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) shape;
                for (int i = 0; i < 4; i++)
                    g.drawImage(rectangle.image, rectangle.cell[i].x, rectangle.cell[i].y, null);
            } else if (shape instanceof Square) {
                Square square = (Square) shape;
                for (int i = 0; i < 4; i++)
                    g.drawImage(square.image, square.cell[i].x, square.cell[i].y, null);
            } else if (shape instanceof TForm) {
                TForm tForm = (TForm) shape;
                for (int i = 0; i < 4; i++)
                    g.drawImage(tForm.image, tForm.cell[i].x, tForm.cell[i].y, null);
            } else if (shape instanceof ZForm) {
                ZForm zForm = (ZForm) shape;
                for (int i = 0; i < 4; i++)
                    g.drawImage(zForm.image, zForm.cell[i].x, zForm.cell[i].y, null);
            }
        }

    }
}
