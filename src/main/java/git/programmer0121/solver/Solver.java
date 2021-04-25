package git.programmer0121.solver;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    
    protected Board board;
    
    public final Move WIN = new Move(null, 0);
    public final Move LOSE = new Move(null, 0);
    
    List<Group> groups = new ArrayList<>();
    
    public Solver(Board board) {
        this.board = board;
        groups.addAll(board.rows);
        groups.addAll(board.cols);
        groups.addAll(board.sqrs);
    }
    
    public Move nakedSingle() {
        for (Group group : groups) {
            for (Cell cell : group.cells) {
                if (cell.get() == 0 && cell.canSetCount == 1) {
                    for (int value = 1; value <= 9; value++)
                        if (cell.canSet[value] == 0)
                            return new Move(cell, value);
                }
            }
        }
        return null;
    }
    
    public Move hiddenSingle() {
        for (int value = 1; value <= 9; value++)
            for (Group group : groups) {
                if (group.canSet[value] != 0)
                    continue;
                int counter = 0;
                Cell cell = null;
                for (Cell c : group.cells) {
                    if (c.canSet[value] == 0) {
                        cell = c;
                        counter++;
                    }
                }
                if (counter == 1)
                    return new Move(cell, value);
            }
        return null;
    }
    
    public Move heuristicMove() {
        Move m = nakedSingle();
        return (m != null) ? m : hiddenSingle();
    }
    
    public Move solve() {
        Move m;
        do {
            m = heuristicMove();
            if (m != null)
                m.execute();
        } while (m != null);
        return null;
    }
    
}
