package git.programmer0121.sudokuapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class IndexController {
    @GetMapping("/api")
    public Map welcome() {
        return Collections.singletonMap("message", "Sudoku");
    }
}
