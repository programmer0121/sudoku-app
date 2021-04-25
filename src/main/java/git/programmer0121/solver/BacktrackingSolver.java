package git.programmer0121.solver;

import java.util.ArrayDeque;
import java.util.Deque;

public class BacktrackingSolver extends Solver {
    
    private final Deque<Move> moves = new ArrayDeque<>();
    
    public BacktrackingSolver(Board board) {
        super(board);
    }
    
    public Move cellWithMinCandidates() {
        int min = Integer.MAX_VALUE;
        Move move = WIN;
        for (Group group : groups) {
            for (Cell cell : group.cells) {
                if (cell.get() == 0) {
                    if (cell.canSetCount == 0)
                        return LOSE;
                    else if (cell.canSetCount == 1) {
                        min = 1;
                        for (int value = 1; value <= 9; value++)
                            if (cell.canSet[value] == 0)
                                move = new Move(cell, value);
                    }
                    else if (cell.canSetCount < min) {
                        min = cell.canSetCount;
                        move = new Move(cell, 0, Move.Type.GUESS);
                    }
                }
            }
        }
        return move;
    }
    
    public Move bestMove() {
        Move m = cellWithMinCandidates();
        if (m == WIN || m == LOSE || m.cell.canSetCount == 1)
            return m;
        Move m2 = nakedSingle();
        return (m2 != null) ? m2 : m;
    }
    
    @Override
    public Move solve() {
        do {
            Move m = bestMove();
            if (m == WIN || m == LOSE)
                return m;
            else if (m.type == Move.Type.SURE)
                execute(m);
            else
                return solve(m.cell);
        } while(true);
    }
    
    private void execute(Move m) {
        m.execute();
        moves.push(m);
    }
    
    private Move solve(Cell guessCell) {
        for (int candidate = 1; candidate <= 9; candidate++) {
            if (guessCell.canSet[candidate] == 0) {
                Move m = new Move(guessCell, candidate, Move.Type.GUESS);
                execute(m);
                if (solve() == WIN)
                    return WIN;
                undoSureMoves();
                undo();
            }
        }
        return LOSE;
    }
    
    private void undo() {
        Move m = moves.pop();
        m.cell.clear();
    }
    
    private void undoSureMoves() {
        while (!moves.isEmpty()) {
            Move m = moves.getFirst();
            if (m.type == Move.Type.SURE)
                undo();
            else 
                break;
        }
    }
}
