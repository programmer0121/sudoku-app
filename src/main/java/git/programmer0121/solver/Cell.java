package git.programmer0121.solver;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final Group gRow;
    private final Group gCol;
    private final Group gSqr;
    
    private int value = 0;
    int[] canSet = new int[] {0,0,0,0,0,0,0,0,0,0};
    int canSetCount = 9;

    public Cell(Group gRow, Group gCol, Group gSqr) {
        this.gRow = gRow;
        this.gCol = gCol;
        this.gSqr = gSqr;
    }
    
    public void set(int value) {
        if (this.value == 0) {
            this.value = value;
            gRow.set(value);
            gCol.set(value);
            gSqr.set(value);
        }
        else {
            throw new RuntimeException("set value when not empty");
        }
    }
    
    public int get() {
        return value;
    }
    
    public void clear() {
        if (value != 0) {
            gRow.clear(value);
            gCol.clear(value);
            gSqr.clear(value);
            value = 0;
        }
        else {
            throw new RuntimeException("clear value empty");
        }
    }
    
    public void groupSet(int value) {
        if (canSet[value] == 0)
            canSetCount--;
        canSet[value]--;
    }
    
    public void groupClear(int value) {
        canSet[value]++;
        if (canSet[value] == 0)
            canSetCount++;
    }
    
    @Override
    public String toString() {
        List<Integer> candidates = new ArrayList<>();
        for (int v = 1; v <= 9; v++)
            if (canSet[v] == 0)
                candidates.add(v);
        return String.format("[%d,%d] = %d (%s)", gRow.idx, gCol.idx, value, candidates.toString());
    }
    
}
