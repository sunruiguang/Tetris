package com.srg.cube;

import java.awt.image.BufferedImage;

public class Shape {
    public Cell[] cell;
    public BufferedImage image;

    public Shape() {
        cell = new Cell[4];
    }

    public void autoDown() {
        for (int i = 0; i < 4; i++) {
            cell[i].autoDown();
        }
    }
}
