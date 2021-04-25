package git.programmer0121.solver;

public class Move {
    
    public enum Type {SURE, GUESS}
    
    Cell cell;
    int value;
    Type type = Type.SURE;

    public Move(Cell c, int v) {
        cell = c;
        value = v;
    }

    public Move(Cell c, int v, Type t) {
        cell = c;
        value = v;
        type = t;
    }
    
    @Override
    public String toString() {
        return String.format("%s: %d in %s", type, value, cell);
    }
    
    public void execute() {
        cell.set(value);
    }
    
}
