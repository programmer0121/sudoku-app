package git.programmer0121.solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    
    public static void main(String[] args) {
        int[][] rawBoard = read();
        Board board = new Board(rawBoard);
        Solver solver = new BacktrackingSolver(board);
        solver.solve();
        print(board.toString());
    }
    
    private static int[][] read() {
        int[][] board = new int[9][9];
        try {
            Scanner sc = new Scanner(new File("in.txt"));
            for (int row = 0; row < 9; row++) {
                String line = sc.nextLine();
                for (int col = 0; col < 9; col++) {
                    board[row][col] = line.charAt(col) - '0';
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
        return board;
    }
    
    private static void print(String board) {
        try (PrintWriter printer = new PrintWriter("out.txt")) {
            printer.print(board);
            printer.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
