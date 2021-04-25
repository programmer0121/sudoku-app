package git.programmer0121.solver;

import java.util.ArrayList;
import java.util.List;

public class Group {
    List<Cell> cells = new ArrayList<>();
    int emptyCount = 9;
    int[] canSet = new int[10];
    int idx;
    
    public Group(int idx) {
        this.idx = idx;
        for (int i = 1; i <= 9; i++)
            canSet[i] = 0;
    }
    
    public void set(int value) {
        emptyCount--;
        canSet[value]--;
        for (Cell c : cells) {
            c.groupSet(value);
        }
    }
    
    public void clear(int value) {
        emptyCount++;
        canSet[value]++;
        for (Cell c : cells) {
            c.groupClear(value);
        }
    }
    
}
