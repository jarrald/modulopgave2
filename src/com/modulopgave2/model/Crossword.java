package com.modulopgave2.model;

import com.modulopgave2.model.Word;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Crossword {

    char[][] grid;

    public char[][] getGrid() { return grid; }
    public void setGrid(char[][] grid) { this.grid = grid; }

    public Crossword(char[][] grid) {
        this.grid = grid;
    }


    public boolean hasCell(int x, int y) {
        if((x >= 0 && x < grid.length)
                && (y >= 0 && y < grid[x].length))
            return true;
        return false;
    }

    public char getCell(int x, int y) {
            return grid[x][y];
    }

    public void setCell(int x, int y, char value) {
        grid[x][y] = value;
    }
}
