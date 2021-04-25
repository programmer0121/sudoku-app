package git.programmer0121.sudokuapp;

import git.programmer0121.solver.BacktrackingSolver;
import git.programmer0121.solver.Board;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {
    @GetMapping("/api")
    public Map welcome() {
        return Collections.singletonMap("message", "Sudoku");
    }

    @PostMapping("/api/solve")
    public Map solve(@RequestBody Map obj) {
        String boardText = (String) obj.get("board");
        Board b = new Board(boardText);
        BacktrackingSolver solver = new BacktrackingSolver(b);
        solver.solve();
        return new HashMap<>() {{ put("board", b.toString()); }};
    }
}
