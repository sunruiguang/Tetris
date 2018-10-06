package com.srg.cube;

public abstract class Cell {
    private int x;
    private int y;
    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void down(){
        y++;
    }

    public void left(){
        x--;
    }

    public void right(){
        x++;
    }
}
