package git.programmer0121.solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Board {
    
    final Cell[][] board = new Cell[9][9];
    final List<Group> rows = new ArrayList<>();
    final List<Group> cols = new ArrayList<>();
    final List<Group> sqrs = new ArrayList<>();
    
    public Board() {
        init();
    }
    
    public Board(int[][] board) {
        this();
        set(board);
    }

    public Board (String boardText) {
        this();
        int[][] board = read(boardText);
        set(board);
    }
    
    private void init() {
        rows.clear();
        cols.clear();
        sqrs.clear();
        for (int i = 0; i < 9; i++) {
            rows.add(new Group(i));
            cols.add(new Group(i));
            sqrs.add(new Group(i));
        }
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int sqr = (row/3)*3 + col/3;
                Cell cell = new Cell(rows.get(row), cols.get(col), sqrs.get(sqr));
                board[row][col] = cell;
                rows.get(row).cells.add(cell);
                cols.get(col).cells.add(cell);
                sqrs.get(sqr).cells.add(cell);
            }
        }
    }
    
    public final void set(int[][] inBoard) {
        init();
        for (int row = 0; row < 9; row++)
        for (int col = 0; col < 9; col++)
        if (inBoard[row][col] != 0)
            board[row][col].set(inBoard[row][col]);
    }
    
    public int[][] get() {
        int[][] outBoard = new int[9][9];
        for (int row = 0; row < 9; row++)
            for (int col = 0; col < 9; col++)
                outBoard[row][col] = board[row][col].get();
        return outBoard;
    }

    private int[][] read(String boardText) {
        int board[][] = new int[9][9];
        String[] lines = boardText.split("\n");
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                board[row][col] = lines[row].charAt(col) - '0';
            }
        }
        return board;
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int v = board[row][col].get();
                s.append(v == 0 ? "-" : Integer.toString(v));
            }
            s.append('\n');
        }
        return s.toString();
    }

}
