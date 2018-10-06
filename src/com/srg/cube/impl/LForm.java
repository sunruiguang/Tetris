package com.srg.cube.impl;

import com.srg.Main;
import com.srg.cube.Cell;
import com.srg.cube.Shape;

public class LForm extends Shape {
    public LForm(int x, int y) {
        image = Main.blue;
        cell[0] = new Cell(x, y);
        cell[1] = new Cell(x, y + image.getHeight());
        cell[2] = new Cell(x, y + image.getHeight() * 2);
        cell[3] = new Cell(x + image.getWidth(), y + image.getHeight() * 2);
    }
}
